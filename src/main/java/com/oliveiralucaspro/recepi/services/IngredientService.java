package com.oliveiralucaspro.recepi.services;

import com.oliveiralucaspro.recepi.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
