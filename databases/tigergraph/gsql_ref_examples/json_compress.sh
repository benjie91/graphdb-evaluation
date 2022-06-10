#!/bin/bash
# Reformat multi-line JSON arrays as a single line, it if will fit
# $1 = graph name
out=Compressed_json
maxLen=80

cd ${1}*
cd Tests
mkdir $out

for jfile in *.json; do
	#mv $jfile Compressed_json/
	python ../../json_compress_lists.py $jfile $maxLen $out/$jfile
done


