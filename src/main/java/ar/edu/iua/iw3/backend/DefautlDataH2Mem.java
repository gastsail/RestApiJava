package ar.edu.iua.iw3.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component  				//Candidato para el autowired
@Profile("h2mem") 			//Esta implementación solo es candidata para dicho perfil
public class DefautlDataH2Mem implements IDefaultData {

	private Logger log = LoggerFactory.getLogger(IDefaultData.class);
	
	@Override
	public void mensaje() {
		log.info("-----------Perfil H2 Mem ----------");
	}

}
