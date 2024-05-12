package modelo.Consultas;

import java.sql.*;
import hopital.Conexion;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import modelo.UsuarioModel;

public class ConsultaUsuario extends Conexion{
    public boolean insertarUsuario(UsuarioModel user){
        boolean banderainsertado = false;
        PreparedStatement ps = null;
        Connection con = conectar(); // metodo de conexion
        String sql = "INSERT INTO usuarios(Nombre,Usuario,password,tipo,Fechanacimiento,telefono,direccion)"
                    + "VALUES(?,?,?,?,?,?,?)";
        try {
            DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
            
            ps = con.prepareStatement(sql);
            ps.setString(1,user.getNombre());
            ps.setString(2, user.getUsuario());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getTipo());
            ps.setString(5, dateFormat.format(user.getFechanacimiento()));
            ps.setString(6, user.getTelefono());
            ps.setString(7, user.getDireccion());
            
            ps.execute();
            banderainsertado = true;            
        } catch (SQLException e) {
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
    public boolean modificarUsuario(UsuarioModel user){
        boolean banderainsertado = false;
        PreparedStatement ps = null;
        DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
        
        Connection con = conectar(); // metodo de conexion
        String sql = "";
                
        
        
        try {
            if(user.getPassword().equals("")){
                sql = "UPDATE usuarios SET Nombre = ?, Usuario = ?, tipo = ?, Fechanacimiento = ?, telefono = ?, direccion = ? WHERE id = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1,user.getNombre());
                ps.setString(2, user.getUsuario());
                ps.setString(3, user.getTipo());
                ps.setString(4, dateFormat.format(user.getFechanacimiento()));
                ps.setString(5, user.getTelefono());
                ps.setString(6, user.getDireccion());
                ps.setInt(7, user.getId());

                ps.execute();
                banderainsertado = true;
            } else{
                sql = "UPDATE usuarios SET Nombre = ?, Usuario = ?, password = ?, tipo = ?, Fechanacimiento = ?, telefono = ?, direccion = ? WHERE id = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1,user.getNombre());
                ps.setString(2, user.getUsuario());
                ps.setString(3, user.getPassword());
                ps.setString(4, user.getTipo());
                ps.setString(5, dateFormat.format(user.getFechanacimiento()));
                ps.setString(6, user.getTelefono());
                ps.setString(7, user.getDireccion());
                ps.setInt(8, user.getId());

                ps.execute();
                banderainsertado = true;
            }
            
                        
        } catch (SQLException e) {
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
    public boolean eliminarUsuario(UsuarioModel user){
        boolean banderainsertado = false;
        PreparedStatement ps = null;
        Connection con = conectar(); // metodo de conexion
        String sql = "DELETE FROM usuarios WHERE id = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, user.getId());
            
            ps.execute();
            banderainsertado = true;            
        } catch (SQLException e) {
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
    public List listarusuarios(){
        PreparedStatement ps = null;
        ResultSet rs;
        Connection con = conectar(); // metodo de conexion
        String sql = "SELECT * FROM usuarios"; 
        ArrayList<UsuarioModel> listaUser = new ArrayList();
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                UsuarioModel u = new UsuarioModel();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("Nombre"));
                u.setUsuario(rs.getString("Usuario"));
                u.setPassword(rs.getString("password"));
                u.setTipo(rs.getString("tipo"));
                u.setFechanacimiento(rs.getDate("Fechanacimiento"));
                u.setTelefono(rs.getString("telefono"));
                u.setDireccion(rs.getString("direccion"));
                
                listaUser.add(u);
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
        return listaUser;
    }
    public UsuarioModel Buscarusuarios(String usuario, String password){
        PreparedStatement ps = null;
        ResultSet rs;
        Connection con = conectar(); // metodo de conexion
        String sql = "SELECT * FROM usuarios WHERE Usuario = ? AND password = ? LIMIT 1"; 
        UsuarioModel u = new UsuarioModel();
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("Nombre"));
                u.setUsuario(rs.getString("Usuario"));
                u.setPassword(rs.getString("password"));
                u.setTipo(rs.getString("tipo"));
                u.setFechanacimiento(rs.getDate("Fechanacimiento"));
                u.setTelefono(rs.getString("telefono"));
                u.setDireccion(rs.getString("direccion"));
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
        return u;
    }
    public UsuarioModel Buscarusuario(String nombre){
        PreparedStatement ps = null;
        ResultSet rs;
        Connection con = conectar(); // metodo de conexion
        String sql = "SELECT * FROM usuarios WHERE Nombre LIKE '%"+ nombre +"%' LIMIT 1"; 
        UsuarioModel u = new UsuarioModel();
        
        try {
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("Nombre"));
                u.setUsuario(rs.getString("Usuario"));
                u.setPassword(rs.getString("password"));
                u.setTipo(rs.getString("tipo"));
                u.setFechanacimiento(rs.getDate("Fechanacimiento"));
                u.setTelefono(rs.getString("telefono"));
                u.setDireccion(rs.getString("direccion"));
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
        return u;
    }
}
