#ifndef BASE_HPP
#define BASE_HPP

// Infrastructure
#include <functional>
#include <algorithm>
#include <type_traits>
#include <numeric>
#include <initializer_list>

// Basic
#include <iostream>

// Containers
#include <vector>
#include <string>
#include <stack>
#include <queue>
#include <map>


// Macros
#define repeat(i, n) for (size_t i = 0; i < n; ++i)
#define all(container) container.begin(), container.end()
#define r_all(container) container.rbegin(), container.rend()
#define take(container, count) container.begin(), container.begin() + count

// Aliases
template<class T>
using Table = std::vector<std::vector<T>>;


#endif
