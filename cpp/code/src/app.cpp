/*
 * This C++ source file was generated by the Gradle 'init' task.
 */

#include "app.hpp"
#include "threading.hpp"
#include "vector_utils.hpp"
#include <chrono>
#include "print.hpp"

using namespace ovd;
using namespace std;
using namespace std::chrono;

inline decltype(auto) getEnd(time_point<steady_clock> start) {
    return duration_cast<nanoseconds>(high_resolution_clock::now() - start).count();
}

int main () {
    auto elements = utils::createVector(1, 10000);

    auto startNormal = high_resolution_clock::now();
    auto sumNormal = calculateSum(elements, 0, elements.size());
    auto timeNormal = getEnd(startNormal);

    auto startThreaded = high_resolution_clock::now();
    auto sumThreaded = calculateThreadedSum(elements);
    auto timeThreaded = getEnd(startThreaded);

    print("sumNormal: ", sumNormal, ", time: ", timeNormal, '\n');
    print("sumThreaded: ", sumThreaded, ", time: ", timeThreaded, '\n');
    return 0;
}
