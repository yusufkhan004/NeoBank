package model;


import Exceptions.AccNotFoundException;
import Exceptions.InsufficientBalanceException;
import Exceptions.InvalidAgeException;
import Exceptions.InvalidAmountException;

public interface AdminOperation {

	public String username = "Yusuf@123";
	public String password = "yusuf123";

	public void openAccount() throws InvalidAgeException;

	public void deposit() throws InvalidAmountException,AccNotFoundException, InsufficientBalanceException;

	public void withdraw() throws AccNotFoundException;

	public void deleteAccount() throws AccNotFoundException;

}
