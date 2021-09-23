package de.traendy.composeflowplayground.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import de.traendy.composeflowplayground.appContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class BudgetRepository {

    private val budgetDataStore: DataStore<BudgetStore> = (appContext as Context).budgetDataStore

    object BudgetSerializer : Serializer<BudgetStore> {
        override val defaultValue: BudgetStore = BudgetStore.getDefaultInstance()

        override suspend fun readFrom(input: InputStream): BudgetStore {
            try {
                return withContext(Dispatchers.IO) { BudgetStore.parseFrom(input) }
            } catch (exception: InvalidProtocolBufferException) {
                throw CorruptionException("Cannot read proto.", exception)
            }
        }

        override suspend fun writeTo(t: BudgetStore, output: OutputStream) =
            withContext(Dispatchers.IO) { t.writeTo(output) }
    }

    suspend fun addBudget(budget: Budget) =
        budgetDataStore.updateData { it.toBuilder().setBudgetValue(budget.amount).build() }

    suspend fun reduceBudget(amount:Float) = budgetDataStore.updateData {
        val newBudget = it.budgetValue - amount
        it.toBuilder().setBudgetValue(newBudget).build() }

    suspend fun increaseBudget(amount:Float) = budgetDataStore.updateData {
        val newBudget = it.budgetValue + amount
        it.toBuilder().setBudgetValue(newBudget).build() }

    suspend fun deleteBudget() = addBudget(Budget( 0f))

    val budgetFlow: Flow<Budget> =
        budgetDataStore.data.map { budget -> Budget(budget.budgetValue) }.catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e("BudgetRepo", "Error reading sort order preferences.", exception)
                emit(Budget(0.0f))
            } else {
                throw exception
            }

        }
}

private val Context.budgetDataStore: DataStore<BudgetStore> by dataStore(
    fileName = "budgetPreference",
    serializer = BudgetRepository.BudgetSerializer
)