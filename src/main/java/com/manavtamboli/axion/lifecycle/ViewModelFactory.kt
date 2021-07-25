package com.manavtamboli.axion.lifecycle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


@Suppress("Unchecked_Cast", "FunctionName")
inline fun <reified VM : ViewModel, reified A1> AxionFactory(arg1 : A1) : ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(VM::class.java))
                VM::class.java.getConstructor(A1::class.java).newInstance(arg1) as T
            else throw IllegalStateException()
        }
    }
}

@Suppress("Unchecked_Cast", "FunctionName")
inline fun <reified VM : ViewModel, reified A1, reified A2> AxionFactory(arg1 : A1, arg2: A2) : ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(VM::class.java))
                VM::class.java.getConstructor(A1::class.java, A2::class.java).newInstance(arg1, arg2) as T
            else throw IllegalStateException()
        }
    }
}

@Suppress("Unchecked_Cast", "FunctionName")
inline fun <reified VM : ViewModel, reified A1, reified A2, reified A3> AxionFactory(arg1 : A1, arg2: A2, arg3 : A3) : ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(VM::class.java))
                VM::class.java.getConstructor(A1::class.java, A2::class.java, A3::class.java).newInstance(arg1, arg2, arg3) as T
            else throw IllegalStateException()
        }
    }
}