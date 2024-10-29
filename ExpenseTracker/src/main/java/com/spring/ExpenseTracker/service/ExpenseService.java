package com.spring.ExpenseTracker.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.spring.ExpenseTracker.dao.ExpenseDao;
import com.spring.ExpenseTracker.model.Expense;

public class ExpenseService {
	private ExpenseDao expenseDao;

	public ExpenseDao getExpenseDao() {
		return expenseDao;
	}

	public void setExpenseDao(ExpenseDao expenseDao) {
		this.expenseDao = expenseDao;
	}

	public ExpenseService(ExpenseDao expenseDao) {
		super();
		this.expenseDao = expenseDao;
	}
	
	@Transactional
	public void addExpense(Expense expense)
	{
		expenseDao.addExpense(expense);
	}
	
	@Transactional
	public void updateExpense(Expense expense)
	{
		expenseDao.updateExpense(expense);
	}
	
	@Transactional
	public void deleteExpense(int id)
	{
		expenseDao.deleteExpense(id);
	}
	
	@Transactional
	public Expense getExpenseById(int id)
	{
		return expenseDao.getExpenseById(id);
	}
	
	@Transactional
	public List<Expense> getAllExpenses()
	{
		return expenseDao.getAllExpenses();
	}
	
	@Transactional
	public List<Expense> getExpensesByCategory(String Category)
	{
		return expenseDao.getExpensesByCategory(Category);
	}
	
	@Transactional
	public List<Expense> getExpensesByDateRange(Date startDate, Date endDate)
	{
		return expenseDao.getExpensesByDateRange(startDate, endDate);
	}
	
	@Transactional
	public Double getTotalExpenses()
	{
		return expenseDao.getTotalExpenses();
	}
	
	@Transactional
	public Double getTotalExpensesByCategory(String category)
	{
		return expenseDao.getTotalExpensesByCategory(category);
	}
	
	@Transactional
	public Double getAverageExpenseAmount()
	{
		return expenseDao.getAverageExpenseAmount();
	}
	
	@Transactional
	public Expense getHighestExpense()
	{
		return expenseDao.getHighestExpense();
	}
	
	@Transactional
	public List<Expense> getExpensesGreaterThan(Double amount)
	{
		return expenseDao.getExpensesGreaterThan(amount);
	}	
}
