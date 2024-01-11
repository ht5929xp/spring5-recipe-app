package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/RecipeLookup")
public class RecipeLookup {
	
	private RecipeRepository recipeRepo;

	public RecipeLookup(RecipeRepository recipeRepo) {
		super();
		this.recipeRepo = recipeRepo;
	}

	@GetMapping({"", "/"})
	public String viewRecipe(Model model) {
		
		log.debug("Reached viewRecipe controller");
		
		Recipe grilledChickenRecipe = recipeRepo.getRecipeByTitle("Spicy Grilled Chicken Tacos");
		
		System.out.println("Ingredients: ");
		for(Ingredient i : grilledChickenRecipe.getIngredients()) {
			System.out.println("test: " + i.getDescription() + "   " + i.getAmount() + "   " + i.getUom());
		}
		
		model.addAttribute("recipe", grilledChickenRecipe);
		
		return "index";
	}
	
}
