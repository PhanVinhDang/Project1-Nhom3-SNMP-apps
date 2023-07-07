package snmp;

public class SnmpManager {
	private String ip, port, community;

	public SnmpManager(String ip, String port, String community) {
		this.ip=ip;
		this.port=port;
		this.community=community;
	}

	public String getIp() {
		return ip;
	}

	public String getPort() {
		return port;
	}

	public String getCommunity() {
		return community;
	}

	public String get(String oid) {
		String result="";
		SnmpGet Get = new SnmpGet(oid, this);
		result = Get.get();
		return result;
	}

	public String getnext(String oid) {
		String result="";
		SnmpGetNext GetNext = new SnmpGetNext(oid, this);
		result = GetNext.getnext();
		return result;
	}

	public String getnextOid(String oid) {
		String result="";
		SnmpGetNext GetNext = new SnmpGetNext(oid, this);
		result = GetNext.getnextOID();
		return result;
	}

	public String walk(String oid) {
		String result="";
		SnmpWalk Walk = new SnmpWalk(oid, this);
		result = Walk.walk();
		return result;
	}

}