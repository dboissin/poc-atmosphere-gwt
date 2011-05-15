package fr.dbo.poc.client.util;

import org.atmosphere.gwt.client.AtmosphereGWTSerializer;
import org.atmosphere.gwt.client.SerialTypes;

import fr.dbo.poc.shared.dto.MessageDTO;

@SerialTypes(value = {MessageDTO.class})
public abstract class MessageDTOSerializer extends AtmosphereGWTSerializer {

}

