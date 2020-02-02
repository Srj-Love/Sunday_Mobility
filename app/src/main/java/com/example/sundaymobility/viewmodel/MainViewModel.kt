package com.example.sundaymobility.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sundaymobility.model.UserModel
import com.example.sundaymobility.repository.UserRepository
import com.example.sundaymobility.utils.Constants
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch

class MainViewModel(private val repository:UserRepository) : ViewModel() {

    init {
        viewModelScope.launch {

            getUsersData()
        }
    }

    private val _userList = MutableLiveData<List<UserModel>>() // private should not bwe expose


   suspend fun getUsersData() {
       val users = repository.getUsers()
       _userList.value = users
    }



    fun getUsers(): LiveData<List<UserModel>> {
        return _userList
    }

    override fun onCleared() {
        super.onCleared()
    }


}