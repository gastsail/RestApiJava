package ar.edu.iua.iw3.backend.model.persistence;

import ar.edu.iua.iw3.backend.model.Ingredientes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredienteRepository  extends JpaRepository<Ingredientes, Long> {

    List<Ingredientes> findByProductoListPrecioLista(double precioLista);

    @Query(value = "select * from ingredientes i inner join producto_ingrediente_detalle pid \n" +
            "on i.id = pid.ingrediente_id\n" +
            "inner join productos p on pid.producto_id = p.id\n" +
            "where\n" +
            "p.precio_lista > ?1", nativeQuery = true)
    public List<Ingredientes> preciomayorque(double precio);
}
