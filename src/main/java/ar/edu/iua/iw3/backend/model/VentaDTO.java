package ar.edu.iua.iw3.backend.model;

import javax.persistence.*;
import java.io.Serializable;



public class VentaDTO implements Serializable {


    private String fecha;

    public String getFecha() {
        return fecha;
    }

    public void setNombre(String fecha) {
        this.fecha = fecha;
    }

    public VentaDTO(String fecha) {
        this.fecha = fecha;
    }
}