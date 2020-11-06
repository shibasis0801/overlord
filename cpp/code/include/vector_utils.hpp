#ifndef CPP_VECTOR_UTILS_HPP
#define CPP_VECTOR_UTILS_HPP

#include "base.hpp"

using std::vector;

namespace ovd {
    namespace utils {
//         Replace with STL
        vector<int> createVector(int start, int end) {
            int count = end - start + 1;
            if (count < 0) {
                return vector<int>();
            }

            vector<int> elements(count);
            repeat(i, count) {
                elements[i] = start + i;
            }
            return elements;
        }
    }
}

#endif //CPP_VECTOR_UTILS_HPP
