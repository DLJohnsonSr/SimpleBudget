package dev.dljsr.budget.app.services;

import dev.dljsr.budget.app.repositories.BudgetRepository;
import dev.dljsr.budget.app.models.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetCreationService {

    @Autowired
    BudgetRepository budgetRepository;

    public void createBudget(int year, int month){

        Budget budget = new Budget(year, month);
        budgetRepository.save(budget);
    }
}
