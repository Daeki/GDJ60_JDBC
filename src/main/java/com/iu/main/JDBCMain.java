package com.iu.main;

import com.iu.main.departments.DepartmentController;
import com.iu.main.departments.DepartmentDAO;
import com.iu.main.departments.DepartmentDTO;
import com.iu.main.locations.LocationController;
import com.iu.main.locations.LocationDAO;
import com.iu.main.util.DBConnection;

public class JDBCMain {

	public static void main(String[] args) {
		System.out.println("start");
		DepartmentController dc = new DepartmentController();
		LocationController lc = new LocationController();
		DepartmentDAO departmentDAO = new DepartmentDAO();
	
		try {
//			lc.start();
//			dc.start();
			DepartmentDTO departmentDTO = new DepartmentDTO();
			departmentDTO.setDepartment_id(280);
	
			int result = departmentDAO.deleteData(departmentDTO);
			if(result>0) {
				System.out.println("성공");
			}else {
				System.out.println("실패");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("finish");
	}

}
