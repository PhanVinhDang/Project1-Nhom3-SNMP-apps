package util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Validate {
	private static Validate validator = null;
	private String ip, community;
	private int port;

	private Validate() {

	}

	public static Validate getInstance() {
		if(validator==null) {
			validator=new Validate();
		}
		return validator;
	}

	public boolean validateIp(String ip) {
		if (ip.equals("127.0.0.1")) { return true; }
		if (ip == null) { return false; }
		if (ip.trim().equals("")) { return false; }
		if (ip.indexOf("-") >= 0) { return false; }
		String[] strPartes = ip.replace('.', '-').split("-");
		// if (strPartes.length != intPartes) { return false; }
		for (int i = 0; i < strPartes.length; i++) {
			String strPedaco = strPartes[i];
			if (strPedaco == null) { return false; }
			if (strPedaco.trim().equals("")) { return false; }
			try {
				int intPedaco = Integer.parseInt(strPedaco);
				if ((intPedaco == 0) || (intPedaco >= 254)) { return false; }
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return true;
	}

	public boolean validatePort(String port) {
		try {
			this.port=Integer.parseInt(port);
		} catch (NumberFormatException e) {
			return false;
		}

		if(this.port==0) {
			return false;
		}else return true;

	}

	public boolean validateCommunity(String community) {
		this.community=community;
		if(
				community.equals(null) ||
				community.equals("")
				) {
			return false;
		}
		return true;
	}

	public boolean validateOID(String oid) {
		if(
				oid.equals(null) ||
				oid.equals("")
				) {
			return false;
		}
		return true;
	}



}
