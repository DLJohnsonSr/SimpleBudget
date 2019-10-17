package dev.dljsr.budget.app.controllers;

import dev.dljsr.budget.app.models.Budget;
import dev.dljsr.budget.app.models.Category;
import dev.dljsr.budget.app.repositories.BudgetRepository;
import dev.dljsr.budget.app.repositories.CategoryRepository;
import dev.dljsr.budget.app.services.BudgetUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {

    @Autowired
    BudgetRepository budgetRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BudgetUpdateService budgetUpdateService;

    @GetMapping("/add-category/{budId}")
    public String addCategory(@PathVariable("budId") long budId, Model model){
        model.addAttribute("budgetId", budId);
        model.addAttribute("category", new Category());
        return "category-form";
    }

    @PostMapping("/save-category")
    public String saveCategory(@ModelAttribute Category category, BindingResult result){
        if(result.hasErrors()){
            return "category-form";
        }
        categoryRepository.save(category);
        Budget budget = category.getBudget();
        budget.addCategories(category);
        budgetRepository.save(budget);
        return "redirect:/";
    }

    @GetMapping("/edit-category/{catId}")
    public String editCategory(@PathVariable("catId") long catId, Model model){
        Category category =  categoryRepository.findById(catId).get();
        model.addAttribute("budgetId", category.getBudget().getId());
        model.addAttribute("category", category);
        return "category-form";
    }

    @GetMapping("/delete-category/{catId}")
    public String deleteCategory(@PathVariable("catId") long catId){
        categoryRepository.deleteById(catId);
        return "redirect:/";
    }

    @GetMapping("/edit-category-budgeted/{catId}")
    public String editCategoryBudgeted(@PathVariable("catId") long catId, Model model){
        model.addAttribute("categoryId", catId);
        return "category-budgeted-form";
    }

    @PostMapping("/update-category-budgeted/{catId}")
    public String updateCategoryBudgeted(@PathVariable("catId") long catId, @RequestParam("amount") String amount){
        budgetUpdateService.updateOneCategoryOrSubcategoryBudgetedOrActual("budgeted", catId, amount);
        return "redirect:/";
    }

    @GetMapping("/edit-category-actual/{catId}")
    public String editCategoryActual(@PathVariable("catId") long catId, Model model){
        model.addAttribute("categoryId", catId);
        return "category-actual-form";
    }

    @PostMapping("/update-category-actual/{catId}")
    public String updateCategoryActual(@PathVariable("catId") long catId, @RequestParam("amount") String amount){
        budgetUpdateService.updateOneCategoryOrSubcategoryBudgetedOrActual("actual", catId, amount);
        budgetUpdateService.updateAllBudgetValues(categoryRepository.findById(catId).get().getBudget().getId());
        return "redirect:/";
    }
}
