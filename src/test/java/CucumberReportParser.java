import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class CucumberReportParser {

    public static void main(String[] args) {
        try {
            File inputFile = new File("target/cucumber-report/cucumber.xml");
                        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                        Document doc = dBuilder.parse(inputFile);
                        doc.getDocumentElement().normalize();

                        NodeList nList = doc.getElementsByTagName("testcase");

                        StringBuilder report = new StringBuilder("Cucumber Test Report\n====================\n");

                        for (int temp = 0; temp < nList.getLength(); temp++) {
                            Node nNode = nList.item(temp);

                            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element eElement = (Element) nNode;
                                String testName = eElement.getAttribute("name");
                                String testClass = eElement.getAttribute("classname");
                                String duration = eElement.getAttribute("time");

                                NodeList failureList = eElement.getElementsByTagName("failure");
                                String status = (failureList.getLength() > 0) ? "Failed" : "Passed";

                                report.append("Test Name: ").append(testName).append("\n");
                                report.append("Class: ").append(testClass).append("\n");
                                report.append("Duration: ").append(duration).append(" seconds\n");
                                report.append("Status: ").append(status).append("\n\n");
                            }
                        }

                        System.out.println(report.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
