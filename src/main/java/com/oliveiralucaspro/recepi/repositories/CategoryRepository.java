package com.oliveiralucaspro.recepi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.oliveiralucaspro.recepi.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
