package com.notylines.elbus.repository

import com.notylines.elbus.db.Run
import com.notylines.elbus.db.RunDatabase

class RunRepository(
    private val db: RunDatabase
) {
    suspend fun deleteRun(run: Run) {
        db.getRunDao().deleteRun(run)
    }

    fun getSavedRuns(): List<Run> {
        return db.getRunDao().getRuns()
    }

    suspend fun addRun(run: Run) {
        db.getRunDao().upsertRun(run)
    }
}