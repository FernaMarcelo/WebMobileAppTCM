package com.example.bazar_uft;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BD_BazarUFT extends SQLiteOpenHelper
{
    private static final int VERSION_BASEDATOS=1;
    private static final String NOMBRE_BASEDATOS="BD_BAZARUFT.db";
    private static final String TABLA_COMPRA= "CREATE TABLE compra (id_compra INTEGER NOT NULL PRIMARY KEY, "+" NombreProducto TEXT , Proveedor TEXT , CodigoProducto TEXT, TipoBoleta TEXT )";
    private static final String TABLA_VENTA= "CREATE TABLE venta (id_venta INTEGER NOT NULL PRIMARY KEY, "+" NombreProducto TEXT , CodigoProducto TEXT, Precio TEXT ,TipoBoleta TEXT)";

    public BD_BazarUFT(Context context)
    {
        super(context,NOMBRE_BASEDATOS,null,VERSION_BASEDATOS);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase )
    {
        System.out.println("se crean las tablas");
        sqLiteDatabase.execSQL(TABLA_COMPRA);
        sqLiteDatabase.execSQL(TABLA_VENTA);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS '"+TABLA_COMPRA + "'");
        db.execSQL("DROP TABLE IF EXISTS '"+TABLA_VENTA + "'");
    }

    public void insertarCompra(Compra compra) {
        SQLiteDatabase db = getWritableDatabase();

        if (db != null)
        {
            db.execSQL("INSERT INTO  compra " +
                    "( NombreProducto , Proveedor, CodigoProducto,TipoBoleta )" +
                    " VALUES" +"('"+ compra.getNombreProducto() +"','"+ compra.getProveedor() +"','" + compra.getCodigoProducto()+"','"+ compra.getTipoBoleta()+"')");
            db.close();
        }

    }
    public void insertarVenta(Venta venta) {
        SQLiteDatabase db = getWritableDatabase();

        if (db != null)
        {
            db.execSQL("INSERT INTO  venta " +
                    "( NombreProducto, CodigoProducto,Precio, TipoBoleta )" +
                    " VALUES" +"('"+ venta.getNombreProducto() +"','" + venta.getCodigoProducto()+"','" + venta.getPrecio()+"','"+ venta.getTipoBoleta()+"')");
            db.close();
        }

    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }



    public Compra buscarCompra(int id_compra)
    {
        SQLiteDatabase db = getWritableDatabase();
        Compra auxCompra = new Compra();
        Cursor auxCursor = db.rawQuery("SELECT * FROM  compra WHERE id_compra = '"+ id_compra + "'", null);

        auxCursor.moveToFirst();

        if (auxCursor != null )
        {

            auxCompra.setNombreProducto(auxCursor.getString(0));
            auxCompra.setProveedor(auxCursor.getString(1));
            auxCompra.setCodigoProducto(auxCursor.getString(2));
           
        }

        else
        {
            auxCompra.setNombreProducto("");
            auxCompra.setProveedor("");
            auxCompra.setCodigoProducto("");

        }

        auxCursor.close();
        db.close();
        return auxCompra;

    }





    public Integer contarCompras(String TipoBoleta)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor auxCursor = db.rawQuery("SELECT COUNT(*) FROM compra WHERE TipoBoleta = '" + TipoBoleta + "'", null);
        auxCursor.moveToFirst();

        Integer i;
        try{
            i = auxCursor.getInt(0);
        }
        catch (Exception ex){
            i = 0;
        }
        auxCursor.close();
        db.close();
        return i;
    }



    public Integer contarVentas(String TipoBoleta)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor auxCursor = db.rawQuery("SELECT COUNT(*) FROM venta WHERE TipoBoleta = '" + TipoBoleta + "'", null);
        auxCursor.moveToFirst();

        Integer i;
        try{
            i = auxCursor.getInt(0);
        }
        catch (Exception ex){
            i = 0;
        }
        auxCursor.close();
        db.close();
        return i;
    }
}
