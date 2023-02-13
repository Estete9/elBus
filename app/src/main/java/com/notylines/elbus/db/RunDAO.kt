package com.notylines.elbus.db

import androidx.room.*

@Dao
interface RunDAO {

    @Query("SELECT * FROM run_table")
    suspend fun getRuns(): List<Run>

    @Delete
    suspend fun deleteRun(run: Run)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertRun(run: Run)
}