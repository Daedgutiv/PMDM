package com.example.comprawebview.BBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.comprawebview.core.ObjetoCompra;

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
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLA_COMPRA + "(" + ID + " int PRIMARY KEY," + NOMBRE + " string(60) NOT NULL," + CANTIDAD + " int NOT NULL)");
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

    public String recuperarNombres(){
        String nombres = "";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + NOMBRE + " FROM " + TABLA_COMPRA, null);

        while (cursor.moveToNext()){
            nombres= nombres +  cursor.getString(0) + "." ;
        }

        cursor.close();
        if (nombres.length()!=0){
            nombres = nombres.substring(0, nombres.length()-1);
        }


        System.out.println("----------" + nombres);
        return nombres;
    }

    public String recuperarCantidades(){
        String cantidades = "";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + CANTIDAD + " FROM " + TABLA_COMPRA, null);

        while (cursor.moveToNext()){
            cantidades= cantidades + cursor.getString(0) + ".";
        }

        cursor.close();
        if (cantidades.length()!=0){
            cantidades = cantidades.substring(0, cantidades.length()-1);
                    }

        return cantidades;
    }

    public String recuperarIds(){
        String ids ="";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + ID + " FROM " + TABLA_COMPRA, null);

        while(cursor.moveToNext()){
            ids = ids + cursor.getInt(0) + ".";
        }

        cursor.close();

        if (ids.length()!=0){
            ids = ids.substring(0, ids.length()-1);
        }


        System.out.println("+++++++++" + ids);

        return ids;
    }

    public boolean insertar(ObjetoCompra objeto){
        Cursor cursor=null;
        boolean toret = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        try{
            db.beginTransaction();
            db.execSQL("INSERT OR IGNORE INTO " + TABLA_COMPRA + "(" + ID + ", " + NOMBRE + ", " + CANTIDAD + ") VALUES(?,?,?)", new String[]{String.valueOf(objeto.getId()), objeto.getNombre(), objeto.getCantidad()});
            db.setTransactionSuccessful();
            toret=true;
        } catch (SQLException exc){
            Log.e("DBManager.insertar", exc.getMessage());
        } finally {
            db.endTransaction();
        }
        db.close();
        return toret;
    }

    public boolean eliminar(int id){
        boolean toret = false;

        SQLiteDatabase db = this.getWritableDatabase();

        try{
            db.beginTransaction();
            db.execSQL("DELETE FROM " + TABLA_COMPRA + " WHERE " + ID + " = ?", new String[]{String.valueOf(id)});
            db.setTransactionSuccessful();
            toret = true;
        } catch (SQLException exc){
            Log.e("DBManager.eliminar", exc.getMessage());
        } finally {
            db.endTransaction();
        }

        return toret;
    }

    public boolean modificar(ObjetoCompra objeto){
        boolean toret = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(NOMBRE, objeto.getNombre());
        valores.put(CANTIDAD, objeto.getCantidad());

        try{
            db.beginTransaction();
            db.update(TABLA_COMPRA, valores, ID + "=?", new String[]{Integer.toString(objeto.getId())});
            db.setTransactionSuccessful();
            toret=true;
        } catch (SQLException exc){
            Log.e("DBManager.modificar", exc.getMessage());
        } finally {
            db.endTransaction();
        }

        return toret;
    }

}
