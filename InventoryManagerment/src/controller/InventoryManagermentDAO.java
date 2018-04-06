package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.print.attribute.standard.PrinterIsAcceptingJobs;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.InventoryManagermentVO;
import model.SellProductVO;

public class InventoryManagermentDAO {
	// 재고관리 등록창
	public InventoryManagermentVO getInventoryManagermentregiste(InventoryManagermentVO ivo) throws Exception {

		String dml = "insert into  inventory " + " (IM_CODE, im_tracenumber, im_productname, im_kg, im_date )"
				+ "values " + "(inventory_seq.nextval, ?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement pstmt = null;
		InventoryManagermentVO retval = null;

		try {

			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(dml);

			pstmt.setInt(1, ivo.getIm_tracenumber());
			pstmt.setString(2, ivo.getIm_productname());
			pstmt.setInt(3, ivo.getIm_kg());
			pstmt.setString(4, ivo.getIm_date());

			int i = pstmt.executeUpdate();
			retval = new InventoryManagermentVO();
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
			System.out.println(8897);
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
			System.out.println(9977);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}
		return retval;
	}

	public ArrayList<InventoryManagermentVO> getInventorymanagermentTotal() {
		ArrayList<InventoryManagermentVO> list = new ArrayList<InventoryManagermentVO>();
		String tml = "select * from inventory";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		InventoryManagermentVO emvo = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(tml);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				emvo = new InventoryManagermentVO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4),
						rs.getString(5));
				list.add(emvo);
			}
		} catch (SQLException se) {
			System.out.println(se);
			System.out.println(123);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {

			}

		}
		return list;
	}

	public ArrayList<String> getColunmnNameinven() {
		ArrayList<String> columnNameinven = new ArrayList<String>();

		String sql = "select * from inventory";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();

			for (int i = 1; i <= cols; i++) {
				columnNameinven.add(rsmd.getColumnName(i));
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {

			}
		}
		return columnNameinven;
	}

	// 재고관리 검색
	public InventoryManagermentVO getInventoryCheack(String im_productname) throws Exception {
		String dml = "select * from inventory where im_productname = ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		InventoryManagermentVO retval = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, im_productname);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				retval = new InventoryManagermentVO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4),
						rs.getString(5));
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {

			}
		}
		return retval;
	}

	// 재고관리 삭제
	public void getInventoryDelete(int im_code) throws Exception {
		String dml = "delete from inventory where im_code = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, im_code);

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("재고상품 삭제");
				alert.setHeaderText("재고상품 삭제 완료.");
				alert.setContentText("재고상품 삭제 성공!!!");

				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("재고상품 삭제");
				alert.setHeaderText("재고상품 삭제 실패.");
				alert.setContentText("재고상품 삭제 실패!!!");

				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
			System.out.println(4567);
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
			System.out.println(45676666);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}
	}

	// 재고관리 수정
	public InventoryManagermentVO getInventoryUpdate(InventoryManagermentVO ivo, int im_code) throws Exception {

		String dml = "update inventory set " + " im_tracenumber=?, im_productname=?, im_kg=? where im_code = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		InventoryManagermentVO retval = null;
		SellProductVO spVo = null;

		try {

			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, ivo.getIm_tracenumber());
			pstmt.setString(2, ivo.getIm_productname());
			pstmt.setInt(3, ivo.getIm_kg());
			pstmt.setInt(4, im_code);

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("재고 수정");
				alert.setHeaderText("재고 수정 완료.");
				alert.setContentText("재고 수정 성공!!!");

				alert.showAndWait();
				retval = new InventoryManagermentVO();

			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("재고 실패");
				alert.setHeaderText("재고 수정 실패.");
				alert.setContentText("재고 수정 실패!!!");

				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("e=[" + "]");
		} catch (Exception e) {
			System.out.println("e=[" + "]");
		} finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}
		return retval;
	}
	
	

	public InventoryManagermentVO getSellInventoryCheack(int im_tracenumber) throws Exception {
		String dml = "select * from inventory where im_tracenumber = ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		InventoryManagermentVO retval = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, im_tracenumber);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				retval = new InventoryManagermentVO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4),
						rs.getString(5));
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {

			}
		}
		return retval;
	}
	
	
}
