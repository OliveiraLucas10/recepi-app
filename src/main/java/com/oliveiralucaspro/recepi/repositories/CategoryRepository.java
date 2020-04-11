package com.oliveiralucaspro.recepi.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.oliveiralucaspro.recepi.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);

}
