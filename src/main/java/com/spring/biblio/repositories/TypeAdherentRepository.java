package com.spring.biblio.repositories;

import com.spring.biblio.entities.TypeAdherent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeAdherentRepository extends JpaRepository<TypeAdherent, Long> {

}