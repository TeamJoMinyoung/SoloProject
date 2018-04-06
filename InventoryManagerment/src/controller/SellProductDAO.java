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
import model.SellProductVO;

public class SellProductDAO {

	// 판매자료 등록
	public SellProductVO getSellProductregiste(SellProductVO spvo) throws Exception {

		String dml = "insert into sellproduct "
				+ "(sp_code, sp_tracenumber, sp_name, sp_productname, sp_price, sp_kg, sp_date, sp_sum)" + " values "
				+ "(sellproduct_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement pstmt = null;
		SellProductVO retval = null;

		try {

			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, spvo.getSp_tracenumber());
			pstmt.setString(2, spvo.getSp_name());
			pstmt.setString(3, spvo.getSp_productname());
			pstmt.setInt(4, spvo.getSp_price());
			pstmt.setInt(5, spvo.getSp_kg());
			pstmt.setString(6, spvo.getSp_date());
			pstmt.setLong(7, spvo.getSp_sum());

			int i = pstmt.executeUpdate();
			retval = new SellProductVO();
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

	public ArrayList<SellProductVO> getSellProductTotal() {
		ArrayList<SellProductVO> list = new ArrayList<SellProductVO>();
		String tml = "select sp_code, sp_tracenumber, sp_name, sp_productname, sp_price, sp_kg, sp_date, sp_sum from sellproduct";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SellProductVO emVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(tml);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				emVo = new SellProductVO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5),
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
		String sql = "select sp_code, sp_tracenumber, sp_name, sp_productname, sp_price, sp_kg, sp_date, sp_sum from sellproduct";
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

	// 판매관리 판매
	public SellProductVO getSellProductUpdate(SellProductVO spvo, int sp_code) throws Exception {
		String dml = "update sellproduct set "
				+ " sp_tracenumber=?, sp_name=?, sp_productname=?, sp_price=?, sp_kg=?, sp_date=?, sp_sum=? where sp_code = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		SellProductVO retval = null;

		try {

			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, spvo.getSp_tracenumber());
			pstmt.setString(2, spvo.getSp_name());
			pstmt.setString(3, spvo.getSp_productname());
			pstmt.setInt(4, spvo.getSp_price());
			pstmt.setInt(5, spvo.getSp_kg());
			pstmt.setString(6, spvo.getSp_date());
			pstmt.setLong(7, spvo.getSp_sum());
			pstmt.setInt(8, sp_code);

			int i = pstmt.executeUpdate();

			retval = new SellProductVO();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("판매업체 수정");
				alert.setHeaderText("판매업체 수정 완료.");
				alert.setContentText("판매업체 수정 성공!!!");

				alert.showAndWait();
				retval = new SellProductVO();

				System.out.println(spvo.getSp_kg());
				System.out.println(sp_code);
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

	public void getSellproductDelete(int sp_code) throws Exception {
		String dml = "delete from sellproduct where sp_code = ?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, sp_code);

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

	public void sellProduct(int tracenumber, int sp_code) {

		StringBuffer sql = new StringBuffer();
		sql.append(" update inventory set im_kg = (select im_kg-sp_kg 수량 ");
		sql.append(" from inventory ,sellproduct ");
		sql.append(" where im_tracenumber = sp_tracenumber and sp_code = ?)");
		sql.append(" where im_tracenumber = ? ");
		
		

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, sp_code);
			pstmt.setInt(2, tracenumber);

			

			int i = pstmt.executeUpdate();

		} catch (SQLException se) {
			System.out.println("입고목록 삭제시 재고량 변동 sql문오류" + se);
		} catch (Exception e) {
			System.out.println("입고삭제시 재고량 변동 진행오류" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {

			}
		}

	}

	public void selldelete(int tracenumber, int sp_code) {

		StringBuffer sql = new StringBuffer();
		sql.append(" update inventory set im_kg = (select im_kg+sp_kg 수량 ");
		sql.append(" from inventory ,sellproduct ");
		sql.append(" where im_tracenumber = sp_tracenumber and sp_code = ?)");
		sql.append(" where im_tracenumber = ? ");

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, sp_code);
			pstmt.setInt(2, tracenumber);

			

			int i = pstmt.executeUpdate();

		} catch (SQLException se) {
			System.out.println("입고목록 삭제시 재고량 변동 sql문오류" + se);
		} catch (Exception e) {
			System.out.println("입고삭제시 재고량 변동 진행오류" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {

			}
		}

	}

	public SellProductVO getSellproductCheack(String sp_name) throws Exception {
		String dml = "select sp_code, sp_tracenumber, sp_name, sp_productname, sp_price, sp_kg, sp_date, sp_sum from sellproduct where sp_name = ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SellProductVO retval = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, sp_name);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				retval = new SellProductVO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getString(7), rs.getLong(8));

			}
		} catch (SQLException se) {
			System.out.println(se);
			System.out.println("여기입니다");
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
