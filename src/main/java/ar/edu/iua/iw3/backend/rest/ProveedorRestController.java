package ar.edu.iua.iw3.backend.rest;

import ar.edu.iua.iw3.backend.business.IProveedorBusiness;
import ar.edu.iua.iw3.backend.business.IVentaBusiness;
import ar.edu.iua.iw3.backend.business.exception.BusinessException;
import ar.edu.iua.iw3.backend.business.exception.NotFoundException;
import ar.edu.iua.iw3.backend.model.ProveedorDTO;
import ar.edu.iua.iw3.backend.model.Venta;
import ar.edu.iua.iw3.backend.model.VentaDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = Constantes.URL_PROVEEDOR)
public class ProveedorRestController extends BaseRestController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IProveedorBusiness ProveedorBusiness;

		//http://localhost:8080/api/v1/proveedor/verNombre?id=5
		@GetMapping(value = "/verNombre")
	    public ResponseEntity<ProveedorDTO> loadNombre(@RequestParam("id") Long n) {
	        try {
	            return new ResponseEntity<ProveedorDTO>(ProveedorBusiness.verNombre(n), HttpStatus.OK);
	        } catch (BusinessException e) {
	            return new ResponseEntity<ProveedorDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
	        } catch (NotFoundException e) {
	            return new ResponseEntity<ProveedorDTO>(HttpStatus.NOT_FOUND);
	        }
	    }
		
}