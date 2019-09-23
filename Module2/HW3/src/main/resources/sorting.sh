#!/usr/bin/env bash
START_TIME=`date +"%s"`
sort -n ./numbers.txt -o ./script_sorted.txt
END_TIME=`date +"%s"`
TOTAL=`expr "$END_TIME" - "$START_TIME"`
echo "Total time to sort in seconds:" ${TOTAL}