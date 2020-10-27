package ar.edu.iua.iw3.backend.business;

import ar.edu.iua.iw3.backend.business.exception.BusinessException;
import ar.edu.iua.iw3.backend.business.exception.NotFoundException;
import ar.edu.iua.iw3.backend.model.Ingredientes;
import ar.edu.iua.iw3.backend.model.persistence.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteBusiness implements IIngredienteBusiness {


    @Autowired
    private IngredienteRepository ingredienteDAO;

    @Override
    public Ingredientes load(Long id) throws BusinessException, NotFoundException {
        Optional<Ingredientes> op;
        try {
            op = ingredienteDAO.findById(id);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
        if (!op.isPresent())
            throw new NotFoundException("No se encuentra el producto id=" + id);
        return op.get();
    }

    @Override
    public List<Ingredientes> list() throws BusinessException {
        try {
            return ingredienteDAO.findAll();
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }


    @Override
    public List<Ingredientes>  findByProductoListPrecioLista(double precio) throws BusinessException {
        try {
            return ingredienteDAO.findByProductoListPrecioLista(precio);
        } catch (Exception e) {
            throw new BusinessException(e);
        }

    }

    public List<Ingredientes> preciomayorque(double precio) throws BusinessException, NotFoundException{
    	try {
    		return ingredienteDAO.preciomayorque(precio);
    	} catch (Exception e) {
    		throw new BusinessException(e);
    	}
    }

}
