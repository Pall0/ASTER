package com.example.magfra.aster;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 5/5/2016.
 */
public class Sessione {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public Sessione( Context ctx){
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("iscritti", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }



    public void setLoggedin(boolean logggedin){
        editor.putBoolean("loggedInmode",logggedin);
        editor.commit();
    }

    public boolean loggedin(){
        return prefs.getBoolean("loggedInmode", false);
    }
}