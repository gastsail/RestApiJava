package ar.edu.iua.iw3.backend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

// JsonIdentityInfo hace que no se genere un bucle infinito cuando agregamos un proveedor al producto, ya que al agregarlo
// el proveedor tiene una lista de productos y se vuelve a llamar, por lo que genera un bucle infinito

@Entity
@Table(name = "proveedor")
@JsonIgnoreProperties({"hibernateLazyinItializer","handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,property = "id")
public class Proveedor implements Serializable {


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
