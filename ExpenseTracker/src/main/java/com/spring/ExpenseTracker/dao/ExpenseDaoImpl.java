package com.spring.ExpenseTracker.dao;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.ExpenseTracker.model.Expense;

public class ExpenseDaoImpl implements ExpenseDao{
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	public ExpenseDaoImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addExpense(Expense expense) {
		String query = "insert into expenses(id, description, amount, category, date) values(?,?,?,?,?)";
		jdbcTemplate.update(query, expense.getId(), expense.getDescription(), expense.getAmount(), expense.getCategory(), expense.getDate());
	}

	@Override
	public void updateExpense(Expense expense) {
		String query = "update expenses set description = ?, amount = ?, category = ?, date = ? where id = ?";
		jdbcTemplate.update(query, expense.getDescription(), expense.getAmount(), expense.getCategory(), expense.getDate(), expense.getId());
	}

	@Override
	public void deleteExpense(int id) {
		String query = "delete from expenses where id = ?";
		jdbcTemplate.update(query, id);
	}

	@Override
	public Expense getExpenseById(int id) {
		String query = "select * from expenses where id = ?";
		RowMapper<Expense> rowMapper = new RowMapperImpl();
		Expense expense = jdbcTemplate.queryForObject(query, rowMapper, id);
		return expense;
	}

	@Override
	public List<Expense> getAllExpenses() {
		String query = "select * from expenses";
		List<Expense> expenses = jdbcTemplate.query(query, new RowMapperImpl());
		return expenses;
	}

	@Override
	public List<Expense> getExpensesByCategory(String category) {
		String query = "select * from expenses where categoy = ?";
		List<Expense> expenses = jdbcTemplate.query(query, new RowMapperImpl(), category);
		return expenses;
	}

	@Override
	public List<Expense> getExpensesByDateRange(Date startDate, Date endDate) {
		String query = "select * from expenses where date between ? and ?";
		List<Expense> expenses = jdbcTemplate.query(query, new RowMapperImpl(), startDate, endDate);
		return expenses;
	}

	@Override
	public Double getTotalExpenses() {
		String query = "select sum(amount) from expenses";
		return jdbcTemplate.queryForObject(query, Double.class);
	}

	@Override
	public Double getTotalExpensesByCategory(String category) {
		String query = "select sum(amount) from expenses where category = ?";
		return jdbcTemplate.queryForObject(query, Double.class, category);
	}

	@Override
	public Double getAverageExpenseAmount() {
		String query = "select avg(amount) from expenses";
		return jdbcTemplate.queryForObject(query, Double.class);
	}

	@Override
	public Expense getHighestExpense() {
		String query = "select * from expenses order by amount desc limit 1";
		return (Expense) jdbcTemplate.queryForObject(query, new RowMapperImpl());
	}

	@Override
	public List<Expense> getExpensesGreaterThan(Double amount) {
		String query = "select * from expenses where amount > ?";
		List<Expense> expenses = jdbcTemplate.query(query, new RowMapperImpl(), amount);
		return expenses;
	}

}
