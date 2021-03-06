package com.example.ejercicio1.BBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ejercicio1.core.Contacto;

public class SQLOH extends SQLiteOpenHelper {

    public static final String DB_NOMBRE = "agenda";
    public static final int DB_VERSION = 1;
    public static String CONTACTO_NOMBRE="nombre";
    public static String CONTACTO_APELLIDO1="apellido1";
    public static String CONTACTO_APELLIDO2="apellido2";
    public static String CONTACTO_EMAIL="email";
    public static String CONTACTO_TELEFONO="telefono";
    public static String CONTACTO_ID="_id";
    public static String TABLA_AGENDA = "agenda";

    public SQLOH( Context context) {
        super(context, DB_NOMBRE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DBManager", "Creando BBDD " + DB_NOMBRE + " v" + DB_VERSION);
        try {
            db.beginTransaction();
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLA_AGENDA + "(" + CONTACTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CONTACTO_NOMBRE + " string(16) NOT NULL, " + CONTACTO_APELLIDO1 + " string(20) NOT NULL, " + CONTACTO_APELLIDO2 + " string(20), " + CONTACTO_EMAIL + " string(30), " + CONTACTO_TELEFONO + " string(9) NOT NULL" + ")" );
            db.setTransactionSuccessful();
        } catch (SQLException exc){
            Log.e("DBManager.onCreate", "Creando" + TABLA_AGENDA + ": " + exc.getMessage());
        } finally {
            db.endTransaction();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i("DBManager", "DB" + DB_NOMBRE + ": v" + oldVersion + "-> v" + newVersion);
        try{
            db.beginTransaction();
            db.execSQL("DROP TABLE IF EXISTS " + TABLA_AGENDA);
            db.setTransactionSuccessful();
        } catch (SQLException exc){
            Log.e("DBManager.onUpdate", exc.getMessage());
        } finally {
            db.endTransaction();
        }
        this.onCreate(db);
        db.close();
    }

    public Cursor recuperar(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM " + TABLA_AGENDA, null);
        return cursor;
    }

    public boolean insertarContacto(Contacto contacto){

        Cursor cursor = null;
        boolean toret = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        try{
            db.beginTransaction();
            db.execSQL("INSERT OR IGNORE INTO " + TABLA_AGENDA + "(" + CONTACTO_NOMBRE + ", " + CONTACTO_APELLIDO1 + ", " +CONTACTO_APELLIDO2 + ", " + CONTACTO_EMAIL + ", " + CONTACTO_TELEFONO + ") VALUES(?,?,?,?,?)", new String[]{contacto.getNombre(), contacto.getApellido1(), contacto.getApellido2(), contacto.getEmail(), contacto.getTelefono()});
            db.setTransactionSuccessful();
            toret =true;
        } catch (SQLException exc){
            Log.e("DBManager.insertar", exc.getMessage());
        } finally {
            db.endTransaction();
        }
        db.close();
        return toret;

    }

    public boolean eliminarCotacto(int id){
        boolean toret = false;
        SQLiteDatabase db = this.getWritableDatabase();

        try{
            db.beginTransaction();
            db.execSQL("DELETE FROM " + TABLA_AGENDA + " WHERE " + CONTACTO_ID + " = ?", new String[]{String.valueOf(id)});
            db.setTransactionSuccessful();
            toret = true;
        } catch (SQLException exc){
            Log.e("DBManager.eliminar", exc.getMessage());
        }finally {
            db.endTransaction();
        }

        return toret;
    }

    public boolean modificarContacto(Contacto contacto){
        boolean toret= false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(CONTACTO_NOMBRE, contacto.getNombre());
        valores.put(CONTACTO_APELLIDO1, contacto.getApellido1());
        valores.put(CONTACTO_APELLIDO2, contacto.getApellido2());
        valores.put(CONTACTO_EMAIL, contacto.getEmail());
        valores.put(CONTACTO_TELEFONO, contacto.getTelefono());

        try {
             db.beginTransaction();
             db.update(TABLA_AGENDA, valores, CONTACTO_ID + "=?", new String[]{Integer.toString(contacto.getId())});
             db.setTransactionSuccessful();
             toret=true;
        } catch (SQLException exc){
            Log.e("DBManager.modificar", exc.getMessage());
        }finally {
            db.endTransaction();
        }


        return toret;
    }

    public Contacto getContacto(int id){

        System.out.println(id);
        SQLiteDatabase db = this.getReadableDatabase();

        System.out.println("SELECT " + CONTACTO_NOMBRE + ", " + CONTACTO_APELLIDO1 + ", " + CONTACTO_APELLIDO2 + ", " + CONTACTO_EMAIL + ", " + CONTACTO_TELEFONO + " FROM " + TABLA_AGENDA + " WHERE " + CONTACTO_ID + " = " + id);

        Cursor cursor = db.rawQuery("SELECT " + CONTACTO_NOMBRE + ", " + CONTACTO_APELLIDO1 + ", " + CONTACTO_APELLIDO2 + ", " + CONTACTO_EMAIL + ", " + CONTACTO_TELEFONO + " FROM " + TABLA_AGENDA + " WHERE " + CONTACTO_ID + " = " + id, null);

        Contacto contacto=null;
        while (cursor.moveToNext()){
            contacto = new Contacto(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(4), cursor.getString(3));
        }

        cursor.close();
        return contacto;
    }


}
