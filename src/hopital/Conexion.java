package hopital;
import java.lang.System.Logger;
import java.sql.*;
import java.util.logging.Level;

public class Conexion {
    private final String bd = "hospital";
    private final String server = "jdbc:mysql://localhost:3306/" + bd;
    private final String user = "root";
    private final String pass = "";
    private Connection con = null;
    
    public Connection conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(this.server,this.user,this.pass);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException ex) { 
            java.util.logging.Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
}
