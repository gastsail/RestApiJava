package ar.edu.iua.iw3.backend.business;

import ar.edu.iua.iw3.backend.business.exception.BusinessException;
import ar.edu.iua.iw3.backend.business.exception.NotFoundException;
import ar.edu.iua.iw3.backend.model.ProveedorDTO;

public interface IProveedorBusiness {

	ProveedorDTO verNombre(Long id) throws NotFoundException, BusinessException;
}
