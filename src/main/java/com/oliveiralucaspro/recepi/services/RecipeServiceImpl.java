package com.oliveiralucaspro.recepi.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.oliveiralucaspro.recepi.domain.Recipe;
import com.oliveiralucaspro.recepi.repositories.RecipeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Override
    public Set<Recipe> getRecipes() {
	Set<Recipe> recipeSet = new HashSet<>();
	recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
	return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {
	Optional<Recipe> findById = recipeRepository.findById(id);

	if (!findById.isPresent()) {
	    throw new RuntimeException("Recipe not Found!");
	}

	return findById.get();
    }

}
