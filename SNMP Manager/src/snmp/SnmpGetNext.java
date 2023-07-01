package snmp;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SnmpGetNext {
	private String ip, port, community, address, oid;
	private Snmp snmp = null;
	private PDU pdu = null;
	
	public SnmpGetNext(String oid, SnmpManager manager) {
		this.ip=manager.getIp();
		this.port=manager.getPort();
		this.community=manager.getCommunity();
		this.oid=oid;
	}
	
	public String getnext() {
		String result="";
		String nextOid="";

		try {
			TransportMapping transport = new DefaultUdpTransportMapping();
			transport.listen();


			CommunityTarget comtarget = new CommunityTarget();
			comtarget.setCommunity(new OctetString(community));
			comtarget.setVersion(SnmpConstants.version2c);
			comtarget.setAddress(new UdpAddress(ip + "/" + port));
			comtarget.setRetries(2);
			comtarget.setTimeout(1000);

			// Khởi tạo PDU
			pdu = new PDU();
			pdu.add(new VariableBinding(new OID(oid)));
			pdu.setRequestID(new Integer32(1));
			pdu.setType(PDU.GETNEXT);			

			// Thiết lập kết nối snmp với đối tượng
			snmp = new Snmp(transport);

			ResponseEvent response = snmp.getNext(pdu, comtarget);

			if (response != null) {
				PDU responsePDU = response.getResponse();

				int errorStatus = responsePDU.getErrorStatus();
				int errorIndex = responsePDU.getErrorIndex();
				String errorStatusText = responsePDU.getErrorStatusText();
				OidDescription oidValue = new OidDescription(responsePDU.getVariableBindings().get(0).getOid().toString());
				String description = oidValue.sendPost();

				if (errorStatus == PDU.noError)	{
					//oid=responsePDU.getVariableBindings().get(0).getOid().toString();
					result="Value: "+responsePDU.getVariableBindings().get(0).getVariable().toString()+
							"\nOID: "+responsePDU.getVariableBindings().get(0).getOid().toString()+
							"\nDescription: \n"+description;

					//result=responsePDU.getVariableBindings().toString();

				}else{
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Alert");
					alert.setContentText("Error Status = " + errorStatus+"\nError Index = " + errorIndex+"\nError Status Text = " + errorStatusText);
					Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
					alert.showAndWait();
				}
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Alert");
				alert.setContentText("Timeout");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				alert.showAndWait();	
			}
			snmp.close();


		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Alert");
			alert.setContentText("Lỗi khi gửi requests đến OID: "+oid+"\n Nguyên nhân: "+e.toString());
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			alert.showAndWait();
		}
		result=result+"\n____________________________________________\n";
		return result;
	}
	
	public String getnextOID() {
		String nextOid="";

		try {
			TransportMapping transport = new DefaultUdpTransportMapping();
			transport.listen();

			CommunityTarget comtarget = new CommunityTarget();
			comtarget.setCommunity(new OctetString(community));
			comtarget.setVersion(SnmpConstants.version2c);
			comtarget.setAddress(new UdpAddress(ip + "/" + port));
			comtarget.setRetries(2);
			comtarget.setTimeout(1000);

			// Khởi tạo PDU
			pdu = new PDU();
			pdu.add(new VariableBinding(new OID(oid)));
			pdu.setRequestID(new Integer32(1));
			pdu.setType(PDU.GETNEXT);			

			// Thiết lập kết nối snmp với đối tượng
			snmp = new Snmp(transport);

			ResponseEvent response = snmp.getNext(pdu, comtarget);

			if (response != null) {
				PDU responsePDU = response.getResponse();

				int errorStatus = responsePDU.getErrorStatus();
				int errorIndex = responsePDU.getErrorIndex();
				String errorStatusText = responsePDU.getErrorStatusText();

				if (errorStatus == PDU.noError)	{
					nextOid=responsePDU.getVariableBindings().get(0).getOid().toString();
				}else{
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Alert");
					alert.setContentText("Error Status = " + errorStatus+"\nError Index = " + errorIndex+"\nError Status Text = " + errorStatusText);
					Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
					alert.showAndWait();
				}
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Alert");
				alert.setContentText("Timeout");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				alert.showAndWait();	
			}
			snmp.close();


		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Alert");
			alert.setContentText("Lỗi khi gửi requests tới OID: "+oid+"\n Nguyên nhân: "+e.toString());
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			alert.showAndWait();
		}
		return nextOid;
	}
	
}
