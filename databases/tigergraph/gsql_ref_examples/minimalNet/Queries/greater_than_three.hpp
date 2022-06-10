# This content should be added to <tigergraph.root.dir>/dev/gdk/gsql/src/QueryUdf/ExprFunctions.hpp
#include <algorithm>  // for std::reverse
inline bool greater_than_three (double x) {
  return x > 3;
}
inline string reverse(string str){
  std::reverse(str.begin(), str.end());
  return str;
}
