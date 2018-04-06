package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.BuyCompanyVo;

public class BuyCompanyDAO {
	// 매입업체 자료 입력
	public BuyCompanyVo getBuyCompanyregiste(BuyCompanyVo bcvo) throws Exception {

		String dml = "insert into buycompany "
				+ "(bc_code, bc_name, bc_businessnumber, bc_ceo, bc_cphone, bc_address, bc_manager, bc_managerphone)"
				+ " values " + "(buycompany_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		BuyCompanyVo retval = null;

		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, bcvo.getBc_name());
			pstmt.setInt(2, bcvo.getBc_businessnumber());
			pstmt.setString(3, bcvo.getBc_ceo());
			pstmt.setString(4, bcvo.getBc_cphone());
			pstmt.setString(5, bcvo.getBc_address());
			pstmt.setString(6, bcvo.getBc_manager());
			pstmt.setString(7, bcvo.getBc_managerphone());

			int i = pstmt.executeUpdate();

			retval = new BuyCompanyVo();

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
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
	
	//매입업체 토탈
	public ArrayList<BuyCompanyVo> getBuyCompanyTotal() {
		ArrayList<BuyCompanyVo> list = new ArrayList<BuyCompanyVo>();
		String tml = "select * from buycompany";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BuyCompanyVo emVo = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(tml);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				emVo = new BuyCompanyVo(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8));
				list.add(emVo);
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

	public ArrayList<String> getColumnName() {

		ArrayList<String> columnName = new ArrayList<String>();

		String sql = "select * from buycompany";
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
				columnName.add(rsmd.getColumnName(i));
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
		return columnName;
	}
	//매입업체 수정
	public BuyCompanyVo getBuycompanyUpdate(BuyCompanyVo bvo, int bc_code) throws Exception {

		String dml = "update buycompany set "
				+ " bc_name=?, bc_businessnumber=?, bc_ceo=?, bc_cphone=?, bc_address=?, bc_manager=?, bc_managerphone=? where bc_code = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		BuyCompanyVo retval = null;

		try {

			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, bvo.getBc_name());
			pstmt.setInt(2, bvo.getBc_businessnumber());
			pstmt.setString(3, bvo.getBc_ceo());
			pstmt.setString(4, bvo.getBc_cphone());
			pstmt.setString(5, bvo.getBc_address());
			pstmt.setString(6, bvo.getBc_manager());
			pstmt.setString(7, bvo.getBc_managerphone());
			pstmt.setInt(8, bc_code);

			int i = pstmt.executeUpdate();

			retval = new BuyCompanyVo();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("매입업체 수정");
				alert.setHeaderText("매입업체 수정 완료.");
				alert.setContentText("매입업체 수정 성공!!!");

				alert.showAndWait();
				retval = new BuyCompanyVo();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("매입업체 수정");
				alert.setHeaderText("매입업체 수정 실패.");
				alert.setContentText("매입업체 수정 실패!!!");

				alert.showAndWait();
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
			System.out.println(1234);
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
			System.out.println(12346666);
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
	
	
	//매입업체 삭제
	public void getBuycompanyDelete(int bc_code) throws Exception {
		String dml = "delete from buycompany where bc_code = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, bc_code);

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("매입업체 삭제");
				alert.setHeaderText("매입업체 삭제 완료.");
				alert.setContentText("매입업체 삭제 성공!!!");

				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("매입업체 삭제");
				alert.setHeaderText("매입업체 삭제 실패.");
				alert.setContentText("매입업체 삭제 실패!!!");

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
	
	public BuyCompanyVo getBuycompanyCheack(String bc_name) throws Exception {
		String dml = "select * from buycompany where bc_name = ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BuyCompanyVo retval = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, bc_name);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				retval = new BuyCompanyVo(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8));
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
