package snmp;

import java.util.ArrayList;

public class MibTree {
	private ArrayList<Oid> folder_oids;
	private ArrayList<Oid> simple_oids;
	private ArrayList<Oid> tables_oids;
	private ArrayList<Oid> editable_oids;
	
	public MibTree() {
		folder_oids = new ArrayList<Oid>();
		simple_oids = new ArrayList<Oid>(); 
		tables_oids = new ArrayList<Oid>(); 
		editable_oids = new ArrayList<Oid>();
		
		addFolderOids();
		addTableOids();
		addSimpleOids();
		addEditableOids();
	}

	// Cây MIB-2 và các thư mục con
	private void addFolderOids() {
		this.folder_oids.add(new Oid("system", ".1.3.6.1.2.1.1"));
		this.folder_oids.add(new Oid("interfaces", ".1.3.6.1.2.1.2"));
		this.folder_oids.add(new Oid("at", ".1.3.6.1.2.1.3"));
		this.folder_oids.add(new Oid("ip", ".1.3.6.1.2.1.4"));
		this.folder_oids.add(new Oid("icmp", ".1.3.6.1.2.1.5"));
		this.folder_oids.add(new Oid("tcp", ".1.3.6.1.2.1.6"));
		this.folder_oids.add(new Oid("udp", ".1.3.6.1.2.1.7"));
		this.folder_oids.add(new Oid("egp", ".1.3.6.1.2.1.8"));
		this.folder_oids.add(new Oid("snmp", ".1.3.6.1.2.1.11"));
		this.folder_oids.add(new Oid("host", ".1.3.6.1.2.1.25"));
	}
	
	private void addSimpleOids() {
		this.simple_oids.add(new Oid("sysDescr", ".1.3.6.1.2.1.1.1.0"));
		this.simple_oids.add(new Oid("sysObjectID", ".1.3.6.1.2.1.1.2.0"));
		this.simple_oids.add(new Oid("sysUpTime", ".1.3.6.1.2.1.1.3.0"));
		this.simple_oids.add(new Oid("sysContact", ".1.3.6.1.2.1.1.4.0"));
		this.simple_oids.add(new Oid("sysName", ".1.3.6.1.2.1.1.5.0"));
		this.simple_oids.add(new Oid("sysLocation", ".1.3.6.1.2.1.1.6.0"));
		this.simple_oids.add(new Oid("sysServices", ".1.3.6.1.2.1.1.7.0"));

		this.simple_oids.add(new Oid("ifNumber", ".1.3.6.1.2.1.2.1.0"));

		this.simple_oids.add(new Oid("ipForwarding", ".1.3.6.1.2.1.4.1.0"));
		this.simple_oids.add(new Oid("ipDefaultTTL", ".1.3.6.1.2.1.4.2.0"));
		this.simple_oids.add(new Oid("ipInReceives", ".1.3.6.1.2.1.4.3.0"));
		this.simple_oids.add(new Oid("ipInHdrErrors", ".1.3.6.1.2.1.4.4.0"));
		this.simple_oids.add(new Oid("ipInAddrErrors", ".1.3.6.1.2.1.4.5.0"));
		this.simple_oids.add(new Oid("ipForwDatagrams", ".1.3.6.1.2.1.4.6.0"));
		this.simple_oids.add(new Oid("ipInUnknownProtos", ".1.3.6.1.2.1.4.7.0"));
		this.simple_oids.add(new Oid("ipInDiscards", ".1.3.6.1.2.1.4.8.0"));
		this.simple_oids.add(new Oid("ipInDelivers", ".1.3.6.1.2.1.4.9.0"));
		this.simple_oids.add(new Oid("ipOutRequests", ".1.3.6.1.2.1.4.10.0"));
		this.simple_oids.add(new Oid("ipOutDiscards", ".1.3.6.1.2.1.4.11.0"));
		this.simple_oids.add(new Oid("ipOutNoRoutes", ".1.3.6.1.2.1.4.12.0"));
		this.simple_oids.add(new Oid("ipReasmTimeout", ".1.3.6.1.2.1.4.13.0"));
		this.simple_oids.add(new Oid("ipReasmReqds", ".1.3.6.1.2.1.4.14.0"));
		this.simple_oids.add(new Oid("ipReasmOKs", ".1.3.6.1.2.1.4.15.0"));
		this.simple_oids.add(new Oid("ipReasmFails", ".1.3.6.1.2.1.4.16.0"));
		this.simple_oids.add(new Oid("ipFragOKs", ".1.3.6.1.2.1.4.17.0"));
		this.simple_oids.add(new Oid("ipFragFails", ".1.3.6.1.2.1.4.18.0"));
		this.simple_oids.add(new Oid("ipFragCreates", ".1.3.6.1.2.1.4.19.0"));
		this.simple_oids.add(new Oid("ipRoutingDiscards", ".1.3.6.1.2.1.4.23.0"));

		this.simple_oids.add(new Oid("icmpInMsgs", ".1.3.6.1.2.1.5.1.0"));
		this.simple_oids.add(new Oid("icmpInMsgs", ".1.3.6.1.2.1.5.1.0"));
		this.simple_oids.add(new Oid("icmpInErrors", ".1.3.6.1.2.1.5.2.0"));
		this.simple_oids.add(new Oid("icmpInDestUnreachs", ".1.3.6.1.2.1.5.3.0"));
		this.simple_oids.add(new Oid("icmpInTimeExcds", ".1.3.6.1.2.1.5.4.0"));
		this.simple_oids.add(new Oid("icmpInParmProbs", ".1.3.6.1.2.1.5.5.0"));
		this.simple_oids.add(new Oid("icmpInSrcQuenchs", ".1.3.6.1.2.1.5.6.0"));
		this.simple_oids.add(new Oid("icmpInRedirects", ".1.3.6.1.2.1.5.7.0"));
		this.simple_oids.add(new Oid("icmpInEchos", ".1.3.6.1.2.1.5.8.0"));
		this.simple_oids.add(new Oid("icmpInEchoReps", ".1.3.6.1.2.1.5.9.0"));
		this.simple_oids.add(new Oid("icmpInTimestamps", ".1.3.6.1.2.1.5.10.0"));
		this.simple_oids.add(new Oid("icmpInTimestampReps", ".1.3.6.1.2.1.5.11.0"));
		this.simple_oids.add(new Oid("icmpInAddrMasks", ".1.3.6.1.2.1.5.12.0"));
		this.simple_oids.add(new Oid("icmpInAddrMaskReps", ".1.3.6.1.2.1.5.13.0"));
		this.simple_oids.add(new Oid("icmpOutMsgs", ".1.3.6.1.2.1.5.14.0"));
		this.simple_oids.add(new Oid("icmpOutErrors", ".1.3.6.1.2.1.5.15.0"));
		this.simple_oids.add(new Oid("icmpOutDestUnreachs", ".1.3.6.1.2.1.5.16.0"));
		this.simple_oids.add(new Oid("icmpOutTimeExcds", ".1.3.6.1.2.1.5.17.0"));
		this.simple_oids.add(new Oid("icmpOutParmProbs", ".1.3.6.1.2.1.5.18.0"));
		this.simple_oids.add(new Oid("icmpOutSrcQuenchs", ".1.3.6.1.2.1.5.19.0"));
		this.simple_oids.add(new Oid("icmpOutRedirects", ".1.3.6.1.2.1.5.20.0"));
		this.simple_oids.add(new Oid("icmpOutEchos", ".1.3.6.1.2.1.5.21.0"));
		this.simple_oids.add(new Oid("icmpOutEchoReps", ".1.3.6.1.2.1.5.22.0"));
		this.simple_oids.add(new Oid("icmpOutTimestamps", ".1.3.6.1.2.1.5.23.0"));
		this.simple_oids.add(new Oid("icmpOutTimestampReps", ".1.3.6.1.2.1.5.24.0"));
		this.simple_oids.add(new Oid("icmpOutAddrMaskReps", ".1.3.6.1.2.1.5.25.0"));

		this.simple_oids.add(new Oid("tcpRtoAlgorithm", ".1.3.6.1.2.1.6.1.0"));
		this.simple_oids.add(new Oid("tcpRtoMin", ".1.3.6.1.2.1.6.2.0"));
		this.simple_oids.add(new Oid("tcpRtoMax", ".1.3.6.1.2.1.6.3.0"));
		this.simple_oids.add(new Oid("tcpRtoMaxConn", ".1.3.6.1.2.1.6.4.0"));
		this.simple_oids.add(new Oid("tcpActiveOpens", ".1.3.6.1.2.1.6.5.0"));
		this.simple_oids.add(new Oid("tcpPassiveOpens", ".1.3.6.1.2.1.6.6.0"));
		this.simple_oids.add(new Oid("tcpAttemptFails", ".1.3.6.1.2.1.6.7.0"));
		this.simple_oids.add(new Oid("tcpEstabResets", ".1.3.6.1.2.1.6.8.0"));
		this.simple_oids.add(new Oid("tcpCurrEstab", ".1.3.6.1.2.1.6.9.0"));
		this.simple_oids.add(new Oid("tcpInSegs", ".1.3.6.1.2.1.6.10.0"));
		this.simple_oids.add(new Oid("tcpOutSegs", ".1.3.6.1.2.1.6.11.0"));
		this.simple_oids.add(new Oid("tcpRetransSegs", ".1.3.6.1.2.1.6.12.0"));
		this.simple_oids.add(new Oid("tcpInErrs", ".1.3.6.1.2.1.6.14.0"));
		this.simple_oids.add(new Oid("tcpOutRsts", ".1.3.6.1.2.1.6.15.0"));

		this.simple_oids.add(new Oid("udpInDatagrams", ".1.3.6.1.2.1.7.1.0"));
		this.simple_oids.add(new Oid("udpNoPorts", ".1.3.6.1.2.1.7.2.0"));
		this.simple_oids.add(new Oid("udpInErrors", ".1.3.6.1.2.1.7.3.0"));
		this.simple_oids.add(new Oid("udpOutDatagrams", ".1.3.6.1.2.1.7.2.0"));
		this.simple_oids.add(new Oid("udpInErrors", ".1.3.6.1.2.1.7.4.0"));


		this.simple_oids.add(new Oid("egpInMsgs", ".1.3.6.1.2.1.8.1.0"));
		this.simple_oids.add(new Oid("egpInErrors", ".1.3.6.1.2.1.8.2.0"));
		this.simple_oids.add(new Oid("egpOutMsgs", ".1.3.6.1.2.1.8.3.0"));
		this.simple_oids.add(new Oid("egpOutErrors", ".1.3.6.1.2.1.8.4.0"));
		this.simple_oids.add(new Oid("egpAs", ".1.3.6.1.2.1.8.6.0"));

		this.simple_oids.add(new Oid("transmission", ".1.3.6.1.2.1.10"));

		this.simple_oids.add(new Oid("snmpInPkts", ".1.3.6.1.2.1.11.1.0"));
		this.simple_oids.add(new Oid("snmpOutPkts", ".1.3.6.1.2.1.11.2.0"));
		this.simple_oids.add(new Oid("snmpInBadVersions", ".1.3.6.1.2.1.11.3.0"));
		this.simple_oids.add(new Oid("snmpInBadComunityNames", ".1.3.6.1.2.1.11.4.0"));
		this.simple_oids.add(new Oid("snmpInBadComunityUses", ".1.3.6.1.2.1.11.5.0"));
		this.simple_oids.add(new Oid("snmpInASNParseErrs", ".1.3.6.1.2.1.11.6.0"));
		this.simple_oids.add(new Oid("snmpInTooBigs", ".1.3.6.1.2.1.11.8.0"));
		this.simple_oids.add(new Oid("snmpInNoSuchNames", ".1.3.6.1.2.1.11.9.0"));
		this.simple_oids.add(new Oid("snmpInBadValues", ".1.3.6.1.2.1.11.10.0"));
		this.simple_oids.add(new Oid("snmpInReadOnlys", ".1.3.6.1.2.1.11.11.0"));
		this.simple_oids.add(new Oid("snmpInGenErrs", ".1.3.6.1.2.1.11.12.0"));
		this.simple_oids.add(new Oid("snmpInTotalReqVars", ".1.3.6.1.2.1.11.13.0"));
		this.simple_oids.add(new Oid("snmpInTotalSetVars", ".1.3.6.1.2.1.11.14.0"));
		this.simple_oids.add(new Oid("snmpInGetRequests", ".1.3.6.1.2.1.11.15.0"));
		this.simple_oids.add(new Oid("snmpInGetNexts", ".1.3.6.1.2.1.11.16.0"));
		this.simple_oids.add(new Oid("snmpInSetRequests", ".1.3.6.1.2.1.11.17.0"));
		this.simple_oids.add(new Oid("snmpInGetResponses", ".1.3.6.1.2.1.11.18.0"));
		this.simple_oids.add(new Oid("snmpInTraps", ".1.3.6.1.2.1.11.19.0"));
		this.simple_oids.add(new Oid("snmpOutTooBigs", ".1.3.6.1.2.1.11.20.0"));
		this.simple_oids.add(new Oid("snmpOutNoSuchNames", ".1.3.6.1.2.1.11.21.0"));
		this.simple_oids.add(new Oid("snmpOutBadValues", ".1.3.6.1.2.1.11.22.0"));
		this.simple_oids.add(new Oid("snmpOutGenErrs", ".1.3.6.1.2.1.11.24.0"));
		this.simple_oids.add(new Oid("snmpOutGetRequests", ".1.3.6.1.2.1.11.25.0"));
		this.simple_oids.add(new Oid("snmpOutGetNexts", ".1.3.6.1.2.1.11.26.0"));
		this.simple_oids.add(new Oid("snmpOutSetRequests", ".1.3.6.1.2.1.11.27.0"));
		this.simple_oids.add(new Oid("snmpOutGetResponses", ".1.3.6.1.2.1.11.28.0"));
		this.simple_oids.add(new Oid("snmpOutTraps", ".1.3.6.1.2.1.11.29.0"));

		this.simple_oids.add(new Oid("hrSystemUpTime", ".1.3.6.1.2.1.25.1.1.0"));
		this.simple_oids.add(new Oid("hrSystemNumUsers", ".1.3.6.1.2.1.25.1.5.0"));
		this.simple_oids.add(new Oid("hrSystemProcesses", ".1.3.6.1.2.1.25.1.6.0"));
		this.simple_oids.add(new Oid("hrSystemMaxProcesses", ".1.3.6.1.2.1.25.1.7.0"));
		this.simple_oids.add(new Oid("hrStorageTypes", ".1.3.6.1.2.1.25.2.1"));
		this.simple_oids.add(new Oid("hrMemorySize", ".1.3.6.1.2.1.25.1.7.0"));
		this.simple_oids.add(new Oid("hrDeviceTypes", ".1.3.6.1.2.1.25.3.1"));
		this.simple_oids.add(new Oid("hrFSTypes", ".1.3.6.1.2.1.25.3.9"));
		this.simple_oids.add(new Oid("hrSWOSIndex", ".1.3.6.1.2.1.25.4.1.0"));
		this.simple_oids.add(new Oid("hrSWInstalledLastChange", ".1.3.6.1.2.1.25.6.1.0"));
		this.simple_oids.add(new Oid("hrSWInstalledLastUpdateTime", ".1.3.6.1.2.1.25.6.2.0"));

		this.simple_oids.add(new Oid("hostResourcesMibModule", ".1.3.6.1.2.1.25.7.1"));

		this.simple_oids.add(new Oid("hrMIBCompliance", ".1.3.6.1.2.1.25.7.2.1"));
		this.simple_oids.add(new Oid("hrSystemGroup", ".1.3.6.1.2.1.25.7.3.1"));
		this.simple_oids.add(new Oid("hrStorageGroup", ".1.3.6.1.2.1.25.7.3.2"));
		this.simple_oids.add(new Oid("hrDeviceGroup", ".1.3.6.1.2.1.25.7.3.3"));
		this.simple_oids.add(new Oid("hrSWRunGroup", ".1.3.6.1.2.1.25.7.3.4"));
		this.simple_oids.add(new Oid("hrSWRunPerfGroup", ".1.3.6.1.2.1.25.7.3.5"));
		this.simple_oids.add(new Oid("hrSWInstalledGroup", ".1.3.6.1.2.1.25.7.3.6"));

	}
	
	private void addTableOids() {
		this.tables_oids.add(new Oid("ifTable", ".1.3.6.1.2.1.2.2"));
		this.tables_oids.add(new Oid("atTable", ".1.3.6.1.2.1.3.1"));
		this.tables_oids.add(new Oid("ipAddrTable", ".1.3.6.1.2.1.4.20"));
		this.tables_oids.add(new Oid("ipRouteTable", ".1.3.6.1.2.1.4.21"));
		this.tables_oids.add(new Oid("ipNetToMediaTable", ".1.3.6.1.2.1.4.22"));
		this.tables_oids.add(new Oid("tcpConnTable", ".1.3.6.1.2.1.6.13"));
		this.tables_oids.add(new Oid("udpTable", ".1.3.6.1.2.1.7.5"));
		this.tables_oids.add(new Oid("egpNeighTable", ".1.3.6.1.2.1.8.5"));
		this.tables_oids.add(new Oid("hrStorageTable", ".1.3.6.1.2.1.25.2.3"));
		this.tables_oids.add(new Oid("hrDeviceTable", ".1.3.6.1.2.1.25.3.2"));
		this.tables_oids.add(new Oid("hrProcessorTable", ".1.3.6.1.2.1.25.3.3"));
		this.tables_oids.add(new Oid("hrNetworkTable", ".1.3.6.1.2.1.25.3.4"));
		this.tables_oids.add(new Oid("hrPrinterTable", ".1.3.6.1.2.1.25.3.5"));
		this.tables_oids.add(new Oid("hrDiskStorageTable", ".1.3.6.1.2.1.25.3.6"));
		this.tables_oids.add(new Oid("hrPartitionTable", ".1.3.6.1.2.1.25.3.7"));
		this.tables_oids.add(new Oid("hrFSTable", ".1.3.6.1.2.1.25.3.8"));
		this.tables_oids.add(new Oid("hrSWRunTable", ".1.3.6.1.2.1.25.4.2"));
		this.tables_oids.add(new Oid("hrSWRunPerfTable", ".1.3.6.1.2.1.25.5.1"));
		this.tables_oids.add(new Oid("hrSWInstalledTable", ".1.3.6.1.2.1.25.6.3"));
	}

	private void addEditableOids() {
		this.editable_oids.add(new Oid("ipForwarding", ".1.3.6.1.2.1.4.1.0"));
		this.editable_oids.add(new Oid("ipDefaultTTL", ".1.3.6.1.2.1.4.2.0"));
		this.editable_oids.add(new Oid("snmpEnableAuthenTraps", ".1.3.6.1.2.1.11.30.0"));
		this.editable_oids.add(new Oid("hrSystemDate", ".1.3.6.1.2.1.25.1.2.0"));
		this.editable_oids.add(new Oid("hrSystemInitialLoadDevice", ".1.3.6.1.2.1.25.1.3.0"));
		this.editable_oids.add(new Oid("hrSystemInitialLoadParameters", ".1.3.6.1.2.1.25.1.4.0"));
	}
	
	
	public ArrayList<Oid> getFolder_oids(){
		return folder_oids;
	}
	
	public ArrayList<Oid> getSimple_oids(){
		return simple_oids;
	}
	
	public ArrayList<Oid> getTables_oids(){
		return tables_oids;
	}
	
	public ArrayList<Oid> getEditable_oids(){
		return editable_oids;
	}

}
