cmake_minimum_required(VERSION 3.16)
project(cpp)

set(CMAKE_CXX_STANDARD 20)

include_directories(code)
include_directories(code/include)
include_directories(code/src)
include_directories(code/src/clrs)
include_directories(code/src/hackerrank)
include_directories(code/src/math)

add_executable(cpp
        code/src/app.cpp)
