package modelo.Consultas;

import hopital.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import modelo.LaboratorioModel;

public class ConsultaLaboratorio extends Conexion{
    public boolean insertarHabitacion(LaboratorioModel laboratorio){
        boolean banderainsertado = false;
        PreparedStatement ps = null;
        Connection con = conectar();
        
        String sql = "INSERT INTO laboratorio(nombreExamenClinico,fechaAplico,fechaRequerido)"
                   + "VALUES(?,?,?,?,?,?,?,?)";
        
        try {
            DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
            
            ps.setString(0, laboratorio.getNombreExamen());
            ps.setString(1, dateFormat.format(laboratorio.getFechaAplico()));
            ps.setString(2, dateFormat.format(laboratorio.getFechaRequerido()));
            
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
    public boolean modificarHabitacion(LaboratorioModel laboratorio){
        boolean banderainsertado = false;
        PreparedStatement ps = null;
        Connection con = conectar();
        
        String sql = "UPDATE laboratorio SET nombreExamenClinico = ?,fechaAplico = ?,fechaRequerido = ? WHERE id = ?"
                   + "VALUES(?,?,?,?)";
        try {
            DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
            
            ps.setString(0, laboratorio.getNombreExamen());
            ps.setString(1, dateFormat.format(laboratorio.getFechaAplico()));
            ps.setString(1, dateFormat.format(laboratorio.getFechaRequerido()));
            ps.setInt(3, laboratorio.getId());
            
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
    public boolean EliminarHabitacion(LaboratorioModel laboratorio){
        boolean banderainsertado = false;
        PreparedStatement ps = null;
        Connection con = conectar();
        
        String sql = "DELETE FROM laboratorio WHERE id = ?";
        
        try {
            ps.setInt(0, laboratorio.getId());
            
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
    public LaboratorioModel BuscarHabitacion(String nombreExamenClinico){
        PreparedStatement ps = null;
        ResultSet rs;
        Connection con = conectar(); // metodo de conexion
        String sql = "SELECT * FROM laboratorio WHERE nombreExamenClinico LIKE '%"+ nombreExamenClinico +"%' LIMIT 1"; 
        LaboratorioModel l = new LaboratorioModel();
        
        try {
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                l.setId(rs.getInt("id"));
                l.setNombreExamen(rs.getString("nombreExamenClinico"));
                l.setFechaAplico(rs.getDate("fechaAplico"));
                l.setFechaRequerido(rs.getDate("fechaRequerido"));
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
        return l;
    }

}
