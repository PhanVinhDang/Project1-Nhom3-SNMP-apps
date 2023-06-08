import org.snmp4j.CommunityTarget;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TreeEvent;
import org.snmp4j.util.TreeUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SnmpMonitoringExample {

    public static void main(String[] args) {
        // SNMP parameters
        String ipAddress = "192.168.1.8"; // Replace with the IP address of the SNMP-enabled device
        int port = 161; // Default SNMP port
        int timeout = 5000; // Timeout in milliseconds
        int retries = 3; // Number of retries

        // SNMP community string
        String community = "public"; // Replace with the actual community string

        // OID for the SNMP walk operation
        OID oid = new OID("1.3.6.1.2.1"); // Replace with the desired OID

        try {
            // Create transport mapping and SNMP object
            TransportMapping<?> transport = new DefaultUdpTransportMapping();
            Snmp snmp = new Snmp(transport);
            transport.listen();

            // Set target for the SNMP operation
            Target target = new CommunityTarget(
                    GenericAddress.parse(String.format("udp:%s/%d", ipAddress, port)),
                    new OctetString(community)
            );
            target.setTimeout(timeout);
            target.setRetries(retries);
            target.setVersion(SnmpConstants.version2c);

            // Perform SNMP walk operation
            TreeUtils treeUtils = new TreeUtils(snmp, new DefaultPDUFactory());
            List<TreeEvent> treeEvents = treeUtils.getSubtree(target, oid);
            for (TreeEvent treeEvent : treeEvents) {
                if (treeEvent != null) {
                    if (treeEvent.isError()) {
                        System.err.println("SNMP walk operation failed: " + treeEvent.getErrorMessage());
                    } else {
                        for (VariableBinding varBinding : treeEvent.getVariableBindings()) {
                            System.out.println(varBinding.getOid() + " = " + varBinding.getVariable());
                        }
                    }
                } else {
                    System.err.println("SNMP walk operation timed out");
                }
            }

            // Close SNMP session
            snmp.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
