#!/bin/bash
# Original version (v1.2) Mar 4 2018 VL
# May 1 2018 - fix capitalization of /Graph folder name

if [ "$#" -lt 1 ]; then
   echo "Insufficient arguments.  Run 'graph_install.sh -h' for help."
   exit
fi

if [ $1 = -h ]; then
   echo
   printf 'Synopsis: graph_install_.sh [-h] graph_name\n'
   echo
   printf '    -h: print this help message and exit.\n'
   printf '    graph_name: a graph such as socialNet\n'
   echo Creates the given graph schema and loads test data
   exit
fi

cd $1*/Graph
gsql graph_create.gsql
