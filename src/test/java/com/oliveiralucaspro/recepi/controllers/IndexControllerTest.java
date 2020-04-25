package com.oliveiralucaspro.recepi.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.oliveiralucaspro.recepi.services.RecipeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    IndexController indexController;

    MockMvc mockMvc;

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	indexController = new IndexController(recipeService);

	mockMvc = MockMvcBuilders.standaloneSetup(indexController).setControllerAdvice(new ControllerExceptionHandler())
		.build();
    }

    @Test
    public void testMockMVC() throws Exception {
	mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"))
		.andExpect(model().attributeExists("recipes"));

	verify(recipeService, times(1)).getRecipes();
    }

}
