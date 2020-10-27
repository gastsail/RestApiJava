package ar.edu.iua.iw3.backend.business;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.iua.iw3.backend.business.exception.BusinessException;
import ar.edu.iua.iw3.backend.model.ProveedorDTO;
import ar.edu.iua.iw3.backend.model.persistence.ProveedorRepository;
import org.springframework.stereotype.Service;

@Service
public class ProveedorBusiness implements IProveedorBusiness{

	@Autowired
	private ProveedorRepository proveedorDAO;

	@Override
	public ProveedorDTO verNombre(Long id) throws BusinessException{
			try{
				return proveedorDAO.verNombre(id);
			} catch (Exception e) {
				throw new BusinessException(e);
			}
		}

}
