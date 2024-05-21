package ru.zhogin.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.zhogin.database.insult.InsultDBO

@Dao
interface InsultsDao {
    @Query("SELECT * FROM insults")
    suspend fun getAllInsults() : List<InsultDBO>
    @Query("SELECT * FROM insults")
    fun observeAllInsults() : Flow<List<InsultDBO>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInsult(insultDBO: InsultDBO)
    @Delete
    suspend fun deleteInsult(insultDBO: InsultDBO)
    @Query("DELETE FROM insults")
    suspend fun clearAllInsults()
}