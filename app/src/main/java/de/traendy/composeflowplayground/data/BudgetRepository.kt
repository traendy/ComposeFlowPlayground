package de.traendy.composeflowplayground.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class BudgetRepository {
    private val budgetDao = AppDataBase.create(null).budgetDao()

    suspend fun addBudget(budget: Budget) = withContext(Dispatchers.IO){
        budgetDao.insert(budget)
    }

    suspend fun deleteBudget(budget: Budget) = withContext(Dispatchers.IO){
        budgetDao.delete(budget)
    }

    val budgetFlow: Flow<Budget>
        get() = budgetDao.getBudget()
}