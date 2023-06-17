package com.example.inventory

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast

class DBManager {
    val dbName = "Inventory"
    val dbTable = "Products"
    val id = "id"
    val image = "Image"
    val name = "Name"
    val description = "Description"
    val price = "Price"
    val dbVersion = 1
    val sqlCrearTabla = "CREATE TABLE IF NOT EXISTS " + dbTable + " ("+ id + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ image + " INTEGER,"+ name + " TEXT NOT NULL,"+ description + " TEXT NOT NULL,"+ price + " TEXT NOT NULL)"

    var sqlDB : SQLiteDatabase?= null

    constructor(context: Context){
        val db = DBHelperInventory(context)
        sqlDB = db.writableDatabase

    }
    inner class DBHelperInventory(context: Context) : SQLiteOpenHelper(context, dbName, null, dbVersion){
        var context : Context ?= context
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(sqlCrearTabla)
            Toast.makeText(this.context, "Base de datos creada",
                Toast.LENGTH_LONG).show()
        }
        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS " + dbTable)
        }
    }
    fun getProducts(projection: Array<String>, selection: String, selectionArgs: Array<String>, orderBy: String) : Cursor {
        val query = SQLiteQueryBuilder()
        query.tables = dbTable
        val cursor = query.query(sqlDB, projection, 	selection, selectionArgs, null, null, orderBy)
        return cursor
    }

}
