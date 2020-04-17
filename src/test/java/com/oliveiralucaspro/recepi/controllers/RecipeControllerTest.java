package com.oliveiralucaspro.recepi.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.oliveiralucaspro.recepi.domain.Recipe;
import com.oliveiralucaspro.recepi.services.RecipeService;

class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    RecipeController controller;

    @BeforeEach
    void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);

	controller = new RecipeController(recipeService);
    }

    @Test
    void testShowById() throws Exception {
	Recipe recipe = Recipe.builder().id(1L).build();

	MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	when(recipeService.findById(anyLong())).thenReturn(recipe);

	mockMvc.perform(get("/recipe/show/1")).andExpect(status().isOk()).andExpect(view().name("recipe/show"));

    }

}
