package guru.springframework.init;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.model.Ingredient;
import guru.springframework.model.Note;
import guru.springframework.model.Recipe;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;

@Component
public class Initializer implements CommandLineRunner {

	private RecipeRepository recipeRepo;
	private CategoryRepository categoryRepo;
	private UnitOfMeasureRepository uomRepo;

	@Autowired
	public Initializer(RecipeRepository recipeRepo, CategoryRepository categoryRepo, UnitOfMeasureRepository uomRep) {
		super();
		this.recipeRepo = recipeRepo;
		this.categoryRepo = categoryRepo;
		this.uomRepo = uomRep;
	}

	@Override
	public void run(String... args) throws Exception {
		Recipe grilledChickenRecipe = new Recipe();
		grilledChickenRecipe.setTitle("Spicy Grilled Chicken Tacos");
		grilledChickenRecipe.setDescription("Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.");
		grilledChickenRecipe.getCategories().add(categoryRepo.findByCategoryName("MEXICAN"));
				
		Ingredient ingredient1 = new Ingredient();
		ingredient1.setDescription("2 tablespoons ancho chili powder");
		ingredient1.setUom(uomRepo.getByUom("TABLESPOON"));
		ingredient1.setAmount(new BigDecimal(2.0));
		ingredient1.setRecipe(grilledChickenRecipe);
		
		Ingredient ingredient2 = new Ingredient();
		ingredient2.setDescription("1 teaspoon dried oregano");
		ingredient2.setUom(uomRepo.getByUom("TEASPOON"));
		ingredient2.setAmount(new BigDecimal(1.0));
		ingredient2.setRecipe(grilledChickenRecipe);
		
		Note note1 = new Note(grilledChickenRecipe,
				"Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
		
		grilledChickenRecipe.getIngredients().add(ingredient1);
		grilledChickenRecipe.getIngredients().add(ingredient2);
		grilledChickenRecipe.setNote(note1);
		
		recipeRepo.save(grilledChickenRecipe);
	}

}
