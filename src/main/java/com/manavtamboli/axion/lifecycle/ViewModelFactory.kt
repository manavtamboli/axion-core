package com.manavtamboli.axion.lifecycle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manavtamboli.axion.core.Arc

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

@Suppress("Unchecked_Cast", "FunctionName")
inline fun <reified VM : AndroidViewModel, reified A1> AndroidAxionFactory(arg1: A1) : ViewModelProvider.AndroidViewModelFactory {
    val application = Arc.require().application
    return object : ViewModelProvider.AndroidViewModelFactory(application) {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(VM::class.java))
                VM::class.java.getConstructor(Application::class.java, A1::class.java).newInstance(application, arg1) as T
            else throw IllegalStateException()
        }
    }
}

@Suppress("Unchecked_Cast", "FunctionName")
inline fun <reified VM : AndroidViewModel, reified A1, reified A2> AndroidAxionFactory(arg1: A1, arg2: A2) : ViewModelProvider.AndroidViewModelFactory {
    val application = Arc.require().application
    return object : ViewModelProvider.AndroidViewModelFactory(application) {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(VM::class.java))
                VM::class.java.getConstructor(Application::class.java, A1::class.java, A2::class.java).newInstance(application, arg1, arg2) as T
            else throw IllegalStateException()
        }
    }
}

@Suppress("Unchecked_Cast", "FunctionName")
inline fun <reified VM : AndroidViewModel, reified A1, reified A2, reified A3> AndroidAxionFactory(arg1: A1, arg2: A2, arg3: A3) : ViewModelProvider.AndroidViewModelFactory {
    val application = Arc.require().application
    return object : ViewModelProvider.AndroidViewModelFactory(application) {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(VM::class.java))
                VM::class.java.getConstructor(Application::class.java, A1::class.java, A2::class.java, A3::class.java).newInstance(application, arg1, arg2, arg3) as T
            else throw IllegalStateException()
        }
    }
}