package tacos.web.api;

import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;
import tacos.Ingredient;
import tacos.data.IngredientRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientApiController {

    private IngredientRepository ingredientRepository;

    public IngredientApiController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public Resources<IngredientResource> getIngredients() {
        List<Ingredient> ingredients = (List<Ingredient>) ingredientRepository.findAll();

        List<IngredientResource> ingredientResources = new IngredientResourceAssembler().toResources(ingredients);

        Resources<IngredientResource> recentResources = new Resources<>(ingredientResources);

        return recentResources;
    }
}
