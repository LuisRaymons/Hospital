package controlador;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Consultas.ConsultaMedicamento;
import modelo.MedicamentoModel;

public class MedicamentoController {
    private ConsultaMedicamento cm;
    
    public MedicamentoController(){
        this.cm = new ConsultaMedicamento();
    }
    
    public boolean insertarMedicamento(String nombre,Date caducidad,String stock,String precio,String tipo){
        boolean banderainsertado = false;
        MedicamentoModel mm = new MedicamentoModel();
        Date current = new Date();
        
        try {
            if(nombre.equals("") || stock.equals("") || tipo.equals("")){
                JOptionPane.showMessageDialog(null, "Es necesario llenar todos los campos"); 
            } else if(caducidad.equals(current) || caducidad.before(current)){
                JOptionPane.showMessageDialog(null, "El medicamento esta caducado");
            }else{
                mm.setNombre(nombre);
                mm.setCaducidad(caducidad);
                mm.setStock(Integer.parseInt(stock));
                mm.setPrecio(Double.parseDouble(precio));
                mm.setTipo(tipo);
                
                banderainsertado = this.cm.insertarMedicamento(mm);
                
                if(banderainsertado){
                    JOptionPane.showMessageDialog(null, "El medicamento se inserto con exito");
                } else{
                    JOptionPane.showMessageDialog(null, "Error al insertar el medicamento, intente mas tarde");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            banderainsertado = false;
        }
        return banderainsertado;
    }

    public boolean modificarMedicamento(String id, String nombre,Date caducidad,String stock,String precio,String tipo){
        boolean banderamodificado = false;
        MedicamentoModel mm = new MedicamentoModel();
        
        try {
            if(id.equals("") || nombre.equals("") || stock.equals("") || tipo.equals("")){
                JOptionPane.showMessageDialog(null, "Es necesario llenar todos los campos"); 
            } else{
                mm.setId(Integer.parseInt(id));
                mm.setNombre(nombre);
                mm.setCaducidad(caducidad);
                mm.setStock(Integer.parseInt(stock));
                mm.setPrecio(Double.parseDouble(precio));
                mm.setTipo(tipo);
                
                banderamodificado = this.cm.modificarMedicamento(mm);
                
                if(banderamodificado){
                    JOptionPane.showMessageDialog(null, "El medicamento se modifico con exito");
                } else{
                    JOptionPane.showMessageDialog(null, "Error al modificar el medicamento, intente mas tarde");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            banderamodificado = false;
        }
        return banderamodificado;
    }

    public boolean eliminarMedicamento(String id){
        boolean banderaeliminado = false;
        
        if(id.equals("")){
            JOptionPane.showMessageDialog(null, "Debes buscar el medicamento a eliminar");
        } else{
            MedicamentoModel mm = new MedicamentoModel();
            mm.setId(Integer.parseInt(id));
            
            banderaeliminado = this.cm.EliminarMedicamento(mm);
            
            if(banderaeliminado){
                JOptionPane.showMessageDialog(null, "Medicamento eliminado con exito");
            } else{
                JOptionPane.showMessageDialog(null, "No se pudo eliminar al medicamento, intente mas tardes");
            }
        }
        return banderaeliminado;
    }
    
    public List<String> BuscarMedicamento(String nombre){
        List<String> Medicina = new LinkedList<>();
        try {
            if(nombre.equals("")){
                JOptionPane.showMessageDialog(null, "Para buscar un medicamento es necesario ingresar nombre del medicamento");
            } else{
                MedicamentoModel mm = this.cm.BuscarMedicamento(nombre);
                
                if(mm.getNombre() != null){
                    Medicina.add(String.valueOf(mm.getId()));
                    Medicina.add(mm.getNombre());
                    Medicina.add(mm.getCaducidad().toString());
                    Medicina.add(String.valueOf(mm.getStock()));
                    Medicina.add(String.valueOf(mm.getPrecio()));
                    Medicina.add(mm.getTipo());

                } else{
                    JOptionPane.showMessageDialog(null, "No se encontro al medicamento, intente con otro nombre");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return Medicina;
    }
}
