package snmp;

public class Oid {
	private String property, oid, type;

	public Oid(String property, String oid) {
		super();
		this.property = property;
		this.oid = oid;
	}

	public String getproperty() {
		return property;
	}

	public void setproperty(String property) {
		this.property = property;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String gettype() {
		return type;
	}

	public void settype(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return property;
	}
}
  

