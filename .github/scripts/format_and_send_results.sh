#!/bin/bash

# Extract and format Maven test results
TEST_RESULTS=$(grep -A 2 'Results:' maven_output.txt | awk '{if ($0 !~ /^

\[INFO\]

/) print}')
BUILD_SUMMARY=$(grep 'BUILD SUCCESS' maven_output.txt | awk '{print substr($0, index($0,$3))}')
TOTAL_TIME=$(grep 'Total time' maven_output.txt | awk '{print substr($0, index($0,$3))}')
FINISHED_AT=$(grep 'Finished at' maven_output.txt | awk '{print substr($0, index($0,$3))}')

# Format output
FORMATTED_OUTPUT="*Test Results*\n"
FORMATTED_OUTPUT+="${TEST_RESULTS}\n"
FORMATTED_OUTPUT+="*Build:* ${BUILD_SUMMARY}\n"
FORMATTED_OUTPUT+="*Duration:* ${TOTAL_TIME}\n"
FORMATTED_OUTPUT+="*Finished at:* ${FINISHED_AT}\n"
FORMATTED_OUTPUT=$(echo "${FORMATTED_OUTPUT}" | sed 's/time: //g' | sed 's/at: //g')

# Send the formatted output to Slack
curl -X POST -H 'Content-type: application/json' --data "{\"text\":\"${FORMATTED_OUTPUT}\"}" "${SLACK_WEBHOOK}"
