#!/bin/bash
# Mar 4 2018 VL

#shopt -s nullglob
if [ $1 = -h ]; then
   printf 'Sypopsis: run_queries.sh [-h][-l] graph_name [test_name]\n'
   echo
   printf '    -h: print this help message and exit.\n'
   printf '    -l: include the RUN QUERY or curl command in the output (for documentation)\n'
   printf '    graph_name: a graph such as socialNet\n'
   printf '    test_name: name of one query. If included, run only that test. Otherwise, run all tests.\n'
   echo
   printf 'For each selected test,\n'
   printf '  For each RUN QUERY or curl command in the command file:\n'
   printf '     Log the command to the output file.\n'
   printf '     Run the command and record the JSON output to the output file.\n'
   exit
fi

# Decide whether to log the test name
if [ $1 = -l ]; then
  log=true
  graphName=$2
  testName=$3
else
  log=false
  graphName=$1
  testName=$2
fi

# Decide whether to run one test or all tests
if [ "$testName" = "" ]; then
   cond="*"
else
   cond=${testName}
fi

cd $graphName*
if [ ! -e Tests ]; then mkdir Tests; fi
cd Tests


for testfile in ${cond}_run\.*; do   # all the matching run files
  test=${testfile%_run\.*}           # the test name
  rm $test\.json
  while read -r line; do
    cmd=$(echo $line | cut -d' ' -f 1)  # get the first token in the command line
    
    if [ "$cmd" != "curl" ]; then       # gsql commands need to invoke gsql
      prmpt="GSQL > "
      cmdPrefix="gsql -g $graphName -c "
    else                                # curl commands are complete already
      prmpt=""
      cmdPrefix=""
    fi
    
    if [ "$log" = true ]; then
      echo "${prmpt}${line}" | tee -a $test.json
    else
      echo "${prmpt}${line}"
    fi
    
    eval "${cmdPrefix}'${line}'" | tee -a $test.json
    echo
  done < $testfile
done


