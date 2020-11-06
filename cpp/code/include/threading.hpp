#ifndef CPP_THREADING_HPP
#define CPP_THREADING_HPP

#include "base.hpp"

using std::cout;
using std::endl;
using std::vector;
using std::thread;
using std::min;


llong calculateSum(
        const vector<int> &numbers,
        size_t start, size_t end
) {
    llong sum = 0;
    traverse(i, start, end) {
        sum += numbers[i];
    }
    return sum;
}

llong calculateThreadedSum(
        const vector<int> &numbers
) {
    auto n_proc = thread::hardware_concurrency();
    auto size = numbers.size();

    int chunk_size = (int)(size / n_proc) + 1;
    llong sum = 0;

    repeat(i, n_proc) {
        auto start = i * chunk_size;
        auto end = min(start + chunk_size, size);
        sum += calculateSum(numbers, start, end);
    }

    return sum;
}


#endif //CPP_THREADING_HPP
