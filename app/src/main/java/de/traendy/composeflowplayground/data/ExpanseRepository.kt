package de.traendy.composeflowplayground.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ExpanseRepository {

    private val expanseDao = AppDataBase.create(null).expanseDao()

    suspend fun addExpanse(expanse: Expanse) = withContext(Dispatchers.IO){
        expanseDao.insert(expanse)
    }

    suspend fun deleteExpanse(expanse: Expanse) = withContext(Dispatchers.IO){
        expanseDao.delete(expanse)
    }

    val expanseFlow: Flow<List<Expanse>>
        get() = expanseDao.getAll()

}