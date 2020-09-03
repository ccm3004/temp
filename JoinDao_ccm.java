package com.ccm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.ccm.dto.JoinBean_ccm;
import com.hongseok.dto.JoinBean;
import com.hongseok.dto.bookBean;

import org.apache.tomcat.jni.Poll;

public class JoinDao_ccm {
	
	DBConnectionMgr pool = DBConnectionMgr.getInstance(); //connection 만들기 db연결
	
	Connection con= null;
	PreparedStatement pstmt = null;
	ResultSet rs =null;
	
	public int insertData(JoinBean_ccm bean) {
		int result = 0;
		String sql = "insert into memberlist values(null,?,?,?,?,default)";
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getPw());
			pstmt.setString(3, bean.getName());
			pstmt.setString(4, bean.getEmail());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			pool.freeConnection(con,pstmt);
		}
		
		return result;
	}
	
	public Vector<JoinBean_ccm> selectAllData(){
		Vector<JoinBean_ccm> v = new Vector<JoinBean_ccm>();
		
		String sql = "select * from memberlist";
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				JoinBean_ccm bean = new JoinBean_ccm();
				bean.setIdx(rs.getInt(1));
				bean.setId(rs.getString(2));
				bean.setPw(rs.getString(3));
				bean.setName(rs.getString(4));
				bean.setEmail(rs.getString(5));
				bean.setLevel(rs.getString(6));
				v.add(bean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			pool.freeConnection(con, pstmt, rs);
		}		
		return v;
	}
	
	public String getMemberLv(String id) {
		String result = null;
		String sql = "select level from memberlist where id=?";
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				result= rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			pool.freeConnection(con,pstmt,rs);
		}
		
		return result;
	}
	public boolean login(String id, String pw){
		boolean result = false;
		String sql = "select * from memberlist where id=? and pw=?";
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				result =true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pool.freeConnection(con, pstmt, rs); 
		}
		return result;
	}
	
	public int updateJoinLv(String id, String lv){
		int result=0;
		
		String sql = "update memberlist set level=? where id=? ";
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lv);
			pstmt.setString(2, id);
		
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return result;
	}
	
	
	public JoinBean_ccm getMember(String id){
		JoinBean_ccm bean = new JoinBean_ccm();
		String query = "select * from memberlist where id=?";
				
		try{
			con=pool.getConnection(); //connection 만들기  - db연결
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				bean.setIdx(rs.getInt(1));
				bean.setId(rs.getString(2));
				bean.setPw(rs.getString(3));
				bean.setName(rs.getString(4));
				bean.setEmail(rs.getString(5));

			}
		
		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
	}
	
	public int deleteRow(String id){

		
		int result  = 0;

		String query = "delete from memberlist where id = ?"; // PreparedStatement의경우 ?로 처리한다. (')이거 안들어간다.
		
		try {
			con=pool.getConnection(); //connection 만들기  - db연결
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, id); //1번째 넣는 값
			 //pstmt.setString(2, pw); //2번째 넣는 값
		
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			pool.freeConnection(con, pstmt);
		}
		return result;
	
	}
	
	public int updataProc(String id, String pw, String name, String email){
	
		int result  = 0;
		
		String query = "update memberlist set pw = ? , name = ?, email =? where id=?";
		try {
			con=pool.getConnection(); //connection 만들기  - db연결
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, pw); //2번째 넣는 값
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			pool.freeConnection(con, pstmt);
		}
		return result;
	}
	
	
	public boolean isExistId(String id){
		boolean result=false;
		String sql = "select idx from memberlist where id=?";
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return result;
	}	
}
