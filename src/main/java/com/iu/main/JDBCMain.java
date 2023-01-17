package com.iu.main;

import com.iu.main.util.DBConnection;

public class JDBCMain {

	public static void main(String[] args) {
		System.out.println("start");
		DBConnection con = new DBConnection();
		
		//참조변수명.멤버들
		try {
			con.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("finish");
	}

}
