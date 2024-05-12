package controlador;

import javax.swing.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import modelo.Consultas.ConsultaUsuario;
import modelo.UsuarioModel;
import vista.LoginVista;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class UsuarioController{
    private ConsultaUsuario cu;
    private LoginVista lv;
    
    public UsuarioController(){
        this.cu = new ConsultaUsuario();
    }
    
    public UsuarioModel Buscarusuarios(String usuario, String Password) {
        UsuarioModel u = new UsuarioModel();
        
        try {
            if(this.validarCorreo(usuario) == true){
                String PasswordMD5 = this.getMD5(Password);
                u = this.cu.Buscarusuarios(usuario, PasswordMD5);
            } else{
                JOptionPane.showMessageDialog(null, "El correo es incorrecto, ingrese uno correcto (uncorreo@hotmail.com)");
                u = null;
            }
        } catch (Exception e) {
            System.out.println("Error al buscar usuario " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al consultar su usuario, verifique que el servidor este predndido");
            u = null;
        }
        return u;
    }
    public boolean insertarRegistro(String name,String email,String passw,String Cpassw,String tipouser,String phone,Date birithday,String address){
        UsuarioModel u = new UsuarioModel();
        boolean banderainsertado = false;
        Date current = new Date();
        
        try {
            
            if(name.equals("") || email.equals("") || passw.equals("") || Cpassw.equals("") || tipouser.trim().equals("Selecciona una opcion") || birithday.equals("") || address.equals("")){
                JOptionPane.showMessageDialog(null, "Es necesario llenar todos los campos");
            } else{
                if(passw.length() < 8 && Cpassw.length()< 8){
                    JOptionPane.showMessageDialog(null, "La contraseña debe tener mas de 8 caracteres como minimo");
                }else if(birithday.equals(current) || birithday.after(current)){
                    JOptionPane.showMessageDialog(null, "La fecha de nacimiento no debe ser menor o igual al dia de hoy");
                } else{
                  if(validarCorreo(email) == true){
                        if(passw.equals(Cpassw)){ 
                            u.setNombre(name);
                            u.setUsuario(email);
                            u.setPassword(this.getMD5(passw));
                            u.setTipo(tipouser);
                            u.setTelefono(phone);
                            u.setFechanacimiento(birithday);
                            u.setDireccion(address);

                            banderainsertado = this.cu.insertarUsuario(u);

                            if(banderainsertado){
                                JOptionPane.showMessageDialog(null, "El registro se inserto con exito");
                            } else{
                                JOptionPane.showMessageDialog(null, "Error al insertar el usuario, intente mas tarde");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Las contraseña no son iguales, verifique sus contraseñas");
                            banderainsertado = false;
                        }
                   }
                   else{
                        JOptionPane.showMessageDialog(null, "El correo que se ingreso no tiene formato correcto (uncorreo@hotmail.com)");
                        banderainsertado = false;
                   }  
                } 
            }
             
        } catch (Exception e) {
            System.out.println(e.getMessage());
            banderainsertado = false;
        }
        return banderainsertado;
    }
    public boolean modificarRegistro(int id,String name,String email,String passw,String Cpassw,String tipouser,String phone,Date birithday,String address){
        UsuarioModel u = new UsuarioModel();
        boolean banderainsertado = false;
        Date current = new Date();
        
        try {
            
            if(name.equals("") || email.equals("")  || tipouser.trim().equals("Selecciona una opcion") || birithday.equals("") || address.equals("")){
                JOptionPane.showMessageDialog(null, "Es necesario llenar todos los campos");
            } else if(birithday == null){
                if(birithday.equals(current) || birithday.after(current)){
                    JOptionPane.showMessageDialog(null, "La fecha de nacimiento no debe ser menor o igual al dia de hoy");
                }
            } else if(validarCorreo(email) == false){
                JOptionPane.showMessageDialog(null, "El correo que se ingreso no tiene formato correcto (uncorreo@hotmail.com)");
            } else{
                
                if(!passw.equals("") || !Cpassw.equals("")){
                   if(passw.equals(Cpassw)){
                       u.setPassword(this.getMD5(passw));
                   } else{
                       JOptionPane.showMessageDialog(null, "Las contraseñas no son iguales");
                   }   
                } else{
                    u.setPassword("");
                }
                
                u.setId(id);
                u.setNombre(name);
                u.setUsuario(email);
                
                u.setTipo(tipouser);
                u.setTelefono(phone);
                u.setFechanacimiento(birithday);
                u.setDireccion(address);

                banderainsertado = this.cu.modificarUsuario(u);

                if(banderainsertado){
                    JOptionPane.showMessageDialog(null, "El registro se modifico con exito");
                } else{
                    JOptionPane.showMessageDialog(null, "Error al modificar el usuario, intente mas tarde");
                }
            }
             
        } catch (Exception e) {
            System.out.println(e.getMessage());
            banderainsertado = false;
        }
        return banderainsertado;
    }
    public boolean eliminarusuario(String id){
        boolean eliminado = false;
        
        if(id.equals("")){
            JOptionPane.showMessageDialog(null, "Debes buscar el usuario a eliminar");
        } else{
            UsuarioModel um = new UsuarioModel();
            um.setId(Integer.parseInt(id));
            
            eliminado = this.cu.eliminarUsuario(um);
            
            if(eliminado){
                JOptionPane.showMessageDialog(null, "Usuario eliminado con exito");
            } else{
                JOptionPane.showMessageDialog(null, "No se pudo eliminar al usuario, intente mas tardes");
            }
        }
        return eliminado;
    }
    public List<String> Buscarusuarios(String usuario){
        List<String> user = new LinkedList<>();
        
        try {
            if(usuario.equals("")){
                JOptionPane.showMessageDialog(null, "Para buscar un usuario es necesario ingresar nombre del usuario");
            } else{
                UsuarioModel us = this.cu.Buscarusuario(usuario);
                
                if(us.getNombre() != null){
                    user.add(String.valueOf(us.getId()));
                    user.add(us.getNombre());
                    user.add(us.getUsuario());
                    user.add(us.getPassword());
                    user.add(us.getTelefono());
                    user.add(us.getTipo());
                    user.add(us.getDireccion());
                    user.add(us.getFechanacimiento().toString());                
                } else{
                    JOptionPane.showMessageDialog(null, "El usuario que busca no se encontro");
                }
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    
    public boolean validarCorreo(String correo){
        boolean banderaiscorreo = false;
        Pattern pattern = Pattern
                    .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(correo);
        
        if(mather.find()== true){
            banderaiscorreo = true; 
        } else{
           banderaiscorreo = false; 
        }
        return banderaiscorreo;
    }
    public String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    

}
    
    
