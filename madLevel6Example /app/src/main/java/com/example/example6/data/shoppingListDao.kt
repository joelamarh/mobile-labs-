package com.example.example6.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ShoppingListDao {

    @Query("SELECT * FROM shoppingListTable")
    fun getAllShoppingListItems(): LiveData<List<ShoppingListItem>>

    @Insert
    fun insertShoppingListItem(shoppingListItem: ShoppingListItem)

    @Delete
    fun deleteShoppingListItem(shoppingListItem: ShoppingListItem)

    @Query("DELETE FROM shoppingListTable")
    fun deleteAllShoppingListItems()

    @Update
    fun updateShoppingListItem(shoppingListItem: ShoppingListItem)

}