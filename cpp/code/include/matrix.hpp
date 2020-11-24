#ifndef CPP_MATRIX_HPP
#define CPP_MATRIX_HPP

#include "base.hpp"


using std::vector;
using std::function;
using std::out_of_range;

template<class T>
using Matrix = std::vector<std::vector<T>>;

namespace ovd {
    template<class T>
    Matrix<T> createMatrix(size_t rows, size_t cols) {
        return Matrix<T>(rows, vector<T>(cols));
    }

    template<class T>
    Matrix<T> createMatrix(size_t rows, size_t cols, function<T(size_t, size_t)> generator) {
        auto matrix = Matrix<T>(rows, vector<T>(cols));
        repeat(row, rows) {
            repeat(col, cols) {
                matrix[row][col] = generator(row, col);
            }
        }
        return std::move(matrix);
    }

    // For a given rectangular matrix, return the column view of a column of that matrix as an array like object.
    template<class T>
    struct ColumnView {
        ColumnView(const Matrix<T> &matrix, size_t columnIndex): data(matrix), rows(matrix.size()), idx(columnIndex)
        {
            size_t columns = 0;
            if (rows > 0) {
                columns = matrix[0].size();
            }

            if (rows <= 0) {
                throw out_of_range("Matrix is empty");
            }
            if (columnIndex >= columns) {
                throw out_of_range("Column index not present in matrix");
            }
        }

        size_t size() const {
            return rows;
        }

        T operator[](size_t row) const {
            if (row < rows) {
                return data[row][idx];
            }
            else {
                throw out_of_range("Column index not present in matrix");
            }
        }


    private:
        size_t idx;
        size_t rows;
        const Matrix<T>& data;
    };
}

#endif //CPP_MATRIX_HPP
