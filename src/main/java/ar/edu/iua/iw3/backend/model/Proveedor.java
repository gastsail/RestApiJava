package ar.edu.iua.iw3.backend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "proveedor")
@JsonIgnoreProperties({"hibernateLazyinItializer","handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,property = "id")

// ------- Usa el constructor de una clase nueva llamada ProveedorDTO--------------------
//-------------LO QUE SE MUESTRA A CONTINUACION (SqlResultSetMapping) ES EL MAPEO ENTRE LA RESPUESTA DE LA BASE DE DATOS Y EL DTO--
@NamedNativeQuery(name = "Proveedor.soloNombre", query = "select proveedor.name from proveedor where id = ?1", resultSetMapping = "proveedorMap")
@SqlResultSetMapping(
       name="proveedorMap",
       classes = {
               @ConstructorResult(
                       columns = {
                               @ColumnResult(name = "proveedor.name", type = String.class),
                       },
                       targetClass = ProveedorDTO.class
               )
       }
)


public class Proveedor implements Serializable {

	private static final long serialVersionUID = 2047686961507729289L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonBackReference
    private Long id;

    @Column(length = 100, nullable=true)
    private String name;

    @OneToMany(targetEntity = Producto.class,mappedBy = "proveedor",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Producto> productoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }
}
