package ar.edu.iua.iw3.backend.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.util.List;

//Aqui definimos la Query con nombre + el DTO para responder a la consulta:
//Una query por nombre + DTO para devolver las ventas cuyo producto tenga el nombre X, 
//mostrando sólo la fecha de la venta.
@NamedNativeQuery(name = "Venta.soloNombre", query = "select v.fecha from ventas v inner join "
		+ "producto_venta_detalle pvd on v.id = pvd.venta_id inner join productos p "
		+ "on pvd.producto_id = p.id where p.nombre = ?1", resultSetMapping = "ventamap")
@SqlResultSetMapping(
       name="ventamap",
       classes = {
               @ConstructorResult(
                       columns = {
                               @ColumnResult(name = "v.fecha", type = String.class),
                       },
                       targetClass = VentaDTO.class
               )
       }
)


@Entity
@Table(name = "ventas")
public class Venta implements Serializable {

	private static final long serialVersionUID = 4122893409072057489L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String fecha;
    @Column(length = 250)

    
    //En esta relacion de muchos a muchos se crea una tabla intermedia cuya clave principal esta formada por las claves principales de las tablas que 
    // une.
    @ManyToMany(mappedBy = "ventaList")
    @JsonBackReference
    private List<Producto> productoList;
   

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String nombre) {
        this.fecha = nombre;
    }
}
