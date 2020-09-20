package ar.edu.iua.iw3.backend.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.iua.iw3.backend.business.exception.BusinessException;
import ar.edu.iua.iw3.backend.business.exception.NotFoundException;
import ar.edu.iua.iw3.backend.model.Producto;
import ar.edu.iua.iw3.backend.model.Venta;
import ar.edu.iua.iw3.backend.model.persistence.VentaRepository;
import org.springframework.stereotype.Service;

@Service
public class VentaBusiness implements IVentaBusiness{

	@Autowired
	private VentaRepository ventaDAO;

	@Override
	public List<Producto> getAllVentasForProduct(long idProducto) throws NotFoundException, BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
