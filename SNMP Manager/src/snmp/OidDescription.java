package snmp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OidDescription {
    final String oid;

    public OidDescription(String oid) {
        this.oid = oid;
    }

    public String translate() {

        String output = "";
        try {
            String command = "C:\\usr\\bin\\snmptranslate -Td " + this.oid; // Thay đường dẫn trên bằng đường dẫn đã cài net-snmp bin
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));


            String line;
            while ((line = reader.readLine()) != null) {
                output += line;
                output += "\n";
            }
        } catch (IOException e) {
        }
        return output;
     
    }
}
