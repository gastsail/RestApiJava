package ar.edu.iua.iw3.backend.model;

import java.io.Serializable;

public class ProveedorDTO implements Serializable {

	private static final long serialVersionUID = 764370721802870806L;
	private String name;

    public String getNombre() {
        return name;
    }

    public void setNombre(String name) {
        this.name = name;
    }

    public ProveedorDTO(String nombre) {
        this.name = nombre;
    }
}
