package sergio.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sergio.commands.RecipeCommand;
import sergio.converters.RecipeCommandToRecipe;
import sergio.converters.RecipeToRecipeCommand;
import sergio.domain.Recipe;
import sergio.repositories.reactive.RecipeReactiveRepository;

/**
 * Created by jt on 6/13/17.
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeReactiveRepository recipeReactiveRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeReactiveRepository recipeReactiveRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeReactiveRepository = recipeReactiveRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Flux<Recipe> getRecipes() {
        log.debug("I'm in the service");

        return recipeReactiveRepository.findAll();
    }

    @Override
    public Mono<Recipe> findById(String id) {

       return recipeReactiveRepository.findById(id);
    }

    @Override
    public Mono<RecipeCommand> findCommandById(String id) {

    	return recipeReactiveRepository.findById(id)
    			.map(recipe -> {
    				RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);
    				
    				recipeCommand.getIngredients().forEach(rc -> {
    					rc.setRecipeId(recipeCommand.getId());
    				});
    				
    				return recipeCommand;
    			});
    }

    @Override
    public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command) {
    	
    	return recipeReactiveRepository.save(recipeCommandToRecipe.convert(command))
    			.map(recipeToRecipeCommand::convert);
    }

    @Override
    public Mono<Void> deleteById(String idToDelete) {
        recipeReactiveRepository.deleteById(idToDelete).block();

        return Mono.empty();
    }
}