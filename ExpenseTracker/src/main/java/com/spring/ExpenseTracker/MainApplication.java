package com.spring.ExpenseTracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.ExpenseTracker.config.AppConfig;
import com.spring.ExpenseTracker.controller.ExpenseController;
import com.spring.ExpenseTracker.model.Expense;

public class MainApplication {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	private static ExpenseController expenseController;

	public static void main(String[] args) throws NumberFormatException, ParseException {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		expenseController = context.getBean(ExpenseController.class);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		outer: while (true) {
			System.out.println("PRESS 1 to add new Expense");
			System.out.println("PRESS 2 to update Expense");
			System.out.println("PRESS 3 to delete Expense");
			System.out.println("PRESS 4 to get Expense by id");
			System.out.println("PRESS 5 to get All Expenses");
			System.out.println("PRESS 6 to get Expenses by Category");
			System.out.println("PRESS 7 to get Expenses by Date Range");
			System.out.println("PRESS 8 to get Total Expenses");
			System.out.println("PRESS 9 to get Total Expenses By Category");
			System.out.println("PRESS 10 to get Average Expense Amount");
			System.out.println("PRESS 11 to get Highest Expense");
			System.out.println("PRESS 12 to get Expenses Greater Than");
			System.out.println("PRESS 13 to EXIT");

			try {
				String input = reader.readLine();
				switch (input) {
				case ("1"):
					addExpense(reader);
					break;
				case ("2"):
					updateExpense(reader);
					break;
				case ("3"):
					deleteExpense(reader);
					break;
				case ("4"):
					viewExpenseById(reader);
					break;
				case ("5"):
					viewAllExpenses();
					break;
				case ("6"):
					getExpensesByCategory(reader);
					break;
				case ("7"):
					getExpensesByDateRange(reader, reader);
					break;
				case ("8"):
					getTotalExpenses();
					break;
				case ("9"):
					getTotalExpensesByCategory(reader);
					break;
				case ("10"):
					getAverageExpenseAmount();
					break;
				case ("11"):
					getHighestExpense();
					break;
				case ("12"):
					getExpensesGreaterThan(reader);
					break;
				case ("13"):
					System.out.println("Exiting Expense Tracker...");
					break outer;
				default:
					System.out.printf("Invalid Input!! %n Try Again %n");
				}

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static void getExpensesGreaterThan(BufferedReader reader) throws NumberFormatException, IOException {
		System.out.print("Enter the Amount: ");
		Double amount = Double.parseDouble(reader.readLine());
		List<Expense> expenses = expenseController.getExpensesGreaterThan(amount);
		for(Expense expense: expenses)
		{
			System.out.println(expense);
		}
		System.out.println("***************************************************************************");
	}

	private static void getHighestExpense() {
		Expense highestAmountExpense = expenseController.getHighestExpense();
		System.out.println(highestAmountExpense);
		System.out.println("***************************************************************************");
	}

	private static void getAverageExpenseAmount() {
		Double averageExpenseAmount = expenseController.getAverageExpenseAmount();
		System.out.printf("Average Expense Amount: %.2f %n", averageExpenseAmount);
		System.out.println("***************************************************************************");
	}

	private static void getTotalExpensesByCategory(BufferedReader reader) throws IOException {
		System.out.print("Enter Expense Category: ");
		String category = reader.readLine();
		Double totalExpenseByCategory = expenseController.getTotalExpensesByCategory(category);
		System.out.printf("Total Expenses of Category %s: %.2f %n", category, totalExpenseByCategory);
		System.out.println("***************************************************************************");
	}

	private static void getTotalExpenses() {
		Double totalExpenses = expenseController.getTotalExpenses();
		System.out.printf("Total Expenses: %.2f %n", totalExpenses);
		System.out.println("***************************************************************************");
	}

	private static void getExpensesByDateRange(BufferedReader reader, BufferedReader reader2) throws ParseException, IOException {
		System.out.println("Enter Start Date: ");
		Date startDate = dateFormat.parse(reader.readLine());
		
		System.out.println("Enter End Date: ");
		Date endDate = dateFormat.parse(reader.readLine());
		
		List<Expense> expenses = expenseController.getExpensesByDateRange(startDate, endDate);
		for(Expense expense: expenses)
		{
			System.out.println(expense);
		}
		System.out.println("***************************************************************************");
	}

	private static void getExpensesByCategory(BufferedReader reader) throws IOException {
		System.out.print("Enter Expense Category: ");
		String category = reader.readLine();
		List<Expense> expenses = expenseController.getExpensesByCategory(category);
		for(Expense expense: expenses)
		{
			System.out.println(expense);
		}
		System.out.println("***************************************************************************");
	}

	private static void viewAllExpenses() {
		System.out.println("Generating Expenses...");
		List<Expense> expenses = expenseController.getAllExpenses();
		for(Expense expense: expenses)
		{
			System.out.println(expense);
		}
		System.out.println("***************************************************************************");
	}

	private static void viewExpenseById(BufferedReader reader) throws NumberFormatException, IOException {
		System.out.print("Enter Expense ID: ");
		int id = Integer.parseInt(reader.readLine());
		Expense expense = expenseController.getExpenseById(id);
		System.out.println(expense);
		System.out.println("***************************************************************************");
	}

	private static void deleteExpense(BufferedReader reader) throws NumberFormatException, IOException {
		System.out.print("Enter Expense ID to delete: ");
		int id = Integer.parseInt(reader.readLine());
		
		String result = expenseController.deleteExpense(id);
		System.out.println(result);
		System.out.println("***************************************************************************");
	}

	private static void updateExpense(BufferedReader reader) throws IOException, ParseException, NumberFormatException {
		System.out.print("Enter Expense ID to update: ");
		int id = Integer.parseInt(reader.readLine());

		System.out.print("Enter new Description: ");
		String description = reader.readLine();
		
		System.out.print("Enter new amount: ");
		double amount = Double.parseDouble(reader.readLine());

		System.out.print("Enter new category: ");
		String category = reader.readLine();

		System.out.print("Enter new date (dd-MM-yyyy): ");
		Date date = dateFormat.parse(reader.readLine());
		
		String result = expenseController.updateExpense(new Expense(id, description, amount, category, date));
		System.out.println(result);
		System.out.println("***************************************************************************");
	}

	private static void addExpense(BufferedReader reader) throws IOException, ParseException, NumberFormatException {
			System.out.print("Enter id: ");
			int id = Integer.parseInt(reader.readLine());
			System.out.print("Enter Description of Expense: ");
			String description = reader.readLine();
			System.out.print("Enter amount: ");
			double amount = Double.parseDouble(reader.readLine());
			System.out.print("Enter Category of the Expense: ");
			String category = reader.readLine();
			System.out.print("Enter date (dd-MM-yyyy): ");
			Date date = dateFormat.parse(reader.readLine());

			String result = expenseController.addExpense(new Expense(id, description, amount, category, date));
			System.out.println(result);
			System.out.println("***************************************************************************");
	}
}
