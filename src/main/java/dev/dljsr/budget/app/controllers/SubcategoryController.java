package dev.dljsr.budget.app.controllers;

import dev.dljsr.budget.app.models.Subcategory;
import dev.dljsr.budget.app.models.Category;
import dev.dljsr.budget.app.repositories.CategoryRepository;
import dev.dljsr.budget.app.repositories.SubcategoryRepository;
import dev.dljsr.budget.app.services.BudgetUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class SubcategoryController {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SubcategoryRepository subcategoryRepository;
    @Autowired
    BudgetUpdateService budgetUpdateService;

   @GetMapping("/add-subcategory/{catId}")
    public String addSubcategory(@PathVariable("catId") long catId, Model model){
        model.addAttribute("category", categoryRepository.findById(catId).get());
        model.addAttribute("subcategory", new Subcategory());
        return "subcategory-form";
    }

    @PostMapping("/save-subcategory")
    public String saveSubcategory(@ModelAttribute Subcategory subcategory, BindingResult result){
       if (result.hasErrors()){
           return "subcategory-form";
       }
        subcategoryRepository.save(subcategory);
        Category category = subcategory.getCategory();
        category.addSubcategory(subcategory);
        categoryRepository.save(category);
        budgetUpdateService.updateAllBudgetValues(category.getBudget().getId());
        return "redirect:/";
    }

    @GetMapping("/edit-subcategory/{subId}")
    public String editSubcategory(@PathVariable("subId") long subId, Model model){
       Subcategory subcategory = subcategoryRepository.findById(subId).get();
        model.addAttribute("categoryId", subcategory.getCategory().getId());
        model.addAttribute("subcategory", subcategory);
        return "subcategory-form";
    }

    @GetMapping("/delete-subcategory/{subId}")
    public String deleteSubcategory(@PathVariable("subId") long subId){
        subcategoryRepository.deleteById(subId);
        return "redirect:/";
    }

    @GetMapping("/edit-subcategory-budgeted/{subId}")
    public String editSubcategoryBudgeted(@PathVariable("subId") long subId, Model model){
        model.addAttribute("subcategoryId", subId);
        return "subcategory-budgeted-form";
    }

    @PostMapping("/update-subcategory-budgeted/{subId}")
    public String updateSubcategoryBudgeted(@PathVariable("subId") long subId, @RequestParam("amount") String amount){
        budgetUpdateService.updateOneCategoryOrSubcategoryBudgetedOrActual("budgeted", subId, amount);
        return "redirect:/";
    }

    @GetMapping("/edit-subcategory-actual/{subId}")
    public String editSubcategoryActual(@PathVariable("subId") long subId, Model model){
        model.addAttribute("subcategoryId", subId);
        return "subcategory-actual-form";
    }

    @PostMapping("/update-subcategory-actual/{subId}")
    public String updateSubcategoryActual(@PathVariable("subId") long subId, @RequestParam("amount") String amount){
        budgetUpdateService.updateOneCategoryOrSubcategoryBudgetedOrActual("actual", subId, amount);
        budgetUpdateService.updateAllBudgetValues(subcategoryRepository.findById(subId).get().getCategory().getBudget().getId());
        return "redirect:/";
    }
}
