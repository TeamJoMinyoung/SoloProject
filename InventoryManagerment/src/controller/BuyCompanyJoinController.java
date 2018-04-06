package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.BuyCompanyVo;

public class BuyCompanyJoinController implements Initializable {
	@FXML
	private TableView<BuyCompanyVo> tableviewbc = new TableView<>(); //매입회사 테이블뷰
	@FXML
	private TextField txt_Bc_name; //매입회사 상호명
	@FXML
	private TextField txt_Bc_businessnumber; //매입회사 사업자번호
	@FXML
	private TextField txt_Bc_ceo; //매입회사 대표명
	@FXML
	private TextField txt_Bc_cphone; //매입회사 대표번호
	@FXML
	private TextField txt_Bc_address; //매입회사 주소
	@FXML
	private TextField txt_Bc_manager; //매입회사 담당자
	@FXML
	private TextField txt_Bc_managerphone; //매입회사 담당자 전화번호
	@FXML
	private Button btn_Buycom_join; //매입회사 등록버튼
	@FXML
	private Button btn_Buycom_init; //매입회사 초괴화버튼
	@FXML
	private Button btn_Buycom_exit; //매입회사 종료
	@FXML
	private Button btn_Buycom_edit; //매입회사 수정버튼
	@FXML
	private Button btn_Buycom_delete; //매입회사 삭제버튼

	BuyCompanyVo buycompany = new BuyCompanyVo();
	ObservableList<BuyCompanyVo> data = FXCollections.observableArrayList();
	ObservableList<BuyCompanyVo> selectbuycompany;

	int selectedIndex;

	int bc_code;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		//매입회사 테이블뷰
		TableColumn colno = new TableColumn("NO.");
		colno.setMinWidth(20);
		colno.setStyle("-fx-allignment:CENTER");
		colno.setCellValueFactory(new PropertyValueFactory<>("bc_code"));

		TableColumn colname = new TableColumn("상호명");
		colname.setMinWidth(100);
		colname.setStyle("-fx-allignment:CENTER");
		colname.setCellValueFactory(new PropertyValueFactory<>("bc_name"));

		TableColumn colbusinessnumber = new TableColumn("사업자번호");
		colbusinessnumber.setMinWidth(117);
		colbusinessnumber.setStyle("-fx-allignment:CENTER");
		colbusinessnumber.setCellValueFactory(new PropertyValueFactory<>("bc_businessnumber"));

		tableviewbc.setItems(data);
		tableviewbc.getColumns().addAll(colno, colname, colbusinessnumber);

		totalList();
		
		
		//매입회사 등록액션
		btn_Buycom_join.setOnAction(event -> {
			try {
				data.removeAll(data); 
				BuyCompanyVo bvo = null;
				BuyCompanyDAO bdao = null;

				if (event.getSource().equals(btn_Buycom_join)) {
					bvo = new BuyCompanyVo(txt_Bc_name.getText(),
							Integer.parseInt(txt_Bc_businessnumber.getText().trim()), txt_Bc_ceo.getText(),
							txt_Bc_cphone.getText(), txt_Bc_address.getText(), txt_Bc_manager.getText(),
							txt_Bc_managerphone.getText());
					bdao = new BuyCompanyDAO();
					bdao.getBuyCompanyregiste(bvo);

					if (bdao != null) {
						totalList();
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("매입업체 등록");
						alert.setHeaderText(txt_Bc_name.getText() + "매입회사가 성공적으로 추가되었습니다..");
						alert.setContentText("다음 매입업체를 입력하세요");
						alert.showAndWait();

						txt_Bc_name.setEditable(true);
						txt_Bc_businessnumber.setEditable(true);
						txt_Bc_ceo.setEditable(true);
						txt_Bc_cphone.setEditable(true);
						txt_Bc_address.setEditable(true);
						txt_Bc_manager.setEditable(true);
						txt_Bc_managerphone.setEditable(true);
						handlerBtn_Buycom_initActoion(event);
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
		btn_Buycom_edit.setOnAction(event -> handlerBtn_Buycom_editActoion(event)); //매입회사 수정액션
		btn_Buycom_init.setOnAction(event -> handlerBtn_Buycom_initActoion(event)); //매입회사 초기화액션
		btn_Buycom_exit.setOnAction(event -> handlerBtn_Buycom_exitActoion(event)); //매입회사 종료액션
		btn_Buycom_delete.setOnAction(event -> handlerBtn_Buycom_deleteActoion(event)); //매입회사 삭제액션
		
		
		//매입회사 마우스액션
		tableviewbc.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				
				try {
					selectbuycompany = tableviewbc.getSelectionModel().getSelectedItems();
					selectedIndex = tableviewbc.getSelectionModel().getSelectedIndex();
					bc_code = selectbuycompany.get(0).getBc_code();
					
					txt_Bc_name.setText(selectbuycompany.get(0).getBc_name());
					
					txt_Bc_businessnumber.setText(selectbuycompany.get(0).getBc_businessnumber()+"");
					txt_Bc_ceo.setText(selectbuycompany.get(0).getBc_ceo());
					txt_Bc_cphone.setText(selectbuycompany.get(0).getBc_cphone());
					txt_Bc_address.setText(selectbuycompany.get(0).getBc_address());
					txt_Bc_manager.setText(selectbuycompany.get(0).getBc_manager());
					txt_Bc_managerphone.setText(selectbuycompany.get(0).getBc_managerphone());
				}catch(Exception e) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("매입업체 정보 수정 삭제");
					alert.setHeaderText("매입업체 정보를 입력하시오."); 
					alert.setContentText("다음에는 주의하세요!");
					alert.showAndWait(); 
				}
				
			}
		});
			
		
		

	}
	
	
	//매입회사 삭제버튼 액션
	public void handlerBtn_Buycom_deleteActoion(ActionEvent event) {
		BuyCompanyDAO bDao = null;
		bDao = new BuyCompanyDAO();
		try {
			bDao.getBuycompanyDelete(bc_code);
			data.removeAll(data);
			
			totalList();
			handlerBtn_Buycom_initActoion(event);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//매입회사 수정액션
	public void handlerBtn_Buycom_editActoion(ActionEvent event) {
		BuyCompanyVo bVo = null;
		BuyCompanyDAO bDao = null;
		
		try {
			
			selectbuycompany = tableviewbc.getSelectionModel().getSelectedItems();
			selectedIndex = tableviewbc.getSelectionModel().getSelectedIndex();
			data.remove(selectedIndex);
			bVo = new BuyCompanyVo(txt_Bc_name.getText(),
					Integer.parseInt(txt_Bc_businessnumber.getText().trim()), txt_Bc_ceo.getText(),
					txt_Bc_cphone.getText(), txt_Bc_address.getText(), txt_Bc_manager.getText(),
					txt_Bc_managerphone.getText());
			
			handlerBtn_Buycom_initActoion(event);
			
			bDao = new BuyCompanyDAO();
			bDao.getBuycompanyUpdate(bVo, bc_code);
			data.removeAll(data);
			totalList(); 
		}catch(Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("매입업체 정보 수정");
			alert.setHeaderText("수정되는 정보를 정확히 입력하시오.");
			alert.setContentText("다음에는 주의하세요!");
			alert.showAndWait(); 
		}
	}
	
	
	//매입회사 종료버튼
	public void handlerBtn_Buycom_exitActoion(ActionEvent event) {
		Platform.exit();
	}
	
	
	//매입회사 초기화버튼 
	public void handlerBtn_Buycom_initActoion(ActionEvent event) {
		txt_Bc_name.clear();
		txt_Bc_businessnumber.clear();
		txt_Bc_ceo.clear();
		txt_Bc_cphone.clear();
		txt_Bc_address.clear();
		txt_Bc_manager.clear();
		txt_Bc_managerphone.clear();
	}
	
	
	//매입회사 토탈리스트
	public void totalList() {
		Object[][] totalData;

		BuyCompanyDAO bDao = new BuyCompanyDAO();
		BuyCompanyVo bVo = null;
		ArrayList<String> title;
		ArrayList<BuyCompanyVo> list;

		title = bDao.getColumnName();
		int columnCount = title.size();

		list = bDao.getBuyCompanyTotal();
		int rowCount = list.size();

		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			bVo = list.get(index);
			data.add(bVo);
		}

	}

}
