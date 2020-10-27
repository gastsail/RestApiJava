package ar.edu.iua.iw3.backend.business;

import ar.edu.iua.iw3.backend.business.exception.BusinessException;
import ar.edu.iua.iw3.backend.business.exception.NotFoundException;
import ar.edu.iua.iw3.backend.model.Ingredientes;
import ar.edu.iua.iw3.backend.model.Producto;

import java.util.List;

public interface IIngredienteBusiness {

    public Ingredientes load(Long id) throws BusinessException, NotFoundException;

    public List<Ingredientes> list() throws BusinessException;

    public List<Ingredientes> findByProductoListPrecioLista(double precioLista) throws BusinessException;

    public List<Ingredientes> preciomayorque(double precio) throws BusinessException, NotFoundException;
}
