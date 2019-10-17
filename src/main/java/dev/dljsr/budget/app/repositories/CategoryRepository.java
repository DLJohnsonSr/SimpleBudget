package dev.dljsr.budget.app.repositories;

import dev.dljsr.budget.app.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
