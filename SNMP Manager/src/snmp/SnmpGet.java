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

public class SnmpGet {
	private String ip, port, community, oid;
	private Snmp snmp = null;
	private PDU pdu = null;
	
	public SnmpGet(String oid, SnmpManager manager) {
		this.ip=manager.getIp();
		this.port=manager.getPort();
		this.community=manager.getCommunity();
		this.oid=oid;
	}

	
	public String get() {
		
		String result="";

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
			pdu.setType(PDU.GET);
			pdu.setRequestID(new Integer32(1));

			// Thiết lập kết nối snmp đến đối tượng

			snmp = new Snmp(transport);


			//gửi requests...
			ResponseEvent response = snmp.get(pdu, comtarget);

			if (response != null) {
				//nhận requests
				PDU responsePDU = response.getResponse();

				int errorStatus = responsePDU.getErrorStatus();
				int errorIndex = responsePDU.getErrorIndex();
				String errorStatusText = responsePDU.getErrorStatusText();
				OidDescription oidValue = new OidDescription(responsePDU.getVariableBindings().get(0).getOid().toString());
				String description = oidValue.translate();
				if (errorStatus == PDU.noError){
					result="Value: "+responsePDU.getVariableBindings().get(0).getVariable().toString()+
							"\nOID: "+responsePDU.getVariableBindings().get(0).getOid().toString()+
							"\nDescription: \n"+description;
					
				} else{
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Alert");
					alert.setContentText("Error Status = " + errorStatus+"\nError Index = " + errorIndex+"\nError Status Text = " + errorStatusText);
					alert.showAndWait();	
				}
			}else{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Alert");
				alert.setContentText("Timeout");
				alert.showAndWait();	
			}
			snmp.close();

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Alert");
			alert.setContentText("Lỗi khi gửi requests đến OID: "+oid+"\n Nguyên nhân: "+e.toString());
			alert.showAndWait();
		} 
		result=result+"\n____________________________________________\n";
		return result;
	}
}
