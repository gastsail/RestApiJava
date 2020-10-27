package ar.edu.iua.iw3.backend.business;

import java.util.List;

import ar.edu.iua.iw3.backend.business.exception.BusinessException;
import ar.edu.iua.iw3.backend.business.exception.NotFoundException;
import ar.edu.iua.iw3.backend.model.Venta;
import ar.edu.iua.iw3.backend.model.VentaDTO;

public interface IVentaBusiness {

	//List<Producto> listAllVentasForProduct(long idProducto) throws NotFoundException, BusinessException;
	List<Venta> findByProductoListId(Long id) throws NotFoundException, BusinessException;
    List<Venta> list() throws BusinessException;
    Venta load(Long id) throws BusinessException, NotFoundException;
	Venta addVenta(Venta venta) throws BusinessException;
	Venta update(Venta venta, Long id) throws NotFoundException, BusinessException;
	void delete(Long id) throws BusinessException, NotFoundException;
	List<VentaDTO> soloNombre(String nombre) throws NotFoundException, BusinessException;
}
