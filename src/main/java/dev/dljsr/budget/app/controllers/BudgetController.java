package dev.dljsr.budget.app.controllers;

import dev.dljsr.budget.app.models.Budget;
import dev.dljsr.budget.app.repositories.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BudgetController {

    @Autowired
    BudgetRepository budgetRepository;

    @GetMapping("/")
    public String showIndex(Model model){
        model.addAttribute("budgets", budgetRepository.findAll());
        return "index";
    }

    @GetMapping("/add-budget")
    public String addBudget(Model model){
        model.addAttribute("budget", new Budget());
        return "budget-form";
    }

    @PostMapping("/save-budget")
    public String saveBudget(@ModelAttribute Budget budget, BindingResult result){
        if(result.hasErrors()){
            return "budget-form";
        }
        budgetRepository.save(budget);
        return "redirect:/";
    }

    @GetMapping("/edit-budget/{budId}")
    public String editBudget(@PathVariable("budId") long budId, Model model){
        model.addAttribute("budget", budgetRepository.findById(budId).get());
        return "budget-form";
    }
    @GetMapping("/delete-budget/{budId}")
    public String deleteBudget(@PathVariable("budId") long budId){
        budgetRepository.deleteById(budId);
        return "redirect:/";
    }

    @GetMapping("/view-budget/{budId}")
    public String viewBudget(@PathVariable("budId") long budId, Model model){
        model.addAttribute("budget", budgetRepository.findById(budId).get());
        return "budget-view";
    }
    
}
