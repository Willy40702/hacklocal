package com.mkyong.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mkyong.customer.CustomerDAO;
import com.mkyong.customer.Customer;
import com.sun.java.swing.plaf.windows.resources.windows;

public class JdbcCustomerDAO implements CustomerDAO
{
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insert(Customer customer){
		
		String sql1 = "INSERT INTO users " +
				"(USER_ID, USERNAME, PASSWORD, ENABLED) VALUES (?, ?, ?, ?)";
		
		Connection conn = null;
		
		if(findByCustomerId(customer.getuserId()) == null){
			try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql1);
				ps.setInt(1, customer.getuserId());
				ps.setString(2, customer.getName());
				ps.setString(3, customer.getPassword());
				ps.setInt(4, customer.getEnabled());
				ps.executeUpdate();
				ps.close();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
				
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {}
				}
			}
			String sql2 = "INSERT INTO user_roles " +
					"(USER_ROLE_ID, USER_ID,AUTHORITY) VALUES (?, ?, ?)";
			try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql2);
				ps.setNull(1, 0);
				ps.setInt(2, customer.getuserId());
				ps.setString(3, "ROLE_USER");
				ps.executeUpdate();
				ps.close();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
				
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {}
				}
			}
			
		}
		
	}
	
	public Customer findByCustomerId(int userId){
		
		String sql = "SELECT * FROM users WHERE USER_ID = ?";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			Customer customer = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer(
						rs.getInt("USER_ID"),
						rs.getString("USERNAME"), 
						rs.getString("PASSWORD"),
						rs.getInt("ENABLED")
				);
			}
			rs.close();
			ps.close();
			return customer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
}
