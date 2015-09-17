package org.jdbcBasic.seminar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.jdbcBasic.seminar.bean.EmployeeBean;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class EmployeeCURD implements EmployeeCURDimpl{
	
	//create employee into database
	
	public boolean createEmployee(){
		boolean b = false ;
		Connection con = null ;
		
		Statement stmt = null ;
			
		int result = 0 ;

		Scanner scCreateEmp = new Scanner(System.in);
		EmployeeBean employeeBean = new EmployeeBean();
		System.out.println("Enter the employee name ");
		String employeeName = scCreateEmp.nextLine();
		employeeBean.setEmployeeName(employeeName);
		System.out.println("Enter the employee address ");
		String employeeAddress = scCreateEmp.nextLine();
		employeeBean.setEmployeeAddress(employeeAddress);
		System.out.println("Enter the employee contact number");
		String employeeContactNum= scCreateEmp.nextLine();
		employeeBean.setEmployeeContactNum(employeeContactNum);
		
		try {
			con = (Connection) ConnectionFactory.getConnection();
			stmt = con.createStatement();
			result = stmt.executeUpdate("insert into employeedetails (employeeName , employeeAddress , employeeContactNum ) values ('"+employeeBean.getEmployeeName()+"','"+employeeBean.getEmployeeAddress()+"','"+employeeBean.getEmployeeAddress()+"')" );
			System.out.println(result);
			System.out.println("Successfully inserted employee!!!");
			b =true;
			con.commit();			
			stmt.close();			
			con.close();
			scCreateEmp.close();			
			
		} catch (SQLException e) {
			//e.printStackTrace();
		}
		
		return b;
	}
	
	//update employee into database by unique ID (Primary key)
	
	public boolean updateEmployee(){
		
		EmployeeBean employeeBean = new EmployeeBean() ;
		
		Scanner scCreateEmp = new Scanner(System.in);
		Scanner scCreateEmp1 = new Scanner(System.in);
		Scanner scCreateEmp2 = new Scanner(System.in);
		Scanner scCreateEmp3 = new Scanner(System.in);
		System.out.println("Enter the employee ID ");
		String employeeID = scCreateEmp.nextLine();
		System.out.println(employeeID);
		
		System.out.println("Enter the employee name ");
		String employeeName = scCreateEmp1.nextLine();
		employeeBean.setEmployeeName(employeeName);
		System.out.println("Enter the employee address ");
		String employeeAddress = scCreateEmp2.nextLine();
		employeeBean.setEmployeeAddress(employeeAddress);
		System.out.println("Enter the employee contact number");
		String employeeContactNum= scCreateEmp3.nextLine();
		employeeBean.setEmployeeContactNum(employeeContactNum);
		
		
		
		boolean b = false;
		
		Connection con = null;
		
		int result = 0 ;
		
		PreparedStatement pstmt = null ;
		
		try {
			
			con = (Connection) ConnectionFactory.getConnection();
			
			pstmt = (PreparedStatement) con.prepareStatement("UPDATE EMPLOYEEDETAILS SET employeeName =? , employeeAddress = ? , employeeContactNum = ? WHERE employeeID = ? ");
			
			pstmt.setString(1, employeeBean.getEmployeeName() );
			
			pstmt.setString(2, employeeBean.getEmployeeAddress());
			
			pstmt.setString(3, employeeBean.getEmployeeContactNum());
			
			pstmt.setString(4, employeeID);
			
			result = pstmt.executeUpdate();
			
			con.commit();
			
			b = true ;
			
			pstmt.close();
			
			con.close();
			
			scCreateEmp.close();
			
			scCreateEmp1.close();
			
			scCreateEmp2.close();
			
			scCreateEmp3.close();
			
			System.out.println("Succesfully updated employee");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return b;
	}
	
	//delete employee from database by primary key
	public boolean deleteEmployee(){
		
		boolean b = false ;
		
		Connection con = null;
		
		PreparedStatement pstmt = null;
		
		Scanner scDelete = new Scanner(System.in);
		System.out.println("Enter the Employee ID to delete ");
		int employeeID = scDelete.nextInt();
		
		try{
			
			con = (Connection) ConnectionFactory.getConnection();
			
			pstmt = (PreparedStatement) con.prepareStatement("DELETE FROM employeedetails where employeeID = ?");
			
			
			pstmt.setLong(1, employeeID);
			
			pstmt.executeUpdate();
			
			con.commit();
			
			pstmt.close();
			
			b = true;
			
			System.out.println("Successfully deleted employee");
			
		}
		catch(SQLException e){
			e.printStackTrace();
			scDelete.close();
		}
		return b;
	}
	
	
	// retrieve all or show all database to user
	
	public boolean retrieveEmployee(){
	boolean b = false ;
		
		Connection con = null;
		
		PreparedStatement pstmt = null;
		
		ResultSet result = null;
		
		try{
			
			con = (Connection) ConnectionFactory.getConnection();
			
			pstmt = (PreparedStatement) con.prepareStatement("SELECT * FROM employeedetails");
			
			result = pstmt.executeQuery();
			
			while(result.next()){
				EmployeeBean employeeBean = new EmployeeBean();
				
				employeeBean.setEmployeeName(result.getString("employeeName"));
				employeeBean.setEmployeeAddress(result.getString("employeeAddress"));				
				employeeBean.setEmployeeContactNum(result.getString("employeeContactNum"));

				System.out.println(employeeBean.getEmployeeName() +"    "+ employeeBean.getEmployeeAddress()+"     "+ employeeBean.getEmployeeContactNum());
			}
			
			
			
			
			System.out.println("Successfully employee shown!!!");
			
		}
		catch(SQLException e){
			e.printStackTrace();
			
		}
		return b;
	}	
	
}	