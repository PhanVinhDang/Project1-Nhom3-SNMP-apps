package util;

public class Validate {
	private static Validate validator = null;
	private String ip, community;
	private int port;

	private Validate() {

	}

	public static Validate getInstance() {
		if (validator == null) {
			validator = new Validate();
		}
		return validator;
	}

	public boolean validateIp(String ip) {
		if (ip.equals("127.0.0.1")) {
			return true;
		}
		if (ip.trim().equals("")) {
			return false;
		}
		if (ip.indexOf("-") >= 0) {
			return false;
		}
		String[] strPartes = ip.replace('.', '-').split("-");
		for (int i = 0; i < strPartes.length; i++) {
			String strPedaco = strPartes[i];
			if (strPedaco == null) {
				return false;
			}
			if (strPedaco.trim().equals("")) {
				return false;
			}
			try {
				int intPedaco = Integer.parseInt(strPedaco);
				if ((intPedaco == 0) || (intPedaco >= 254)) {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return true;
	}

	public boolean validatePort(String port) {
		try {
			this.port = Integer.parseInt(port);
		} catch (NumberFormatException e) {
			return false;
		}

		if (this.port == 0) {
			return false;
		} else
			return true;

	}

	public boolean validateCommunity(String community) {
		this.community = community;
		if (community == null ||
				community.equals("")) {
			return false;
		}
		return true;
	}

	public boolean validateOID(String oid) {
		if (oid == null ||
				oid.equals("")) {
			return false;
		}
		return true;
	}

}
