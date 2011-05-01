package fr.dbo.poc.client.util;

import org.atmosphere.gwt.client.AtmosphereGWTSerializer;
import org.atmosphere.gwt.client.SerialTypes;

import fr.dbo.poc.shared.dto.SimpleDTO;

@SerialTypes(value = {SimpleDTO.class})
public abstract class SimpleDTOSerializer extends AtmosphereGWTSerializer {

}
