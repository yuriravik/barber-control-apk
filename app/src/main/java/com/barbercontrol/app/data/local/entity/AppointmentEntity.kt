package com.barbercontrol.app.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidade Room que representa um agendamento armazenado localmente.
 *
 * Esta é uma entidade de exemplo — expanda conforme o contrato da API.
 */
@Entity(tableName = "appointments")
data class AppointmentEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "barber_name") val barberName: String,
    @ColumnInfo(name = "service_name") val serviceName: String,
    @ColumnInfo(name = "scheduled_at") val scheduledAt: Long, // timestamp Unix (ms)
    @ColumnInfo(name = "status") val status: String,          // ex: "PENDING", "CONFIRMED", "CANCELLED"
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis()
)
