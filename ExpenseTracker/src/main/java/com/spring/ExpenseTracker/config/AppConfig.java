package com.spring.ExpenseTracker.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.spring.ExpenseTracker.controller.ExpenseController;
import com.spring.ExpenseTracker.dao.ExpenseDao;
import com.spring.ExpenseTracker.dao.ExpenseDaoImpl;
import com.spring.ExpenseTracker.service.ExpenseService;

@Configuration
public class AppConfig {
	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/expense_db");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("abc123");
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate()
	{
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public ExpenseDao expenseDao()
	{
		return new ExpenseDaoImpl(jdbcTemplate());
	}
	
	@Bean
	public ExpenseService expenseService()
	{
		return new ExpenseService(expenseDao());
	}
	
	@Bean
	public ExpenseController expenseController()
	{
		return new ExpenseController(expenseService());
	}
}
