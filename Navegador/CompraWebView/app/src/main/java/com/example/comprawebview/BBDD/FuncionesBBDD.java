package com.example.comprawebview.BBDD;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;

public class FuncionesBBDD extends SQLiteOpenHelper {

    public static final String BD_NOMBRE="ListaCompra";
    public static final int DB_VERSION = 1;
    public static final String ID="id";
    public static final String NOMBRE = "nombre";
    public static final String CANTIDAD="cantidad";
    public static final String TABLA_COMPRA="listaCompra";

    public FuncionesBBDD(Context c){
        super(c, BD_NOMBRE, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("DBManager", "Creando BBDD " + BD_NOMBRE + " v" + DB_VERSION);
        try{
            db.beginTransaction();
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLA_COMPRA + "(" + ID + " int PRIMARY KEY AUTOINCREMENT," + NOMBRE + " string(60) NOT NULL," + CANTIDAD + " int NOT NULL)");
            db.setTransactionSuccessful();
        } catch (SQLException exc){
            Log.e("DBManager.onCreate", "Creando " + TABLA_COMPRA + ": " + exc.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DBManager", "DB" + BD_NOMBRE + ": v" + oldVersion + "-> v" + newVersion);
        try{
            db.beginTransaction();
            db.execSQL("DROP TABLE IF EXISTS " + TABLA_COMPRA);
            db.setTransactionSuccessful();
        } catch (SQLException exc){
            Log.e("DBManager.onUpdate", exc.getMessage());
        } finally {
            db.endTransaction();
        }
        this.onCreate(db);
        db.close();
    }

    public String[] recuperar(){
        String[] lista = null;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_COMPRA, null);



        return lista;
    }
}
