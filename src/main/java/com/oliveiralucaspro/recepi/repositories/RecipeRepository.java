package com.oliveiralucaspro.recepi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.oliveiralucaspro.recepi.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
