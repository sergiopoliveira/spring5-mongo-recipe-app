package sergio.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import sergio.domain.Recipe;
import sergio.services.RecipeService;

@Configuration
public class WebConfig {

	@Bean
	public RouterFunction<?> routes(RecipeService recipeService) {
		return RouterFunctions.route(GET("/api/recipes"),
				serverRequest -> ServerResponse
							.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(recipeService.getRecipes(), Recipe.class));
	}
}
