package com.oliveiralucaspro.recepi.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.oliveiralucaspro.recepi.domain.Recipe;
import com.oliveiralucaspro.recepi.repositories.RecipeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;
    
    @Override
    public Set<Recipe> getRecipes() {

	Set<Recipe> recipeSet = new HashSet<>();
	recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
	
	return recipeSet;
    }

}
