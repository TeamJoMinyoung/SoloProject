package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.BuyProductVO;
import model.InventoryManagermentVO;

public class BuyProductDAO {

	// 매입상품 등록
	public BuyProductVO getBuyCompanyregiste(BuyProductVO bpvo) throws Exception {

		String dml = "insert into buyproduct "
				+ "(bp_tracenumber, bp_code, bp_name, bp_productname, bp_price, bp_kg, bp_date, bp_sum)" + " values "
				+ "(?, buyproduct_seq.nextval, ?, ?, ?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement pstmt = null;
		BuyProductVO retval = null;

		try {

			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(dml);

			pstmt.setInt(1, bpvo.getBp_tracenumber());
			pstmt.setString(2, bpvo.getBp_name());
			pstmt.setString(3, bpvo.getBp_productname());
			pstmt.setInt(4, bpvo.getBp_price());
			pstmt.setInt(5, bpvo.getBp_kg());
			pstmt.setString(6, bpvo.getBp_date()+"");
			pstmt.setLong(7, bpvo.getBp_sum());

			int i = pstmt.executeUpdate();
			retval = new BuyProductVO();
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
			System.out.println("yayaya");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
			System.out.println("yayaya1234");
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

	public ArrayList<BuyProductVO> getBuyProductTotal() {
		ArrayList<BuyProductVO> list = new ArrayList<BuyProductVO>();
		String tml = "select bp_code, bp_tracenumber, bp_name, bp_productname, bp_price, bp_kg, bp_date, bp_sum from buyproduct";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BuyProductVO emVo = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(tml);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				emVo = new BuyProductVO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getDate(7)+"", rs.getLong(8));
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

		String sql = "select bp_tracenumber, bp_code, bp_name, bp_productname, bp_price, bp_kg, bp_date from buyproduct";
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

	// 매입상품 수정
	public BuyProductVO getBuyproductUpdate(BuyProductVO bpvo, int bp_code) throws Exception {
		String dml = "update buyproduct set "
				+ " bp_tracenumber=?, bp_name=?, bp_productname=?, bp_price=?, bp_kg=?, bp_date=?, bp_sum=? where bp_code = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		BuyProductVO retval = null;

		try {

			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, bpvo.getBp_tracenumber());
			pstmt.setString(2, bpvo.getBp_name());
			pstmt.setString(3, bpvo.getBp_productname());
			pstmt.setInt(4, bpvo.getBp_price());
			pstmt.setInt(5, bpvo.getBp_kg());
			pstmt.setString(6, bpvo.getBp_date());
			pstmt.setLong(7, bpvo.getBp_sum());
			pstmt.setInt(8, bp_code);

			int i = pstmt.executeUpdate();

			retval = new BuyProductVO();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("매입업체 수정");
				alert.setHeaderText("매입업체 수정 완료.");
				alert.setContentText("매입업체 수정 성공!!!");

				alert.showAndWait();
				retval = new BuyProductVO();
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

	// 매입상품 삭제
	public void getBuycompanyDelete(int bp_tracenumber) throws Exception {
		String dml = "delete from buyproduct where bp_code = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, bp_tracenumber);

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

	// 매입단 상호명 검색
	public BuyProductVO getBuyProductCheack(String bp_name) throws Exception {
		String dml = "select bp_code, bp_tracenumber, bp_name, bp_productname, bp_price, bp_kg, bp_date, bp_sum from buyproduct where bp_name = ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BuyProductVO retval = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, bp_name);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				retval = new BuyProductVO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getString(7), rs.getLong(8));
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
