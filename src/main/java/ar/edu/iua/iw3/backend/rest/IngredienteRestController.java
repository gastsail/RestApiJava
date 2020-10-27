package ar.edu.iua.iw3.backend.rest;

import ar.edu.iua.iw3.backend.business.IIngredienteBusiness;
import ar.edu.iua.iw3.backend.business.exception.BusinessException;
import ar.edu.iua.iw3.backend.business.exception.NotFoundException;
import ar.edu.iua.iw3.backend.model.Ingredientes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Constantes.URL_INGREDIENTES)
public class IngredienteRestController {


    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IIngredienteBusiness ingredienteBusiness;

    @GetMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ingredientes>> list() {
        try {
            return new ResponseEntity<List<Ingredientes>>(ingredienteBusiness.list(), HttpStatus.OK);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<List<Ingredientes>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/precio_producto")
    public ResponseEntity<List<Ingredientes>> loadByProductoPrecio(@RequestParam("precio") double precio) {
        try {
            return new ResponseEntity<List<Ingredientes>>(ingredienteBusiness.findByProductoListPrecioLista(precio), HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<List<Ingredientes>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // http://localhost:8080/api/v1/ingredientes/mayor_precio?precio=
    @GetMapping(value = "/mayor_precio")
    public ResponseEntity<List<Ingredientes>> loadPrecioMayor(@RequestParam("precio") double precio) {
        try {
            return new ResponseEntity<List<Ingredientes>>(ingredienteBusiness.preciomayorque(precio), HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<List<Ingredientes>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<List<Ingredientes>>(HttpStatus.NOT_FOUND);
        }
    }
}

