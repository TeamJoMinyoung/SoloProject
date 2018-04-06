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
	private TableView<SellCompanyVO> tableviewsc = new TableView<>(); //�Ǹ�ȸ�� ���̺��
	@FXML
	private TextField txt_Sc_name; //�Ǹ�ȸ��  ��ȣ��
	@FXML
	private TextField txt_Sc_businessnumber; //�Ǹ�ȸ��  ����ڹ�ȣ
	@FXML
	private TextField txt_Sc_ceo; //�Ǹ�ȸ��  ��ǥ��
	@FXML
	private TextField txt_Sc_cphone; //�Ǹ�ȸ��  ��ǥ��ȭ
	@FXML
	private TextField txt_Sc_address; //�Ǹ�ȸ�� �ּ�
	@FXML
	private TextField txt_Sc_manager; //�Ǹ�ȸ��  �����
	@FXML
	private TextField txt_Sc_managerphone; //�Ǹ�ȸ��  ����� ��ȭ��ȣ
	@FXML
	private Button btn_Sc_join; //�Ǹ�ȸ��  ���
	@FXML
	private Button btn_Sc_init; //�Ǹ�ȸ�� �ʱ�ȭ
	@FXML
	private Button btn_Sc_exit; //�Ǹ�ȸ�� ����
	@FXML
	private Button btn_Sc_edit; //�Ǹ�ȸ�� ����
	@FXML
	private Button btn_Sc_delete; //�Ǹ�ȸ��  ����

	SellCompanyVO sellcompany = new SellCompanyVO();
	ObservableList<SellCompanyVO> data = FXCollections.observableArrayList();
	ObservableList<SellCompanyVO> selectsellcompany;

	int selectedIndex;

	int sc_code;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//�Ǹ�ȸ�� ���̺��
		TableColumn colno = new TableColumn("NO.");
		colno.setMinWidth(20);
		colno.setStyle("-fx-allignment:CENTER");
		colno.setCellValueFactory(new PropertyValueFactory<>("sc_code"));

		TableColumn colname = new TableColumn("��ȣ��");
		colname.setMinWidth(100);
		colname.setStyle("-fx-allignment:CENTER");
		colname.setCellValueFactory(new PropertyValueFactory<>("sc_name"));

		TableColumn colbusinessnumber = new TableColumn("����ڹ�ȣ");
		colbusinessnumber.setMinWidth(117);
		colbusinessnumber.setStyle("-fx-allignment:CENTER");
		colbusinessnumber.setCellValueFactory(new PropertyValueFactory<>("sc_businessnumber"));

		tableviewsc.setItems(data);
		tableviewsc.getColumns().addAll(colno, colname, colbusinessnumber);

		totalList();
		
		
		//�Ǹ�ȸ�� ��Ϲ�ư�׼�
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
						alert.setTitle("���Ծ�ü ���");
						alert.setHeaderText(txt_Sc_name.getText() + "����ȸ�簡 ���������� �߰��Ǿ����ϴ�..");
						alert.setContentText("���� ���Ծ�ü�� �Է��ϼ���");
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
				alert.setTitle("���Ծ�ü �Է�");
				alert.setHeaderText("���Ծ�ü ������ ��Ȯ�� �Է��Ͻÿ�.");
				alert.setContentText("�������� �����ϼ���!");
				alert.showAndWait();
			}
		});

		btn_Sc_edit.setOnAction(event -> handlerBtn_Sc_editActoion(event)); //�Ǹ�ȸ�� ������ư ���ٽ�
		btn_Sc_init.setOnAction(event -> handlerBtn_Sc_initActoion(event)); //�Ǹ�ȸ�� �ʱ�ȭ��ư ���ٽ�
		btn_Sc_exit.setOnAction(event -> handlerBtn_Sc_exitActoion(event)); //�Ǹ�ȸ�� �����ư ���ٽ�
		btn_Sc_delete.setOnAction(event -> handlerBtn_Sc_deleteActoion(event)); //�Ǹ�ȸ�� ������ư ���ٽ�
		
		
		//�Ǹ�ȸ�� ���콺�̺�Ʈ
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
					alert.setTitle("�Ǹž�ü ���� ���� ����");
					alert.setHeaderText("�Ǹž�ü ������ �Է��Ͻÿ�.");
					alert.setContentText("�������� �����ϼ���!");
					alert.showAndWait();
				}
			}
		});

	}
	
	
	//�Ǹ�ȸ�� ����
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
	
	
	//�Ǹ�ȸ�� ����
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
			alert.setTitle("���Ծ�ü ���� ����");
			alert.setHeaderText("�����Ǵ� ������ ��Ȯ�� �Է��Ͻÿ�.");
			alert.setContentText("�������� �����ϼ���!");
			alert.showAndWait();
		}
	}
	
	//�Ǹ�ȸ�� ����
	public void handlerBtn_Sc_exitActoion(ActionEvent event) {
		Platform.exit();
	}
	
	//�Ǹ�ȸ�� �ʱ�ȭ
	public void handlerBtn_Sc_initActoion(ActionEvent event) {
		txt_Sc_name.clear();
		txt_Sc_businessnumber.clear();
		txt_Sc_ceo.clear();
		txt_Sc_cphone.clear();
		txt_Sc_address.clear();
		txt_Sc_manager.clear();
		txt_Sc_managerphone.clear();
	}
	
	
	//�Ǹ�ȸ�� ��Ż����Ʈ
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
