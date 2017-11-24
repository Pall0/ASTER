package com.example.magfra.aster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private Sessione session;
    private DbHelper db;
    private EditText uCF, uPsw;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        session =new Sessione(this);
        db = new DbHelper(this);
        uCF = (EditText) findViewById(R.id.user_cf);
        uPsw = (EditText) findViewById(R.id.psw_log);

        final Button bAccedi = (Button) findViewById(R.id.accedi);

        bAccedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


        final TextView tRegistrati = (TextView) findViewById(R.id.per_registrazione);
        tRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regis = new Intent( LoginActivity.this, DatiAnagraficiActivity.class);
                startActivity(regis);
            }
        });

        if (session.loggedin()){
            Intent accedi = new Intent(LoginActivity.this, UserAreaActivity.class);
            startActivity(accedi);
            finish();

        }
    }




    private void login() {

        String cf = uCF.getText().toString();
        String psw = uPsw.getText().toString();
        if (db.getUser(cf, psw)) {
            session.setLoggedin(true);
            Intent accedi = new Intent(LoginActivity.this, UserAreaActivity.class);
            startActivity(accedi);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "cf o password errati!", Toast.LENGTH_SHORT).show();
        }

    }
}
