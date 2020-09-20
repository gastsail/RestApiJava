package ar.edu.iua.iw3.backend.business;

import java.util.List;

import ar.edu.iua.iw3.backend.business.exception.BusinessException;
import ar.edu.iua.iw3.backend.business.exception.NotFoundException;
import ar.edu.iua.iw3.backend.model.Producto;
import ar.edu.iua.iw3.backend.model.Venta;

public interface IVentaBusiness {

	
	public List<Producto>getAllVentasForProduct(long idProducto) throws NotFoundException, BusinessException;
}
