/*
 * This C++ source file was generated by the Gradle 'init' task.
 */

#include "app.hpp"
#include "threading.hpp"
#include "vector_utils.hpp"
#include <chrono>
#include "print.hpp"
#include "matrix.hpp"
#include "linear_algebra/elementary.hpp"

using namespace ovd;
using namespace std;
using namespace std::chrono;

inline decltype(auto) getEnd(time_point<steady_clock> start) {
    return duration_cast<nanoseconds>(high_resolution_clock::now() - start).count();
}


void printColumns() {
    auto matrix = createMatrix<int>(3, 3, [&] (size_t row, size_t col) -> int {
        return (3 * row) + col + 1;
    });
    // Write 2d range iterator to convert this into one loop
    repeat(i, 3) {
        repeat(j, 3) {
            print(matrix[i][j]);
        }
        print("\n");
    }

    repeat(i, 3) {
        auto column = ColumnView(matrix, i);
        repeat(j, 3) {
            print(column[j], "\n");
        }
        print("\n");
    }

    Matrix<int> secondMatrix = {
            {1, 2, 3},
            {2, 4, 6},
            {3, 6, 9}
    };
    repeat(i, 3) {
        repeat(j, 3) {
            print(secondMatrix[i][j]);
        }
        print("\n");
    }
    auto row = secondMatrix[0];
    auto column = ColumnView(secondMatrix, 0);
    print(arrayProduct(row, column), "\n");
    print(arrayProduct(column, row), "\n");
}


int main () {
    auto elements = utils::createVector(1, 10000);

    auto startNormal = high_resolution_clock::now();
    auto sumNormal = accumulate(all(elements), 0);
    auto timeNormal = getEnd(startNormal);

    auto startThreaded = high_resolution_clock::now();
    auto sumThreaded = calculateThreadedSum(elements);
    auto timeThreaded = getEnd(startThreaded);

    print("sumNormal: ", sumNormal, ", time: ", timeNormal, '\n');
    print("sumThreaded: ", sumThreaded, ", time: ", timeThreaded, '\n');

    printColumns();
    return 0;
}
