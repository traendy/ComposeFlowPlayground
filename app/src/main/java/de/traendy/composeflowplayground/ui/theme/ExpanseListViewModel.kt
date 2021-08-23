package de.traendy.composeflowplayground.ui.theme

import androidx.lifecycle.*
import de.traendy.composeflowplayground.data.BudgetRepository
import de.traendy.composeflowplayground.data.Expanse
import de.traendy.composeflowplayground.data.ExpanseRepository

import kotlinx.coroutines.launch

class ExpanseListViewModel : ViewModel() {

    private val expanseRepository = ExpanseRepository()
    private val budgetRepository = BudgetRepository()

    val expanseList = expanseRepository.expanseFlow.asLiveData()
    val budget = budgetRepository.budgetFlow.asLiveData()

    fun onItemAdded(name: String, amount: Float) {
        val expanse = Expanse(0, name, amount)
        viewModelScope.launch {
            expanseRepository.addExpanse(expanse)
        }
    }

    fun onExpanseDelete(expanse: Expanse){
        viewModelScope.launch {
            expanseRepository.deleteExpanse(expanse)
        }
    }
}