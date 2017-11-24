package com.example.magfra.aster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by magfra on 25/10/2017.
 */

public class DbHelper extends SQLiteOpenHelper {


    public static final String TAG = DbHelper.class.getSimpleName();
    //nome database e versione
    public static final String DB_NAME = "iscritti.db";
    public static final int DB_VERSION = 1;

    //nome tabella
    public static final String ISCRITTI_TABLE = "iscritti";

    //nomi colonne
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_COGNOME = "cognome";
    public static final String COLUMN_DATA_DI_NASCITA = "dataNascita";
    public static final String COLUMN_COMUNE_NASCITA = "comune";
    public static final String COLUMN_PROVINCIA_NASCITA = "provincia";
    public static final String COLUMN_SESSO = "sesso";
    public static final String COLUMN_CF = "cf";

    public static final String COLUMN_INDIRIZZO="indirizzo";
    public static final String COLUMN_COMUNE_RESIDENZA ="comuneResidenza";
    public static final String COLUMN_PROVINCIA_RESIDENZA="provinciaResidenza";
    public static final String COLUMN_CAP="cap";
    public static final String COLUMN_CELL="telCellulare";
    public static final String COLUMN_FISSO="telefonoFisso";
    public static final String COLUMN_EMAIL="email";
    public static final String COLUMN_PSW="password";
    public static final String COLUMN_IBAN="iban";


    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + ISCRITTI_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NOME + " TEXT,"
            + COLUMN_COGNOME + " TEXT,"
            + COLUMN_DATA_DI_NASCITA + " TEXT,"
            + COLUMN_COMUNE_NASCITA + " TEXT,"
            + COLUMN_PROVINCIA_NASCITA + " TEXT,"
            + COLUMN_SESSO + " INTEGER,"
            + COLUMN_CF + " TEXT UNIQUE,"

            +COLUMN_INDIRIZZO + " TEXT,"
            +COLUMN_COMUNE_RESIDENZA + " TEXT,"
            +COLUMN_CAP + " TEXT,"
            +COLUMN_PROVINCIA_RESIDENZA + " TEXT,"
            +COLUMN_CELL + " TEXT,"
            +COLUMN_FISSO + " TEXT ,"
            +COLUMN_EMAIL + " TEXT,"
            +COLUMN_PSW + " TEXT,"
            +COLUMN_IBAN + " TEXT);";

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *  @param context to use to open or create the database
     *
     */
    public DbHelper(Context context) {
        super( context, DB_NAME, null, DB_VERSION );
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ISCRITTI_TABLE);
        onCreate(db);
    }

    public void aggiungiIscritto(String nome, String cognome, String dataNascita, String comuneNascita, String provinciaNascita, int sesso, String cf,
                                 String indirizzo,String comuneResidenza,String cap, String provinciaResidenza,String cell,String fisso,String email,String psw,String iban) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, nome);
        values.put(COLUMN_COGNOME, cognome);
        values.put(COLUMN_DATA_DI_NASCITA, dataNascita);
        values.put(COLUMN_COMUNE_NASCITA, comuneNascita);
        values.put(COLUMN_PROVINCIA_NASCITA, provinciaNascita);
        values.put(COLUMN_SESSO, sesso);
        values.put(COLUMN_CF, cf);


        values.put(COLUMN_INDIRIZZO,indirizzo);
        values.put(COLUMN_COMUNE_RESIDENZA,comuneResidenza);
        values.put(COLUMN_CAP,cap);
        values.put(COLUMN_PROVINCIA_RESIDENZA,provinciaResidenza);
        values.put(COLUMN_CELL,cell);
        values.put(COLUMN_FISSO,fisso);
        values.put(COLUMN_EMAIL,email);
        values.put(COLUMN_PSW,psw);
        values.put(COLUMN_IBAN,iban);
        long id = db.insert(ISCRITTI_TABLE, null, values);
        db.close();
        Log.d(TAG, "Ti sei registrato " + id);
    }

    public boolean getUser(String cf, String password) {
        String selectQuery = "select * from " + ISCRITTI_TABLE
                + " where " + COLUMN_CF + " = " + "'" + cf + "'"
                + " and " + COLUMN_PSW + "=" + "'" + password + "'";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if(cursor.getCount()>0){
            return true;
        }
        db.close();
        return false;
    }
}
