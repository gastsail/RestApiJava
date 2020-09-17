package ar.edu.iua.iw3.backend.model.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.iua.iw3.backend.model.Producto;
import ar.edu.iua.iw3.backend.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long>{

	List<Producto> findAllByNombreStartingWith(Long id);
	//Traer todas las ventas del producto 1
}
