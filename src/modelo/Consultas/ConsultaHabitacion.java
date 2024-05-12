package modelo.Consultas;

import hopital.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import modelo.HabitacionModel;

public class ConsultaHabitacion extends Conexion{
    public boolean insertarHabitacion(HabitacionModel habitacion){
        boolean banderainsertado = false;
        PreparedStatement ps = null;
        Connection con = conectar();
        
        String sql = "INSERT INTO habitacion(numerohabitacion,activa,piso,cama,televisor,telefono,sillonesVisita)"
                   + "VALUES(?,?,?,?,?,?,?,?)";
        
        try {
            ps.setString(0, habitacion.getNumeroHAbitacion());
            ps.setBoolean(1, habitacion.getActiva());
            ps.setInt(2, habitacion.getPiso());
            ps.setString(3, habitacion.getCama());
            ps.setString(4, habitacion.getTelevisor());
            ps.setString(5, habitacion.getTelefono());
            ps.setString(6, habitacion.getSillonVisitas());
            
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
    public boolean modificarHabitacion(HabitacionModel habitacion){
        boolean banderainsertado = false;
        PreparedStatement ps = null;
        Connection con = conectar();
        
        String sql = "UPDATE habitacion SET numerohabitacion = ?,activa = ?,piso = ?,cama = ?,televisor = ?, telefono = ?, sillonesVisita = ? WHERE id = ?"
                   + "VALUES(?,?,?,?,?,?,?,?)";
        try {
            DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
            
            ps.setString(0, habitacion.getNumeroHAbitacion());
            ps.setBoolean(1, habitacion.getActiva());
            ps.setInt(2, habitacion.getPiso());
            ps.setString(3, habitacion.getCama());
            ps.setString(4, habitacion.getTelevisor());
            ps.setString(5, habitacion.getTelefono());
            ps.setString(6, habitacion.getSillonVisitas());
            ps.setInt(7, habitacion.getId());
            
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
    public boolean EliminarHabitacion(HabitacionModel habitacion){
        boolean banderainsertado = false;
        PreparedStatement ps = null;
        Connection con = conectar();
        
        String sql = "DELETE FROM habitacion WHERE id = ?";
        
        try {
            ps.setInt(0, habitacion.getId());
            
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
    public HabitacionModel BuscarHabitacion(String numerohabitacion){
        PreparedStatement ps = null;
        ResultSet rs;
        Connection con = conectar(); // metodo de conexion
        String sql = "SELECT * FROM habitacion WHERE numerohabitacion LIKE '%"+ numerohabitacion +"%' LIMIT 1"; 
        HabitacionModel h = new HabitacionModel();
        
        try {
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                h.setId(rs.getInt("id"));
                h.setNumeroHAbitacion(rs.getString("numerohabitacion"));
                h.setActiva(rs.getBoolean("activa"));
                h.setPiso(rs.getInt("piso"));
                h.setCama(rs.getString("cama"));
                h.setTelevisor(rs.getString("televisor"));
                h.setTelefono(rs.getString("telefono"));
                h.setSillonVisitas(rs.getString("sillonesVisita"));
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
        return h;
    }

}
