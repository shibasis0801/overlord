#ifndef RANGE_HPP
#define RANGE_HPP

// Compile Time Duck Typing
namespace ovd {
    struct range {
        int start;
        int stop;
        int stride;

        range(int start, int stop) : start(start), stop(stop), stride(1) {}

        range(int start, int stop, int stride) : start(start), stop(stop), stride(stride) {}

        struct iterator;

        iterator begin() {
            return { start, stride, stride > 0 };
        }

        iterator end() {
            return { stop, stride, stride > 0 };
        }

        range step(int stride) {
            if (stride < 0)
                return { stop, start, stride * this->stride };
            else
                return { start, stop, stride * this->stride };
        }

        struct iterator {

            int value;
            bool increasing = true;
            int step = 1;
            iterator(int value, int step, bool increasing) : value(value), step(step), increasing(increasing) {}

            iterator &operator=(int element) {
                value = element;
                return *this;
            }

            // Prefix
            iterator &operator++() {
                value += step;
                return *this;
            }

            // Postfix
            iterator operator++(int) {
                auto temp = iterator(value, this->step, increasing);
                value += step;
                return temp;
            }

            bool operator!=(const iterator &iter) const {
                return increasing ? value < iter.value : value > iter.value;
            }

            int operator*() const {
                return value;
            }
        };
    };
}

#endif