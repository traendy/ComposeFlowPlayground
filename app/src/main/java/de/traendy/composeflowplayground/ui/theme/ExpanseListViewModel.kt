package de.traendy.composeflowplayground.ui.theme

import androidx.lifecycle.*
import de.traendy.composeflowplayground.data.Expanse
import de.traendy.composeflowplayground.data.ExpanseRepository

import kotlinx.coroutines.launch

class ExpanseListViewModel : ViewModel() {

    private val repository = ExpanseRepository()

    val list = repository.expanseFlow.asLiveData()

    fun onItemAdded(name: String, amount: Float) {
        val expanse = Expanse(0, name, amount)
        viewModelScope.launch {
            repository.addExpanse(expanse)
        }
    }

    fun onExpanseDelete(expanse: Expanse){
        viewModelScope.launch {
            repository.deleteExpanse(expanse)
        }
    }
}