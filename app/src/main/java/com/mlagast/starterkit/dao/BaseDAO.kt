package com.mlagast.starterkit.dao

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.mlagast.starterkit.model.BaseEntity

@Dao
interface BaseDAO<T: BaseEntity> {
    val tableName: String

    /**
     * Select all objects in the database.
     */
    @RawQuery
    fun select(query: SupportSQLiteQuery): List<T>

    /**
     * Execute SQL query in the database.
     */
    @RawQuery
    fun execute(query: SupportSQLiteQuery): Any

    /**
     * Insert an object in the database.
     *
     * @param obj the object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T)

    /**
     * Insert an array of objects in the database.
     *
     * @param obj the objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: List<T>)

    /**
     * Update an object from the database.
     *
     * @param obj the object to be updated
     */
    @Update
    fun update(obj: T)

    /**
     * Delete an object from the database
     *
     * @param obj the object to be deleted
     */
    @Delete
    fun delete(obj: T)

    /**
     * Delete all objects from the database
     */
    fun deleteAll() {
        execute(SimpleSQLiteQuery(String.format("DELETE FROM ${tableName}")))
    }
}