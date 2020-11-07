@file:JvmName("FragmentUtils")
package com.phoenixoverlord.pravegaapp.framework.extensions

import androidx.fragment.app.Fragment

fun Fragment.getSimpleName() : String {
    return this.javaClass.simpleName
}
