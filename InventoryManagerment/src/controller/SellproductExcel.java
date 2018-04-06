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
		// ��ũ�� ����
		XSSFWorkbook workbook = new XSSFWorkbook();
		// ��ũ��Ʈ ����
		XSSFSheet sheet = workbook.createSheet();
		// �� ����
		XSSFRow row = sheet.createRow(0);
		// �� ����
		XSSFCell cell;
		// ��� ���� ����
		cell = row.createCell(0);
		cell.setCellValue("NO.");
		cell = row.createCell(1);
		cell.setCellValue("�̷¹�ȣ");
		cell = row.createCell(2);
		cell.setCellValue("��ȣ��");
		cell = row.createCell(3);
		cell.setCellValue("��ǰ��");
		cell = row.createCell(4);
		cell.setCellValue("�ǸŰ���");
		cell = row.createCell(5);
		cell.setCellValue("KG");
		cell = row.createCell(6);
		cell.setCellValue("�Ǹų�¥");
		cell = row.createCell(7);
		cell.setCellValue("�Ǹ��Ѿ�");

		// ����Ʈ�� size ��ŭ row�� ����
		SellProductVO spVo;
		for (int rowIdx = 0; rowIdx < list.size(); rowIdx++) {
			spVo = list.get(rowIdx);

			// �� ����
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
		// �Էµ� ���� ���Ϸξ���
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
