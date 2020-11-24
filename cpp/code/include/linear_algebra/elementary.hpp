#ifndef CPP_ELEMENTARY_HPP
#define CPP_ELEMENTARY_HPP

#include "../matrix.hpp"

namespace ovd {
    template<class T>
    T dotProduct(const vector<T> &row, const ColumnView <T> &column) {
        auto rsize = row.size(), csize = column.size(), size = rsize;
        T product = 0;

        if (rsize != csize) {
            throw out_of_range("row and column size differ");
        } else {
            repeat(i, size) {
                product += row[i] * column[i];
            }
        }

        return product;
    }

    template<class T>
    T dotProduct(const ColumnView <T> &column, const vector<T> &row) {
        return dotProduct(row, column);
    }
}

#endif //CPP_ELEMENTARY_HPP
