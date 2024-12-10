package pageobject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AllureReportLink {

    public static void main(String[] args) {
        String reportUrl = "http://192.168.1.43:49163/index.html";
        int totalTests = getTotalExecutedTests(reportUrl);
        System.out.println("Total executed tests for now: " + totalTests);
    }

    public static int getTotalExecutedTests(String allureReportUrl) {
        int totalExecutedTests = 0;
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpGet request = new HttpGet(allureReportUrl);
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String content = EntityUtils.toString(entity);
                Document doc = Jsoup.parse(content);

                Elements suites = doc.select("div.suite-item");
                for (Element suite : suites) {
                    Element testCountElement = suite.selectFirst("span.test-count");
                    if (testCountElement != null) {
                        totalExecutedTests += Integer.parseInt(testCountElement.text());
                    }
                }
            }

            response.close();
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return totalExecutedTests;
    }
}
