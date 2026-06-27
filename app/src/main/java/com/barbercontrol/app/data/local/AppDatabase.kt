package com.barbercontrol.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.barbercontrol.app.data.local.dao.AppointmentDao
import com.barbercontrol.app.data.local.entity.AppointmentEntity

/**
 * Banco de dados Room da aplicação.
 *
 * Ao adicionar novas entidades:
 * 1. Adicione a classe na lista [entities].
 * 2. Incremente a versão [version].
 * 3. Forneça uma migration ou use `fallbackToDestructiveMigration()` em desenvolvimento.
 */
@Database(
    entities = [AppointmentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appointmentDao(): AppointmentDao
}
