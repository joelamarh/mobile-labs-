package com.example.example6.repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.example6.data.ShoppingListDao
import com.example.example6.data.ShoppingListDatabase
import com.example.example6.data.ShoppingListItem

class ShoppingListRepository(context: Context) {

    private var shoppingListDao: ShoppingListDao

    init {
        val shoppingListDatabase = ShoppingListDatabase.getDatabase(context)
        shoppingListDao = shoppingListDatabase.shoppingListDao()
    }

    fun getAllShoppingListItems(): LiveData<List<ShoppingListItem>> {
        return shoppingListDao.getAllShoppingListItems()
    }

    fun insertShoppingListItem(shoppingListItem: ShoppingListItem) {
        shoppingListDao.insertShoppingListItem(shoppingListItem)
    }

    fun deleteShoppingListItem(shoppingListItem: ShoppingListItem) {
        shoppingListDao.deleteShoppingListItem(shoppingListItem)
    }

    fun deleteAllShoppingListItems() {
        shoppingListDao.deleteAllShoppingListItems()
    }

    fun updateShoppingListItem(shoppingListItem: ShoppingListItem) {
        shoppingListDao.updateShoppingListItem(shoppingListItem)
    }

}