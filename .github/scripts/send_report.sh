#!/bin/bash

# Debug: Print the content of the cucumber.json file
echo "Debug: Content of cucumber.json"
cat target/cucumber.json

# Parse Cucumber JSON report
passedTests=$(jq '[.[] | .elements | .[] | .steps | .[] | select(.result.status == "passed")] | length' target/cucumber.json)
failedTests=$(jq '[.[] | .elements | .[] | .steps | .[] | select(.result.status == "failed")] | length' target/cucumber.json)
skippedTests=$(jq '[.[] | .elements | .[] | .steps | .[] | select(.result.status == "skipped")] | length' target/cucumber.json)

# Debug: Print the parsed results
echo "Debug: Parsed Results"
echo "Passed Tests: ${passedTests}"
echo "Failed Tests: ${failedTests}"
echo "Skipped Tests: ${skippedTests}"

# Create summary in the required format
summary="const passedTests = ${passedTests};\nconst failedTests = ${failedTests};\nconst skippedTests = ${skippedTests};"

# Send the summary to Slack
SLACK_WEBHOOK_URL=$1
curl -X POST -H 'Content-type: application/json' --data "{\"text\":\"${summary}\"}" "${SLACK_WEBHOOK_URL}"
