package de.traendy.composeflowplayground.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {
    @Query("SELECT * From budget")
    fun getBudget(): Flow<Budget>

    @Insert
    fun insert(budget: Budget)

    @Delete
    fun delete(budget: Budget)
}