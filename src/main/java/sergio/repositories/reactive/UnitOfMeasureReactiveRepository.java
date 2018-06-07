package sergio.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import sergio.domain.UnitOfMeasure;

public interface UnitOfMeasureReactiveRepository extends ReactiveMongoRepository<UnitOfMeasure, String>{

}
