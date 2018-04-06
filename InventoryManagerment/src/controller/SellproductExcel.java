package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.SellProductVO;

public class SellproductExcel {
	public boolean xlsxWiter(List<SellProductVO> list, String saveDir) {
		// 워크북 생성
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 워크시트 생성
		XSSFSheet sheet = workbook.createSheet();
		// 행 생성
		XSSFRow row = sheet.createRow(0);
		// 셀 생성
		XSSFCell cell;
		// 헤더 정보 구성
		cell = row.createCell(0);
		cell.setCellValue("NO.");
		cell = row.createCell(1);
		cell.setCellValue("이력번호");
		cell = row.createCell(2);
		cell.setCellValue("상호명");
		cell = row.createCell(3);
		cell.setCellValue("상품명");
		cell = row.createCell(4);
		cell.setCellValue("판매가격");
		cell = row.createCell(5);
		cell.setCellValue("KG");
		cell = row.createCell(6);
		cell.setCellValue("판매날짜");
		cell = row.createCell(7);
		cell.setCellValue("판매총액");

		// 리스트의 size 만큼 row를 생성
		SellProductVO spVo;
		for (int rowIdx = 0; rowIdx < list.size(); rowIdx++) {
			spVo = list.get(rowIdx);

			// 행 생성
			row = sheet.createRow(rowIdx + 1);

			cell = row.createCell(0);
			cell.setCellValue(spVo.getSp_code());
			cell = row.createCell(1);
			cell.setCellValue(spVo.getSp_tracenumber());
			cell = row.createCell(2);
			cell.setCellValue(spVo.getSp_name());
			cell = row.createCell(3);
			cell.setCellValue(spVo.getSp_productname());
			cell = row.createCell(4);
			cell.setCellValue(spVo.getSp_price());
			cell = row.createCell(5);
			cell.setCellValue(spVo.getSp_kg());
			cell = row.createCell(6);
			cell.setCellValue(spVo.getSp_date());
			cell = row.createCell(7);
			cell.setCellValue(spVo.getSp_sum());

		}
		// 입력된 내용 파일로쓰기
		String strReportPDF = "sellproduct_" + System.currentTimeMillis() + ".xlsx";
		File file = new File(saveDir + "\\" + strReportPDF);
		FileOutputStream fos = null;

		boolean saveSuccess;
		saveSuccess = false;
		try {
			fos = new FileOutputStream(file);
			if (fos != null) {
				workbook.write(fos);
				saveSuccess = true;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (workbook != null)
					workbook.close();
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return saveSuccess;
	}

}
