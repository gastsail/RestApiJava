package ar.edu.iua.iw3.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component  				//Candidato para el autowired
@Profile("cliente") 			//Esta implementación solo es candidata para dicho perfil
public class DefaultDataCliente implements IDefaultData {

	private Logger log = LoggerFactory.getLogger(IDefaultData.class);
	
	@Override
	public void mensaje() {
		log.info("-----------Perfil Cliente ----------");
	}

}
