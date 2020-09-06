package ar.edu.iua.iw3.backend.rest;

import ar.edu.iua.iw3.backend.business.IProductoBusiness;
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

// 5 - con el @RestController le decimos a esta clase que es la controladora en donde una direccion http como localhost:8080/productos/v1/1 nos devuelva un objeto para mapear
// RequestMapping se usa para decir que los metodos que escribo en esta clase (los resultados) entran por la URL definida en constantes
// Usamos la interfaz IProductoBusiness solo para llamar a los métodos que creamos para trabajar sobre los datos (estos metodos estan implementados en ProductoBusiness donde reside la lógica)
// GetMapping y PostMapping se usan como metodos HTTP , GET o POST
// PathVariable se usa para definir que un atributo al usar por ejemplo un get puede cambiar (ver metodo load)
// el produces luego de cualquier anotador POST o GET nos dice el tipo de dato que tiene que devolver (es el content-type)

@RestController
@RequestMapping(value = Constantes.URL_PRODUCTOS)
public class ProductosRestController extends BaseRestController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IProductoBusiness productoBusiness;

	@GetMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> list() {
		try {
			return new ResponseEntity<List<Producto>>(productoBusiness.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// curl -X POST "http://localhost:8080/api/v1/productos" -H "Content-Type: application/json" -d '{"nombre":"Arroz","descripcion":"Arroz que no se pasa","precioLista":89.56,"enStock":true, "productoDetalle":'{"detalle":"Light"}'}' -v
	@PostMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Producto producto) {
		try {
			productoBusiness.add(producto);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_PRODUCTOS + "/" + producto.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// curl -X PUT "http://localhost:8080/api/v1/productos" -H "Content-Type: application/json" -d '{"id":1,"nombre":"Arroz","descripcion":"Arroz que no se pasa","precioLista":76.32,"enStock":true}' -v
	@PutMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@RequestBody Producto producto) {
		try {
			productoBusiness.update(producto);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = { "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> load(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<Producto>(productoBusiness.load(id),HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}

	// curl -X DELETE "http://localhost:8080/api/v1/productos/1"
	@DeleteMapping(value = { "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		try {
			productoBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

	//curl -X GET  'http://localhost:8080/api/v1/productos/description?desc=arroz%20gallo%20de%20oro'
	@GetMapping(value = "/description")
	public ResponseEntity<Producto> loadByDescription(@RequestParam("desc") String desc) {
		try {
			return new ResponseEntity<Producto>(productoBusiness.findByDescripcion(desc), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}

	//curl -X GET  'http://localhost:8080/api/v1/productos/desc?contains="leche'
	@GetMapping(value = "/desc")
	public ResponseEntity<List<Producto>> getProductListByContainingDescription(@RequestParam("contains") String content) {
		try {
			return new ResponseEntity<List<Producto>>(productoBusiness.findAllProductsThatContainsDescription(content), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//curl -X GET  'http://localhost:8080/api/v1/productos/price?price=200&condition=mayor'
	@GetMapping(value = "/price")
	public ResponseEntity<List<Producto>> getProductByPrice(@RequestParam("price") Double price,@RequestParam("condition") String condition) {
		try {
			return new ResponseEntity<List<Producto>>(productoBusiness.findByPrice(price,condition), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND);
		}
	}

	//curl -X GET  'http://localhost:8080/api/v1/productos/price/asc'
	@GetMapping(value = "/price/asc")
	public ResponseEntity<List<Producto>> getProductListByPriceAsc() {
		try {
			return new ResponseEntity<List<Producto>>(productoBusiness.findByPriceAsc(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//curl -X GET  'http://localhost:8080/api/v1/productos?name="A"
	@GetMapping(value = "")
	public ResponseEntity<List<Producto>> getProductListStartingWith(@RequestParam("name") String name) {
		try {
			return new ResponseEntity<List<Producto>>(productoBusiness.findAllByNombreStartingWith(name), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
}