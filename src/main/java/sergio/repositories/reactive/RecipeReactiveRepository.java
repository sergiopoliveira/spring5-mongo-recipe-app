package sergio.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import sergio.domain.Recipe;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String>{

}
