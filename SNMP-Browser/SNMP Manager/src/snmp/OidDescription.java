package snmp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OidDescription {
    public String oid;

    public OidDescription(String oid) {
        this.oid = oid;
    }

    public String translate() {

        String output = "";
        ProcessBuilder processBuilder = new ProcessBuilder();
        // Windows
        processBuilder.command("cmd.exe", "/c", "snmptranslate -Td " + this.oid);
        try {

            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output += line;
                output += "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;

    }
}
