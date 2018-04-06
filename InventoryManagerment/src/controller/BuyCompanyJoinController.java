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
	private TableView<BuyCompanyVo> tableviewbc = new TableView<>(); //����ȸ�� ���̺��
	@FXML
	private TextField txt_Bc_name; //����ȸ�� ��ȣ��
	@FXML
	private TextField txt_Bc_businessnumber; //����ȸ�� ����ڹ�ȣ
	@FXML
	private TextField txt_Bc_ceo; //����ȸ�� ��ǥ��
	@FXML
	private TextField txt_Bc_cphone; //����ȸ�� ��ǥ��ȣ
	@FXML
	private TextField txt_Bc_address; //����ȸ�� �ּ�
	@FXML
	private TextField txt_Bc_manager; //����ȸ�� �����
	@FXML
	private TextField txt_Bc_managerphone; //����ȸ�� ����� ��ȭ��ȣ
	@FXML
	private Button btn_Buycom_join; //����ȸ�� ��Ϲ�ư
	@FXML
	private Button btn_Buycom_init; //����ȸ�� �ʱ�ȭ��ư
	@FXML
	private Button btn_Buycom_exit; //����ȸ�� ����
	@FXML
	private Button btn_Buycom_edit; //����ȸ�� ������ư
	@FXML
	private Button btn_Buycom_delete; //����ȸ�� ������ư

	BuyCompanyVo buycompany = new BuyCompanyVo();
	ObservableList<BuyCompanyVo> data = FXCollections.observableArrayList();
	ObservableList<BuyCompanyVo> selectbuycompany;

	int selectedIndex;

	int bc_code;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		//����ȸ�� ���̺��
		TableColumn colno = new TableColumn("NO.");
		colno.setMinWidth(20);
		colno.setStyle("-fx-allignment:CENTER");
		colno.setCellValueFactory(new PropertyValueFactory<>("bc_code"));

		TableColumn colname = new TableColumn("��ȣ��");
		colname.setMinWidth(100);
		colname.setStyle("-fx-allignment:CENTER");
		colname.setCellValueFactory(new PropertyValueFactory<>("bc_name"));

		TableColumn colbusinessnumber = new TableColumn("����ڹ�ȣ");
		colbusinessnumber.setMinWidth(117);
		colbusinessnumber.setStyle("-fx-allignment:CENTER");
		colbusinessnumber.setCellValueFactory(new PropertyValueFactory<>("bc_businessnumber"));

		tableviewbc.setItems(data);
		tableviewbc.getColumns().addAll(colno, colname, colbusinessnumber);

		totalList();
		
		
		//����ȸ�� ��Ͼ׼�
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
						alert.setTitle("���Ծ�ü ���");
						alert.setHeaderText(txt_Bc_name.getText() + "����ȸ�簡 ���������� �߰��Ǿ����ϴ�..");
						alert.setContentText("���� ���Ծ�ü�� �Է��ϼ���");
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
				alert.setTitle("���Ծ�ü �Է�");
				alert.setHeaderText("���Ծ�ü ������ ��Ȯ�� �Է��Ͻÿ�.");
				alert.setContentText("�������� �����ϼ���!");
				alert.showAndWait();
			}
		});
		btn_Buycom_edit.setOnAction(event -> handlerBtn_Buycom_editActoion(event)); //����ȸ�� �����׼�
		btn_Buycom_init.setOnAction(event -> handlerBtn_Buycom_initActoion(event)); //����ȸ�� �ʱ�ȭ�׼�
		btn_Buycom_exit.setOnAction(event -> handlerBtn_Buycom_exitActoion(event)); //����ȸ�� ����׼�
		btn_Buycom_delete.setOnAction(event -> handlerBtn_Buycom_deleteActoion(event)); //����ȸ�� �����׼�
		
		
		//����ȸ�� ���콺�׼�
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
					alert.setTitle("���Ծ�ü ���� ���� ����");
					alert.setHeaderText("���Ծ�ü ������ �Է��Ͻÿ�."); 
					alert.setContentText("�������� �����ϼ���!");
					alert.showAndWait(); 
				}
				
			}
		});
			
		
		

	}
	
	
	//����ȸ�� ������ư �׼�
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
	
	
	//����ȸ�� �����׼�
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
			alert.setTitle("���Ծ�ü ���� ����");
			alert.setHeaderText("�����Ǵ� ������ ��Ȯ�� �Է��Ͻÿ�.");
			alert.setContentText("�������� �����ϼ���!");
			alert.showAndWait(); 
		}
	}
	
	
	//����ȸ�� �����ư
	public void handlerBtn_Buycom_exitActoion(ActionEvent event) {
		Platform.exit();
	}
	
	
	//����ȸ�� �ʱ�ȭ��ư 
	public void handlerBtn_Buycom_initActoion(ActionEvent event) {
		txt_Bc_name.clear();
		txt_Bc_businessnumber.clear();
		txt_Bc_ceo.clear();
		txt_Bc_cphone.clear();
		txt_Bc_address.clear();
		txt_Bc_manager.clear();
		txt_Bc_managerphone.clear();
	}
	
	
	//����ȸ�� ��Ż����Ʈ
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
