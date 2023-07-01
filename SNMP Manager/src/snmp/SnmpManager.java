package snmp;

import java.util.ArrayList;

import org.snmp4j.PDU;
import org.snmp4j.Snmp;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class SnmpManager {
	private String ip, port, community;
	private MibTree tree;

	public SnmpManager(String ip, String port, String community) throws Exception {
		this.ip=ip;
		this.port=port;
		this.community=community;
		tree=new MibTree();
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


	public String set(String value, String oid) {
		String result="";
		SnmpSet Set = new SnmpSet(oid,this);
		result=Set.set(value);
		return result;
	}

	public String walk(String oid) {
		String result="";
		SnmpWalk Walk = new SnmpWalk(oid, this);
		result = Walk.walk();
		return result;
	}

}