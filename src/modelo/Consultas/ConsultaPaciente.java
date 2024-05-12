package modelo.Consultas;

import java.sql.*;
import hopital.Conexion;
import modelo.PacienteModel;

public class ConsultaPaciente extends Conexion{
    public boolean insertarPaciente(PacienteModel paciente){
        boolean banderainsertado = false;
        PreparedStatement ps = null;
        Connection con = conectar();
        
        String sql = "INSERT INTO paciente(Nombre,apellidos,direccion,CP,estado,pais,telefono,numeroSeguro) "
                   + "VALUES(?,?,?,?,?,?,?,?)";
        
        try {
            
            ps = con.prepareStatement(sql);
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellidos());
            ps.setString(3, paciente.getDireccion());
            ps.setString(4, paciente.getCp());
            ps.setString(5, paciente.getEstado());
            ps.setString(6, paciente.getPais());
            ps.setString(7, paciente.getTelefono());
            ps.setString(8, paciente.getNumeroSeguro());
            
            ps.execute();
            banderainsertado = true;  
            
        } catch (Exception e) {
            System.out.println(e);
            banderainsertado = false;
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return banderainsertado;
    }
    public boolean modificarPaciente(PacienteModel paciente){
        boolean banderainsertado = false;
        PreparedStatement ps = null;
        Connection con = conectar();
        
        String sql = "UPDATE paciente SET Nombre = ?,apellidos = ?,direccion = ?,CP = ?,estado = ?,pais = ?,telefono = ?,numeroSeguro = ? WHERE id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellidos());
            ps.setString(3, paciente.getDireccion());
            ps.setString(4, paciente.getCp());
            ps.setString(5, paciente.getEstado());
            ps.setString(6, paciente.getPais());
            ps.setString(7, paciente.getTelefono());
            ps.setString(8, paciente.getNumeroSeguro());
            ps.setInt(9, paciente.getId());
            
            ps.execute();
            banderainsertado = true;  
            
        } catch (Exception e) {
            System.out.println(e);
            banderainsertado = false;
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return banderainsertado;
    }
    public boolean EliminarPaciente(PacienteModel paciente){
        boolean banderainsertado = false;
        PreparedStatement ps = null;
        Connection con = conectar();
        
        String sql = "DELETE FROM paciente WHERE id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, paciente.getId());
            
            ps.execute();
            banderainsertado = true;  
            
        } catch (Exception e) {
            System.out.println(e);
            banderainsertado = false;
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return banderainsertado;
    }
    public PacienteModel Buscarpaciente(String nombre){
        PreparedStatement ps = null;
        ResultSet rs;
        Connection con = conectar(); // metodo de conexion
        String sql = "SELECT * FROM paciente WHERE Nombre LIKE '%"+ nombre +"%' LIMIT 1"; 
        PacienteModel p = new PacienteModel();
        
        try {
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("Nombre"));
                p.setApellidos(rs.getString("apellidos"));
                p.setApellidos(rs.getString("apellidos"));
                p.setDireccion(rs.getString("direccion"));
                p.setCp(rs.getString("CP"));
                p.setEstado(rs.getString("estado"));
                p.setPais(rs.getString("pais"));
                p.setTelefono(rs.getString("telefono"));
                p.setNumeroSeguro(rs.getString("numeroSeguro"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return p;
    }

}
