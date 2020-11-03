#ifndef HASHMAP_HPP
#define HASHMAP_HPP

#include "base.hpp"

using std::vector;

template<class T>
using Table = vector<vector<T>>;


template<class Key, class Value>
struct Entry {
    Key key;
    Value value;
};

template<class Key, class Value>
struct HashMap {
    Table<Entry<Key, Value>> entries;



};

#endif //HASHMAP_HPP
