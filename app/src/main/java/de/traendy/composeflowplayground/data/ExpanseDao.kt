package de.traendy.composeflowplayground.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpanseDao {
    @Query("SELECT * From expanse")
    fun getAll():Flow<List<Expanse>>

    @Insert
    fun insert(expanse: Expanse)

    @Delete
    fun delete(expanse: Expanse)
}
