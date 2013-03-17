package com.mkyong.customer;

import com.mkyong.customer.Customer;

public interface CustomerDAO 
{
	public void insert(Customer customer);
	public Customer findByCustomerId(int custId);
}