package com.oliveiralucaspro.recepi.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.oliveiralucaspro.recepi.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);

}
