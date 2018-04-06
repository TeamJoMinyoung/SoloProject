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

	// 재고관리---------------------------------------------------------------------------
	@FXML
	private TableView<InventoryManagermentVO> tableviewIm = new TableView<>();
	@FXML
	private TextField txt_Im_Name; // 재고관리
	@FXML
	private Button btn_Im_Search; // 재고관리
	@FXML
	private Button btn_Im_Edit; // 재고관리
	@FXML
	private Button btn_Im_Delete; // 재고관리
	@FXML
	private Button btn_Im_Total; // 재고관리
	@FXML
	private Button btn_Im_Init; // 재고관리
	@FXML
	private Button btn_Im_Exit; // 재고관리

	InventoryManagermentVO inventory = new InventoryManagermentVO();
	ObservableList<InventoryManagermentVO> imdata = FXCollections.observableArrayList(); // 재고관리--------
	ObservableList<InventoryManagermentVO> selectinventory;
	int selectedinventoryIndex; // 재고관리-------
	int im_code;
	// 재고관리----------------------------------------------------------------------------

	// 매입관리****************************************************************************
	@FXML
	private TableView<BuyProductVO> tableviewBuy = new TableView<>(); // 매입단 뷰
	@FXML
	private Button btn_Buycompany; // 매입업체등록 모달버튼
	@FXML
	private TextField txt_Buy_tracenumber; // 매입단 이력번호입력
	@FXML
	private TextField txt_Buy_comname; // 매입단 상호명
	@FXML
	private TextField txt_Buy_productname; // 매입단 상품명
	@FXML
	private TextField txt_Buy_kg; // 매입단 킬로수
	@FXML
	private TextField txt_Buy_price; // 매입단 매입가격
	@FXML
	private TextField txt_Buy_date; // 매입단 매입날짜
	@FXML
	private TextField txt_Buy_search; // 매입단 검색필드
	@FXML
	private TextField txt_Sell_sell;
	@FXML
	private TextField txt_Buy_totalprice; // 매입단 총합

	@FXML
	private Button btn_Buy_join; // 매입단 등록버튼
	@FXML
	private Button btn_Buy_edit; // 매입단 수정버튼
	@FXML
	private Button btn_Buy_init; // 매입단 초기화버튼
	@FXML
	private Button btn_Buy_exit; // 매입단 종료버튼
	@FXML
	private Button btn_Buy_delete; // 매입단 삭제버튼
	@FXML
	private Button btn_Buy_search; // 매입단 검색 버튼
	@FXML
	private Button btn_Buy_total; // 매입단 전체리스트
	@FXML
	private Button btn_Buy_sum; // 매입단 계산버튼

	BuyProductVO buyproduct = new BuyProductVO();
	ObservableList<BuyProductVO> bpdata = FXCollections.observableArrayList();
	ObservableList<BuyProductVO> selectbuyproduct;
	int selectedbuyproductIndex;
	int bp_code;
	int bp_tracenumber;

	// 매입관리******************************************************************************

	// 판매관리
	// ..............................................................................

	@FXML
	private TableView<SellProductVO> tableviewSell = new TableView<>(); // 판매단 테이블 뷰
	@FXML
	private Button btn_Sellcompany;
	@FXML
	private TextField txt_Sell_tracenumber; // 판매단 이력번호
	@FXML
	private TextField txt_Sell_comname; // 판매단 상호명
	@FXML
	private TextField txt_Sell_productname; // 판매단 상품명
	@FXML
	private TextField txt_Sell_price; // 판매단 판매가격
	@FXML
	private TextField txt_Sell_kg; // 판매단 킬로수
	@FXML
	private TextField txt_Sell_date; // 판매단 판매날짜
	@FXML
	private TextField txt_Sell_search; // 판매단 검색필드
	@FXML
	private TextField txt_Sell_totalprice; // 판매총합 필드
	@FXML
	private Button btn_Sell_sum; // 판매 계산 버튼
	@FXML
	private Button btn_Sell_join; // 판매단 등록버튼
	@FXML
	private Button btn_Sell_sell; // 판대만 판매버튼
	@FXML
	private Button btn_Sell_init; // 판매단 초기화버튼
	@FXML
	private Button btn_Sell_exit; // 판매단 종료버튼
	@FXML
	private Button btn_Sell_delete; // 판매단 삭제버튼
	@FXML
	private Button btn_Sell_search; // 판매단 검색버튼
	@FXML
	private Button btn_Sell_total; // 판매단 전체리스트버튼
	@FXML
	private Button btn_Sell_sellSearch; // 판매단 재고상품검색
	@FXML
	private Button btn_Sell_barchart; // 판매단 차트
	@FXML
	private Button btnExcel; // 엑셀저장버튼
	@FXML
	private Button btnSaveFileDir; // 파일창버튼
	@FXML
	private TextField txtSaveFileDir; // 파일위치
	@FXML
	private Button btnPDF; // PDF저장버튼
	private Stage primaryStage;

	SellProductVO sellproduct = new SellProductVO();
	ObservableList<SellProductVO> spdata = FXCollections.observableArrayList();
	ObservableList<SellProductVO> selectsellproduct;

	int selectedsellproductIndex;
	int sp_code;
	int tracenumber;

	// 판매관리..................................................................................

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 재고관리----------------------------------------------------------------------
		tableviewIm.setEditable(false);

		TableColumn colNo = new TableColumn("NO.");
		colNo.setMinWidth(40);
		colNo.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colNo.setCellValueFactory(new PropertyValueFactory<>("im_code"));

		TableColumn colTracenumber = new TableColumn("이력번호");
		colTracenumber.setMinWidth(150);
		colTracenumber.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colTracenumber.setCellValueFactory(new PropertyValueFactory<>("im_tracenumber"));

		TableColumn colProductname = new TableColumn("상품명");
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
		// 재고관리 전체리스트 보여주는
		// 창----------------------------------------------------------------------

		// 재고관리 전체리스트 버튼 액션
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

		// 재고관리
		// 마우스액션------------------------------------------------------------------------
		tableviewIm.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				try {
					selectinventory = tableviewIm.getSelectionModel().getSelectedItems();
					selectedinventoryIndex = tableviewIm.getSelectionModel().getSelectedIndex();
					im_code = selectinventory.get(0).getIm_code();

				} catch (Exception e) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("매입업체 정보 수정 삭제");
					alert.setHeaderText("매입업체 정보를 입력하시오.");
					alert.setContentText("다음에는 주의하세요!");
					alert.showAndWait();
				}

			}
		});

		// 재고관리-------------------------------------------------------------------------------------

		// 매입관리*************************************************************************************

		TableColumn colbuyNo = new TableColumn("NO.");
		colbuyNo.setMinWidth(40);
		colbuyNo.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colbuyNo.setCellValueFactory(new PropertyValueFactory<>("bp_tracenumber"));

		TableColumn colbuytrace = new TableColumn("이력번호 ");
		colbuytrace.setMinWidth(100);
		colbuytrace.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colbuytrace.setCellValueFactory(new PropertyValueFactory<>("bp_code"));

		TableColumn colbuycomname = new TableColumn("상호명");
		colbuycomname.setMinWidth(100);
		colbuycomname.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colbuycomname.setCellValueFactory(new PropertyValueFactory<>("bp_name"));

		TableColumn colbuyproductname = new TableColumn("상품명");
		colbuyproductname.setMinWidth(150);
		colbuyproductname.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colbuyproductname.setCellValueFactory(new PropertyValueFactory<>("bp_productname"));

		TableColumn colbuyprice = new TableColumn("매입가격");
		colbuyprice.setMinWidth(120);
		colbuyprice.setStyle("-fx-font-size: 18; -fx-text-fill: green; -fx-allignment: CENTER");
		colbuyprice.setCellValueFactory(new PropertyValueFactory<>("bp_price"));

		TableColumn colbuykg = new TableColumn("KG");
		colbuykg.setMinWidth(100);
		colbuykg.setStyle("-fx-font-size: 18; -fx-text-fill: red; -fx-allignment: CENTER");
		colbuykg.setCellValueFactory(new PropertyValueFactory<>("bp_kg"));

		TableColumn colbuydate = new TableColumn("매입날짜");
		colbuydate.setMinWidth(150);
		colbuydate.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colbuydate.setCellValueFactory(new PropertyValueFactory<>("bp_date"));

		TableColumn colbuysum = new TableColumn("매입총합");
		colbuysum.setMinWidth(150);
		colbuysum.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colbuysum.setCellValueFactory(new PropertyValueFactory<>("bp_sum"));

		tableviewBuy.setItems(bpdata);
		tableviewBuy.getColumns().addAll(colbuyNo, colbuytrace, colbuycomname, colbuyproductname, colbuyprice, colbuykg,
				colbuydate, colbuysum);

		totalListbp();

		// 매입단
		// 등록버튼액션***********************************************************************************
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
						alert.setTitle("매입상품 등록");
						alert.setHeaderText(txt_Buy_productname.getText() + "매입상품이 성공적으로 추가되었습니다..");
						alert.setContentText("다음 매입상품를 입력하세요");
						alert.showAndWait();

						handlerBtn_Buy_initActoion(event);

					}
				}
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("매입상품 입력");
				alert.setHeaderText("매입상품 정보를 정확히 입력하시오.");
				alert.setContentText("다음에는 주의하세요!");
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

		btn_Buycompany.setOnAction(event -> handlerBtn_BuycompanyActoion(event)); // 매입업체등록 버튼
		btn_Buy_init.setOnAction(event -> handlerBtn_Buy_initActoion(event)); // 매입단 초기화버튼
		btn_Buy_exit.setOnAction(event -> handlerBtn_Buy_exitActoion(event)); // 매입단 종료버튼
		btn_Buy_edit.setOnAction(event -> handlerBtn_Buy_editActoion(event)); // 매입단 수정버튼
		btn_Buy_delete.setOnAction(event -> handlerBtn_Buy_deleteActoion(event)); // 매입단 삭제버튼
		btn_Buy_search.setOnAction(event -> handlerBtn_Buy_searchActoion(event)); // 매입단 검색버튼
		btn_Buy_sum.setOnAction(event -> handlerBtn_Buy_sumActoion(event));

		// 매입단 마우스 이벤트
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
					alert.setTitle("매입업체 정보 수정 삭제");
					alert.setHeaderText("매입업체 정보를 입력하시오.");
					alert.setContentText("다음에는 주의하세요!");
					alert.showAndWait();
				}

			}
		});

		// 매입단
		// ****************************************************************************************************

		// 판매단
		// ................................................................................................

		TableColumn colsellNo = new TableColumn("NO.");
		colsellNo.setMinWidth(40);
		colsellNo.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colsellNo.setCellValueFactory(new PropertyValueFactory<>("sp_code"));

		TableColumn colselltrace = new TableColumn("이력번호 ");
		colselltrace.setMinWidth(100);
		colselltrace.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colselltrace.setCellValueFactory(new PropertyValueFactory<>("sp_tracenumber"));

		TableColumn colsellcomname = new TableColumn("상호명");
		colsellcomname.setMinWidth(100);
		colsellcomname.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colsellcomname.setCellValueFactory(new PropertyValueFactory<>("sp_name"));

		TableColumn colsellproductname = new TableColumn("상품명");
		colsellproductname.setMinWidth(150);
		colsellproductname.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colsellproductname.setCellValueFactory(new PropertyValueFactory<>("sp_productname"));

		TableColumn colsellprice = new TableColumn("판매가격");
		colsellprice.setMinWidth(120);
		colsellprice.setStyle("-fx-font-size: 18; -fx-text-fill: green; -fx-allignment: CENTER");
		colsellprice.setCellValueFactory(new PropertyValueFactory<>("sp_price"));

		TableColumn colsellkg = new TableColumn("KG");
		colsellkg.setMinWidth(100);
		colsellkg.setStyle("-fx-font-size: 18; -fx-text-fill: red; -fx-allignment: CENTER");
		colsellkg.setCellValueFactory(new PropertyValueFactory<>("sp_kg"));

		TableColumn colselldate = new TableColumn("판매날짜");
		colselldate.setMinWidth(150);
		colselldate.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colselldate.setCellValueFactory(new PropertyValueFactory<>("sp_date"));

		TableColumn colsellsum = new TableColumn("판매총액");
		colsellsum.setMinWidth(150);
		colsellsum.setStyle("-fx-font-size: 18; -fx-text-fill: white; -fx-allignment: CENTER");
		colsellsum.setCellValueFactory(new PropertyValueFactory<>("sp_sum"));

		tableviewSell.setItems(spdata);
		tableviewSell.getColumns().addAll(colsellNo, colselltrace, colsellcomname, colsellproductname, colsellprice,
				colsellkg, colselldate, colsellsum);

		totalListsp();

		btn_Sellcompany.setOnAction(event -> handlerBtn_Sellcompany(event)); // 판매단 판매업체등록
		btn_Sell_init.setOnAction(event -> handlerBtn_Sell_initActoion(event)); // 판매단 초기화
		btn_Sell_delete.setOnAction(event -> handlerBtn_Sell_deleteActoion(event)); // 판매단 삭제버튼
		btn_Sell_search.setOnAction(event -> handlerBtn_Sell_searchActoion(event)); // 판매단 상호명검색버튼
		btn_Sell_sellSearch.setOnAction(event -> handlerBtn_Sell_sellSearchActoion(event)); // 판매단 재고상품 검색
		btn_Sell_barchart.setOnAction(event -> handlerBtn_Sell_barchartActoion(event)); // 판대단 바차트 모달 버튼
		btnExcel.setOnAction(event -> handlerBtnExcelActoion(event));// 엑셀파일생성
		btnSaveFileDir.setOnAction(event -> handlerBtnSaveFileDirActoion(event)); // 파일저장폴더
		btnPDF.setOnAction(event -> handlerBtnPDFFileDirActoion(event)); // PDF파일생성
		btn_Sell_sum.setOnAction(event -> handlerBtn_Sell_sumActoion(event));
		// 판매단 토탈리스트
		btn_Sell_total.setOnAction(event -> {
			spdata.removeAll(spdata);
			totalListsp();
		});

		// 판매단 판매버튼
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
				System.out.println("입고 이력 삭제버튼 관련 오류" + e1);
			}
		});

		// 판매관리 판매등록
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
						alert.setTitle("판매 등록 완료");
						alert.setHeaderText("판매 등록 완료");
						alert.setContentText("판매 등록 완료");
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
					alert.setTitle("판매업체 정보 수정 삭제");
					alert.setHeaderText("판매업체 정보를 입력하시오.");
					alert.setContentText("다음에는 주의하세요!");
					alert.showAndWait();
				}
			}
		});

	}

	// 판매총액 계산 버튼
	public void handlerBtn_Sell_sumActoion(ActionEvent event) {
		int price = Integer.parseInt(txt_Sell_price.getText().trim());
		int kg = Integer.parseInt(txt_Sell_kg.getText().trim());

		long total;

		total = price * kg;

		txt_Sell_totalprice.setText(total + "");

	}

	// 매입총액 계산 버튼
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

	// PDF 저장버튼
	public void handlerBtnPDFFileDirActoion(ActionEvent event) {
		try {

			FXMLLoader loaderPdf = new FXMLLoader();
			loaderPdf.setLocation(getClass().getResource("/View/pdfImage.fxml"));

			Stage dialogPdf = new Stage(StageStyle.UTILITY);
			dialogPdf.initModality(Modality.WINDOW_MODAL);
			dialogPdf.initOwner(btnPDF.getScene().getWindow());
			dialogPdf.setTitle("학생 성적표 PDF 차트 이미지 선택");

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
					// pdf document 선언
					// (Rectangle pageSize, float marginLeft, float marginRight, float marginTop,
					// float marginBottom)
					Document document = new Document(PageSize.A4, 0, 0, 30, 30);

					// pdf 파일을 저장할 공간을 선언. pdf파일이 생성된다. 그후 스트림으로 저장.
					String strReportPDFName = "sellproduct_" + System.currentTimeMillis() + ".pdf";
					PdfWriter.getInstance(document,
							new FileOutputStream(txtSaveFileDir.getText() + "\\" + strReportPDFName));
					// document를 열어 pdf문서를 쓸수있도록한다..
					document.open();
					// 한글지원폰트 설정..
					BaseFont bf = BaseFont.createFont("font/HMFMPYUN.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

					Font font = new Font(bf, 8, Font.NORMAL);
					Font font2 = new Font(bf, 14, Font.BOLD);

					// 타이틀
					Paragraph title = new Paragraph("매출표", font2);

					// 중간정렬
					title.setAlignment(Element.ALIGN_CENTER);
					// 문서에 추가
					document.add(title);
					document.add(new Paragraph("\r\n"));
					// 생성 날짜
					/*
					 * LocalDate date = .getValue(); Paragraph writeDay = new
					 * Paragraph(date.toString(), font);
					 */
					// 오른쪽 정렬
					/*
					 * writeDay.setAlignment(Element.ALIGN_RIGHT); // 문서에 추가 document.add(writeDay);
					 * document.add(new Paragraph("\r\n"));
					 */

					// 테이블생성 Table객체보다 PdfPTable객체가 더 정교하게 테이블을 만들수 있다.
					// 생성자에 컬럼수를 써준다..
					PdfPTable table = new PdfPTable(8);
					// 각각의 컬럼에 width를 정한다..
					table.setWidths(new int[] { 30, 100, 100, 100, 70, 70, 100, 100 });
					// 컬럼 타이틀..
					PdfPCell header1 = new PdfPCell(new Paragraph("NO.", font));
					PdfPCell header2 = new PdfPCell(new Paragraph("이력번호", font));
					PdfPCell header3 = new PdfPCell(new Paragraph("상호명", font));
					PdfPCell header4 = new PdfPCell(new Paragraph("상품명", font));
					PdfPCell header5 = new PdfPCell(new Paragraph("판매가격", font));
					PdfPCell header6 = new PdfPCell(new Paragraph("KG.", font));
					PdfPCell header7 = new PdfPCell(new Paragraph("판매날짜", font));
					PdfPCell header8 = new PdfPCell(new Paragraph("판매총액", font));

					// 가로정렬
					header1.setHorizontalAlignment(Element.ALIGN_CENTER);
					header2.setHorizontalAlignment(Element.ALIGN_CENTER);
					header3.setHorizontalAlignment(Element.ALIGN_CENTER);
					header4.setHorizontalAlignment(Element.ALIGN_CENTER);
					header5.setHorizontalAlignment(Element.ALIGN_CENTER);
					header6.setHorizontalAlignment(Element.ALIGN_CENTER);
					header7.setHorizontalAlignment(Element.ALIGN_CENTER);
					header8.setHorizontalAlignment(Element.ALIGN_CENTER);

					// 세로정렬
					header1.setVerticalAlignment(Element.ALIGN_MIDDLE);
					header2.setVerticalAlignment(Element.ALIGN_MIDDLE);
					header3.setVerticalAlignment(Element.ALIGN_MIDDLE);
					header4.setVerticalAlignment(Element.ALIGN_MIDDLE);
					header5.setVerticalAlignment(Element.ALIGN_MIDDLE);
					header6.setVerticalAlignment(Element.ALIGN_MIDDLE);
					header7.setVerticalAlignment(Element.ALIGN_MIDDLE);
					header8.setVerticalAlignment(Element.ALIGN_MIDDLE);

					// 테이블에 추가..
					table.addCell(header1);
					table.addCell(header2);
					table.addCell(header3);
					table.addCell(header4);
					table.addCell(header5);
					table.addCell(header6);
					table.addCell(header7);
					table.addCell(header8);

					// DB 연결 및 리스트 선택
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

						// 가로정렬
						cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell8.setHorizontalAlignment(Element.ALIGN_CENTER);

						// 세로정렬
						cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);

						// 테이블에 셀 추가
						table.addCell(cell1);
						table.addCell(cell2);
						table.addCell(cell3);
						table.addCell(cell4);
						table.addCell(cell5);
						table.addCell(cell6);
						table.addCell(cell7);
						table.addCell(cell8);

					}

					// 문서에 테이블추가.
					document.add(table);
					document.add(new Paragraph("\r\n"));
					Alert alert = new Alert(AlertType.INFORMATION);
					if (cbBarImage.isSelected()) {
						// 막대 그래프 이미지 추가
						Paragraph barImageTitle = new Paragraph("상품별 총킬로수 파이차트              날짜별 총판매총액 막대그래프", font);
						barImageTitle.setAlignment(Element.ALIGN_CENTER);
						document.add(barImageTitle);
						document.add(new Paragraph("\r\n"));
						final String barImageUrl = "chartImage/studentBarChart.png";
						// 기존에 javafx.scene.image.Image 객체을 사용하고 있어 충돌이 생겨 아래와
						// 같이 사용함.
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

					// 문서를 닫는다. 쓰기 종료.
					document.close();
					dialogPdf.close();
					txtSaveFileDir.clear();
					btnPDF.setDisable(true);
					btnExcel.setDisable(true);

					
					alert.setTitle("PDF 파일 생성");
					alert.setHeaderText("판매 목록 PDF 파일 생성 성공.");
					alert.setContentText("판매 목록 PDF 파일.");
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

	// 파일 경로 수정
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

		// xlsx 파일 쓰기
		saveSuccess = excelWriter.xlsxWiter(list, txtSaveFileDir.getText());
		if (saveSuccess) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("엑셀 파일 생성");
			alert.setHeaderText("판매 목록 엑셀 파일 생성 성공.");
			alert.setContentText("판매 목록 엑셀 파일.");
			alert.showAndWait();
		}

		txtSaveFileDir.clear();
		btnExcel.setDisable(true);
		btnPDF.setDisable(true);
	}

	// 판매단 바차트 액션
	public void handlerBtn_Sell_barchartActoion(ActionEvent event) {
		try {
			SellProductVO spvo = new SellProductVO();

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btn_Sell_barchart.getScene().getWindow());
			dialog.setTitle("막대 그래프");

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
			totaldate.setName("총킬로수");
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

			// 막대 그래프 이미지 저장
			WritableImage snapShot = scene.snapshot(null);
			ImageIO.write(SwingFXUtils.fromFXImage(snapShot, null), "png", new File("chartImage/studentBarChart.png"));

		} catch (IOException e) {

		}
	}

	// 판매단 판매검색버튼
	public void handlerBtn_Sell_sellSearchActoion(ActionEvent event) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/searchsellinventory.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btn_Sell_search.getScene().getWindow());
			dialog.setTitle("판매 등록");
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

			TableColumn colname = new TableColumn("이력번호");
			colname.setMinWidth(128);
			colname.setStyle("-fx-allignment:CENTER");
			colname.setCellValueFactory(new PropertyValueFactory<>("im_tracenumber"));

			TableColumn colmanagerphone = new TableColumn("상품명");
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
						alert.setTitle("재고 정보 검색");
						alert.setHeaderText("이력번호을 입력하시오.");
						alert.setContentText("다음에는 주의하세요!");
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
						alert.setTitle("제품 정보 검색");
						alert.setHeaderText(searchNumber + " 리스트에 없습니다.");
						alert.setContentText("다시 검색하세요.");
						alert.showAndWait();

					}
				} catch (Exception e1) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("제품 정보 검색오류");
					alert.setHeaderText("검색에 오류가 발생하였습니다.");
					alert.setContentText("다시 하세요.");
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

	// 판매단 상호명 검색 이벤트...................................................
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
				alert.setTitle("판매 정보 검색");
				alert.setHeaderText("상호명의 이름을 입력하시오.");
				alert.setContentText("다음에는 주의하세요!");
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
				alert.setTitle("판매 정보 검색");
				alert.setHeaderText(searchName + " 상호명이 리스트에 없습니다.");
				alert.setContentText("다시 검색하세요.");
				alert.showAndWait();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("판매 정보 검색 오류");
			alert.setHeaderText("판매 정보 검색에 오류가 발생하였습니다.");
			alert.setContentText("다시 하세요.");
			alert.showAndWait();
		}
	}

	// 판매단 삭제이벤트 삭제와 동시에 업데이트하여 재고를 수정한다.........................
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

	// 판매단 초기화 버튼...............................................................
	public void handlerBtn_Sell_initActoion(ActionEvent event) {
		txt_Sell_tracenumber.clear();
		txt_Sell_comname.clear();
		txt_Sell_productname.clear();
		txt_Sell_price.clear();
		txt_Sell_kg.clear();
		txt_Sell_date.clear();

	}

	// 판매등록업체 버튼액션............................................................
	public void handlerBtn_Sellcompany(ActionEvent event) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/sellcompanyjoin.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btn_Sellcompany.getScene().getWindow());
			dialog.setTitle("판매업체등록");
			Parent parentEdit = (Parent) loader.load();
			Scene scene = new Scene(parentEdit);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();

		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	// 재고관리 삭제버튼
	// 액션-----------------------------------------------------------------------------------------
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

	// 매입회사 검색
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
				alert.setTitle("매입상호명 정보 검색");
				alert.setHeaderText("매입상호명의 이름을 입력하시오.");
				alert.setContentText("다음에는 주의하세요!");
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
				alert.setTitle("매입상호명 정보 검색");
				alert.setHeaderText(searchName + " 매입상호명이 리스트에 없습니다.");
				alert.setContentText("다시 검색하세요.");
				alert.showAndWait();
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("매입상호명 검색 오류");
			alert.setHeaderText("매입상호명 검색에 오류가 발생하였습니다.");
			alert.setContentText("다시 하세요.");
			alert.showAndWait();
		}

	}

	// 재고관리 검색버튼 액션
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
				alert.setTitle("재고 정보 검색");
				alert.setHeaderText("상품명의 이름을 입력하시오.");
				alert.setContentText("다음에는 주의하세요!");
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
				alert.setTitle("재고 정보 검색");
				alert.setHeaderText(searchName + " 상품명 리스트에 없습니다.");
				alert.setContentText("다시 검색하세요.");
				alert.showAndWait();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("재고 정보 검색 오류");
			alert.setHeaderText("재고 정보 검색에 오류가 발생하였습니다.");
			alert.setContentText("다시 하세요.");
			alert.showAndWait();
		}
	}

	// 매입단 삭제 버튼*****************************************************************
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

	// 매입단 수정 버튼******************************************************
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
			alert.setTitle("매입업체 정보 수정");
			alert.setHeaderText("수정되는 정보를 정확히 입력하시오.");
			alert.setContentText("다음에는 주의하세요!");
			alert.showAndWait();
		}
	}

	// 매입단 종료버튼*************************************************************
	public void handlerBtn_Buy_exitActoion(ActionEvent event) {
		Platform.exit();
	}

	// 매입단 초기화버튼*****************************************************
	public void handlerBtn_Buy_initActoion(ActionEvent event) {
		txt_Buy_tracenumber.clear();
		txt_Buy_comname.clear();
		txt_Buy_productname.clear();
		txt_Buy_price.clear();
		txt_Buy_kg.clear();
		txt_Buy_date.clear();
	}

	// 매입단 매입업체등록버튼*******************************************
	public void handlerBtn_BuycompanyActoion(ActionEvent event) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/buycompanyjoin.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btn_Buycompany.getScene().getWindow());
			dialog.setTitle("매입업체등록");
			Parent parentEdit = (Parent) loader.load();
			Scene scene = new Scene(parentEdit);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();

		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	// 재고관리 초기화버튼------------------------------------------
	private void handlerBtn_Im_InitActoion(ActionEvent event) {
		txt_Im_Name.clear();
	}

	// 재고관리 종료버튼---------------------------------------------
	private void handlerBtn_Im_ExitActoion(ActionEvent event) {
		Platform.exit();
	}

	// 재고관리 수정 모달창-------------------------------------------
	public void handlerBtn_Im_EditActoion(ActionEvent event) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/inventoryjoin.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btn_Im_Edit.getScene().getWindow());
			dialog.setTitle("수정");
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

	// 매입단 토탈리스트*************************************************
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

	// 재고관리 토탈리스트-----------------------------------------------------------
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

	// 판매단 토탈리스트
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
