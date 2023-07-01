package snmp;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OidDescription {
    public String oid;

    public OidDescription(String Oid) {
        this.oid = Oid;
    }
     
    public String sendPost() throws Exception {

            String url = "https://www.marcuscom.com/cgi-bin/snmptransc.pl";

            HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();

            //add request header
            httpClient.setRequestMethod("POST");
            httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
            httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = "oid="+this.oid+"&xOps=detail&action=Translate";

            // Send post request
            httpClient.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
                wr.writeBytes(urlParameters);
                wr.flush();
            }


            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(httpClient.getInputStream()))) {

                String line;
                StringBuilder response = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    response.append(line);
                }

                //print result
                return addNewlinesWithPreservedFormat(extractValueBetweenTags(response.toString(), "PRE", "pre"), 100);

            }


        }


        public static String extractValueBetweenTags(String response, String tagname1, String tagname2) {
            String patternString = "<" + tagname1 + ">(.*?)</" + tagname2 + ">";
            Pattern pattern = Pattern.compile(patternString, Pattern.DOTALL);
            Matcher matcher = pattern.matcher(response);

            if (matcher.find()) {
                return matcher.group(1);
            } else {
                return "";
            }
        }

        public static String addNewlinesWithPreservedFormat(String text, int charactersPerLine) {
            StringBuilder builder = new StringBuilder(text);
            int index = charactersPerLine;
            while (index < builder.length()) {
                int spaceIndex = findLastWhitespaceIndex(builder, index);
                if (spaceIndex != -1) {
                    builder.replace(spaceIndex, spaceIndex + 1, "\n");
                } else {
                    builder.insert(index, "\n");
                }
                index += charactersPerLine + 1; // +1 to account for the added newline character
            }
            return builder.toString();
        }

        public static int findLastWhitespaceIndex(StringBuilder builder, int index) {
            int spaceIndex = -1;
            for (int i = index; i >= 0; i--) {
                char currentChar = builder.charAt(i);
                if (Character.isWhitespace(currentChar)) {
                    spaceIndex = i;
                    break;
                }
            }
            return spaceIndex;
        }

    
}
