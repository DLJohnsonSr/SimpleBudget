package dev.dljsr.budget.app.models;

import dev.dljsr.budget.app.models.Category;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private BigDecimal budgeted;
    private BigDecimal actual;
    private BigDecimal remaining;
    @ManyToOne
    private Category category;

    public Subcategory() {
        this.budgeted = new BigDecimal("0.00");
        this.actual = new BigDecimal("0.00");
        this.remaining = new BigDecimal("0.00");
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

    public void setRemaining(String remaining){
        this.remaining = new BigDecimal(remaining);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
