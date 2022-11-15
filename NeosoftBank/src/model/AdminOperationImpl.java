package model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import Exceptions.AccNotFoundException;
import Exceptions.InsufficientBalanceException;
import Exceptions.InvalidAgeException;
import Exceptions.InvalidAmountException;

public class AdminOperationImpl implements AdminOperation {

	private static long accntNo = 1000000000l;
	List<Customer> data = NeoBankMain.cust;
	Map<Long, LinkedHashSet<Transaction>> trans = NeoBankMain.transactions;
	LinkedHashSet<Transaction> transactions = new LinkedHashSet<Transaction>();

	public boolean login() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the username");
		String username = sc.nextLine();
		System.out.println("Enter the password");
		String password = sc.nextLine();

		if (username.equals(AdminOperation.username) && password.equals(AdminOperation.password)) {
			System.out.println("successfully logged In");
			return true;
		} else {
			System.out.println("bad Credentials");
			return false;
		}
	}

	void transactionRecord(long accountNumber, Transaction t) {

		transactions.add(t);
		trans.put(accountNumber, transactions);

		Iterator<Entry<Long, LinkedHashSet<Transaction>>> itr = trans.entrySet().iterator();

		while (itr.hasNext()) {
			Entry<Long, LinkedHashSet<Transaction>> tr = itr.next();
			System.out.println("Key : " + tr.getKey() + " and values : " + tr.getValue());
		}

	}

	@Override
	public void deposit() throws InvalidAmountException, AccNotFoundException, InsufficientBalanceException {
		Scanner sc = new Scanner(System.in);
		BigDecimal amount;
		long accountNumber;
		boolean found = false;

		Customer cust = new Customer();

		System.out.println("Enter the account number of the customer");
		accountNumber = sc.nextLong();

		ListIterator<Customer> itr = data.listIterator();

		while (itr.hasNext()) {
			Customer c = itr.next();
			if (c.getAccNo() == accountNumber) {
				found = true;
				break;
			}
		}

		if (found) {
			ListIterator<Customer> itr1 = data.listIterator();
			while (itr1.hasNext()) {
				Customer c = itr1.next();
				if (c.getAccNo() == accountNumber) {

					System.out.println("Enter the amount to deposit");
					
					amount = sc.nextBigDecimal();
					if(amount.compareTo(c.getBalance()) == 1) {
						throw new InsufficientBalanceException("Sorry! you do not have enough balance");
					}
					if (amount.compareTo(BigDecimal.ZERO) < 0) {
						throw new InvalidAmountException("Invalid Deposit amount");
					}

					int value = data.indexOf(c);

					BigDecimal total = c.getBalance().add(amount);

					System.out.println("total amount =" + total);
					c.setBalance(total);
					System.out.println("c value = " + c);
					data.set(value, c);
					System.out.println(data);

					Transaction t = new Transaction();
					t.setTransactionType("Deposit");
					t.setTransactionAmount(amount);
					t.setDate(new Date());
					t.setBalance(total);

					transactionRecord(accountNumber, t);

					break;
				}
			}

		} else {

			throw new AccNotFoundException("Account Not Found");

		}
	}

	@Override
	public void withdraw() throws AccNotFoundException {

		Scanner sc = new Scanner(System.in);
		BigDecimal amount;
		long accountNumber;
		boolean found = false;

		Customer cust = new Customer();

		System.out.println("Enter the account number of the customer");
		accountNumber = sc.nextLong();

		ListIterator<Customer> itr = data.listIterator();

		while (itr.hasNext()) {
			Customer c = itr.next();
			if (c.getAccNo() == accountNumber) {
				found = true;
				break;
			}
		}

		if (found) {
			ListIterator<Customer> itr1 = data.listIterator();
			while (itr1.hasNext()) {
				Customer c = itr1.next();
				if (c.getAccNo() == accountNumber) {

					System.out.println("Enter the amount to withDraw");
					amount = sc.nextBigDecimal();

					int value = data.indexOf(c);

					BigDecimal total = c.getBalance().subtract(amount);

					System.out.println("total amount =" + total);
					c.setBalance(total);
					System.out.println("c value = " + c);
					data.set(value, c);
					System.out.println(data);

					Transaction t = new Transaction();
					t.setTransactionType("withdraw");
					t.setTransactionAmount(amount);
					t.setDate(new Date());
					t.setBalance(total);

					transactionRecord(accountNumber, t);

					break;
				}
			}

		} else {
			throw new AccNotFoundException("Sorry, we couldn't find any account based on this account number, Provide correct account number and Try Again");
			
		}

	}

	@Override
	public void deleteAccount() throws AccNotFoundException {
		Scanner sc = new Scanner(System.in);
		long accountNumber;
		boolean found = false;

		Customer cust = new Customer();

		System.out.println("Enter the account number to be deleted");
		accountNumber = sc.nextLong();

		ListIterator<Customer> itr = data.listIterator();

		while (itr.hasNext()) {
			Customer c = itr.next();
			if (c.getAccNo() == accountNumber) {
				found = true;
				break;
			}
		}

		if (found) {
			ListIterator<Customer> itr1 = data.listIterator();
			while (itr1.hasNext()) {
				Customer c = itr1.next();
				if (c.getAccNo() == accountNumber) {

					int value = data.indexOf(c);
					data.remove(value);
					System.out.println("We are sad to see you go :-(, your account is deleted succesfully");
					break;
				}
			}

		} else {
//			Account notfoundExcption
			throw new AccNotFoundException("We already do not have any accounts based on this account number");
//			System.out.println("We already do not have any accounts based on this account number");
		}
	}

	@Override
	public void openAccount() throws InvalidAgeException {

		int age;
		String name = null;
		long accountNumber = 0;
		BigDecimal balance = null;

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the Age");
		age = sc.nextInt();

		if (age < 18) {
			throw new InvalidAgeException("Age must be 18 or greater than 18");
//			System.out.println("Age should be 18 or greater then 18");
		} else {
			System.out.println("Enter the Name");
			name = sc.next();

//			System.out.println("Enter the account number");
//			accntNo = sc.nextLong();
			accountNumber = ++accntNo;

			System.out.println("Enter the balance amount");
			balance = sc.nextBigDecimal();

		}

		Customer cust = new Customer();
		cust.setName(name);
		cust.setAccNo(accountNumber);
		cust.setBalance(balance);
		cust.setAge(age);
		cust.setDate(new Date());

		data.add(cust);
		System.out.println(
				" _________________________________________________________________________________________________");
		System.out.println("|Congratulations! You have successfully registed your account. Your Account number is : "
				+ accountNumber + "|");
		System.out.println(
				"|_________________________________________________________________________________________________|");

		System.out.println("Data in list");
		System.out.println("_________________________________________________");
		data.forEach(obj -> System.out.println(obj.getName() + " | " + obj.getAge() + " | " + obj.getAccNo() + " | "
				+ obj.getBalance() + " | " + obj.getDate()));
	}

	void showAll() {
		System.out.println("Data in list");
		System.out.println("_________________________________________________");
		data.forEach(obj -> System.out.println(obj.getName() + " | " + obj.getAge() + " | " + obj.getAccNo() + " | "
				+ obj.getBalance() + " | " + obj.getDate()));
	}

}
