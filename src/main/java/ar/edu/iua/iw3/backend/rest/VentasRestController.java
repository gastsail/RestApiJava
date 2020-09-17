package ar.edu.iua.iw3.backend.rest;

import ar.edu.iua.iw3.backend.business.IProductoBusiness;
import ar.edu.iua.iw3.backend.business.IVentaBusiness;
import ar.edu.iua.iw3.backend.business.exception.BusinessException;
import ar.edu.iua.iw3.backend.business.exception.NotFoundException;
import ar.edu.iua.iw3.backend.model.Producto;
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
@RequestMapping(value = Constantes.URL_VENTAS)
public class VentasRestController extends BaseRestController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IVentaBusiness ventaBusiness;

	@GetMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> listAllVentasForProduct(@RequestParam ("idProducto") Long id) {
		try {
			return new ResponseEntity<List<Producto>>(ventaBusiness.getAllVentasForProduct(id), HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (NotFoundException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND);
		}
	}
	
}