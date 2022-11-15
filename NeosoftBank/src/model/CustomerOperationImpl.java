package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerOperationImpl implements CustomerOperation {
	List<Customer> data = NeoBankMain.cust;
	Map<Long, LinkedHashSet<Transaction>> trans = NeoBankMain.transactions;
	long currentAccountNumber;

	@Override
	public boolean login() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter your name");
		String name = sc.nextLine();
		System.out.println("Enter the account number");
		long acc = sc.nextLong();

		ListIterator<Customer> itr = data.listIterator();

		while (itr.hasNext()) {

			Customer c = itr.next();
//			System.out.println("----------------------------");
//			System.out.println(name.equals(c.getName()));
//			System.out.println(acc == c.getAccNo());
			if (name.equals(c.getName()) && (acc == c.getAccNo())) {
				currentAccountNumber = c.getAccNo();
//				System.out.println(currentAccountNumber);
				return true;
			}
		}
		System.out.println("bad Credentials");
		return false;
	}

	@Override
	public void viewAccount() {
		Iterator<Customer> itr = data.iterator();
		while (itr.hasNext()) {
			Customer c = itr.next();
			if (c.getAccNo() == currentAccountNumber) {
				System.out.println("Name :" + c.getName());
				System.out.println("Age : " + c.getAge());
				System.out.println("Account number : " + c.getAccNo());
				System.out.println("Balance : " + c.getBalance());
			}
		}

	}

	@Override
	public void allTransaction() {
		Iterator<Entry<Long, LinkedHashSet<Transaction>>> itr = trans.entrySet().iterator();

		while (itr.hasNext()) {
			Entry<Long, LinkedHashSet<Transaction>> tr = itr.next();
			if (tr.getKey() == currentAccountNumber) {
				System.out.println("Key : " + tr.getKey() + " and values : " + tr.getValue());
			}
		}
	}

	@Override
	public void transferMoney() {

		Scanner sc = new Scanner(System.in);
		BigDecimal amount;
		long receiverAccountNumber;
		boolean found = false;

		Customer cust = new Customer();

		System.out.println("Enter the account number of the receiver");
		receiverAccountNumber = sc.nextLong();

		ListIterator<Customer> itr = data.listIterator();

		while (itr.hasNext()) {
			Customer c = itr.next();
			if (c.getAccNo() == receiverAccountNumber) {
				found = true;
				break;
			}
		}

		if (found) {
			ListIterator<Customer> itr1 = data.listIterator();
			while (itr1.hasNext()) {
				Customer c = itr1.next();
				if (c.getAccNo() == receiverAccountNumber) {

					System.out.println("Enter the amount to transfer");
					amount = sc.nextBigDecimal();

					int value = data.indexOf(c);

					BigDecimal total = c.getBalance().add(amount);

					System.out.println("total amount =" + total);
					c.setBalance(total);
					System.out.println("c value = " + c);
					data.set(value, c);

					ListIterator<Customer> itr2 = data.listIterator();
					while (itr2.hasNext()) {
						Customer c1 = itr2.next();
						if (c1.getAccNo() == currentAccountNumber) {
//							
//							System.out.println("Enter the amount to withDraw");
//							amount = sc.nextBigDecimal();

							int value1 = data.indexOf(c1);

							System.out.println(c1.getBalance());
							System.out.println(amount);
							BigDecimal total1 = c1.getBalance().subtract(amount);

							System.out.println("total amount =" + total1);
							c1.setBalance(total1);
							System.out.println("c value = " + c1);
							data.set(value1, c1);
							System.out.println(data);
							break;
						}
					}

					System.out.println(data);
					break;
				}
			}

		} else {
//			Account notfoundExcption
			System.out.println("No account is present on this account number");
		}

	}

	@Override
	public void last5Transaction() {
		Iterator<Map.Entry<Long, LinkedHashSet<Transaction>>> itr = trans.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<Long, LinkedHashSet<Transaction>> tr = itr.next();
			if (tr.getKey() == currentAccountNumber) {
				
				List<Transaction> listTr = new ArrayList<Transaction>(tr.getValue());
				
				List<Transaction> myLast5Transaction = listTr.subList(listTr.size()>5?listTr.size()-5:0, listTr.size());
				myLast5Transaction.forEach(t ->{ 
					System.out.println("|transactionType = " + t.getTransactionType() + " | transactionAmount = "
							+ t.getTransactionAmount() + " | transactionDate = " + t.getDate() + " | Balance = "
							+ t.getBalance() + " |");
					
					});
			
				
			}
		}
	}

}
