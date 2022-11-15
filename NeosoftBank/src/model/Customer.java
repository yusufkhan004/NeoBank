package model;

import java.math.BigDecimal;
import java.util.Date;

public class Customer {
	private String name;
	private long accNo;
	private BigDecimal balance;
	private int age;
	private Date date;

	public Customer() {
		super();
	}

	public Customer(String name, long accNo, BigDecimal balance, int age, Date date) {
		super();
		this.name = name;
		this.accNo = accNo;
		this.balance = balance;
		this.age = age;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", accNo=" + accNo + ", balance=" + balance + ", age=" + age + ", date="
				+ date + "]";
	}

}
