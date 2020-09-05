package ar.edu.iua.iw3.backend.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "producto_Detalle")
public class ProductoDetalle implements Serializable {

	private static final long serialVersionUID = 1732092477207140348L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250)
    private String detalle;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setDetalle(String nombre) {
        this.detalle = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

}
