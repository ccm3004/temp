package com.ccm.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import com.ccm.dto.BoardBean_ccm;
import com.ccm.dto.BoardRpyBean_ccm;
import com.hongseok.dto.PageBean;


public class BoardDao_ccm {		
	
	DBConnectionMgr pool = DBConnectionMgr.getInstance(); //connection 만들기 db연결
	
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Connection con  =null;
	
	public int insetData(BoardBean_ccm bean){
		int result=0;
	
		String sql = "insert into board values (null, ?,?,?, now())"; //null? now()?
		
		try {
			con=pool.getConnection(); //connection 만들기  - db연결
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getTitle());
			pstmt.setString(3, bean.getContent());			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			pool.freeConnection(con, pstmt);
		}
		
		return result;
	}// end of method
	
	/*public Vector<BoardBean_ccm> getAllData(){
		Vector<BoardBean_ccm> v = new Vector<BoardBean_ccm>();
	
		String sql = "select * from board"; //전체 
		
		try {
			con=pool.getConnection(); //connection 만들기  - db연결
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				BoardBean_ccm bean = new BoardBean_ccm();
				bean.setIdx(rs.getInt(1));
				bean.setId(rs.getString(2));
				bean.setTitle(rs.getString(3));
				bean.setContent(rs.getString(4));
				bean.setRdate(rs.getDate(5));
				v.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return v;		
	}*/
	
	
	public BoardBean_ccm getData(String idx){
		BoardBean_ccm bean = new BoardBean_ccm();
	
		String sql = "select * from board where idx=?";
		
		
		try {
			con=pool.getConnection(); //connection 만들기  - db연결
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(idx));
			rs=pstmt.executeQuery();
			
			if(rs.next()){				
				bean.setIdx(rs.getInt(1));
				bean.setId(rs.getString(2));
				bean.setTitle(rs.getString(3));
				bean.setContent(rs.getString(4));
				bean.setRdate(rs.getDate(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}
	
	/*
	public int insertRpy(String oidx, String id, String rpy){
		int result=0;
		PreparedStatement pstmt = null;
		Connection con = null;
		String sql = "insert into board_re values (null,?,?,?)";
		
		try {
			con=pool.getConnection(); //connection 만들기  - db연결
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, oidx);
			pstmt.setString(2, id);
			pstmt.setString(3, rpy);						
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			pool.freeConnection(con, pstmt);
		}
		
		return result;
	}
	
	
	public Vector<BoardRpyBean_ccm> getRpy(String oidx){
		Vector<BoardRpyBean_ccm> v = new Vector<BoardRpyBean_ccm>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con  =null;
		String sql = "select * from board_re where board_idx=?";
		
		
		try {
			con=pool.getConnection();
			  //connection 만들기  - db연결
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(oidx));
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				BoardRpyBean_ccm bean = new BoardRpyBean_ccm();
				bean.setIdx(rs.getInt(1));
				bean.setOidx(rs.getInt(2));
				bean.setId(rs.getString(3));
				bean.setPry(rs.getString(4));
				v.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v;
	}*/
	//
	public int insertRpy(String oidx, String id, String rpy){
		int result=0;
	
		String sql = "insert into board_rpy values (null,?,?,?,now())";
		
		try {
			con=pool.getConnection(); //connection 만들기  - db연결
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, oidx);
			pstmt.setString(2, id);
			pstmt.setString(3, rpy);	
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			pool.freeConnection(con, pstmt);
		}
		
		return result;
	}
	
	
	public Vector<BoardRpyBean_ccm> getRpy(String oidx){
		Vector<BoardRpyBean_ccm> v = new Vector<BoardRpyBean_ccm>();
		
		String sql = "select * from board_rpy where board_idx=?";
		
		
		try {
			con=pool.getConnection();
			  //connection 만들기  - db연결
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(oidx));
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				BoardRpyBean_ccm bean = new BoardRpyBean_ccm();
				bean.setIdx(rs.getInt(1));
				bean.setOidx(rs.getInt(2));
				bean.setId(rs.getString(3));
				bean.setPry(rs.getString(4));
				bean.setRpydate(rs.getDate(5));
				v.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v;
	}
	
	
	
	int totalpage =0;// 전체페이지 개수
	int pagerow = 10; //한페이지에 보여질 개수
		
	public Vector<BoardBean_ccm> getAllData2(String pageidx){
		Vector<BoardBean_ccm> v = new Vector<BoardBean_ccm>();
		
		int currentPage = (Integer.parseInt(pageidx)-1)*pagerow;
		
		String sql = "select * from board limit ?,?"; //전체 
		
		
		try {
			con=pool.getConnection(); //connection 만들기  - db연결
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, currentPage);
			pstmt.setInt(2, pagerow);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				BoardBean_ccm bean = new BoardBean_ccm();
				bean.setIdx(rs.getInt(1));
				bean.setId(rs.getString(2));
				bean.setTitle(rs.getString(3));
				bean.setContent(rs.getString(4));
				bean.setRdate(rs.getDate(5));
				v.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			pool.freeConnection(con, pstmt, rs);	
		}
		return v;		
	}
	

	public Vector<BoardBean_ccm> getAllData(){
		Vector<BoardBean_ccm> v = new Vector<BoardBean_ccm>();
	
		String sql = "select * from board"; //전체 
		
		try {
			con=pool.getConnection(); //connection 만들기  - db연결
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				BoardBean_ccm bean = new BoardBean_ccm();
				bean.setIdx(rs.getInt(1));
				bean.setId(rs.getString(2));
				bean.setTitle(rs.getString(3));
				bean.setContent(rs.getString(4));
				bean.setRdate(rs.getDate(5));
				v.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return v;		
	}
	
	public int getTotalPage(){
		String query = "select count(idx) from board";
		
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(query);
			rs=pstmt.executeQuery(); //select일때
			
			if(rs.next()){
				totalpage=rs.getInt(1); //rs의 첫번째것을 가져오라
			}
			
			totalpage = (int)Math.ceil(totalpage/(float)pagerow);  //math.ceil 올림하는 함수 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pool.freeConnection(con, pstmt, rs);	
		}
		
		return totalpage;
	}
	
	
	public int deleteRow(String id){
		
		int result  = 0;
		String query = "delete from board where id = ?"; // PreparedStatement의경우 ?로 처리한다. (')이거 안들어간다.
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

	public int deleteidx(String idx){
		
		int result  = 0;
		String query = "delete from board where idx = ?"; // PreparedStatement의경우 ?로 처리한다. (')이거 안들어간다.
		try {
			con=pool.getConnection(); //connection 만들기  - db연결
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, idx); //1번째 넣는 값
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
}// end of class








