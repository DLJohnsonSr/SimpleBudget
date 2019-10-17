package dev.dljsr.budget.app.repositories;

import dev.dljsr.budget.app.models.Budget;
import org.springframework.data.repository.CrudRepository;

public interface BudgetRepository extends CrudRepository<Budget, Long> {

        Budget findByYearAndMonthOrderByYearDescMonthDesc(int year, int month);
}
