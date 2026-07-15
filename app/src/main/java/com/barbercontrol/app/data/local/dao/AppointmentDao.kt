package com.barbercontrol.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.barbercontrol.app.data.local.entity.AppointmentEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO Room para operações de agendamentos.
 *
 * O uso de Flow garante atualizações reativas na UI sempre que os dados mudarem.
 */
@Dao
interface AppointmentDao {

    /** Observa todos os agendamentos ordenados por data. */
    @Query("SELECT * FROM appointments ORDER BY scheduled_at ASC")
    fun getAll(): Flow<List<AppointmentEntity>>

    /** Busca um agendamento pelo ID. */
    @Query("SELECT * FROM appointments WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): AppointmentEntity?

    /** Insere ou atualiza agendamentos (upsert). */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(appointments: List<AppointmentEntity>)

    /** Remove um agendamento pelo ID. */
    @Query("DELETE FROM appointments WHERE id = :id")
    suspend fun deleteById(id: String)

    /** Remove todos os agendamentos (usado no logout). */
    @Query("DELETE FROM appointments")
    suspend fun deleteAll()
}
