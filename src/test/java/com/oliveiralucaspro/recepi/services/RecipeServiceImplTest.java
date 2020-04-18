package com.oliveiralucaspro.recepi.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.oliveiralucaspro.recepi.domain.Recipe;
import com.oliveiralucaspro.recepi.repositories.RecipeRepository;

class RecipeServiceImplTest {

    RecipeServiceImpl service;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);

	service = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void testFindById() {
	Recipe recipe = new Recipe();
	recipe.setId(1L);

	when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

	Recipe returned = service.findById(1L);

	assertNotNull(returned);
	verify(recipeRepository).findById(anyLong());
	verify(recipeRepository, never()).findAll();

    }

    @Test
    void testGetRecipes() {
	Set<Recipe> recipeSet = new HashSet<>();
	Recipe recipe = new Recipe();
	recipe.setId(1L);
	recipeSet.add(recipe);

	when(recipeRepository.findAll()).thenReturn(recipeSet);

	Set<Recipe> recipes = service.getRecipes();

	assertEquals(1, recipes.size());
	verify(recipeRepository).findAll();
	verify(recipeRepository, never()).findById(anyLong());
    }

}
