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
import model.SellCompanyVO;

public class SellCompanyDAO {

	// 판매업체 자료 입력
	public SellCompanyVO getSellCompanyregiste(SellCompanyVO scvo) throws Exception {
		String dml = "insert into sellcompany "
				+ "(sc_code, sc_name, sc_businessnumber, sc_ceo, sc_cphone, sc_address, sc_manager, sc_managerphone)"
				+ " values " + "(sellcompany_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement pstmt = null;
		SellCompanyVO retval = null;

		try {

			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, scvo.getSc_name());
			pstmt.setInt(2, scvo.getSc_businessnumber());
			pstmt.setString(3, scvo.getSc_ceo());
			pstmt.setString(4, scvo.getSc_cphone());
			pstmt.setString(5, scvo.getSc_address());
			pstmt.setString(6, scvo.getSc_manager());
			pstmt.setString(7, scvo.getSc_managerphone());

			int i = pstmt.executeUpdate();

			retval = new SellCompanyVO();
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

	public ArrayList<SellCompanyVO> getSellCompanyTotal() {
		ArrayList<SellCompanyVO> list = new ArrayList<SellCompanyVO>();
		String tml = "select * from sellcompany";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SellCompanyVO emvo = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(tml);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				emvo = new SellCompanyVO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8));
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

	public ArrayList<String> getColumnName() {
		ArrayList<String> columnName = new ArrayList<String>();

		String sql = "select * from sellcompany";

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
	
	
	//판매회사 수정
	public SellCompanyVO getSellcompanyUpdate(SellCompanyVO svo, int sc_code) throws Exception {

		String dml = "update sellcompany set "
				+ "  sc_name=?, sc_businessnumber=?, sc_ceo=?, sc_cphone=?, sc_address=?, sc_manager=?, sc_managerphone=? where sc_code = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		SellCompanyVO retval = null;

		try {

			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, svo.getSc_name());
			pstmt.setInt(2, svo.getSc_businessnumber());
			pstmt.setString(3, svo.getSc_ceo());
			pstmt.setString(4, svo.getSc_cphone());
			pstmt.setString(5, svo.getSc_address());
			pstmt.setString(6, svo.getSc_manager());
			pstmt.setString(7, svo.getSc_managerphone());
			pstmt.setInt(8, sc_code);

			int i = pstmt.executeUpdate();

			retval = new SellCompanyVO();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("판매업체 수정");
				alert.setHeaderText("판매업체 수정 완료.");
				alert.setContentText("판매업체 수정 성공!!!");

				alert.showAndWait();
				retval = new SellCompanyVO();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("판매업체 수정");
				alert.setHeaderText("판매업체 수정 실패.");
				alert.setContentText("판매업체 수정 실패!!!");

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
	
	//판매회사 삭제
	public void getSellcompanyDelete(int sc_code) throws Exception {
		String dml = "delete from sellcompany where sc_code = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, sc_code);

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("판매업체 삭제");
				alert.setHeaderText("판매업체 삭제 완료.");
				alert.setContentText("판매업체 삭제 성공!!!");

				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("판매업체 삭제");
				alert.setHeaderText("판매업체 삭제 실패.");
				alert.setContentText("판매업체 삭제 실패!!!");

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

}
