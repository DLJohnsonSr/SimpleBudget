package dev.dljsr.budget.app.repositories;

import dev.dljsr.budget.app.models.Subcategory;
import org.springframework.data.repository.CrudRepository;

public interface SubcategoryRepository extends CrudRepository<Subcategory, Long> {
}
