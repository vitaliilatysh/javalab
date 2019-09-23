#!/usr/bin/env bash
for i in $(seq 5000000)
do
  echo $((RANDOM%100000+0))
done

