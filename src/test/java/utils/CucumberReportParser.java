package utils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class CucumberReportParser {

    public static void main(String[] args) {
        int passed = 0, failed = 0, skipped = 0, undefined = 0;

        try {
            File inputFile = new File("target/cucumber-report/cucumber.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("testcase");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    NodeList failureList = eElement.getElementsByTagName("failure");
                    if (failureList.getLength() > 0) {
                        failed++;
                    } else {
                        passed++;
                    }
                }
            }

            String output = String.format(
                    "Test Results\n:white_check_mark: %d | :x: %d | :fast_forward: %d | :hourglass_flowing_sand: %d | :question: 0\nResults: %d failed tests | Duration: <1s\nBuild: No build information provided",
                    passed, failed, skipped, undefined, failed);

            System.out.println(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
