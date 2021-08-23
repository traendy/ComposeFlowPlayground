package de.traendy.composeflowplayground.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expanse(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "amount") val amount: Float = 0.0f
)


fun create() = listOf(
    Expanse(1, "Milk", 10.0f),
    Expanse(2, "Honey", 11.0f),
    Expanse(3, "Apples", 12.0f),
    Expanse(4, "Banana", 13.0f),
    Expanse(5, "Sausage", 14.0f),
    Expanse(6, "Dreaks", 15.0f),
)

