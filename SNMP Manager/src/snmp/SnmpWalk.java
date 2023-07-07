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

public class SnmpWalk {

	private String ip, port, community, oid;
	private Snmp snmp = null;
	private PDU pdu = null;
	private SnmpManager manager = null;

	public SnmpWalk(String oid, SnmpManager manager) {
		this.manager = manager;
		this.ip = manager.getIp();
		this.port = manager.getPort();
		this.community = manager.getCommunity();
		this.oid = oid;
	}

	public String walk() {
		String result = "";
		String oidtemp = "";
		String[] count;
		String[] countTemp;
		boolean a = true;

		count = oid.split("\\.");

		while (a) {
			try {
				TransportMapping transport = new DefaultUdpTransportMapping();
				transport.listen();

				// khởi tạo connection tới đối tượng
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

					if (errorStatus == PDU.noError) {
						oidtemp = responsePDU.getVariableBindings().get(0).getOid().toString();
					} else {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Alert");
						alert.setHeaderText("SnmpWalk line 85");
						alert.setContentText("Error Status = " + errorStatus + "\nError Index = " + errorIndex
								+ "\nError Status Text = " + errorStatusText);

						alert.showAndWait();
					}
				} else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Alert");
					alert.setHeaderText("SnmpWalk line 89");
					alert.setContentText("Timeout");

					alert.showAndWait();
				}
				snmp.close();

			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Alert");
				alert.setHeaderText("SnmpWalk line 100");
				alert.setContentText("Lỗi khi gửi yêu cầu đến OID:  " + oid + "\n Nguyên nhân: " + e.toString());

				alert.showAndWait();
			}

			countTemp = oidtemp.split("\\.");

			if (count[7].equals(countTemp[6])) {
				SnmpGet Get = new SnmpGet(oid, manager);
				result = result + Get.get() + "\n";
				oid = oidtemp;
			} else {
				a = false;
			}
		}
		result = result + "\n____________________________________________\n";
		return result;
	}

}
