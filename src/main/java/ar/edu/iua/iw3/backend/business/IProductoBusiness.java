package ar.edu.iua.iw3.backend.business;

import ar.edu.iua.iw3.backend.business.exception.BusinessException;
import ar.edu.iua.iw3.backend.business.exception.NotFoundException;
import ar.edu.iua.iw3.backend.model.Producto;

import java.util.List;

// 3 - Creamos una interface que va a contener nuestras operaciones de negocio, luego la vamos a implementar en ProductoBusiness
// para definir la lógica de negocio de estos metodos

public interface IProductoBusiness {

	public Producto load(Long id) throws BusinessException, NotFoundException;

	public List<Producto> list() throws BusinessException;

	public Producto save(Producto producto) throws BusinessException;

	public void delete(Long id) throws BusinessException, NotFoundException;

}