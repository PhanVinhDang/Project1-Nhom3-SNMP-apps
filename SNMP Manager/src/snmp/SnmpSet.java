package snmp;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SnmpSet {
	private String ip, port, community, oid;
	private Snmp snmp = null;
	private PDU pdu = null;
	private OID Oid = null;
	private SnmpManager manager=null;
	
	public SnmpSet(String oid, SnmpManager manager) {
		this.manager=manager;
		this.ip=manager.getIp();
		this.port=manager.getPort();
		this.community=manager.getCommunity();
		this.oid=oid;
	}
	
	public String set(String value) {
		String result="";
		try {
			TransportMapping transport = new DefaultUdpTransportMapping();
			transport.listen();

			//A CommunityTarget represents SNMP target properties for community based message processing models (SNMPv1 and SNMPv2c).
			CommunityTarget comtarget = new CommunityTarget();
			comtarget.setCommunity(new OctetString(community));
			comtarget.setVersion(SnmpConstants.version2c);
			comtarget.setAddress(new UdpAddress(ip + "/" + port));
			comtarget.setRetries(2);
			comtarget.setTimeout(1000);

			// Khởi tạo PDU

			pdu = new PDU();
			Oid = new OID(oid);
			Variable var = new OctetString(value);
			VariableBinding vb = new VariableBinding(Oid,var);
			pdu.add(vb);
			pdu.setType(PDU.SET);
			pdu.setRequestID(new Integer32(1));

			// Thiết lập kết nối snmp với đối tượng
			snmp = new Snmp(transport);


			//gửi requests...
			
			
			ResponseEvent response = snmp.set(pdu, comtarget);

			if (response != null) {
				//nhận requests
				PDU responsePDU = response.getResponse();

				int errorStatus = responsePDU.getErrorStatus();
				int errorIndex = responsePDU.getErrorIndex();
				String errorStatusText = responsePDU.getErrorStatusText();

				if (errorStatus == PDU.noError){
					//result=responsePDU.getVariableBindings().get(0).getOid().toString();
					result=responsePDU.getVariableBindings().get(0).getVariable().toString();
				} else{
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Alert");
					alert.setHeaderText("SnmpSet line 80");
					alert.setContentText("Error Status = " + errorStatus+"\nError Index = " + errorIndex+"\nError Status Text = " + errorStatusText);
					Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
					alert.showAndWait();	
				}
			}else{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Alert");
				alert.setHeaderText("SnmpSet line 88");
				alert.setContentText("Timeout");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				alert.showAndWait();	
			}
			
			snmp.close();

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Alert");
			alert.setHeaderText("SnmpSet line 98");
			alert.setContentText("Lỗi khi gửi requests đến OID: "+oid+"\n Nguyên nhân: "+e.toString());
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			alert.showAndWait();
		} 
		result=result+"\n____________________________________________\n";
		return result;
	}
	
}
