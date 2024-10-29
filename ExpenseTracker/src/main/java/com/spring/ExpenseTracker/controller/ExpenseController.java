package com.spring.ExpenseTracker.controller;

import java.util.Date;
import java.util.List;

import com.spring.ExpenseTracker.model.Expense;
import com.spring.ExpenseTracker.service.ExpenseService;

public class ExpenseController {
	private ExpenseService expenseService;

	public ExpenseController(ExpenseService expenseService) {
		super();
		this.expenseService = expenseService;
	}
	
	public String addExpense(Expense expense)
	{
		expenseService.addExpense(expense);
		return "Expense added Successfully";
	}
	
	public String updateExpense(Expense expense)
	{
		expenseService.updateExpense(expense);
		return "Expense Updated Successfully";
	}
	
	public String deleteExpense(int id)
	{
		expenseService.deleteExpense(id);
		return "Expense deleted Successfully";
	}
	
	public Expense getExpenseById(int id)
	{
		return expenseService.getExpenseById(id);
	}
	
	public List<Expense> getAllExpenses()
	{
		return expenseService.getAllExpenses();
	}
	
	public List<Expense> getExpensesByCategory(String category)
	{
		return expenseService.getExpensesByCategory(category);
	}
	
	public List<Expense> getExpensesByDateRange(Date startDate, Date endDate)
	{
		return expenseService.getExpensesByDateRange(startDate, endDate);
	}
	
	public Double getTotalExpenses()
	{
		return expenseService.getTotalExpenses();
	}
	
	public Double getTotalExpensesByCategory(String category)
	{
		return expenseService.getTotalExpensesByCategory(category);
	}
	
	public Double getAverageExpenseAmount()
	{
		return expenseService.getAverageExpenseAmount();
	}
	
	public Expense getHighestExpense()
	{
		return expenseService.getHighestExpense();
	}
	
	public List<Expense> getExpensesGreaterThan(Double amount)
	{
		return expenseService.getExpensesGreaterThan(amount);
	}
}
