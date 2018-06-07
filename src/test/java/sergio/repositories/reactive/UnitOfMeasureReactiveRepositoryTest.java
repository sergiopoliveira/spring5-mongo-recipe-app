package sergio.repositories.reactive;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import sergio.domain.UnitOfMeasure;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {

	private static final String PINCH = "Pinch";
	
	@Autowired
	UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

	@Before
	public void setUp() throws Exception {
		unitOfMeasureReactiveRepository.deleteAll().block();
	}

	@Test
	public void testSaveUom() {
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setDescription(PINCH);

		unitOfMeasureReactiveRepository.save(uom).block();

		Long count = unitOfMeasureReactiveRepository.count().block();

		assertEquals(Long.valueOf(1L), count);
	}

	@Test
	public void testFindByDescription() throws Exception {
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setDescription(PINCH);

		unitOfMeasureReactiveRepository.save(uom).then().block();

		UnitOfMeasure uomFetched = unitOfMeasureReactiveRepository.findByDescription(PINCH).block();

		assertEquals(PINCH, uomFetched.getDescription());
	}

}
