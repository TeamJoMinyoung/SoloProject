package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.SellCompanyVO;

public class SellCompanyJoinContorller implements Initializable {
	@FXML
	private TableView<SellCompanyVO> tableviewsc = new TableView<>(); //판매회사 테이블뷰
	@FXML
	private TextField txt_Sc_name; //판매회사  상호명
	@FXML
	private TextField txt_Sc_businessnumber; //판매회사  사업자번호
	@FXML
	private TextField txt_Sc_ceo; //판매회사  대표명
	@FXML
	private TextField txt_Sc_cphone; //판매회사  대표전화
	@FXML
	private TextField txt_Sc_address; //판매회사 주소
	@FXML
	private TextField txt_Sc_manager; //판매회사  담당자
	@FXML
	private TextField txt_Sc_managerphone; //판매회사  담당자 전화번호
	@FXML
	private Button btn_Sc_join; //판매회사  등록
	@FXML
	private Button btn_Sc_init; //판매회사 초기화
	@FXML
	private Button btn_Sc_exit; //판매회사 종료
	@FXML
	private Button btn_Sc_edit; //판매회사 수정
	@FXML
	private Button btn_Sc_delete; //판매회사  삭제

	SellCompanyVO sellcompany = new SellCompanyVO();
	ObservableList<SellCompanyVO> data = FXCollections.observableArrayList();
	ObservableList<SellCompanyVO> selectsellcompany;

	int selectedIndex;

	int sc_code;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//판매회사 테이블뷰
		TableColumn colno = new TableColumn("NO.");
		colno.setMinWidth(20);
		colno.setStyle("-fx-allignment:CENTER");
		colno.setCellValueFactory(new PropertyValueFactory<>("sc_code"));

		TableColumn colname = new TableColumn("상호명");
		colname.setMinWidth(100);
		colname.setStyle("-fx-allignment:CENTER");
		colname.setCellValueFactory(new PropertyValueFactory<>("sc_name"));

		TableColumn colbusinessnumber = new TableColumn("사업자번호");
		colbusinessnumber.setMinWidth(117);
		colbusinessnumber.setStyle("-fx-allignment:CENTER");
		colbusinessnumber.setCellValueFactory(new PropertyValueFactory<>("sc_businessnumber"));

		tableviewsc.setItems(data);
		tableviewsc.getColumns().addAll(colno, colname, colbusinessnumber);

		totalList();
		
		
		//판매회사 등록버튼액션
		btn_Sc_join.setOnAction(event -> {
			try {

				data.removeAll(data);
				SellCompanyVO svo = null;
				SellCompanyDAO sdao = null;

				if (event.getSource().equals(btn_Sc_join)) {
					svo = new SellCompanyVO(txt_Sc_name.getText(),
							Integer.parseInt(txt_Sc_businessnumber.getText().trim()), txt_Sc_ceo.getText(),
							txt_Sc_cphone.getText(), txt_Sc_address.getText(), txt_Sc_manager.getText(),
							txt_Sc_managerphone.getText());
					sdao = new SellCompanyDAO();
					sdao.getSellCompanyregiste(svo);

					if (sdao != null) {
						totalList();
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("매입업체 등록");
						alert.setHeaderText(txt_Sc_name.getText() + "매입회사가 성공적으로 추가되었습니다..");
						alert.setContentText("다음 매입업체를 입력하세요");
						alert.showAndWait();

						txt_Sc_name.setEditable(true);
						txt_Sc_businessnumber.setEditable(true);
						txt_Sc_ceo.setEditable(true);
						txt_Sc_cphone.setEditable(true);
						txt_Sc_address.setEditable(true);
						txt_Sc_manager.setEditable(true);
						txt_Sc_managerphone.setEditable(true);
						handlerBtn_Sc_initActoion(event);
					}
				}

			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("매입업체 입력");
				alert.setHeaderText("매입업체 정보를 정확히 입력하시오.");
				alert.setContentText("다음에는 주의하세요!");
				alert.showAndWait();
			}
		});

		btn_Sc_edit.setOnAction(event -> handlerBtn_Sc_editActoion(event)); //판매회사 수정버튼 람다식
		btn_Sc_init.setOnAction(event -> handlerBtn_Sc_initActoion(event)); //판매회사 초기화버튼 람다식
		btn_Sc_exit.setOnAction(event -> handlerBtn_Sc_exitActoion(event)); //판매회사 종료버튼 람다식
		btn_Sc_delete.setOnAction(event -> handlerBtn_Sc_deleteActoion(event)); //판매회사 삭제버튼 람다식
		
		
		//판매회사 마우스이벤트
		tableviewsc.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				try {

					selectsellcompany = tableviewsc.getSelectionModel().getSelectedItems();
					selectedIndex = tableviewsc.getSelectionModel().getSelectedIndex();
					sc_code = selectsellcompany.get(0).getSc_code();

					txt_Sc_name.setText(selectsellcompany.get(0).getSc_name());

					txt_Sc_businessnumber.setText(selectsellcompany.get(0).getSc_businessnumber() + "");
					txt_Sc_ceo.setText(selectsellcompany.get(0).getSc_ceo());
					txt_Sc_cphone.setText(selectsellcompany.get(0).getSc_cphone());
					txt_Sc_address.setText(selectsellcompany.get(0).getSc_address());
					txt_Sc_manager.setText(selectsellcompany.get(0).getSc_manager());
					txt_Sc_managerphone.setText(selectsellcompany.get(0).getSc_managerphone());
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("판매업체 정보 수정 삭제");
					alert.setHeaderText("판매업체 정보를 입력하시오.");
					alert.setContentText("다음에는 주의하세요!");
					alert.showAndWait();
				}
			}
		});

	}
	
	
	//판매회사 삭제
	public void handlerBtn_Sc_deleteActoion(ActionEvent event) {
		SellCompanyDAO sDao = null;
		sDao = new SellCompanyDAO();
		
		try {
			sDao.getSellcompanyDelete(sc_code);
			data.removeAll(data);
			
			totalList();
			handlerBtn_Sc_initActoion(event);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//판매회사 수정
	public void handlerBtn_Sc_editActoion(ActionEvent event) {
		SellCompanyVO sVo = null;
		SellCompanyDAO sDao = null;

		try {

			selectsellcompany = tableviewsc.getSelectionModel().getSelectedItems();
			selectedIndex = tableviewsc.getSelectionModel().getSelectedIndex();
			data.remove(selectedIndex);
			sVo = new SellCompanyVO(txt_Sc_name.getText(), Integer.parseInt(txt_Sc_businessnumber.getText().trim()),
					txt_Sc_ceo.getText(), txt_Sc_cphone.getText(), txt_Sc_address.getText(), txt_Sc_manager.getText(),
					txt_Sc_managerphone.getText());

			handlerBtn_Sc_initActoion(event);

			sDao = new SellCompanyDAO();
			sDao.getSellcompanyUpdate(sVo, sc_code);
			data.removeAll(data);
			totalList();

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("매입업체 정보 수정");
			alert.setHeaderText("수정되는 정보를 정확히 입력하시오.");
			alert.setContentText("다음에는 주의하세요!");
			alert.showAndWait();
		}
	}
	
	//판매회사 종료
	public void handlerBtn_Sc_exitActoion(ActionEvent event) {
		Platform.exit();
	}
	
	//판매회사 초기화
	public void handlerBtn_Sc_initActoion(ActionEvent event) {
		txt_Sc_name.clear();
		txt_Sc_businessnumber.clear();
		txt_Sc_ceo.clear();
		txt_Sc_cphone.clear();
		txt_Sc_address.clear();
		txt_Sc_manager.clear();
		txt_Sc_managerphone.clear();
	}
	
	
	//판매회사 토탈리스트
	public void totalList() {
		Object[][] totalData;

		SellCompanyDAO sDao = new SellCompanyDAO();
		SellCompanyVO sVo = null;
		ArrayList<String> title;
		ArrayList<SellCompanyVO> list;

		title = sDao.getColumnName();
		int columnCount = title.size();

		list = sDao.getSellCompanyTotal();
		int rowCount = list.size();

		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			sVo = list.get(index);
			data.add(sVo);
		}
	}

}
