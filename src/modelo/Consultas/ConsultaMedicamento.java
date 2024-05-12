package modelo.Consultas;

import hopital.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import modelo.MedicamentoModel;

public class ConsultaMedicamento extends Conexion{
    public boolean insertarMedicamento(MedicamentoModel medicina){
        boolean banderainsertado = false;
        PreparedStatement ps = null;
        Connection con = conectar();
        
        String sql = "INSERT INTO medicamento(nombre,caducidad,stock,precio,tipo)"
                   + "VALUES(?,?,?,?,?)";
        
        try {
            DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
            ps = con.prepareStatement(sql);
            ps.setString(1, medicina.getNombre());
            ps.setString(2, dateFormat.format(medicina.getCaducidad()));
            ps.setInt(3, medicina.getStock());
            ps.setDouble(4, medicina.getPrecio());
            ps.setString(5, medicina.getTipo());
            
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
    public boolean modificarMedicamento(MedicamentoModel medicina){
        boolean banderainsertado = false;
        PreparedStatement ps = null;
        Connection con = conectar();
        
        String sql = "UPDATE medicamento SET nombre = ?,caducidad = ?,stock = ?,precio = ?,tipo = ? WHERE id = ?";
        try {
            DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
            ps = con.prepareStatement(sql);
            ps.setString(1, medicina.getNombre());
            ps.setString(2, dateFormat.format(medicina.getCaducidad()));
            ps.setInt(3, medicina.getStock());
            ps.setDouble(4, medicina.getPrecio());
            ps.setString(5, medicina.getTipo());
            ps.setInt(6, medicina.getId());
            
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
    public boolean EliminarMedicamento(MedicamentoModel medicina){
        boolean banderainsertado = false;
        PreparedStatement ps = null;
        Connection con = conectar();
        
        String sql = "DELETE FROM medicamento WHERE id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, medicina.getId());
            
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
    public MedicamentoModel BuscarMedicamento(String nombre){
        PreparedStatement ps = null;
        ResultSet rs;
        Connection con = conectar(); // metodo de conexion
        String sql = "SELECT * FROM medicamento WHERE Nombre LIKE '%"+ nombre +"%' LIMIT 1"; 
        
        MedicamentoModel m = new MedicamentoModel();
        
        try {
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                m.setId(rs.getInt("id"));
                m.setNombre(rs.getString("nombre"));
                m.setCaducidad(rs.getDate("caducidad"));
                m.setStock(rs.getInt("stock"));
                m.setPrecio(rs.getDouble("precio"));
                m.setTipo(rs.getString("tipo"));
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
        return m;
    }

}
