package dev.dljsr.budget.app.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String incomeExpense;
    private BigDecimal budgeted;
    private BigDecimal actual;
    private BigDecimal remaining;
    @ManyToOne
    private Budget budget;
    @OneToMany(mappedBy = "category")
    private Set<Subcategory> subcategorySet;

    public Category(){
        this.budgeted = new BigDecimal("0.00");
        this.actual = new BigDecimal("0.00");
        this.remaining = new BigDecimal("0.00");
        this.subcategorySet = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIncomeExpense() {
        return incomeExpense;
    }

    public void setIncomeExpense(String incomeExpense) {
        this.incomeExpense = incomeExpense;
    }

    public BigDecimal getBudgeted() {
        return budgeted;
    }

    public void setBudgeted(BigDecimal budgeted) {
        this.budgeted = budgeted;
    }

    public void setBudgeted(String budgeted) {
        this.budgeted = new BigDecimal(budgeted);
    }

    public BigDecimal getActual() {
        return actual;
    }

    public void setActual(BigDecimal actual) {
        this.actual = actual;
    }

    public void setActual(String actual) {
        this.actual = new BigDecimal(actual);
    }

    public BigDecimal getRemaining() {
        return remaining;
    }

    public void setRemaining(BigDecimal remaining) {
        this.remaining = remaining;
    }

    public void setRemaining(String remaining) {
        this.remaining = new BigDecimal(remaining);
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public Set<Subcategory> getSubcategorySet() {
        return subcategorySet;
    }

    public void setSubcategorySet(Set<Subcategory> subcategorySet) {
        this.subcategorySet = subcategorySet;
    }

    public void addSubcategory(Subcategory subcategory){
        this.subcategorySet.add(subcategory);
    }
}
