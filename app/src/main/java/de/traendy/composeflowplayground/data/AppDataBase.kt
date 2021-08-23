package de.traendy.composeflowplayground.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Expanse::class), version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun expanseDao(): ExpanseDao

    companion object {
        private var INSTANCE:AppDataBase? = null
        fun create(context: Context?):AppDataBase {
            if (INSTANCE == null && context == null) {
                throw IllegalMonitorStateException("This method has to be provided with application context.")
            }
            if (INSTANCE != null) return requireNotNull(INSTANCE)
            INSTANCE = Room.databaseBuilder(
                requireNotNull(context),
                AppDataBase::class.java,
                "database-name"
            ).build()
            return requireNotNull(INSTANCE)
        }
    }
}

