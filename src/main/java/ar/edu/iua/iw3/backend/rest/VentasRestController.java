package ar.edu.iua.iw3.backend.rest;

import ar.edu.iua.iw3.backend.business.IVentaBusiness;
import ar.edu.iua.iw3.backend.business.exception.BusinessException;
import ar.edu.iua.iw3.backend.business.exception.NotFoundException;
import ar.edu.iua.iw3.backend.model.Producto;
import ar.edu.iua.iw3.backend.model.Venta;
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
    public ResponseEntity<List<Venta>> list() {
        try {
            return new ResponseEntity<List<Venta>>(ventaBusiness.list(), HttpStatus.OK);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<List<Venta>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@PostMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Venta venta) {
		try {
			ventaBusiness.addVenta(venta);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_VENTAS + "/" + venta.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @GetMapping(value = { "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Venta> load(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<Venta>(ventaBusiness.load(id),HttpStatus.OK);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<Venta>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<Venta>(HttpStatus.NOT_FOUND);
        }
    }

    @SuppressWarnings("Duplicates")
    @DeleteMapping(value = { "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            ventaBusiness.delete(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    @SuppressWarnings("Duplicates")
    @PutMapping(value = { "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody Venta venta, @PathVariable("id") Long id) {
        try {
            ventaBusiness.update(venta, id);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }
/*
    //localhost:8080/api/v1/ventas/producto?idProducto=1
	@GetMapping(value = { "/producto" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> listAllVentasForProduct(@RequestParam ("idProducto") Long id) {
		try {
			return new ResponseEntity<List<Producto>>(ventaBusiness.listAllVentasForProduct(id), HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (NotFoundException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND);
		}
	}*/
		//Get que nos trae las ventas segun el id de producto pasado
		//localhost:8080/api/v1/ventas/producto?idProducto=1
		@GetMapping(value = { "/producto" }, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Venta>> findByProductoListId(@RequestParam ("idProducto") Long id) {
			try {
				return new ResponseEntity<List<Venta>>(ventaBusiness.findByProductoListId(id),HttpStatus.OK);
			} catch (BusinessException e) {
				log.error(e.getMessage(), e);
				return new ResponseEntity<List<Venta>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}catch (NotFoundException e) {
				return new ResponseEntity<List<Venta>>(HttpStatus.NOT_FOUND);
			}
		}
		
		
		/*@GetMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<Venta>> findByProductoListId() {
	        try {
	            return new ResponseEntity<List<Venta>>(ventaBusiness.list(), HttpStatus.OK);
	        } catch (BusinessException e) {
	            log.error(e.getMessage(), e);
	            return new ResponseEntity<List<Venta>>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    */
}