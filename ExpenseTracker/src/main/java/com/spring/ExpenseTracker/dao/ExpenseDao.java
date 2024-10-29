package com.spring.ExpenseTracker.dao;

import java.util.Date;
import java.util.List;

import com.spring.ExpenseTracker.model.Expense;

public interface ExpenseDao {
	void addExpense(Expense expense);
	void updateExpense(Expense expense);
	void deleteExpense(int id);
	Expense getExpenseById(int id);
	List<Expense> getAllExpenses();
	List<Expense> getExpensesByCategory(String category);
	List<Expense> getExpensesByDateRange(Date startDate, Date endDate);
	Double getTotalExpenses();
	Double getTotalExpensesByCategory(String category);
	Double getAverageExpenseAmount();
	Expense getHighestExpense();
	List<Expense> getExpensesGreaterThan(Double amount);
	
}
