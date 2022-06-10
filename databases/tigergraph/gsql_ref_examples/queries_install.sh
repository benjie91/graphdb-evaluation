#!/bin/bash
# Mar 4 2018 VL

if [ "$#" -lt 1 ]; then
   echo "Insufficient arguments.  Run 'queries_install.sh -h' for help."
   exit
fi
if [ $1 = -h ]; then
   echo
   printf 'Synopsis: queries_install.sh [-h] graph_name [y]\n'
   echo
   printf '    -h: print this help message and exit.\n'
   printf '    graph_name: a graph such as socialNet\n'
   printf '    y: install the queries.\n'
   echo If the 2nd argument = y, then the queries will be installed also.
   echo   Otherwise, the script will ask whether to install them or not.
   echo
   exit
fi

# $1 is the graph name, such as computerNet or socialNet

# Note: The queries are 2 levels below: $1*/Queries/
queryFolder=$(ls -1d $1*)

cd $queryFolder/Queries

gsql -g $1 'DROP QUERY ALL'

for f in *.gsql; do 
  q="gsql -g $1 $f"
  #echo $q
  $q
done

echo "Number of queries for $1:"
ls -1 *.gsql | wc -l 
read -p "Install the queries? " ans
case $ans in
  [Yy]* ) gsql -g $1 'INSTALL QUERY ALL';;
  * ) exit;;
esac
