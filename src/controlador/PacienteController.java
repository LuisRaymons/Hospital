package controlador;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Consultas.ConsultaPaciente;
import modelo.PacienteModel;

public class PacienteController {
    private ConsultaPaciente cp;
    
    public PacienteController(){
        this.cp = new ConsultaPaciente();
    }
    
    public boolean insertarPaciente(String nombre, String apellidos, String direccion, String cp, String estado, String pais, String telefono, String numeroSeguro){
        PacienteModel p = new PacienteModel();
        boolean banderainsertado = false;
        
        try {
            if(nombre.equals("") || apellidos.equals("") || direccion.equals("") || cp.equals("") || estado.equals("Seleccione una opcion") || pais.equals("Seleccione una opcion") || telefono.equals("")){
               JOptionPane.showMessageDialog(null, "Es necesario llenar todos los campos"); 
            }else{
                p.setNombre(nombre);
                p.setApellidos(apellidos);
                p.setDireccion(direccion);
                p.setCp(cp);
                p.setEstado(estado);
                p.setPais(pais);
                p.setTelefono(telefono);
                p.setNumeroSeguro(numeroSeguro);
                
                banderainsertado = this.cp.insertarPaciente(p);
                
                if(banderainsertado){
                    JOptionPane.showMessageDialog(null, "El paciente se inserto con exito");
                } else{
                    JOptionPane.showMessageDialog(null, "Error al insertar el paciente, intente mas tarde");
                }
                
            }
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            banderainsertado = false;
        }
        return banderainsertado;
    }
    public boolean modificarPaciente(String id, String nombre, String apellidos, String direccion, String cp, String estado, String pais, String telefono, String numeroSeguro){
        PacienteModel p = new PacienteModel();
        boolean banderamodificado = false;
        
        if(id.equals("") || nombre.equals("") || apellidos.equals("") || direccion.equals("") || cp.equals("") || estado.equals("Seleccione una opcion") || pais.equals("Seleccione una opcion") || telefono.equals("")){
            JOptionPane.showMessageDialog(null, "Es necesario llenar todos los campos"); 
        }else{
            p.setId(Integer.parseInt(id));
            p.setNombre(nombre);
                p.setApellidos(apellidos);
                p.setDireccion(direccion);
                p.setCp(cp);
                p.setEstado(estado);
                p.setPais(pais);
                p.setTelefono(telefono);
                p.setNumeroSeguro(numeroSeguro);
                
                banderamodificado = this.cp.modificarPaciente(p);
                
                if(banderamodificado){
                    JOptionPane.showMessageDialog(null, "El registro se modifico con exito");
                } else{
                    JOptionPane.showMessageDialog(null, "Error al modificar el paciente, intente mas tarde");
                }
        }
        
        return banderamodificado;
    }
    public boolean eliminarPaciente(String id){
        boolean eliminado = false;
        
        if(id.equals("")){
            JOptionPane.showMessageDialog(null, "Debes buscar el paciente a eliminar");
        } else{
            PacienteModel p =  new PacienteModel();
            p.setId(Integer.parseInt(id));
            
            eliminado = this.cp.EliminarPaciente(p);
            
            if(eliminado){
                JOptionPane.showMessageDialog(null, "Paciente eliminado con exito");
            } else{
                JOptionPane.showMessageDialog(null, "No se pudo eliminar al paciente, intente mas tardes");
            }
        }
        return eliminado;
    }
    public List<String> BuscarPaciente(String nombre){
        List<String> paciente = new LinkedList<>();
        
        try {
            if(nombre.equals("")){
                JOptionPane.showMessageDialog(null, "Para buscar un paciente es necesario ingresar nombre del paciente");
            } else{
                PacienteModel p = this.cp.Buscarpaciente(nombre);
                
                if(p.getNombre() != null){
                    paciente.add(String.valueOf(p.getId()));
                    paciente.add(p.getNombre());
                    paciente.add(p.getApellidos());
                    paciente.add(p.getDireccion());
                    paciente.add(p.getCp());
                    paciente.add(p.getEstado());
                    paciente.add(p.getPais());
                    paciente.add(p.getTelefono());
                    paciente.add(p.getNumeroSeguro());
                } else{
                    JOptionPane.showMessageDialog(null, "No se encontro al paciente, intente con otro nombre");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return paciente;
    }
}
