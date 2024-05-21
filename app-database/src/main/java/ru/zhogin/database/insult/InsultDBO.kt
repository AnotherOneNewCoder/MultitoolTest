package ru.zhogin.database.insult

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("insults")
data class InsultDBO(
    @ColumnInfo("comment")
    val comment: String,
    @ColumnInfo("created")
    val created: String,
    @ColumnInfo("createdby")
    val createdby: String,
    @ColumnInfo("insult")
    val insult: String,
    @ColumnInfo("language")
    val language: String,
    @PrimaryKey(autoGenerate = false)
    val number: Long,
)
