package com.example.sundaymobility.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sundaymobility.repository.UserRepository
import com.example.sundaymobility.viewmodel.MainViewModel

class MainViewModelFactory(private val repository: UserRepository):
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}