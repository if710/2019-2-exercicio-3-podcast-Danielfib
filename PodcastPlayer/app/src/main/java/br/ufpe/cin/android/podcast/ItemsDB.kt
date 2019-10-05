package br.ufpe.cin.android.podcast

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Banco de dados para os itens feito com base no exemplo visto em sala
@Database(entities= arrayOf(ItemFeed::class), version=1)
abstract class ItemsDB : RoomDatabase() {
    abstract fun itemsDAO() : ItemsDAO
    companion object {
        private var INSTANCE : ItemsDB? = null
        fun getDatabase(ctx: Context) : ItemsDB {
            if (INSTANCE == null) {
                synchronized(ItemsDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        ctx.applicationContext,
                        ItemsDB::class.java,
                        "items.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}