package com.example.magfra.aster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class UserAreaActivity extends AppCompatActivity {
private Button rimborso,logout;
private Sessione session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

         session=new Sessione(this);
        if (!session.loggedin()){
            via();
        }
        rimborso=(Button)findViewById(R.id.regis);
        logout=(Button)findViewById(R.id.esci);
        rimborso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           Intent rimbo = new Intent( UserAreaActivity.this,RichiediRimboActivity.class );
           startActivity( rimbo );
            }
        });
logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        via();
    }
});
    }

    private void via() {
        session.setLoggedin(false);
        Intent esci = new Intent(UserAreaActivity.this, LoginActivity.class);
        startActivity(esci);
        finish();
    }
}
