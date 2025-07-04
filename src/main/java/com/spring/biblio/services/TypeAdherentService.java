package com.spring.biblio.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.biblio.entities.TypeAdherent;
import com.spring.biblio.repositories.TypeAdherentRepository;

@Service
public class TypeAdherentService {
    private final TypeAdherentRepository typeAdherentRepository;

    public TypeAdherentService(TypeAdherentRepository typeAdherentRepository) {
        this.typeAdherentRepository = typeAdherentRepository;
    }

    public List<TypeAdherent> getAllTypesAdherent() {
        return typeAdherentRepository.findAll();
    }
}
