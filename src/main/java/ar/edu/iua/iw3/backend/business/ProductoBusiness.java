package ar.edu.iua.iw3.backend.business;

import ar.edu.iua.iw3.backend.business.exception.BusinessException;
import ar.edu.iua.iw3.backend.business.exception.NotFoundException;
import ar.edu.iua.iw3.backend.model.Producto;
import ar.edu.iua.iw3.backend.model.persistence.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 4 - Implementamos la interfaz IProductoBusiness para poder crear la logica de negocio de esos metodos
// Los métodos que nos devuelve la interfaz son del propio JPA (save, findAll,load,etc)
// Para saber de todos los métodos podemos hacer ctrl + click en JpaRepository en la interfaz

// Optional sirve para devolver de la base de datos, opcionalmente puede traer el dato, o un error si no lo encuentra
// por eso va adentro de un try catch, si el valor en la base de datos esta presente usamos .get() para obtenerlo

// Service se usa para ser candidato de ser instanciado mas adelante y poder usar esta lógica de negocio

// AutoWired es para incorporar el repositorio y poder usar jPA

@Service
public class ProductoBusiness implements IProductoBusiness {

	@Autowired
	private ProductoRepository productoDAO;

	@Override
	public Producto load(Long id) throws BusinessException, NotFoundException {
		Optional<Producto> op;
		try {
			op = productoDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent())
			throw new NotFoundException("No se encuentra el producto id=" + id);
		return op.get();
	}

	@Override
	public List<Producto> list() throws BusinessException {
		try {
			return productoDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Producto save(Producto producto) throws BusinessException {
		try {
			return productoDAO.save(producto);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void delete(Long id) throws BusinessException,NotFoundException {
		try {
			productoDAO.deleteById(id);
		} catch (EmptyResultDataAccessException e1) {
			throw new NotFoundException("No se encuentra el producto id=" + id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

}