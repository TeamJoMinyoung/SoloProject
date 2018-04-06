package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.BuyCompanyVo;
import model.BuyProductVO;
import model.InventoryManagermentVO;
import model.SellProductVO;

public class MainViewController implements Initializable {

	// ������---------------------------------------------------------------------------
	@FXML
	private TableView<InventoryManagermentVO> tableviewIm = new TableView<>();
	@FXML
	private TextField txt_Im_Name; // ������
	@FXML
	private Button btn_Im_Search; // ������
	@FXML
	private Button btn_Im_Edit; // ������
	@FXML
	private Button btn_Im_Delete; // ������
	@FXML
	private Button btn_Im_Total; // ������
	@FXML
	private Button btn_Im_Init; // ������
	@FXML
	private Button btn_Im_Exit; // ������

	InventoryManagermentVO inventory = new InventoryManagermentVO();
	ObservableList<InventoryManagermentVO> imdata = FXCollections.observableArrayList(); // ������--------
	ObservableList<InventoryManagermentVO> selectinventory;
	int selectedinventoryIndex; // ������-------
	int im_code;
	// ������----------------------------------------------------------------------------

	// ���԰���****************************************************************************
	@FXML
	private TableView<BuyProductVO> tableviewBuy = new TableView<>(); // ���Դ� ��
	@FXML
	private Button btn_Buycompany; // ���Ծ�ü��� ��޹�ư
	@FXML
	private TextField txt_Buy_tracenumber; // ���Դ� �̷¹�ȣ�Է�
	@FXML
	private TextField txt_Buy_comname; // ���Դ� ��ȣ��
	@FXML
	private TextField txt_Buy_productname; // ���Դ� ��ǰ��
	@FXML
	private TextField txt_Buy_kg; // ���Դ� ų�μ�
	@FXML
	private TextField txt_Buy_price; // ���Դ� ���԰���
	@FXML
	private TextField txt_Buy_date; // ���Դ� ���Գ�¥
	@FXML
	private TextField txt_Buy_search; // ���Դ� �˻��ʵ�
	@FXML
	private TextField txt_Sell_sell;
	@FXML
	private TextField txt_Buy_totalprice; // ���Դ� ����

	@FXML
	private Button btn_Buy_join; // ���Դ� ��Ϲ�ư
	@FXML
	private Button btn_Buy_edit; // ���Դ� ������ư
	@FXML
	private Button btn_Buy_init; // ���Դ� �ʱ�ȭ��ư
	@FXML
	private Button btn_Buy_exit; // ���Դ� �����ư
	@FXML
	private Button btn_Buy_delete; // ���Դ� ������ư
	@FXML
	private Button btn_Buy_search; // ���Դ� �˻� ��ư
	@FXML
	private Button btn_Buy_total; // ���Դ� ��ü����Ʈ
	@FXML
	private Button btn_Buy_sum; // ���Դ� ����ư

	BuyProductVO buyproduct = new BuyProductVO();
	ObservableList<BuyProductVO> bpdata = FXCollections.observableArrayList();
	ObservableList<BuyProductVO> selectbuyproduct;
	int selectedbuyproductIndex;
	int bp_code;
	int bp_tracenumber;

	// ���԰���******************************************************************************

	// �ǸŰ���
	// ..............................................................................

	@FXML
	private TableView<SellProductVO> tableviewSell = new TableView<>(); // �ǸŴ� ���̺� ��
	@FXML
	private Button btn_Sellcompany;
	@FXML
	private TextField txt_Sell_tracenumber; // �ǸŴ� �̷¹�ȣ
	@FXML
	private TextField txt_Sell_comname; // �ǸŴ� ��ȣ��
	@FXML
	private TextField txt_Sell_productname; // �ǸŴ� ��ǰ��
	@FXML
	private TextField txt_Sell_price; // �ǸŴ� �ǸŰ���
	@FXML
	private TextField txt_Sell_kg; // �ǸŴ� ų�μ�
	@FXML
	private TextField txt_Sell_date; // �ǸŴ� �Ǹų�¥
	@FXML
	private TextField txt_Sell_search; // �ǸŴ� �˻��ʵ�
	@FXML
	private TextField txt_Sell_totalprice; // �Ǹ����� �ʵ�
	@FXML
	private Button btn_Sell_sum; // �Ǹ� ��� ��ư
	@FXML
	private Button btn_Sell_join; // �ǸŴ� ��Ϲ�ư
	@FXML
	private Button btn_Sell_sell; // �Ǵ븸 �ǸŹ�ư
	@FXML
	private Button btn_Sell_init; // �ǸŴ� �ʱ�ȭ��ư
	@FXML
	private Button btn_Sell_exit; // �ǸŴ� �����ư
	@FXML
	private Button btn_Sell_delete; // �ǸŴ� ������ư
	@FXML
	private Button btn_Sell_search; // �ǸŴ� �˻���ư
	@FXML
	private Button btn_Sell_total; // �ǸŴ� ��ü����Ʈ��ư
	@FXML
	private Button btn_Sell_sellSearch; // �ǸŴ� ����ǰ�˻�
	@FXML
	private Button btn_Sell_barchart; // �ǸŴ� ��Ʈ
	@FXML
	private Button btnExcel; // ���������ư
	@FXML
	private Button btnSaveFileDir; // ����â��ư
	@FXML
	private TextField txtSaveFileDir; // ������ġ
	@FXML
	private Button btnPDF; // PDF�����ư
	private Stage primaryStage;

	SellProductVO sellproduct = new SellProductVO();
	ObservableList<SellProductVO> spdata = FXCollections.observableArrayList();
	ObservableList<SellProductVO> selectsellproduct;

	int selectedsellproductIndex;
	int sp_code;
	int tracenumber;

	// �ǸŰ���..................................................................................

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// ������----------------------------------------------------------------------
		tableviewIm.setEditable(false);

		TableColumn colNo = new TableColumn("NO.");
		colNo.setMinWidth(40);
		colNo.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colNo.setCellValueFactory(new PropertyValueFactory<>("im_code"));

		TableColumn colTracenumber = new TableColumn("�̷¹�ȣ");
		colTracenumber.setMinWidth(150);
		colTracenumber.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colTracenumber.setCellValueFactory(new PropertyValueFactory<>("im_tracenumber"));

		TableColumn colProductname = new TableColumn("��ǰ��");
		colProductname.setMinWidth(250);
		colProductname.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colProductname.setCellValueFactory(new PropertyValueFactory<>("im_productname"));

		TableColumn colKg = new TableColumn("KG");
		colKg.setMinWidth(67);
		colKg.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colKg.setCellValueFactory(new PropertyValueFactory<>("im_kg"));

		tableviewIm.setItems(imdata);
		tableviewIm.getColumns().addAll(colNo, colTracenumber, colProductname, colKg);
		totalListinven();
		// ������ ��ü����Ʈ �����ִ�
		// â----------------------------------------------------------------------

		// ������ ��ü����Ʈ ��ư �׼�
		// -----------------------------------------------------------------------
		btn_Im_Total.setOnAction(event -> {

			try {

				imdata.removeAll(imdata);

				totalListinven();

			} catch (Exception e) {

			}

		});

		btn_Im_Edit.setOnAction(event -> handlerBtn_Im_EditActoion(event));
		btn_Im_Exit.setOnAction(event -> handlerBtn_Im_ExitActoion(event));
		btn_Im_Init.setOnAction(event -> handlerBtn_Im_InitActoion(event));
		btn_Im_Search.setOnAction(event -> handlerBtn_Im_SearchActoion(event));
		btn_Im_Delete.setOnAction(event -> handlerBtn_Im_DeleteActoion(event));

		// ������
		// ���콺�׼�------------------------------------------------------------------------
		tableviewIm.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				try {
					selectinventory = tableviewIm.getSelectionModel().getSelectedItems();
					selectedinventoryIndex = tableviewIm.getSelectionModel().getSelectedIndex();
					im_code = selectinventory.get(0).getIm_code();

				} catch (Exception e) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("���Ծ�ü ���� ���� ����");
					alert.setHeaderText("���Ծ�ü ������ �Է��Ͻÿ�.");
					alert.setContentText("�������� �����ϼ���!");
					alert.showAndWait();
				}

			}
		});

		// ������-------------------------------------------------------------------------------------

		// ���԰���*************************************************************************************

		TableColumn colbuyNo = new TableColumn("NO.");
		colbuyNo.setMinWidth(40);
		colbuyNo.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colbuyNo.setCellValueFactory(new PropertyValueFactory<>("bp_tracenumber"));

		TableColumn colbuytrace = new TableColumn("�̷¹�ȣ ");
		colbuytrace.setMinWidth(100);
		colbuytrace.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colbuytrace.setCellValueFactory(new PropertyValueFactory<>("bp_code"));

		TableColumn colbuycomname = new TableColumn("��ȣ��");
		colbuycomname.setMinWidth(100);
		colbuycomname.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colbuycomname.setCellValueFactory(new PropertyValueFactory<>("bp_name"));

		TableColumn colbuyproductname = new TableColumn("��ǰ��");
		colbuyproductname.setMinWidth(150);
		colbuyproductname.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colbuyproductname.setCellValueFactory(new PropertyValueFactory<>("bp_productname"));

		TableColumn colbuyprice = new TableColumn("���԰���");
		colbuyprice.setMinWidth(120);
		colbuyprice.setStyle("-fx-font-size: 18; -fx-text-fill: green; -fx-allignment: CENTER");
		colbuyprice.setCellValueFactory(new PropertyValueFactory<>("bp_price"));

		TableColumn colbuykg = new TableColumn("KG");
		colbuykg.setMinWidth(100);
		colbuykg.setStyle("-fx-font-size: 18; -fx-text-fill: red; -fx-allignment: CENTER");
		colbuykg.setCellValueFactory(new PropertyValueFactory<>("bp_kg"));

		TableColumn colbuydate = new TableColumn("���Գ�¥");
		colbuydate.setMinWidth(150);
		colbuydate.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colbuydate.setCellValueFactory(new PropertyValueFactory<>("bp_date"));

		TableColumn colbuysum = new TableColumn("��������");
		colbuysum.setMinWidth(150);
		colbuysum.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colbuysum.setCellValueFactory(new PropertyValueFactory<>("bp_sum"));

		tableviewBuy.setItems(bpdata);
		tableviewBuy.getColumns().addAll(colbuyNo, colbuytrace, colbuycomname, colbuyproductname, colbuyprice, colbuykg,
				colbuydate, colbuysum);

		totalListbp();

		// ���Դ�
		// ��Ϲ�ư�׼�***********************************************************************************
		btn_Buy_join.setOnAction(event -> {
			try {

				bpdata.removeAll(bpdata);
				imdata.removeAll(imdata);
				spdata.removeAll(spdata);
				BuyProductVO bpvo = null;
				BuyProductDAO bpdao = null;
				InventoryManagermentVO ivo = null;
				InventoryManagermentDAO idao = null;

				if (event.getSource().equals(btn_Buy_join)) {

					bpvo = new BuyProductVO(Integer.parseInt(txt_Buy_tracenumber.getText().trim()),
							txt_Buy_comname.getText(), txt_Buy_productname.getText(),
							Integer.parseInt(txt_Buy_price.getText().trim()),
							Integer.parseInt(txt_Buy_kg.getText().trim()), txt_Buy_date.getText(),
							Long.parseLong(txt_Buy_totalprice.getText().trim()));
					ivo = new InventoryManagermentVO(Integer.parseInt(txt_Buy_tracenumber.getText().trim()),
							txt_Buy_productname.getText(), Integer.parseInt(txt_Buy_kg.getText().trim()),
							txt_Buy_date.getText());

					bpdao = new BuyProductDAO();
					bpdao.getBuyCompanyregiste(bpvo);
					idao = new InventoryManagermentDAO();
					idao.getInventoryManagermentregiste(ivo);

					if (bpdao != null) {
						totalListinven();
						totalListbp();

						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("���Ի�ǰ ���");
						alert.setHeaderText(txt_Buy_productname.getText() + "���Ի�ǰ�� ���������� �߰��Ǿ����ϴ�..");
						alert.setContentText("���� ���Ի�ǰ�� �Է��ϼ���");
						alert.showAndWait();

						handlerBtn_Buy_initActoion(event);

					}
				}
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("���Ի�ǰ �Է�");
				alert.setHeaderText("���Ի�ǰ ������ ��Ȯ�� �Է��Ͻÿ�.");
				alert.setContentText("�������� �����ϼ���!");
				alert.showAndWait();
			}
		});

		btn_Buy_total.setOnAction(e -> {
			try {
				bpdata.removeAll(bpdata);
				totalListbp();
			} catch (Exception e1) {

			}
		});

		btn_Buycompany.setOnAction(event -> handlerBtn_BuycompanyActoion(event)); // ���Ծ�ü��� ��ư
		btn_Buy_init.setOnAction(event -> handlerBtn_Buy_initActoion(event)); // ���Դ� �ʱ�ȭ��ư
		btn_Buy_exit.setOnAction(event -> handlerBtn_Buy_exitActoion(event)); // ���Դ� �����ư
		btn_Buy_edit.setOnAction(event -> handlerBtn_Buy_editActoion(event)); // ���Դ� ������ư
		btn_Buy_delete.setOnAction(event -> handlerBtn_Buy_deleteActoion(event)); // ���Դ� ������ư
		btn_Buy_search.setOnAction(event -> handlerBtn_Buy_searchActoion(event)); // ���Դ� �˻���ư
		btn_Buy_sum.setOnAction(event -> handlerBtn_Buy_sumActoion(event));

		// ���Դ� ���콺 �̺�Ʈ
		tableviewBuy.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				try {
					selectbuyproduct = tableviewBuy.getSelectionModel().getSelectedItems();
					selectedbuyproductIndex = tableviewBuy.getSelectionModel().getSelectedIndex();
					bp_tracenumber = selectbuyproduct.get(0).getBp_tracenumber();

					txt_Buy_tracenumber.setText(selectbuyproduct.get(0).getBp_code() + "");
					txt_Buy_comname.setText(selectbuyproduct.get(0).getBp_name());
					txt_Buy_productname.setText(selectbuyproduct.get(0).getBp_productname());
					txt_Buy_price.setText(selectbuyproduct.get(0).getBp_price() + "");
					txt_Buy_kg.setText(selectbuyproduct.get(0).getBp_kg() + "");
					txt_Buy_date.setText(selectbuyproduct.get(0).getBp_date());

				} catch (Exception e) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("���Ծ�ü ���� ���� ����");
					alert.setHeaderText("���Ծ�ü ������ �Է��Ͻÿ�.");
					alert.setContentText("�������� �����ϼ���!");
					alert.showAndWait();
				}

			}
		});

		// ���Դ�
		// ****************************************************************************************************

		// �ǸŴ�
		// ................................................................................................

		TableColumn colsellNo = new TableColumn("NO.");
		colsellNo.setMinWidth(40);
		colsellNo.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colsellNo.setCellValueFactory(new PropertyValueFactory<>("sp_code"));

		TableColumn colselltrace = new TableColumn("�̷¹�ȣ ");
		colselltrace.setMinWidth(100);
		colselltrace.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colselltrace.setCellValueFactory(new PropertyValueFactory<>("sp_tracenumber"));

		TableColumn colsellcomname = new TableColumn("��ȣ��");
		colsellcomname.setMinWidth(100);
		colsellcomname.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colsellcomname.setCellValueFactory(new PropertyValueFactory<>("sp_name"));

		TableColumn colsellproductname = new TableColumn("��ǰ��");
		colsellproductname.setMinWidth(150);
		colsellproductname.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colsellproductname.setCellValueFactory(new PropertyValueFactory<>("sp_productname"));

		TableColumn colsellprice = new TableColumn("�ǸŰ���");
		colsellprice.setMinWidth(120);
		colsellprice.setStyle("-fx-font-size: 18; -fx-text-fill: green; -fx-allignment: CENTER");
		colsellprice.setCellValueFactory(new PropertyValueFactory<>("sp_price"));

		TableColumn colsellkg = new TableColumn("KG");
		colsellkg.setMinWidth(100);
		colsellkg.setStyle("-fx-font-size: 18; -fx-text-fill: red; -fx-allignment: CENTER");
		colsellkg.setCellValueFactory(new PropertyValueFactory<>("sp_kg"));

		TableColumn colselldate = new TableColumn("�Ǹų�¥");
		colselldate.setMinWidth(150);
		colselldate.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colselldate.setCellValueFactory(new PropertyValueFactory<>("sp_date"));

		TableColumn colsellsum = new TableColumn("�Ǹ��Ѿ�");
		colsellsum.setMinWidth(150);
		colsellsum.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colsellsum.setCellValueFactory(new PropertyValueFactory<>("sp_sum"));

		tableviewSell.setItems(spdata);
		tableviewSell.getColumns().addAll(colsellNo, colselltrace, colsellcomname, colsellproductname, colsellprice,
				colsellkg, colselldate, colsellsum);

		totalListsp();

		btn_Sellcompany.setOnAction(event -> handlerBtn_Sellcompany(event)); // �ǸŴ� �Ǹž�ü���
		btn_Sell_init.setOnAction(event -> handlerBtn_Sell_initActoion(event)); // �ǸŴ� �ʱ�ȭ
		btn_Sell_delete.setOnAction(event -> handlerBtn_Sell_deleteActoion(event)); // �ǸŴ� ������ư
		btn_Sell_search.setOnAction(event -> handlerBtn_Sell_searchActoion(event)); // �ǸŴ� ��ȣ��˻���ư
		btn_Sell_sellSearch.setOnAction(event -> handlerBtn_Sell_sellSearchActoion(event)); // �ǸŴ� ����ǰ �˻�
		btn_Sell_barchart.setOnAction(event -> handlerBtn_Sell_barchartActoion(event)); // �Ǵ�� ����Ʈ ��� ��ư
		btnExcel.setOnAction(event -> handlerBtnExcelActoion(event));// �������ϻ���
		btnSaveFileDir.setOnAction(event -> handlerBtnSaveFileDirActoion(event)); // ������������
		btnPDF.setOnAction(event -> handlerBtnPDFFileDirActoion(event)); // PDF���ϻ���
		btn_Sell_sum.setOnAction(event -> handlerBtn_Sell_sumActoion(event));
		// �ǸŴ� ��Ż����Ʈ
		btn_Sell_total.setOnAction(event -> {
			spdata.removeAll(spdata);
			totalListsp();
		});

		// �ǸŴ� �ǸŹ�ư
		btn_Sell_join.setOnAction(e -> {
			SellProductDAO sDao = null;
			sDao = new SellProductDAO();

			selectsellproduct = tableviewSell.getSelectionModel().getSelectedItems();
			tracenumber = selectsellproduct.get(0).getSp_tracenumber();

			try {

				sDao.sellProduct(tracenumber, sp_code);
				spdata.removeAll(spdata);
				imdata.removeAll(imdata);
				totalListsp();
				totalListinven();

			} catch (Exception e1) {
				System.out.println("�԰� �̷� ������ư ���� ����" + e1);
			}
		});

		// �ǸŰ��� �Ǹŵ��
		btn_Sell_sell.setOnAction(event -> {

			try {
				spdata.removeAll(spdata);
				SellProductVO spVo = null;
				SellProductDAO spDao = null;
				if (event.getSource().equals(btn_Sell_sell)) {

					spVo = new SellProductVO(Integer.parseInt(txt_Sell_tracenumber.getText().trim()),
							txt_Sell_comname.getText(), txt_Sell_productname.getText(),
							Integer.parseInt(txt_Sell_price.getText().trim()),
							Integer.parseInt(txt_Sell_kg.getText().trim()), txt_Sell_date.getText(),
							Long.parseLong(txt_Sell_totalprice.getText().trim()));

					spDao = new SellProductDAO();
					spDao.getSellProductregiste(spVo);

					if (spDao != null) {
						totalListsp();
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("�Ǹ� ��� �Ϸ�");
						alert.setHeaderText("�Ǹ� ��� �Ϸ�");
						alert.setContentText("�Ǹ� ��� �Ϸ�");
						alert.showAndWait();

						handlerBtn_Sell_initActoion(event);

					}
				}
			} catch (Exception e) {

			}
		});

		tableviewSell.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				try {

					selectsellproduct = tableviewSell.getSelectionModel().getSelectedItems();
					selectedsellproductIndex = tableviewSell.getSelectionModel().getSelectedIndex();
					sp_code = selectsellproduct.get(0).getSp_code();

					txt_Sell_tracenumber.setText(selectsellproduct.get(0).getSp_tracenumber() + "");
					txt_Sell_comname.setText(selectsellproduct.get(0).getSp_name());
					txt_Sell_productname.setText(selectsellproduct.get(0).getSp_productname());
					txt_Sell_price.setText(selectsellproduct.get(0).getSp_price() + "");
					txt_Sell_kg.setText(selectsellproduct.get(0).getSp_kg() + "");
					txt_Sell_date.setText(selectsellproduct.get(0).getSp_date());

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

	// �Ǹ��Ѿ� ��� ��ư
	public void handlerBtn_Sell_sumActoion(ActionEvent event) {
		int price = Integer.parseInt(txt_Sell_price.getText().trim());
		int kg = Integer.parseInt(txt_Sell_kg.getText().trim());

		long total;

		total = price * kg;

		txt_Sell_totalprice.setText(total + "");

	}

	// �����Ѿ� ��� ��ư
	public void handlerBtn_Buy_sumActoion(ActionEvent event) {

		int price = Integer.parseInt(txt_Buy_price.getText().trim());
		int kg = Integer.parseInt(txt_Buy_kg.getText().trim());

		long total;

		total = price * kg;

		txt_Buy_totalprice.setText(total + "");

	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	// PDF �����ư
	public void handlerBtnPDFFileDirActoion(ActionEvent event) {
		try {

			FXMLLoader loaderPdf = new FXMLLoader();
			loaderPdf.setLocation(getClass().getResource("/View/pdfImage.fxml"));

			Stage dialogPdf = new Stage(StageStyle.UTILITY);
			dialogPdf.initModality(Modality.WINDOW_MODAL);
			dialogPdf.initOwner(btnPDF.getScene().getWindow());
			dialogPdf.setTitle("�л� ����ǥ PDF ��Ʈ �̹��� ����");

			Parent parentPdf = (Parent) loaderPdf.load();

			Button btnPdfSave = (Button) parentPdf.lookup("#btnPdfSave");
			CheckBox cbBarImage = (CheckBox) parentPdf.lookup("#cbBarImage");
			CheckBox cbPieImage = (CheckBox) parentPdf.lookup("#cbPieImage");

			Scene scene = new Scene(parentPdf);
			dialogPdf.setScene(scene);
			dialogPdf.setResizable(false);
			dialogPdf.show();
			btnPdfSave.setOnAction(e -> {

				try {
					// pdf document ����
					// (Rectangle pageSize, float marginLeft, float marginRight, float marginTop,
					// float marginBottom)
					Document document = new Document(PageSize.A4, 0, 0, 30, 30);

					// pdf ������ ������ ������ ����. pdf������ �����ȴ�. ���� ��Ʈ������ ����.
					String strReportPDFName = "sellproduct_" + System.currentTimeMillis() + ".pdf";
					PdfWriter.getInstance(document,
							new FileOutputStream(txtSaveFileDir.getText() + "\\" + strReportPDFName));
					// document�� ���� pdf������ �����ֵ����Ѵ�..
					document.open();
					// �ѱ�������Ʈ ����..
					BaseFont bf = BaseFont.createFont("font/HMFMPYUN.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

					Font font = new Font(bf, 8, Font.NORMAL);
					Font font2 = new Font(bf, 14, Font.BOLD);

					// Ÿ��Ʋ
					Paragraph title = new Paragraph("����ǥ", font2);

					// �߰�����
					title.setAlignment(Element.ALIGN_CENTER);
					// ������ �߰�
					document.add(title);
					document.add(new Paragraph("\r\n"));
					// ���� ��¥
					/*
					 * LocalDate date = .getValue(); Paragraph writeDay = new
					 * Paragraph(date.toString(), font);
					 */
					// ������ ����
					/*
					 * writeDay.setAlignment(Element.ALIGN_RIGHT); // ������ �߰� document.add(writeDay);
					 * document.add(new Paragraph("\r\n"));
					 */

					// ���̺���� Table��ü���� PdfPTable��ü�� �� �����ϰ� ���̺��� ����� �ִ�.
					// �����ڿ� �÷����� ���ش�..
					PdfPTable table = new PdfPTable(8);
					// ������ �÷��� width�� ���Ѵ�..
					table.setWidths(new int[] { 30, 100, 100, 100, 70, 70, 100, 100 });
					// �÷� Ÿ��Ʋ..
					PdfPCell header1 = new PdfPCell(new Paragraph("NO.", font));
					PdfPCell header2 = new PdfPCell(new Paragraph("�̷¹�ȣ", font));
					PdfPCell header3 = new PdfPCell(new Paragraph("��ȣ��", font));
					PdfPCell header4 = new PdfPCell(new Paragraph("��ǰ��", font));
					PdfPCell header5 = new PdfPCell(new Paragraph("�ǸŰ���", font));
					PdfPCell header6 = new PdfPCell(new Paragraph("KG.", font));
					PdfPCell header7 = new PdfPCell(new Paragraph("�Ǹų�¥", font));
					PdfPCell header8 = new PdfPCell(new Paragraph("�Ǹ��Ѿ�", font));

					// ��������
					header1.setHorizontalAlignment(Element.ALIGN_CENTER);
					header2.setHorizontalAlignment(Element.ALIGN_CENTER);
					header3.setHorizontalAlignment(Element.ALIGN_CENTER);
					header4.setHorizontalAlignment(Element.ALIGN_CENTER);
					header5.setHorizontalAlignment(Element.ALIGN_CENTER);
					header6.setHorizontalAlignment(Element.ALIGN_CENTER);
					header7.setHorizontalAlignment(Element.ALIGN_CENTER);
					header8.setHorizontalAlignment(Element.ALIGN_CENTER);

					// ��������
					header1.setVerticalAlignment(Element.ALIGN_MIDDLE);
					header2.setVerticalAlignment(Element.ALIGN_MIDDLE);
					header3.setVerticalAlignment(Element.ALIGN_MIDDLE);
					header4.setVerticalAlignment(Element.ALIGN_MIDDLE);
					header5.setVerticalAlignment(Element.ALIGN_MIDDLE);
					header6.setVerticalAlignment(Element.ALIGN_MIDDLE);
					header7.setVerticalAlignment(Element.ALIGN_MIDDLE);
					header8.setVerticalAlignment(Element.ALIGN_MIDDLE);

					// ���̺� �߰�..
					table.addCell(header1);
					table.addCell(header2);
					table.addCell(header3);
					table.addCell(header4);
					table.addCell(header5);
					table.addCell(header6);
					table.addCell(header7);
					table.addCell(header8);

					// DB ���� �� ����Ʈ ����
					SellProductDAO spDao = new SellProductDAO();
					SellProductVO spVo = new SellProductVO();
					ArrayList<SellProductVO> list;
					list = spDao.getSellProductTotal();
					int rowCount = list.size();

					PdfPCell cell1 = null;
					PdfPCell cell2 = null;
					PdfPCell cell3 = null;
					PdfPCell cell4 = null;
					PdfPCell cell5 = null;
					PdfPCell cell6 = null;
					PdfPCell cell7 = null;
					PdfPCell cell8 = null;

					for (int index = 0; index < rowCount; index++) {
						spVo = list.get(index);
						cell1 = new PdfPCell(new Paragraph(spVo.getSp_code() + "", font));
						cell2 = new PdfPCell(new Paragraph(spVo.getSp_tracenumber() + "", font));
						cell3 = new PdfPCell(new Paragraph(spVo.getSp_name() + "", font));
						cell4 = new PdfPCell(new Paragraph(spVo.getSp_productname() + "", font));
						cell5 = new PdfPCell(new Paragraph(spVo.getSp_price() + "", font));
						cell6 = new PdfPCell(new Paragraph(spVo.getSp_kg() + "", font));
						cell7 = new PdfPCell(new Paragraph(spVo.getSp_date() + "", font));
						cell8 = new PdfPCell(new Paragraph(spVo.getSp_sum() + "", font));

						// ��������
						cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell8.setHorizontalAlignment(Element.ALIGN_CENTER);

						// ��������
						cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);

						// ���̺� �� �߰�
						table.addCell(cell1);
						table.addCell(cell2);
						table.addCell(cell3);
						table.addCell(cell4);
						table.addCell(cell5);
						table.addCell(cell6);
						table.addCell(cell7);
						table.addCell(cell8);

					}

					// ������ ���̺��߰�.
					document.add(table);
					document.add(new Paragraph("\r\n"));
					Alert alert = new Alert(AlertType.INFORMATION);
					if (cbBarImage.isSelected()) {
						// ���� �׷��� �̹��� �߰�
						Paragraph barImageTitle = new Paragraph("��ǰ�� ��ų�μ� ������Ʈ              ��¥�� ���Ǹ��Ѿ� ����׷���", font);
						barImageTitle.setAlignment(Element.ALIGN_CENTER);
						document.add(barImageTitle);
						document.add(new Paragraph("\r\n"));
						final String barImageUrl = "chartImage/studentBarChart.png";
						// ������ javafx.scene.image.Image ��ü�� ����ϰ� �־� �浹�� ���� �Ʒ���
						// ���� �����.
						com.itextpdf.text.Image barImage;
						try {
							if (com.itextpdf.text.Image.getInstance(barImageUrl) != null) {
								barImage = com.itextpdf.text.Image.getInstance(barImageUrl);
								barImage.setAlignment(Element.ALIGN_CENTER);
								barImage.scalePercent(30f);
								document.add(barImage);
								document.add(new Paragraph("\r\n"));
							}
						} catch (IOException ee) {

						}

						

					} 

					// ������ �ݴ´�. ���� ����.
					document.close();
					dialogPdf.close();
					txtSaveFileDir.clear();
					btnPDF.setDisable(true);
					btnExcel.setDisable(true);

					
					alert.setTitle("PDF ���� ����");
					alert.setHeaderText("�Ǹ� ��� PDF ���� ���� ����.");
					alert.setContentText("�Ǹ� ��� PDF ����.");
					alert.showAndWait();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (DocumentException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

	// ���� ��� ����
	public void handlerBtnSaveFileDirActoion(ActionEvent event) {
		final DirectoryChooser directoryChooser = new DirectoryChooser();
		final File selectedDirectory = directoryChooser.showDialog(primaryStage);

		if (selectedDirectory != null) {
			txtSaveFileDir.setText(selectedDirectory.getAbsolutePath());
			btnExcel.setDisable(false);
			btnPDF.setDisable(false);
		}
	}

	public void handlerBtnExcelActoion(ActionEvent event) {
		SellProductDAO spDao = new SellProductDAO();
		boolean saveSuccess;

		ArrayList<SellProductVO> list;
		list = spDao.getSellProductTotal();
		SellproductExcel excelWriter = new SellproductExcel();

		// xlsx ���� ����
		saveSuccess = excelWriter.xlsxWiter(list, txtSaveFileDir.getText());
		if (saveSuccess) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("���� ���� ����");
			alert.setHeaderText("�Ǹ� ��� ���� ���� ���� ����.");
			alert.setContentText("�Ǹ� ��� ���� ����.");
			alert.showAndWait();
		}

		txtSaveFileDir.clear();
		btnExcel.setDisable(true);
		btnPDF.setDisable(true);
	}

	// �ǸŴ� ����Ʈ �׼�
	public void handlerBtn_Sell_barchartActoion(ActionEvent event) {
		try {
			SellProductVO spvo = new SellProductVO();

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btn_Sell_barchart.getScene().getWindow());
			dialog.setTitle("���� �׷���");

			Parent parent = FXMLLoader.load(getClass().getResource("/view/barchart.fxml"));

			BarChart barChart = (BarChart) parent.lookup("#barChart");
			
			PieChart piechart = (PieChart) parent.lookup("#pieChart");
			ObservableList piedata = FXCollections.observableArrayList();
			for(int i = 0; i < spdata.size(); i++) {
				for (int index = 0; index < spdata.size(); index++) {
					if (spdata.get(index).getSp_productname().toString().equals(spdata.get(i).getSp_productname().toString())) {
						
					}
				}
				piedata.add(new PieChart.Data(spdata.get(i).getSp_productname().toString(), spdata.get(i).getSp_kg()));
			}
			
			piechart.setData(piedata);
			
			XYChart.Series totaldate = new XYChart.Series();
			totaldate.setName("��ų�μ�");
			ObservableList total = FXCollections.observableArrayList();
			long sum;
			for (int i = 0; i < spdata.size(); i++) {
				sum = 0;
				for (int index = 0; index < spdata.size(); index++) {
					if (spdata.get(index).getSp_date().toString().equals(spdata.get(i).getSp_date().toString())) {
						sum = sum + spdata.get(index).getSp_sum();
					}
				}
				total.add(new XYChart.Data(spdata.get(i).getSp_date().toString(), sum));
			}
			totaldate.setData(total);
			barChart.getData().add(totaldate);
			
			
			
			

			Button btnClose = (Button) parent.lookup("#btnClose");
			btnClose.setOnAction(e -> dialog.close());

			Scene scene = new Scene(parent);
			dialog.setScene(scene);
			dialog.show();

			// ���� �׷��� �̹��� ����
			WritableImage snapShot = scene.snapshot(null);
			ImageIO.write(SwingFXUtils.fromFXImage(snapShot, null), "png", new File("chartImage/studentBarChart.png"));

		} catch (IOException e) {

		}
	}

	// �ǸŴ� �ǸŰ˻���ư
	public void handlerBtn_Sell_sellSearchActoion(ActionEvent event) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/searchsellinventory.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btn_Sell_search.getScene().getWindow());
			dialog.setTitle("�Ǹ� ���");
			Parent parentEdit = (Parent) loader.load();

			Button btn_SBuy_search = (Button) parentEdit.lookup("#btn_SBuy_search");
			Button btn_SBuy_join = (Button) parentEdit.lookup("#btn_SBuy_join");
			Button btn_SBuy_Exit = (Button) parentEdit.lookup("#btn_SBuy_Exit");
			Button btn_SBuy_init = (Button) parentEdit.lookup("#btn_SBuy_init");
			TextField txt_Sell_tracenumberse = (TextField) parentEdit.lookup("#txt_Sell_tracenumberse");
			TextField txt_Sell_productnamese = (TextField) parentEdit.lookup("#txt_Sell_productnamese");
			TextField txt_Search = (TextField) parentEdit.lookup("#txt_Search");

			TableView<InventoryManagermentVO> tableviewsbc = (TableView<InventoryManagermentVO>) parentEdit
					.lookup("#tableviewsbc");

			tableviewsbc.setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					try {
						selectinventory = tableviewsbc.getSelectionModel().getSelectedItems();
						selectedinventoryIndex = tableviewsbc.getSelectionModel().getSelectedIndex();
						im_code = selectinventory.get(0).getIm_code();

						txt_Sell_tracenumberse.setText(selectinventory.get(0).getIm_tracenumber() + "");
						txt_Sell_productnamese.setText(selectinventory.get(0).getIm_productname());

					} catch (Exception e) {

					}

				}
			});

			TableColumn colname = new TableColumn("�̷¹�ȣ");
			colname.setMinWidth(128);
			colname.setStyle("-fx-allignment:CENTER");
			colname.setCellValueFactory(new PropertyValueFactory<>("im_tracenumber"));

			TableColumn colmanagerphone = new TableColumn("��ǰ��");
			colmanagerphone.setMinWidth(170);
			colmanagerphone.setStyle("-fx-allignment:CENTER");
			colmanagerphone.setCellValueFactory(new PropertyValueFactory<>("im_productname"));

			tableviewsbc.setItems(imdata);
			tableviewsbc.getColumns().addAll(colname, colmanagerphone);

			btn_SBuy_search.setOnAction(e -> {
				InventoryManagermentVO iVo = new InventoryManagermentVO();
				InventoryManagermentDAO iDao = null;
				Object[][] totalData = null;

				int searchNumber = 0;
				boolean searchResult = false;

				try {
					searchNumber = Integer.parseInt(txt_Search.getText().trim());
					iDao = new InventoryManagermentDAO();
					iVo = iDao.getSellInventoryCheack(searchNumber);

					if (searchNumber == 0) {
						searchResult = true;
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("��� ���� �˻�");
						alert.setHeaderText("�̷¹�ȣ�� �Է��Ͻÿ�.");
						alert.setContentText("�������� �����ϼ���!");
						alert.showAndWait();
					}

					ArrayList<String> title;
					ArrayList<InventoryManagermentVO> list;

					title = iDao.getColunmnNameinven();
					int columnCount = title.size();

					list = iDao.getInventorymanagermentTotal();
					int rowCount = list.size();

					totalData = new Object[rowCount][columnCount];

					if (iVo.getIm_tracenumber() == searchNumber) {
						txt_Search.clear();
						imdata.removeAll(imdata);
						for (int index = 0; index < rowCount; index++) {
							System.out.println(index);
							iVo = list.get(index);
							if (iVo.getIm_tracenumber() == searchNumber) {
								imdata.add(iVo);
								searchResult = true;
							}
						}
					}

					if (!searchResult) {
						txt_Search.clear();
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("��ǰ ���� �˻�");
						alert.setHeaderText(searchNumber + " ����Ʈ�� �����ϴ�.");
						alert.setContentText("�ٽ� �˻��ϼ���.");
						alert.showAndWait();

					}
				} catch (Exception e1) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("��ǰ ���� �˻�����");
					alert.setHeaderText("�˻��� ������ �߻��Ͽ����ϴ�.");
					alert.setContentText("�ٽ� �ϼ���.");
					alert.showAndWait();
					e1.printStackTrace();
				}

			});

			btn_SBuy_join.setOnAction(e -> {
				txt_Sell_tracenumber.setText(txt_Sell_tracenumberse.getText());
				txt_Sell_productname.setText(txt_Sell_productnamese.getText());
				dialog.close();
			});

			btn_SBuy_Exit.setOnAction(e -> {
				dialog.close();
			});

			btn_SBuy_init.setOnAction(e -> {
				txt_Sell_tracenumberse.clear();
				txt_Sell_productnamese.clear();
				imdata.removeAll(imdata);
				totalListinven();
			});

			Scene scene = new Scene(parentEdit);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();

		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	// �ǸŴ� ��ȣ�� �˻� �̺�Ʈ...................................................
	public void handlerBtn_Sell_searchActoion(ActionEvent event) {
		SellProductVO spVo = new SellProductVO();
		SellProductDAO spDao = null;

		Object[][] totalData = null;

		String searchName = "";
		boolean searchResult = false;

		try {
			searchName = txt_Sell_search.getText().trim();
			spDao = new SellProductDAO();
			spVo = spDao.getSellproductCheack(searchName);

			if (searchName.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("�Ǹ� ���� �˻�");
				alert.setHeaderText("��ȣ���� �̸��� �Է��Ͻÿ�.");
				alert.setContentText("�������� �����ϼ���!");
				alert.showAndWait();
			}
			if (!searchName.equals("") && (spVo != null)) {
				ArrayList<String> title;
				ArrayList<SellProductVO> list;

				title = spDao.getColumnName();
				int columnCount = title.size();

				list = spDao.getSellProductTotal();
				int rowCount = list.size();

				totalData = new Object[rowCount][columnCount];

				if (spVo.getSp_name().equals(searchName)) {
					txt_Sell_search.clear();
					spdata.removeAll(spdata);
					for (int index = 0; index < rowCount; index++) {
						System.out.println(index);
						spVo = list.get(index);
						if (spVo.getSp_name().equals(searchName)) {
							spdata.add(spVo);
							searchResult = true;
						}
					}
				}
			}

			if (!searchResult) {
				txt_Sell_search.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�Ǹ� ���� �˻�");
				alert.setHeaderText(searchName + " ��ȣ���� ����Ʈ�� �����ϴ�.");
				alert.setContentText("�ٽ� �˻��ϼ���.");
				alert.showAndWait();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("�Ǹ� ���� �˻� ����");
			alert.setHeaderText("�Ǹ� ���� �˻��� ������ �߻��Ͽ����ϴ�.");
			alert.setContentText("�ٽ� �ϼ���.");
			alert.showAndWait();
		}
	}

	// �ǸŴ� �����̺�Ʈ ������ ���ÿ� ������Ʈ�Ͽ� ��� �����Ѵ�.........................
	public void handlerBtn_Sell_deleteActoion(ActionEvent event) {
		SellProductDAO spDao = null;
		spDao = new SellProductDAO();
		selectsellproduct = tableviewSell.getSelectionModel().getSelectedItems();
		tracenumber = selectsellproduct.get(0).getSp_tracenumber();

		try {
			spDao.selldelete(tracenumber, sp_code);
			spDao.getSellproductDelete(sp_code);
			imdata.removeAll(imdata);
			spdata.removeAll(spdata);
			totalListinven();
			totalListsp();

			handlerBtn_Sell_initActoion(event);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// �ǸŴ� �ʱ�ȭ ��ư...............................................................
	public void handlerBtn_Sell_initActoion(ActionEvent event) {
		txt_Sell_tracenumber.clear();
		txt_Sell_comname.clear();
		txt_Sell_productname.clear();
		txt_Sell_price.clear();
		txt_Sell_kg.clear();
		txt_Sell_date.clear();

	}

	// �Ǹŵ�Ͼ�ü ��ư�׼�............................................................
	public void handlerBtn_Sellcompany(ActionEvent event) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/sellcompanyjoin.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btn_Sellcompany.getScene().getWindow());
			dialog.setTitle("�Ǹž�ü���");
			Parent parentEdit = (Parent) loader.load();
			Scene scene = new Scene(parentEdit);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();

		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	// ������ ������ư
	// �׼�-----------------------------------------------------------------------------------------
	public void handlerBtn_Im_DeleteActoion(ActionEvent event) {
		InventoryManagermentDAO iDao = null;
		iDao = new InventoryManagermentDAO();

		try {
			iDao.getInventoryDelete(im_code);
			imdata.removeAll(imdata);

			totalListinven();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ����ȸ�� �˻�
	public void handlerBtn_Buy_searchActoion(ActionEvent event) {

		BuyProductVO bpVo = new BuyProductVO();
		BuyProductDAO bpDao = null;

		Object[][] totalData = null;
		String searchName = "";
		boolean searchResult = false;
		try {
			searchName = txt_Buy_search.getText().trim();
			bpDao = new BuyProductDAO();
			bpVo = bpDao.getBuyProductCheack(searchName);

			if (searchName.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("���Ի�ȣ�� ���� �˻�");
				alert.setHeaderText("���Ի�ȣ���� �̸��� �Է��Ͻÿ�.");
				alert.setContentText("�������� �����ϼ���!");
				alert.showAndWait();
			}
			if (!searchName.equals("") && (bpVo != null)) {
				ArrayList<String> title;
				ArrayList<BuyProductVO> list;

				title = bpDao.getColumnName();
				int columnCount = title.size();

				list = bpDao.getBuyProductTotal();
				int rowCount = list.size();

				totalData = new Object[rowCount][columnCount];

				if (bpVo.getBp_name().equals(searchName)) {
					txt_Buy_search.clear();
					bpdata.removeAll(bpdata);
					for (int index = 0; index < rowCount; index++) {
						System.out.println(index);
						bpVo = list.get(index);
						if (bpVo.getBp_name().equals(searchName)) {
							bpdata.add(bpVo);
							searchResult = true;
						}
					}
				}
			}

			if (!searchResult) {
				txt_Buy_search.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���Ի�ȣ�� ���� �˻�");
				alert.setHeaderText(searchName + " ���Ի�ȣ���� ����Ʈ�� �����ϴ�.");
				alert.setContentText("�ٽ� �˻��ϼ���.");
				alert.showAndWait();
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("���Ի�ȣ�� �˻� ����");
			alert.setHeaderText("���Ի�ȣ�� �˻��� ������ �߻��Ͽ����ϴ�.");
			alert.setContentText("�ٽ� �ϼ���.");
			alert.showAndWait();
		}

	}

	// ������ �˻���ư �׼�
	// ----------------------------------------------------------------------------------------------
	public void handlerBtn_Im_SearchActoion(ActionEvent event) {
		InventoryManagermentVO iVo = new InventoryManagermentVO();
		InventoryManagermentDAO iDao = null;

		Object[][] totalData = null;

		String searchName = "";
		boolean searchResult = false;

		try {
			searchName = txt_Im_Name.getText().trim();
			iDao = new InventoryManagermentDAO();
			iVo = iDao.getInventoryCheack(searchName);

			if (searchName.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("��� ���� �˻�");
				alert.setHeaderText("��ǰ���� �̸��� �Է��Ͻÿ�.");
				alert.setContentText("�������� �����ϼ���!");
				alert.showAndWait();
			}

			if (!searchName.equals("") && (iVo != null)) {
				ArrayList<String> title;
				ArrayList<InventoryManagermentVO> list;

				title = iDao.getColunmnNameinven();
				int columnCount = title.size();

				list = iDao.getInventorymanagermentTotal();
				int rowCount = list.size();

				totalData = new Object[rowCount][columnCount];

				if (iVo.getIm_productname().equals(searchName)) {
					txt_Im_Name.clear();
					imdata.removeAll(imdata);
					for (int index = 0; index < rowCount; index++) {
						System.out.println(index);
						iVo = list.get(index);
						if (iVo.getIm_productname().equals(searchName)) {
							imdata.add(iVo);
							searchResult = true;
						}
					}
				}
			}

			if (!searchResult) {
				txt_Im_Name.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("��� ���� �˻�");
				alert.setHeaderText(searchName + " ��ǰ�� ����Ʈ�� �����ϴ�.");
				alert.setContentText("�ٽ� �˻��ϼ���.");
				alert.showAndWait();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("��� ���� �˻� ����");
			alert.setHeaderText("��� ���� �˻��� ������ �߻��Ͽ����ϴ�.");
			alert.setContentText("�ٽ� �ϼ���.");
			alert.showAndWait();
		}
	}

	// ���Դ� ���� ��ư*****************************************************************
	public void handlerBtn_Buy_deleteActoion(ActionEvent event) {
		BuyProductDAO bpDao = null;
		bpDao = new BuyProductDAO();

		try {
			bpDao.getBuycompanyDelete(bp_tracenumber);
			bpdata.removeAll(bpdata);

			totalListbp();
			handlerBtn_Buy_initActoion(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ���Դ� ���� ��ư******************************************************
	public void handlerBtn_Buy_editActoion(ActionEvent event) {
		BuyProductVO bpvo = null;
		BuyProductDAO bpdao = null;

		try {
			selectbuyproduct = tableviewBuy.getSelectionModel().getSelectedItems();
			selectedbuyproductIndex = tableviewBuy.getSelectionModel().getSelectedIndex();
			bpdata.remove(selectedbuyproductIndex);
			bpvo = new BuyProductVO(Integer.parseInt(txt_Buy_tracenumber.getText().trim()), txt_Buy_comname.getText(),
					txt_Buy_productname.getText(), Integer.parseInt(txt_Buy_price.getText().trim()),
					Integer.parseInt(txt_Buy_kg.getText().trim()), txt_Buy_date.getText());
			handlerBtn_Buy_initActoion(event);

			bpdao = new BuyProductDAO();
			bpdao.getBuyproductUpdate(bpvo, bp_code);
			bpdata.removeAll(bpdata);
			totalListbp();

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���Ծ�ü ���� ����");
			alert.setHeaderText("�����Ǵ� ������ ��Ȯ�� �Է��Ͻÿ�.");
			alert.setContentText("�������� �����ϼ���!");
			alert.showAndWait();
		}
	}

	// ���Դ� �����ư*************************************************************
	public void handlerBtn_Buy_exitActoion(ActionEvent event) {
		Platform.exit();
	}

	// ���Դ� �ʱ�ȭ��ư*****************************************************
	public void handlerBtn_Buy_initActoion(ActionEvent event) {
		txt_Buy_tracenumber.clear();
		txt_Buy_comname.clear();
		txt_Buy_productname.clear();
		txt_Buy_price.clear();
		txt_Buy_kg.clear();
		txt_Buy_date.clear();
	}

	// ���Դ� ���Ծ�ü��Ϲ�ư*******************************************
	public void handlerBtn_BuycompanyActoion(ActionEvent event) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/buycompanyjoin.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btn_Buycompany.getScene().getWindow());
			dialog.setTitle("���Ծ�ü���");
			Parent parentEdit = (Parent) loader.load();
			Scene scene = new Scene(parentEdit);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();

		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	// ������ �ʱ�ȭ��ư------------------------------------------
	private void handlerBtn_Im_InitActoion(ActionEvent event) {
		txt_Im_Name.clear();
	}

	// ������ �����ư---------------------------------------------
	private void handlerBtn_Im_ExitActoion(ActionEvent event) {
		Platform.exit();
	}

	// ������ ���� ���â-------------------------------------------
	public void handlerBtn_Im_EditActoion(ActionEvent event) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/inventoryjoin.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btn_Im_Edit.getScene().getWindow());
			dialog.setTitle("����");
			Parent parentEdit = (Parent) loader.load();
			InventoryManagermentVO inventoryEdit = tableviewIm.getSelectionModel().getSelectedItem();
			selectedinventoryIndex = tableviewIm.getSelectionModel().getSelectedIndex();

			TextField editTracenumber = (TextField) parentEdit.lookup("#txt_Im_TraceNumber");
			TextField editProductname = (TextField) parentEdit.lookup("#txt_Im_ProductName");
			TextField editKg = (TextField) parentEdit.lookup("#txt_Im_Kg");

			editTracenumber.setText(inventoryEdit.getIm_tracenumber() + "");
			editProductname.setText(inventoryEdit.getIm_productname());
			editKg.setText(inventoryEdit.getIm_kg() + "");

			Button btn_Im_Editjoin = (Button) parentEdit.lookup("#btn_Im_Editjoin");
			Button btn_Im_Exit = (Button) parentEdit.lookup("#btn_Im_Exit");

			btn_Im_Editjoin.setOnAction(e -> {
				InventoryManagermentVO iVo = null;
				InventoryManagermentDAO iDao = null;

				TextField txtTracenumber = (TextField) parentEdit.lookup("#txt_Im_TraceNumber");
				TextField txtProductname = (TextField) parentEdit.lookup("#txt_Im_ProductName");
				TextField txtKg = (TextField) parentEdit.lookup("#txt_Im_Kg");

				imdata.remove(selectedinventoryIndex);

				try {
					iVo = new InventoryManagermentVO(Integer.parseInt(txtTracenumber.getText().trim()),
							txtProductname.getText(), Integer.parseInt(txtKg.getText().trim()));

					iDao = new InventoryManagermentDAO();
					iDao.getInventoryUpdate(iVo, im_code);
					imdata.removeAll(imdata);
					totalListinven();
					dialog.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			});

			btn_Im_Exit.setOnAction(e -> {
				dialog.close();
			});
			Scene scene = new Scene(parentEdit);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();

			btn_Im_Exit.setOnAction(e -> {
				dialog.close();
			});
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	// ���Դ� ��Ż����Ʈ*************************************************
	public void totalListbp() {
		Object[][] totalData;

		BuyProductDAO bpDao = new BuyProductDAO();
		BuyProductVO bpVo = null;
		ArrayList<String> title;
		ArrayList<BuyProductVO> list;

		title = bpDao.getColumnName();
		int columnCount = title.size();

		list = bpDao.getBuyProductTotal();
		int rowCount = list.size();

		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			bpVo = list.get(index);
			bpdata.add(bpVo);
		}

	}

	// ������ ��Ż����Ʈ-----------------------------------------------------------
	public void totalListinven() {
		Object[][] totalDatainven;

		InventoryManagermentDAO iDao = new InventoryManagermentDAO();
		InventoryManagermentVO iVo = null;
		ArrayList<String> title;
		ArrayList<InventoryManagermentVO> list;

		title = iDao.getColunmnNameinven();
		int columnCount = title.size();

		list = iDao.getInventorymanagermentTotal();
		int rowCount = list.size();

		totalDatainven = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			iVo = list.get(index);
			imdata.add(iVo);
		}
	}

	// �ǸŴ� ��Ż����Ʈ
	public void totalListsp() {
		Object[][] totalDatasell;

		SellProductDAO spDao = new SellProductDAO();
		SellProductVO spVo = null;
		ArrayList<String> title;
		ArrayList<SellProductVO> list;

		title = spDao.getColumnName();
		int columnCount = title.size();

		list = spDao.getSellProductTotal();
		int rowCount = list.size();

		totalDatasell = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			spVo = list.get(index);
			spdata.add(spVo);
		}
	}

}
