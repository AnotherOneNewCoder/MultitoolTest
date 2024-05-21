package ru.zhogin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.zhogin.database.dao.InsultsDao
import ru.zhogin.database.insult.InsultDBO

class AppDatabase internal constructor(private val database: AppRoomDatabase) {
    val insultsDao : InsultsDao
        get() = database.insultsDao()
}

@Database(
    entities = [
        InsultDBO::class,
    ],
    version = 1,
    exportSchema = true
)
internal abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun insultsDao() : InsultsDao
}

fun AppDataBase(applicationContext: Context) : AppDatabase {
    val appRoomDatabase = Room.databaseBuilder(
        checkNotNull(applicationContext.applicationContext),
        AppRoomDatabase::class.java,
        "multi-tool_db"
    ).build()
    return AppDatabase(appRoomDatabase)
}