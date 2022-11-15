package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Exceptions.AccNotFoundException;
import Exceptions.InsufficientBalanceException;
import Exceptions.InvalidAgeException;
import Exceptions.InvalidAmountException;

class MainOperation {

	public void AdminOperation() throws InvalidAmountException, AccNotFoundException {
		Scanner sc = new Scanner(System.in);
		AdminOperationImpl adminimpl = new AdminOperationImpl();

		int choice1;
		boolean flag = false;

		flag = adminimpl.login();

		if (flag == true) {
			do {
				System.out.println("Admin operations");
				System.out.println("1. Open Account");
				System.out.println("2. Deposit Amount");
				System.out.println("3. WithDraw Amount");
				System.out.println("4. Delete Account");
				System.out.println("6. show all");
				System.out.println("5. Exit From Operation");

				choice1 = sc.nextInt();

				switch (choice1) {
				case 1:
					try {
						adminimpl.openAccount();
					} catch (InvalidAgeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 2:
					try {
						adminimpl.deposit();
					} catch (InvalidAmountException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (AccNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InsufficientBalanceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 3:
					adminimpl.withdraw();
					break;
				case 4:
					adminimpl.deleteAccount();
					break;
				default:
					System.out.println("Pls choose the correct option");
					break;
				case 5:
					break;
				case 6:
					adminimpl.showAll();
				}

			} while (choice1 != 5);
		} else {
			System.out.println("You are not logged in. Pls login and try again");
		}

	}

	public void CustomerOperation() {

		Scanner sc = new Scanner(System.in);
		CustomerOperationImpl custimpl = new CustomerOperationImpl();

		int choice1;
		boolean flag = false;

		flag = custimpl.login();

		if (flag == true) {
			do {
				System.out.println("Customer operations");
				System.out.println("1. View Account");
				System.out.println("2. View All transasctions");
				System.out.println("3. Transfer Money");
				System.out.println("4. View last 5 transactions");
				System.out.println("5. exit");

				choice1 = sc.nextInt();

				switch (choice1) {
				case 1:
					custimpl.viewAccount();
					break;
				case 2:
					custimpl.allTransaction();
					break;
				case 3:
					custimpl.transferMoney();
					break;
				case 4:
					custimpl.last5Transaction();
					break;
				case 5:
					break;
				default:
					System.out.println("Pls choose the correct option");
				}

			} while (choice1 != 5);
		}

	}

}

public class NeoBankMain {

	public static List<Customer> cust = new ArrayList<Customer>();
	public static Map<Long, LinkedHashSet<Transaction>> transactions = new HashMap<Long, LinkedHashSet<Transaction>>();

	public static void main(String[] args) throws InvalidAmountException, AccNotFoundException {
		Scanner sc = new Scanner(System.in);
		int choice;
		MainOperation m = new MainOperation();
		do {

			System.out.println("Welcome to NeoBank ");
			System.out.println("Select an operation from the list");
			System.out.println("1. Admin");
			System.out.println("2. Customer");
			System.out.println("3. Exit");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				m.AdminOperation();
				break;
			case 2:
				m.CustomerOperation();
				break;
			}

		} while (choice != 3);

	}

}
