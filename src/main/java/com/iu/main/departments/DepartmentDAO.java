package com.iu.main.departments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.iu.main.employees.EmployeeDTO;
import com.iu.main.util.DBConnection;

public class DepartmentDAO {
	
	public void getInfos()throws Exception{
		
		Connection con = DBConnection.getConnection();
		
		String sql = "SELECT E.FIRST_NAME, D.DEPARTMENT_NAME "
				+ "FROM EMPLOYEES E "
				+ "INNER JOIN DEPARTMENTS D "
				+ "ON (E.DEPARTMENT_ID = D.DEPARTMENT_ID "
				+ "WHERE D.DEPARTMENT_ID=30";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setEmployeeDTOs(new ArrayList<EmployeeDTO>());
		while (rs.next()) {
			if(departmentDTO.getDepartment_name() == null) {
				departmentDTO.setDepartment_name(rs.getString("DEPARTMENT_NAME"));
			}
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setFirst_name(rs.getString("FIRST_NAME"));
			departmentDTO.getEmployeeDTOs().add(employeeDTO);
			
		}
		
		
		
	}
	
	//join
	public DepartmentDTO getInfo()throws Exception{
		DepartmentDTO departmentDTO = null;
		Connection con = DBConnection.getConnection();
		
		String sql="SELECT E.FIRST_NAME, D.DEPARTMENT_NAME "
				+ "FROM EMPLOYEES E "
				+ "INNER JOIN "
				+ "DEPARTMENTS D "
				+ "ON(E.DEPARTMENT_ID=D.DEPARTMENT_ID) "
				+ "WHERE E.EMPLOYEE_ID = 100";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			departmentDTO = new DepartmentDTO();
			departmentDTO.setEmployeeDTOs(new ArrayList<EmployeeDTO>());
			departmentDTO.setDepartment_name(rs.getString("DEPARTMENT_NAME"));
			departmentDTO.getEmployeeDTOs().get(0).setFirst_name(rs.getString("FIRST_NAME"));
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setFirst_name(rs.getString("FIRST_NAME"));
			departmentDTO.getEmployeeDTOs().add(employeeDTO);	
		}
		
		return departmentDTO;
		
	}
	
	
	//update
	public int updateData(DepartmentDTO departmentDTO)throws Exception{
		Connection con = DBConnection.getConnection();
		
		String sql = "UPDATE DEPARTMENTS SET DEPARTMENT_NAME=?, MANAGER_ID=?, LOCATION_ID=? "
				+ "WHERE DEPARTMENT_ID=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, departmentDTO.getDepartment_name());
		st.setInt(2, departmentDTO.getManager_id());
		st.setInt(3, departmentDTO.getLocation_id());
		st.setInt(4, departmentDTO.getDepartment_id());
		
		int result = st.executeUpdate();
		
		DBConnection.disConnect(st, con);
		
		return result;
	}
	
	//delete
	public int deleteData(DepartmentDTO departmentDTO)throws Exception{
		Connection connection = DBConnection.getConnection();
		
		String sql = "DELETE DEPARTMENTS WHERE DEPARTMENT_ID=?";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		st.setInt(1, departmentDTO.getDepartment_id());
		
		int result = st.executeUpdate();
		
		DBConnection.disConnect(st, connection);
		
		return result;
		
	}
	
	//insert
	public int setData(DepartmentDTO departmentDTO) throws Exception {
		Connection con = DBConnection.getConnection();
		
		String sql ="INSERT INTO DEPARTMENTS (DEPARTMENT_ID, DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)"
				+ " VALUES (DEPARTMENTS_SEQ.NEXTVAL, ?, ?, ?)";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, departmentDTO.getDepartment_name());
		st.setInt(2, departmentDTO.getManager_id());
		st.setInt(3, departmentDTO.getLocation_id());
		
		int result = st.executeUpdate();
		
		DBConnection.disConnect(st, con);
		
		return result;
		
	} 
	
	public DepartmentDTO getDetail(int department_id) throws Exception {
		
		DepartmentDTO departmentDTO=null;
		
		Connection connection = DBConnection.getConnection();
		
		String sql="SELECT * FROM DEPARTMENTS WHERE DEPARTMENT_ID=?";
		PreparedStatement st = connection.prepareStatement(sql);
		
		st.setInt(1, department_id);
		
		ResultSet rs = st.executeQuery();
		//
		if(rs.next()) {
			departmentDTO = new DepartmentDTO();
			departmentDTO.setDepartment_id(rs.getInt("DEPARTMENT_ID"));
			departmentDTO.setDepartment_name(rs.getString("DEPARTMENT_NAME"));
			departmentDTO.setManager_id(rs.getInt("MANAGER_ID"));
			departmentDTO.setLocation_id(rs.getInt("LOCATION_ID"));
		}
		
		DBConnection.disConnect(rs, st, connection);
		
		return departmentDTO;
		
	}
	
	public ArrayList<DepartmentDTO> getList() throws Exception {
		ArrayList<DepartmentDTO> ar = new ArrayList<DepartmentDTO>();

		//DBConnection dbConnection=new DBConnection();
		Connection connection = DBConnection.getConnection();
	
		
		//3. Query??? ??????
		String sql = "SELECT * FROM DEPARTMENTS";
		
		//4. Query??? ?????? ??????
		PreparedStatement st = connection.prepareStatement(sql);
		
		
		//5. ? ??????
		
		//6. ?????? ?????? ??? ?????? ??????
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			DepartmentDTO departmentDTO = new DepartmentDTO();
			departmentDTO.setDepartment_id(rs.getInt("DEPARTMENT_ID"));
			departmentDTO.setDepartment_name(rs.getString("DEPARTMENT_NAME"));
			departmentDTO.setManager_id(rs.getInt("MANAGER_ID"));
			departmentDTO.setLocation_id(rs.getInt("LOCATION_ID"));
			ar.add(departmentDTO);
			
		}
		
		//7. ?????? ??????
		DBConnection.disConnect(rs, st, connection);
		
		return ar;
	}

}
