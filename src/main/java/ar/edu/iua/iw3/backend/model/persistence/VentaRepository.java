package ar.edu.iua.iw3.backend.model.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.iua.iw3.backend.model.Producto;
import ar.edu.iua.iw3.backend.model.Venta;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long>{
	
	List<Venta> findByProductoListId(Long id);
	//Traer todas las ventas del producto 1
	//SELECT * FROM ventas v inner join producto_venta_detalle pvd
	//on v.id = pvd.venta_id inner join productos p
	//on pvd.producto_id = p.id
	//where p.id= '1'
	//order by v.fecha;
}
