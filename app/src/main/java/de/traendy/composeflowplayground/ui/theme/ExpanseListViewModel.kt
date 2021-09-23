package de.traendy.composeflowplayground.ui.theme

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import de.traendy.composeflowplayground.data.Budget
import de.traendy.composeflowplayground.data.BudgetRepository
import de.traendy.composeflowplayground.data.Expanse
import de.traendy.composeflowplayground.data.ExpanseRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.launch

class ExpanseListViewModel : ViewModel() {

    private val expanseRepository = ExpanseRepository()
    private val budgetRepository = BudgetRepository()

    val expanseList = expanseRepository.expanseFlow.asLiveData()
    val budget = budgetRepository.budgetFlow.asLiveData()
    private val backgroundVisibilityFlow = MutableStateFlow(false)
    val isBackgroundVisible = backgroundVisibilityFlow.asLiveData()

    fun onItemAdded(name: String, amount: Float) {
        val expanse = Expanse(0, name, amount)
        viewModelScope.launch {
            expanseRepository.addExpanse(expanse)
            budgetRepository.reduceBudget(expanse.amount)
        }
        backgroundVisibilityFlow.value = true
        delayBackgroundAimation()
    }

    private fun delayBackgroundAimation(){
        viewModelScope.launch {
            delay(5000)
            backgroundVisibilityFlow.emit(false)
        }
    }

    fun onExpanseDelete(expanse: Expanse) {
        viewModelScope.launch {
            expanseRepository.deleteExpanse(expanse)
            budgetRepository.increaseBudget(expanse.amount)
        }
    }

    fun increaseBudget() {
        viewModelScope.launch {
            budgetRepository.addBudget(Budget(100f))
        }
    }

}