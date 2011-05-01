package fr.dbo.poc.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.dbo.poc.server.service.NoobService;
import fr.dbo.poc.shared.dto.OtherDTO;
import fr.dbo.poc.shared.dto.SomeDTO;

/**
 * @author Mathieu Carbou
 * @since 0.7
 */

@Named("noobService")
final class NoobServiceImpl implements NoobService {

     private final Logger logger = LoggerFactory.getLogger(NoobServiceImpl.class);

    { logger.debug("NoobServiceImpl created ! "); }

    private List<SomeDTO> someDTOs = new ArrayList<SomeDTO>();

    public NoobServiceImpl() {
        for (int i = 0; i < 10; i++) {
            SomeDTO someDTO = new SomeDTO();
            someDTO.setSomeString("hello" + i);
            someDTO.setSomeInteger(i);
            for (int j = 0; j < 10; j++) {
                OtherDTO otherDTO = new OtherDTO();
                otherDTO.setOtherInteger(j);
                otherDTO.setOtherString("coucou" + j);
                someDTO.getOtherDTO().add(otherDTO);
            }
            someDTOs.add(someDTO);
        }
    }

    public List<SomeDTO> getAll() { return someDTOs; }

    public SomeDTO get(int index) { return someDTOs.get(index); }

    public void save(SomeDTO someDTO) {
        if (someDTO.getSomeInteger() != null) {
            someDTOs.set(someDTO.getSomeInteger(), someDTO);
        } else {
            someDTO.setSomeInteger(someDTOs.size());
            someDTOs.add(someDTO);
        }
    }
}
