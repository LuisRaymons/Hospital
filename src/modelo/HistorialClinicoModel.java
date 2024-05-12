package modelo;

import java.util.Date;

public class HistorialClinicoModel {
    private int id;
    private String descripcioSintomas;
    private int paciente;
    private int medicamento;
    private int laboratorio;
    private int habitacion;
    private Date fechaCita;
    private String codigoCita;

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
     * @return the descripcioSintomas
     */
    public String getDescripcioSintomas() {
        return descripcioSintomas;
    }

    /**
     * @param descripcioSintomas the descripcioSintomas to set
     */
    public void setDescripcioSintomas(String descripcioSintomas) {
        this.descripcioSintomas = descripcioSintomas;
    }

    /**
     * @return the paciente
     */
    public int getPaciente() {
        return paciente;
    }

    /**
     * @param paciente the paciente to set
     */
    public void setPaciente(int paciente) {
        this.paciente = paciente;
    }

    /**
     * @return the medicamento
     */
    public int getMedicamento() {
        return medicamento;
    }

    /**
     * @param medicamento the medicamento to set
     */
    public void setMedicamento(int medicamento) {
        this.medicamento = medicamento;
    }

    /**
     * @return the laboratorio
     */
    public int getLaboratorio() {
        return laboratorio;
    }

    /**
     * @param laboratorio the laboratorio to set
     */
    public void setLaboratorio(int laboratorio) {
        this.laboratorio = laboratorio;
    }

    /**
     * @return the habitacion
     */
    public int getHabitacion() {
        return habitacion;
    }

    /**
     * @param habitacion the habitacion to set
     */
    public void setHabitacion(int habitacion) {
        this.habitacion = habitacion;
    }

    /**
     * @return the fechaCita
     */
    public Date getFechaCita() {
        return fechaCita;
    }

    /**
     * @param fechaCita the fechaCita to set
     */
    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    /**
     * @return the codigoCita
     */
    public String getCodigoCita() {
        return codigoCita;
    }

    /**
     * @param codigoCita the codigoCita to set
     */
    public void setCodigoCita(String codigoCita) {
        this.codigoCita = codigoCita;
    }
    
    
}
