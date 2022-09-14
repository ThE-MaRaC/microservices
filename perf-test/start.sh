#! /bin/bash
CWD=$(pwd)
export HEAP="-Xms1g -Xmx1g -XX:MaxMetaspaceSize=256m"

jmeter.sh -n -t microservices_client_test.jmx -l microservices_client_test.jtl -e -o results
chrome.exe $CWD/results/index.html
