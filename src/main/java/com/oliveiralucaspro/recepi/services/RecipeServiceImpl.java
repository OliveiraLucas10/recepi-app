package com.oliveiralucaspro.recepi.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oliveiralucaspro.recepi.commands.RecipeCommand;
import com.oliveiralucaspro.recepi.converters.RecipeCommandToRecipe;
import com.oliveiralucaspro.recepi.converters.RecipeToRecipeCommand;
import com.oliveiralucaspro.recepi.domain.Recipe;
import com.oliveiralucaspro.recepi.repositories.RecipeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

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

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long l) {
	return recipeToRecipeCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
	Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

	Recipe savedRecipe = recipeRepository.save(detachedRecipe);
	log.debug("Saved RecipeId:" + savedRecipe.getId());
	return recipeToRecipeCommand.convert(savedRecipe);
    }

}
