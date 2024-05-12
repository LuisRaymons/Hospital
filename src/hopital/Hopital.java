/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hopital;

import controlador.UsuarioController;
import java.util.List;
import modelo.Consultas.ConsultaUsuario;
import modelo.UsuarioModel;
import vista.LoginVista;
import vista.UsuarioView;
import vista.splashHospital;

/**
 *
 * @author Abisoft
 */
public class Hopital {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // splash
        splashHospital sh = new splashHospital();
        sh.setVisible(true);
        
        try{
            Thread.sleep(5 * 1000);
        } catch(Exception e){
            e.printStackTrace();
        }
        sh.dispose();
        /*
        ConsultaUsuario cu = new ConsultaUsuario();
        LoginVista lv = new LoginVista();
        UsuarioView uv = new UsuarioView();
        UsuarioController uc = new UsuarioController();
        */
        
        LoginVista v = new LoginVista();
        v.setVisible(true);
      
        
        
        /*
        ConsultaUsuario cu = new ConsultaUsuario();
        List<UsuarioModel> listauser = cu.listarusuarios();
        listauser.forEach((u) -> {
            System.out.println("El nombre del usuario es " + u.getNombre());
        });
        */
    }
    
}
