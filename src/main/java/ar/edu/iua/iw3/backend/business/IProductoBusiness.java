package ar.edu.iua.iw3.backend.business;

import ar.edu.iua.iw3.backend.business.exception.BusinessException;
import ar.edu.iua.iw3.backend.business.exception.NotFoundException;
import ar.edu.iua.iw3.backend.model.Producto;

import java.util.List;

// 3 - Creamos una interface que va a contener nuestras operaciones de negocio, luego la vamos a implementar en ProductoBusiness
// para definir la lógica de negocio de estos metodos

public interface IProductoBusiness {

    Producto load(Long id) throws BusinessException, NotFoundException;

    List<Producto> list() throws BusinessException;

    Producto add(Producto producto) throws BusinessException;

    Producto update(Producto producto) throws NotFoundException, BusinessException;

    void delete(Long id) throws BusinessException, NotFoundException;

    Producto findByDescripcion(String descripcion) throws BusinessException, NotFoundException;

    Producto findByPrice(Double price, String condition) throws NotFoundException, BusinessException;

    List<Producto> findByPriceAsc() throws BusinessException;

    List<Producto> findAllProductsThatContainsDescription(String content) throws BusinessException;

}