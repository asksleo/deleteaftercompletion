#!/bin/bash

# Debug: Print the content of the cucumber.xml file
echo "Debug: Content of cucumber.xml"
cat target/cucumber.xml

# Parse Cucumber XML report
passedTests=$(xmllint --xpath "count(//testsuite/testcase[not(failure)])" target/cucumber.xml)
failedTests=$(xmllint --xpath "count(//testsuite/testcase[failure])" target/cucumber.xml)
skippedTests=$(xmllint --xpath "count(//testsuite/testcase[skipped])" target/cucumber.xml)
BUILD_SUMMARY=$(grep 'BUILD SUCCESS' maven_output.txt | awk '{print substr($0, index($0,$3))}')
TOTAL_TIME=$(grep 'Total time' maven_output.txt | awk '{print substr($0, index($0,$3))}')
FINISHED_AT=$(grep 'Finished at' maven_output.txt | awk '{print substr($0, index($0,$3))}')

# Debug: Print the parsed results
echo "Debug: Parsed Results"
echo "Passed Tests: ${passedTests}"
echo "Failed Tests: ${failedTests}"
echo "Skipped Tests: ${skippedTests}"

# Create summary in the required format
FORMATTED_OUTPUT+="*Build:* ${BUILD_SUMMARY}\n"
FORMATTED_OUTPUT+="*Duration:* ${TOTAL_TIME}\n"
FORMATTED_OUTPUT+="*Finished at:* ${FINISHED_AT}\n"
summary="const passedTests = ${passedTests};\nconst failedTests = ${failedTests};\nconst skippedTests = ${skippedTests};"

# Send the summary to Slack
SLACK_WEBHOOK_URL=$1
curl -X POST -H 'Content-type: application/json' --data "{\"text\":\"${summary}\"}" "${SLACK_WEBHOOK_URL}"
