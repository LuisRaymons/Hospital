package modelo;

import java.util.Date;

public class LaboratorioModel {
    private int id;
    private String nombreExamen;
    private Date fechaAplico;
    private Date fechaRequerido;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombreExamen
     */
    public String getNombreExamen() {
        return nombreExamen;
    }

    /**
     * @param nombreExamen the nombreExamen to set
     */
    public void setNombreExamen(String nombreExamen) {
        this.nombreExamen = nombreExamen;
    }

    /**
     * @return the fechaAplico
     */
    public Date getFechaAplico() {
        return fechaAplico;
    }

    /**
     * @param fechaAplico the fechaAplico to set
     */
    public void setFechaAplico(Date fechaAplico) {
        this.fechaAplico = fechaAplico;
    }

    /**
     * @return the fechaRequerido
     */
    public Date getFechaRequerido() {
        return fechaRequerido;
    }

    /**
     * @param fechaRequerido the fechaRequerido to set
     */
    public void setFechaRequerido(Date fechaRequerido) {
        this.fechaRequerido = fechaRequerido;
    }
    
    
}
