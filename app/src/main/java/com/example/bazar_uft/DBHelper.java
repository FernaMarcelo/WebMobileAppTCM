package com.example.bazar_uft;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "clientes.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CLIENTES = "clientes";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_DIRECCION = "direccion";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla de clientes
        String createClientesTable = "CREATE TABLE " + TABLE_CLIENTES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOMBRE + " TEXT,"
                + COLUMN_DIRECCION + " TEXT"
                + ")";
        db.execSQL(createClientesTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Manejar la actualización de la base de datos si es necesario
        // Aquí puedes realizar operaciones como eliminar y recrear la tabla
        // o migrar los datos de una versión a otra.
    }
}
