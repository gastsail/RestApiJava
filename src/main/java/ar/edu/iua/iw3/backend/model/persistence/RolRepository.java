package ar.edu.iua.iw3.backend.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.iua.iw3.backend.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
}
