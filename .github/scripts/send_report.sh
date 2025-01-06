#!/bin/bash

# Parse Cucumber JSON report
passedTests=$(jq '.features[] | .elements[] | select(.steps[].result.status=="passed")' target/cucumber.json | wc -l)
failedTests=$(jq '.features[] | .elements[] | select(.steps[].result.status=="failed")' target/cucumber.json | wc -l)
skippedTests=$(jq '.features[] | .elements[] | select(.steps[].result.status=="skipped")' target/cucumber.json | wc -l)

# Create summary in the required format
summary="const passedTests = ${passedTests};\nconst failedTests = ${failedTests};\nconst skippedTests = ${skippedTests};"

# Send the summary to Slack
SLACK_WEBHOOK_URL=$1
curl -X POST -H 'Content-type: application/json' --data "{\"text\":\"${summary}\"}" "${SLACK_WEBHOOK_URL}"
