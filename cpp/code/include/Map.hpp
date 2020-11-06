#ifndef MAP_HPP
#define MAP_HPP

#include "base.hpp"

using std::function;
using std::string;

template<class Key, class Value>
struct Entry {
    Key key;
    Value value;
};


// First I am trying with a vector of vectors.
// Usually doubly linked lists are used.
template<class Key, class Value>
struct HashMap {
    Table<Entry<Key, Value>> entries;
    function<int(Value)> hashFunction;

    HashMap() {
        hashFunction = [=] (Value value) -> int {
            return 0;
        };
    }

    explicit HashMap(function<int(Value)> hashFunction): hashFunction(hashFunction)
    {}
};


// Implement an AVL tree, then use it to implement this.
template<class Key, class Value>
struct TreeMap {

};


void tester() {
    auto x = HashMap<string, string>();
}


#endif //MAP_HPP
