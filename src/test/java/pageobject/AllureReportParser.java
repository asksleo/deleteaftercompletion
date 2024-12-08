package pageobject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashSet; 
import java.util.Set;

import java.io.File;
import java.io.IOException;

public class AllureReportParser {
    public static void main(String[] args) {
        String jsonDir = "C:\\Users\\karan\\Downloads\\allure-report";
        Summary summary = getSummaryFromJson(jsonDir);
        System.out.println(summary);
    }

    public static Summary getSummaryFromJson(String jsonDir) {
        File dir = new File(jsonDir);
        ObjectMapper mapper = new ObjectMapper();
        Summary summary = new Summary();
        int totalFiles = 0;

        for (File file : dir.listFiles()) {
            if (file.getName().endsWith(".json")) {
                totalFiles++;
                try {
                    JsonNode rootNode = mapper.readTree(file);
                    TestResult result = analyzeTestResults(rootNode);
                    summary.totalTests += result.total;
                    summary.passedTests += result.passed;
                    summary.failedTests += result.failed;
                    System.out.println("File: " + file.getName() + " | Passed: " + result.passed + " | Failed: " + result.failed);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (summary.totalTests > 0) {
            summary.calculatePercentages();
            System.out.println("karan");
        }

        return summary;
    }

    private static TestResult analyzeTestResults(JsonNode rootNode) {
        int passedCount = 0;
        int failedCount = 0;
        int totalCount = 0;

        if (rootNode.isArray()) {
            for (JsonNode node : rootNode) {
                if (node.has("status")) {
                    totalCount++;
                    String status = node.path("status").asText();
                    if (status.equals("passed")) {
                        passedCount++;
                    } else if (status.equals("failed")) {
                        failedCount++;
                    }
                }
            }
        }

        return new TestResult(totalCount, passedCount, failedCount);
    }

    static class TestResult {
        int total;
        int passed;
        int failed;

        TestResult(int total, int passed, int failed) {
            this.total = total;
            this.passed = passed;
            this.failed = failed;
        }
    }

    static class Summary {
        int totalTests = 0;
        int passedTests = 0;
        int failedTests = 0;
        double passPercentage = 0;
        double failPercentage = 0;

        void calculatePercentages() {
            this.passPercentage = (passedTests * 100.0) / totalTests;
            this.failPercentage = (failedTests * 100.0) / totalTests;
        }

        @Override
        public String toString() {
            return "Total Test Cases: " + totalTests + "\n" +
                    "Passed Tests: " + passedTests + "\n" +
                    "Failed Tests: " + failedTests + "\n" +
                    "Pass Percentage: " + passPercentage + "%" + "\n" +
                    "Fail Percentage: " + failPercentage + "%";
        }
    }
}
