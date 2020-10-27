package ar.edu.iua.iw3.backend.model.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.iua.iw3.backend.model.Venta;
import ar.edu.iua.iw3.backend.model.VentaDTO;

import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long>{
	
	List<Venta> findByProductoListId(Long id);
	List<VentaDTO> soloNombre(String nombre);
}
