package dev.dljsr.budget.app.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int year;
    private int month;
    private BigDecimal budgetedIncome;
    private BigDecimal budgetedExpense;
    private BigDecimal budgetedRemaining;
    private BigDecimal actualIncome;
    private BigDecimal actualExpense;
    private BigDecimal actualRemaining;
    private BigDecimal totalIncomeRemaining;
    private BigDecimal totalExpenseRemaining;
    @OneToMany(mappedBy = "budget")
    private Set<Category> categorySet;

    public Budget(){
        this.budgetedIncome = new BigDecimal("0.00");
        this.budgetedExpense = new BigDecimal("0.00");
        this.budgetedRemaining = new BigDecimal("0.00");
        this.actualIncome = new BigDecimal("0.00");
        this.actualExpense = new BigDecimal("0.00");
        this.actualRemaining = new BigDecimal("0.00");
        this.totalIncomeRemaining = new BigDecimal("0.00");
        this.totalExpenseRemaining = new BigDecimal("0.00");
        this.categorySet = new HashSet<>();
    }

    public Budget(int year, int month){
        this.year = year;
        this.month = month;
        this.budgetedIncome = new BigDecimal("0.00");
        this.budgetedExpense = new BigDecimal("0.00");
        this.budgetedRemaining = new BigDecimal("0.00");
        this.actualIncome = new BigDecimal("0.00");
        this.actualExpense = new BigDecimal("0.00");
        this.actualRemaining = new BigDecimal("0.00");
        this.totalIncomeRemaining = new BigDecimal("0.00");
        this.totalExpenseRemaining = new BigDecimal("0.00");
        this.categorySet = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public BigDecimal getBudgetedIncome() {
        return budgetedIncome;
    }

    public void setBudgetedIncome(BigDecimal budgetedIncome) {
        this.budgetedIncome = budgetedIncome;
    }

    public void setBudgetedIncome(String budgetedIncome) {
        this.budgetedIncome = new BigDecimal(budgetedIncome);
    }

    public BigDecimal getBudgetedExpense() {
        return budgetedExpense;
    }

    public void setBudgetedExpense(BigDecimal budgetedExpense) {
        this.budgetedExpense = budgetedExpense;
    }

    public void setBudgetedExpense(String  budgetedExpense) {
        this.budgetedExpense = new BigDecimal(budgetedExpense);
    }

    public BigDecimal getBudgetedRemaining() {
        return budgetedRemaining;
    }

    public void setBudgetedRemaining(BigDecimal budgetedRemaining) {
        this.budgetedRemaining = budgetedRemaining;
    }

    public void setBudgetedRemaining(String budgetedRemaining) {
        this.budgetedRemaining = new BigDecimal(budgetedRemaining);
    }

    public BigDecimal getActualIncome() {
        return actualIncome;
    }

    public void setActualIncome(BigDecimal actualIncome) {
        this.actualIncome = actualIncome;
    }

    public void setActualIncome(String actualIncome) {
        this.actualIncome = new BigDecimal(actualIncome);
    }

    public BigDecimal getActualExpense() {
        return actualExpense;
    }

    public void setActualExpense(BigDecimal actualExpense) {
        this.actualExpense = actualExpense;
    }

    public void setActualExpense(String actualExpense) {
        this.actualExpense = new BigDecimal(actualExpense);
    }

    public BigDecimal getActualRemaining() {
        return actualRemaining;
    }

    public void setActualRemaining(BigDecimal actualRemaining) {
        this.actualRemaining = actualRemaining;
    }

    public void setActualRemaining(String actualRemaining) {
        this.actualRemaining = new BigDecimal(actualRemaining);
    }

    public BigDecimal getTotalIncomeRemaining() {
        return totalIncomeRemaining;
    }

    public void setTotalIncomeRemaining(BigDecimal totalIncomeRemaining) {
        this.totalIncomeRemaining = totalIncomeRemaining;
    }

    public void setTotalIncomeRemaining(String totalIncomeRemaining) {
        this.totalIncomeRemaining = new BigDecimal(totalIncomeRemaining);
    }

    public BigDecimal getTotalExpenseRemaining() {
        return totalExpenseRemaining;
    }

    public void setTotalExpenseRemaining(BigDecimal totalExpenseRemaining) {
        this.totalExpenseRemaining = totalExpenseRemaining;
    }

    public void setTotalExpenseRemaining(String totalExpenseRemaining) {
        this.totalExpenseRemaining = new BigDecimal(totalExpenseRemaining);
    }

    public Set<Category> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<Category> categorySet) {
        this.categorySet = categorySet;
    }

    public void addCategories(Category category){
        this.categorySet.add(category);
    }
}
