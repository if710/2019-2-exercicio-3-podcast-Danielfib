package br.ufpe.cin.android.podcast

import androidx.room.*

// Interface DAO para acessar os itens do BD
@Dao
interface ItemsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirItem(vararg items:ItemFeed)
    @Update
    fun atualizarItem(vararg items:ItemFeed)
    @Delete
    fun removerItem(vararg items:ItemFeed)
    @Query("SELECT * FROM items")
    fun todosItens() : List<ItemFeed>
    @Query("SELECT * FROM items WHERE title LIKE :q")
    fun buscaItemPeloTitulo(q:String) : List<ItemFeed>
    @Query("SELECT * FROM items WHERE pubDate LIKE :q")
    fun buscaItemPorData(q:String) : List<ItemFeed>
}