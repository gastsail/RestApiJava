package ar.edu.iua.iw3.backend.model.persistence;

import ar.edu.iua.iw3.backend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// 2 - Creamos un repository con el anotador @Repository que extiende de JpaRepository
// JpaRepository toma dos valores, el primero es la entidad , en este caso producto, y luego el tipo de dato que se guarda
// como ID de esa entidad

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByDescripcion(String description);
    Optional<Producto> findByPrecioListaGreaterThan(Double precio);
    Optional<Producto> findByPrecioListaLessThan(Double precio);
    List<Producto> findAllByOrderByPrecioListaAsc();
}