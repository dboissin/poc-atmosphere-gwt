package fr.dbo.poc.server.service;

import java.util.List;

import fr.dbo.poc.shared.dto.SomeDTO;

public interface NoobService {

    List<SomeDTO> getAll();

    SomeDTO get(int index);

    void save(SomeDTO someDTO);
}
