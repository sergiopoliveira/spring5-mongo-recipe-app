package sergio.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Mono;
import sergio.domain.Category;

public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category, String>{
	
	Mono<Category> findByDescription(String description);

}
