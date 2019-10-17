package dev.dljsr.budget.app.services;

import dev.dljsr.budget.app.models.Category;
import dev.dljsr.budget.app.repositories.CategoryRepository;
import dev.dljsr.budget.app.models.Subcategory;
import dev.dljsr.budget.app.repositories.SubcategoryRepository;
import dev.dljsr.budget.app.models.Budget;
import dev.dljsr.budget.app.repositories.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BudgetUpdateService {

    @Autowired
    BudgetRepository budgetRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SubcategoryRepository subcategoryRepository;

    public void updateOneCategoryOrSubcategoryBudgetedOrActual(String budgetedOrActual, long id, String amount){

        // Update Category
        if(categoryRepository.existsById(id)){
            Category category = categoryRepository.findById(id).get();
            if(budgetedOrActual.equalsIgnoreCase("budgeted")){
                category.setBudgeted(amount);
            }
            else if(budgetedOrActual.equalsIgnoreCase("actual")){
                category.setActual(amount);
            }
            category.setRemaining(subtractBigDecimal(category.getBudgeted().toString(), category.getActual().toString()));
            categoryRepository.save(category);
        }

        //Update Subcategory
        else if (subcategoryRepository.existsById(id)){
            Subcategory subcategory = subcategoryRepository.findById(id).get();
            if(budgetedOrActual.equalsIgnoreCase("budgeted")){
                subcategory.setBudgeted(amount);
            }
            else if(budgetedOrActual.equalsIgnoreCase("actual")){
                subcategory.setActual(amount);
            }
            subcategory.setRemaining(sumBigDecimal(subcategory.getBudgeted().toString(), subcategory.getActual().toString()));
            subcategoryRepository.save(subcategory);
        }


    }

    public void updateAllBudgetValues(long budgetId){

        Budget budget = budgetRepository.findById(budgetId).get();
        String budgetedIncomeSum = "0.00", budgetedExpenseSum = "0.00",
                actualIncomeSum = "0.00", actualExpenseSum = "0.00";

        for (Category category: budget.getCategorySet()){

            if(!category.getSubcategorySet().isEmpty()){
                String categoryBudgetedSum = "0.00", categoryActualSum = "0.00";

                for(Subcategory subcategory: category.getSubcategorySet()){
                    categoryBudgetedSum = sumBigDecimal(categoryBudgetedSum, subcategory.getBudgeted().toString());
                    categoryActualSum = sumBigDecimal(categoryActualSum, subcategory.getActual().toString());
                }

                category.setBudgeted(categoryBudgetedSum);
                category.setActual(categoryActualSum);
                category.setRemaining(subtractBigDecimal(categoryBudgetedSum, categoryActualSum));
            }

            if(category.getIncomeExpense().equalsIgnoreCase("income")){
                budgetedIncomeSum = sumBigDecimal(budgetedIncomeSum, category.getBudgeted().toString());
                actualIncomeSum = sumBigDecimal(actualIncomeSum, category.getActual().toString());
            }
            else if(category.getIncomeExpense().equalsIgnoreCase("expense")){
                budgetedExpenseSum = sumBigDecimal(budgetedExpenseSum, category.getBudgeted().toString());
                actualExpenseSum = sumBigDecimal(actualExpenseSum, category.getActual().toString());

            }
        }

        budget.setBudgetedIncome(budgetedIncomeSum);
        budget.setBudgetedExpense(budgetedExpenseSum);
        budget.setActualIncome(actualIncomeSum);
        budget.setActualExpense(actualExpenseSum);
        budget.setBudgetedRemaining(subtractBigDecimal(budgetedIncomeSum, budgetedExpenseSum));
        budget.setActualRemaining(subtractBigDecimal(actualIncomeSum, actualExpenseSum));
        budget.setTotalIncomeRemaining(subtractBigDecimal(budgetedIncomeSum, actualIncomeSum));
        budget.setTotalExpenseRemaining(subtractBigDecimal(budgetedExpenseSum, actualExpenseSum));
        budgetRepository.save(budget);
    }

    private String sumBigDecimal(String sum1, String sum2){
        BigDecimal bigDecimal1 = new BigDecimal(sum1);
        BigDecimal bigDecimal2 = new BigDecimal(sum2);
        return bigDecimal1.add(bigDecimal2).toString();
    }

    private String subtractBigDecimal(String minuend, String subtrahend){
        BigDecimal bigDecimal1 = new BigDecimal(minuend);
        BigDecimal bigDecimal2 = new BigDecimal(subtrahend);
        return bigDecimal1.subtract(bigDecimal2).toString();
    }
}
