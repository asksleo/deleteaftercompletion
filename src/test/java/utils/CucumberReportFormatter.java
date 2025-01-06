package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CucumberReportFormatter {
    public static void main(String[] args) {
        int passed = 0, failed = 0, skipped = 0, undefined = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("report.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("Test Passed")) {
                    passed++;
                } else if (line.contains("Test Failed")) {
                    failed++;
                } else if (line.contains("Test Skipped")) {
                    skipped++;
                } else if (line.contains("Test Undefined")) {
                    undefined++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String output = String.format(
                "Test Results\n:white_check_mark: %d | :x: %d | :fast_forward: %d | :hourglass_flowing_sand: %d | :question: 0\nResults: %d failed tests | Duration: <1s\nBuild: No build information provided",
                passed, failed, skipped, undefined, failed);

        System.out.println(output);
    }
}
