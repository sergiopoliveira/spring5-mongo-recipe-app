package sergio.repositories;

import org.springframework.data.repository.CrudRepository;

import sergio.domain.Recipe;

/**
 * Created by jt on 6/13/17.
 */
public interface RecipeRepository extends CrudRepository<Recipe, String> {
}
