package utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CucumberReportParser {

    public static void main(String[] args) {
        try {
            // Step 1: Parse Cucumber XML Results
            File inputFile = new File("target/cucumber-report/cucumber.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList testCaseList = doc.getElementsByTagName("testcase");
            int passedTests = 0;
            int failedTests = 0;
            int skippedTests = 0;
            int pendingTests = 0;
            int otherTests = 0;

            for (int i = 0; i < testCaseList.getLength(); i++) {
                Element testCase = (Element) testCaseList.item(i);
                NodeList failureNode = testCase.getElementsByTagName("failure");
                NodeList skippedNode = testCase.getElementsByTagName("skipped");

                if (failureNode.getLength() > 0) {
                    failedTests++;
                } else if (skippedNode.getLength() > 0) {
                    skippedTests++;
                } else {
                    passedTests++;
                }
            }

            // Create summary in the required format
            String summary = String.format(
                    "const passedTests = %d;\nconst failedTests = %d;\nconst skippedTests = %d;\nconst pendingTests = %d;\nconst otherTests = %d;",
                    passedTests, failedTests, skippedTests, pendingTests, otherTests
            );

            // Print the summary to the console
            System.out.println(summary);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendToSlack(String message) {
        try {
            String webhookUrl = System.getenv("SLACK_WEBHOOK");
            URL url = new URL(webhookUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            String jsonPayload = String.format("{\"text\": \"%s\"}", message);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonPayload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
