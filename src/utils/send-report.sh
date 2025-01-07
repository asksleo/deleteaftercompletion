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

# Debug: Print the parsed results
echo "Debug: Parsed Results"
echo "Passed Tests: ${passedTests}"
echo "Failed Tests: ${failedTests}"
echo "Skipped Tests: ${skippedTests}"
echo "Pass Percentage: ${passPercentage}%"
echo "Build Summary: ${BUILD_SUMMARY}"
echo "Total Time: ${TOTAL_TIME}"
echo "Finished At: ${FINISHED_AT}"

# Create summary in the required format
summary="Passed Scenarios = ${passedTests};\nFailed Scenarios = ${failedTests};\nSkipped Scenarios = ${skippedTests};\n Passing rate % = ${passPercentage}%;\nBuild: ${BUILD_SUMMARY};\nDuration: ${TOTAL_TIME};\nFinished at: ${FINISHED_AT};"

# JSON payload with Block Kit for Slack
json_payload=$(cat <<EOF
{
  "blocks": [
    {
      "type": "section",
      "text": {
        "type": "mrkdwn",
        "text": "*Build Summary*\n\n*Passed Tests:* ${passedTests}\n*Failed Tests:* ${failedTests}\n*Skipped Tests:* ${skippedTests}\n*Passing rate:* ${passPercentage}%\n*Build:* ${BUILD_SUMMARY}\n*Duration:* ${TOTAL_TIME}\n*Finished at:* ${FINISHED_AT}"
      },
    {
      "type": "section",
      "fields": [
        {
          "type": "mrkdwn",
          "text": "*Failed Tests:* ${failedTests}"
        },
    {
      "type": "section",
      "fields": [
        {
          "type": "mrkdwn",
          "text": "*Skipped Tests:* ${skippedTests}"
        },
    {
      "type": "section",
      "text": {
        "type": "mrkdwn",
        "text": "*Passing rate:* ${passPercentage}%\n*Build:* ${BUILD_SUMMARY}\n*Duration:* ${TOTAL_TIME}\n*Finished at:* ${FINISHED_AT}"
      }
    }
  ]
}
EOF
)

# Print the JSON payload to the logs for debugging
echo "Debug: JSON Payload"
echo "${json_payload}"

# Send the summary to Slack
SLACK_WEBHOOK_URL=$1
curl -X POST -H 'Content-type: application/json' --data "${json_payload}" "${SLACK_WEBHOOK_URL}"
