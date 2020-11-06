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
        sum += numbers[i] * numbers[i];
    }
    return sum;
}

llong calculateThreadedSum(
        const vector<int> &numbers
) {
    auto n_proc = thread::hardware_concurrency() - 1;
    auto size = numbers.size();

    int chunk_size = (int)(size / n_proc) + 1;
    llong sum = 0;

    vector<thread> processors(n_proc);
    vector<llong> sums(n_proc);

    repeat(i, n_proc) {
        auto start = i * chunk_size;
        auto end = min(start + chunk_size, size);
        processors[i] = thread([&, i, start, end](llong &acc) {
           acc = calculateSum(numbers, start, end);
        }, std::ref(sums[i]));
    }

    repeat(i, n_proc) {
        processors[i].join();
        sum += sums[i];
    }

    return sum;
}


#endif //CPP_THREADING_HPP
