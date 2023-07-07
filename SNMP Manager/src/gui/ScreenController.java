package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import snmp.MibTree;
import snmp.Oid;
import snmp.SnmpManager;
import util.Validate;

public class ScreenController implements Initializable{
	private ObservableList<String> itemcbOperation = FXCollections.observableArrayList("Get","GetNext","Walk");
	private Validate validator = Validate.getInstance();
	private boolean startManager=false;
	private SnmpManager manager;

	private MibTree tree;
	private TreeItem<Oid> mibtreeroot, folder, simple, table, editable;

	private ArrayList<Oid> listFolder, listSimple, listTable, listEditable;

	// Menu items

	@FXML // fx:id="miClose"
	private MenuItem miClose; // Value injected by FXMLLoader

	@FXML // fx:id="miClearParams"
	private MenuItem miClearParams; // Value injected by FXMLLoader

	@FXML // fx:id="miTest"
	private MenuItem miTest; // Value injected by FXMLLoader

	// On-screen items và actions

	@FXML // fx:id="cbOperation"
	private ComboBox<String> cbOperation; // Value injected by FXMLLoader

	@FXML // fx:id="taResult"
	private TextArea taResult; // Value injected by FXMLLoader

	@FXML // fx:id="btExecute"
	private Button btExecute; // Value injected by FXMLLoader

	@FXML // fx:id="btConfirm"
	private Button btConfirm; // Value injected by FXMLLoader

	@FXML // fx:id="btClearResults"
	private Button btClearResults; // Value injected by FXMLLoader

	@FXML // fx:id="tvMIB"
	private TreeView<Oid> tvMIB; // Value injected by FXMLLoader

	@FXML // fx:id="tfIp"
	private TextField tfIp; // Value injected by FXMLLoader

	@FXML // fx:id="tfPort"
	private TextField tfPort; // Value injected by FXMLLoader

	@FXML // fx:id="tfCommunity"
	private TextField tfCommunity; // Value injected by FXMLLoader

	@FXML // fx:id="tfOID"
	private TextField tfOID; // Value injected by FXMLLoader

	@FXML // fx:id="tvTabel"
	private TableView<ObservableList<String>> tvTabel; // Value injected by FXMLLoader

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		cbOperation.setItems(itemcbOperation);
		cbOperation.setTooltip(new Tooltip("Chọn 1 operation"));
		tfIp.setTooltip(new Tooltip("Host IP"));
		tfPort.setTooltip(new Tooltip("port 161"));
		tfCommunity.setTooltip(new Tooltip("public"));
		taResult.setText("");

		//Load item từ cây bên trái
		// Root Item
		mibtreeroot= new TreeItem<Oid>(new Oid("mib-2", ".1.3.6.1.2.1"));
		mibtreeroot.setExpanded(true);
		tvMIB.setRoot(mibtreeroot);

		tree=new MibTree();

		listFolder = tree.getFolder_oids();
		listSimple = tree.getSimple_oids();
		listTable = tree.getTables_oids();
		listEditable = tree.getEditable_oids();

		String[] oidFolder, oidSimple, oidTable, oidEditable;

		for(int i=0; i<listFolder.size();i++) {  // Điền vào các thư mục trong cây
			folder = new TreeItem<Oid>(listFolder.get(i));
			mibtreeroot.getChildren().add(folder);

			oidFolder=listFolder.get(i).getOid().split("\\."); //Split trừ kí tự "." 

			for(int j=0; j<listSimple.size();j++) { // Điền các OID node trong cây
				oidSimple=listSimple.get(j).getOid().split("\\.");

				if(oidFolder[7].equals(oidSimple[7])) {
					simple = new TreeItem<Oid>(listSimple.get(j));
					folder.getChildren().add(simple);
				}
			}

			for(int j=0; j<listTable.size();j++) { // Điền các bảng vào cây
				oidTable=listTable.get(j).getOid().split("\\.");

				if(oidFolder[7].equals(oidTable[7])) {
					table = new TreeItem<Oid>(listTable.get(j));
					folder.getChildren().add(table);
				}
			}

			for(int j=0; j<listEditable.size();j++) { // Điền các OID có thể chỉnh sửa
				oidEditable=listEditable.get(j).getOid().split("\\.");

				if(oidFolder[7].equals(oidEditable[7])) {
					editable = new TreeItem<Oid>(listEditable.get(j));
					folder.getChildren().add(editable);
				}
			}

		}

		tvMIB.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValu, newValue) -> {tfOID.setText(newValue.getValue().getOid());
				});


	}

	// Screen actions

	@FXML
	void confirm(ActionEvent event) throws Exception {
		//Thiết lập trình quản lý

		if(validator.validateIp(tfIp.getText())==false) { //Cảnh báo nếu IP không hợp lệ
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Cảnh báo");
			alert.setHeaderText("Chú ý");
			alert.setContentText("Điền IP hợp lệ");
			alert.showAndWait();
		}else { // tiếp tục nếu hợp lệ
			if(validator.validatePort(tfPort.getText())==false) { //Cảnh báo nếu port không hợp lệ
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Alert");
				alert.setHeaderText("Chú ý");
				alert.setContentText("Port không hợp lệ");
				alert.showAndWait();
			}else { // tiếp tục nếu hợp lệ
				if(validator.validateCommunity(tfCommunity.getText())==false) { //Cảnh báo nếu community không hợp lệ
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Cảnh báo");
					alert.setHeaderText("Chú ý");
					alert.setContentText("Điền Community hợp lệ");		
					alert.showAndWait();
				}else { // tiếp tục nếu hợp lệ
					manager = new SnmpManager(tfIp.getText(), tfPort.getText(), tfCommunity.getText());
					startManager=true;
					btConfirm.setText("Đã xác nhận");
					cbOperation.setDisable(false);
					tfOID.setDisable(false);
					btExecute.setDisable(false);
				}
			}
		}
	}


	@FXML
	void execute(ActionEvent event) throws Exception {
		if(startManager) { //đã khởi tạo SNMP manager
			if(validator.validateOID(tfOID.getText())) { //OID đã được nhập
				String op = cbOperation.getSelectionModel().getSelectedItem();
				try {
					switch (op){
					case "Get":
						taResult.setText(taResult.getText()+"\n"+manager.get(tfOID.getText()));
						break;

					case "GetNext":
						taResult.setText(taResult.getText()+"\n"+manager.getnext(tfOID.getText())); //Điền vào kết quả của lần GetNext đầu
						tfOID.setText(manager.getnextOid(tfOID.getText())); //Update tdOID với OID nhận được tiếp theo
						break;

					case "Walk":
						taResult.clear();
						taResult.setText(manager.walk(tfOID.getText()));
						break;
					}
				} catch (NullPointerException e) {  //Hiển thị ra alert nếu không có operation nào được chọn
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Cảnh báo");
					alert.setHeaderText("Chọn 1 operation");
					alert.setContentText(e.toString());
					alert.showAndWait();
				}
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Cảnh báo");
				alert.setHeaderText("Chú ý");
				alert.setContentText("Nhập vào OID để bắt đầu");
	
				alert.showAndWait();
			}

		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Cảnh báo");
			alert.setHeaderText("Chú");
			alert.setContentText("Nhập vào host để bắt đầu");

			alert.showAndWait();
		}
	}

	@FXML
	void ClearResults(ActionEvent event) {
		taResult.clear();
		tvTabel.setVisible(false);

	}

	@FXML
	void ClearParams(ActionEvent event) {
		tfIp.clear();
		tfPort.clear();
		tfCommunity.clear();
		startManager=false;
		btConfirm.setText("Xác nhận");
	}



	@FXML
	void test(ActionEvent event) {
		tfIp.setText("127.0.0.1");
		tfPort.setText("161");
		tfCommunity.setText("public");
		tfOID.setText(".1.3.6.1.2.1.1.1.0");

	}

	@FXML
	void thoat(ActionEvent event) {
		Platform.exit();
	}

}
