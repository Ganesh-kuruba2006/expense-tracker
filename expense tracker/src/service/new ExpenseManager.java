package service;

import model.Expense;
import utils.FileHandler;

import java.util.*;
import java.time.LocalDate;

public class ExpenseManager {
    private List<Expense> expenses;
    private final String filePath = "expenses.csv";

    public ExpenseManager() {
        this.expenses = FileHandler.loadExpenses(filePath);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        FileHandler.saveExpenses(expenses, filePath);
    }

    public void showExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.");
            return;
        }
        expenses.forEach(System.out::println);
    }

    public void showTotalByCategory() {
        Map<String, Double> totals = new HashMap<>();
        for (Expense e : expenses) {
            totals.put(e.getCategory(), totals.getOrDefault(e.getCategory(), 0.0) + e.getAmount());
        }
        totals.forEach((cat, amt) -> System.out.println(cat + ": $" + amt));
    }
}
