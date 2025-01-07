#!/bin/bash

# Debug: Print the content of the cucumber.xml file
echo "Debug: Content of cucumber.xml"
cat target/cucumber.xml

# Parse Cucumber XML report
passedTests=$(xmllint --xpath "count(//testsuite/testcase[not(failure)])" target/cucumber.xml)
failedTests=$(xmllint --xpath "count(//testsuite/testcase[failure])" target/cucumber.xml)
skippedTests=$(xmllint --xpath "count(//testsuite/testcase[skipped])" target/cucumber.xml)

# Calculate total tests and pass percentage
totalTests=$((passedTests + failedTests + skippedTests))
passPercentage=0
if [ $totalTests -ne 0 ]; then
  passPercentage=$((100 * passedTests / totalTests))
fi

# Extract build details from maven_output.txt
BUILD_SUMMARY=$(grep 'BUILD' maven_output.txt | head -n 1 | awk '{print substr($0, index($0,$3))}')
TOTAL_TIME=$(grep 'Total time' maven_output.txt | awk '{print substr($0, index($0,$3))}')
FINISHED_AT=$(grep 'Finished at' maven_output.txt | awk '{print substr($0, index($0,$3))}')

# Clean up the extracted details
TOTAL_TIME=$(echo "${TOTAL_TIME}" | sed 's/time: //g')
FINISHED_AT=$(echo "${FINISHED_AT}" | sed 's/at: //g')

# Convert date and time format
FINISHED_DATE=$(date -d "${FINISHED_AT}" +"%m-%d-%Y")
FINISHED_TIME=$(date -d "${FINISHED_AT}" +"%H:%M:%S")

# Debug: Print the parsed results
echo "Debug: Parsed Results"
echo "Passed Tests: ${passedTests}"
echo "Failed Tests: ${failedTests}"
echo "Skipped Tests: ${skippedTests}"
echo "Pass Percentage: ${passPercentage}%"
echo "Build Summary: ${BUILD_SUMMARY}"
echo "Total Time: ${TOTAL_TIME}"
echo "Finished Date: ${FINISHED_DATE}"
echo "Finished Time: ${FINISHED_TIME}"

# Create summary in the required format
summary="*Build Summary*\n\n*Passed Tests:* ${passedTests}\n*Failed Tests:* ${failedTests}\n*Skipped Tests:* ${skippedTests}\n*Pass Percentage:* ${passPercentage}%\n*Build:* ${BUILD_SUMMARY}\n*Duration:* ${TOTAL_TIME}\n*Finished Date:* ${FINISHED_DATE}\n*Finished Time:* ${FINISHED_TIME}"

# Send the summary to Slack
SLACK_WEBHOOK_URL=$1
curl -X POST -H 'Content-type: application/json' --data "{\"text\":\"${summary}\"}" "${SLACK_WEBHOOK_URL}"
