package ar.edu.iua.iw3.backend.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.iua.iw3.backend.business.exception.BusinessException;
import ar.edu.iua.iw3.backend.business.exception.NotFoundException;
import ar.edu.iua.iw3.backend.model.Producto;
import ar.edu.iua.iw3.backend.model.Venta;
import ar.edu.iua.iw3.backend.model.persistence.VentaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class VentaBusiness implements IVentaBusiness{

	@Autowired
	private VentaRepository ventaDAO;

	@Override
	public List<Producto> listAllVentasForProduct(long idProducto) throws NotFoundException, BusinessException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Venta addVenta(Venta venta) throws BusinessException {
		try {
			return ventaDAO.save(venta);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

    @Override
    public Venta load(Long id) throws BusinessException, NotFoundException {
        Optional<Venta> op;
        try {
            op = ventaDAO.findById(id);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
        if (!op.isPresent())
            throw new NotFoundException("No se encuentra el producto id=" + id);
        return op.get();
    }

    @Override
    public List<Venta> list() throws BusinessException {
        try {
            return ventaDAO.findAll();
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
	public Venta update(Venta venta, Long id) throws NotFoundException, BusinessException {
		return null;
	}

	@Override
	public void delete(Long id) throws BusinessException, NotFoundException {
		try {
			ventaDAO.deleteById(id);
		} catch (EmptyResultDataAccessException e1) {
			throw new NotFoundException("No se encuentra la venta con id=" + id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
}
