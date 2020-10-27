package ar.edu.iua.iw3.backend.model.persistence;

import ar.edu.iua.iw3.backend.model.Proveedor;
import ar.edu.iua.iw3.backend.model.ProveedorDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

	public ProveedorDTO verNombre(Long id);
    
}