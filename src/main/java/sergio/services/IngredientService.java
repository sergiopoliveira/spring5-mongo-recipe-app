package sergio.services;

import reactor.core.publisher.Mono;
import sergio.commands.IngredientCommand;

/**
 * Created by jt on 6/27/17.
 */
public interface IngredientService {

    Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    Mono<IngredientCommand> saveIngredientCommand(IngredientCommand command);

    Mono<Void> deleteById(String recipeId, String idToDelete);
}
