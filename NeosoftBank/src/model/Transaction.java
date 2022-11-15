package model;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
	private String transactionType;
	private BigDecimal transactionAmount;
	private Date date;
	private BigDecimal balance;

	public Transaction() {
		super();
	}

	public Transaction(String transactionType, BigDecimal transactionAmount, Date date, BigDecimal balance) {
		super();
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.date = date;
		this.balance = balance;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Transaction [transactionType=" + transactionType + ", transactionAmount=" + transactionAmount
				+ ", date=" + date + ", balance=" + balance + "]";
	}

}
