package ar.edu.iua.iw3.backend.model;

import javax.persistence.*;
import java.io.Serializable;

// 1 - Punto de partida, definimos el modelo , creamos esta entidad con el nombre de la tabla y sus anotadores correspondientes

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    private static final long serialVersionUID = 451621105748580924L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nombre;
    @Column(length = 250)
    private String descripcion;
    private double precioLista;
    @Column(columnDefinition = "TINYINT DEFAULT 0")
    private boolean enStock;

    @OneToOne(cascade =  CascadeType.ALL)
    private ProductoDetalle productoDetalle;

	public ProductoDetalle getProductoDetalle() {
		return productoDetalle;
	}

	public void setProductoDetalle(ProductoDetalle productoDetalle) {
		this.productoDetalle = productoDetalle;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioLista() {
        return precioLista;
    }

    public void setPrecioLista(double precioLista) {
        this.precioLista = precioLista;
    }

    public boolean isEnStock() {
        return enStock;
    }

    public void setEnStock(boolean enStock) {
        this.enStock = enStock;
    }

}
