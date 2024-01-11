package guru.springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{
	public Category findByCategoryName(String categoryName);
}
