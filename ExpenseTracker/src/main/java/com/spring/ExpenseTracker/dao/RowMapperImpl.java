package com.spring.ExpenseTracker.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.ExpenseTracker.model.Expense;

public class RowMapperImpl implements RowMapper{

	@Override
	public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
		Expense expense = new Expense();
		expense.setId(rs.getInt(1));
		expense.setDescription(rs.getString(2));
		expense.setAmount(rs.getDouble(3));
		expense.setCategory(rs.getString(4));
		expense.setDate(rs.getDate(5));
		return expense;
	}

}
