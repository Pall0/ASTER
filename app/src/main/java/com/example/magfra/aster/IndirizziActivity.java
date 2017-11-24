package com.example.magfra.aster;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class IndirizziActivity extends AppCompatActivity {
    private EditText uIndirizzo, uCap, uComuneRes, uProvRes, uCell, uFisso, uEmail, uIban, uPsw, uPswConf;
    private String sIndirizzo, sCap, sComuneRes, sProvRes, sCell, sFisso = "", sEmail, sIban, sPsw, sPswConf, sNome, sCognome, sData_nascita, sComune_nascita, sProv_nascita, sCf_dip, sSesso;
    private int sex;
    private DbHelper db;
    private Button bRegistraz, bIndietro;
    private DatiAnag dipe2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indirizzi);
        dipe2 = (DatiAnag) getIntent().getSerializableExtra("datiAnagrafici");

        db = new DbHelper(this);

        uIndirizzo = (EditText) findViewById(R.id.indirizzo_residenza);

        uCap = (EditText) findViewById(R.id.cap);

        uComuneRes = (EditText) findViewById(R.id.comune_residenza);

        uProvRes = (EditText) findViewById(R.id.prov_residenza);

        uCell = (EditText) findViewById(R.id.cellulare);

        uFisso = (EditText) findViewById(R.id.fisso);

        uEmail = (EditText) findViewById(R.id.email);

        uIban = (EditText) findViewById(R.id.iban);

        uPsw = (EditText) findViewById(R.id.password);

        uPswConf = (EditText) findViewById(R.id.conf_psw);

        bRegistraz = (Button) findViewById(R.id.conf_dati_res);

        bRegistraz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        bIndietro = (Button) findViewById(R.id.indietro);

        bIndietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent indIntent = new Intent(IndirizziActivity.this, DatiAnagraficiActivity.class);
                startActivity(indIntent);
            }
        });


    }


    private void register() {

        initialize();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean validate() {
        boolean valid = true;
        String PatternIndirizzo = "[A-Z a-z]{2,55}";

        if (!sIndirizzo.matches(PatternIndirizzo)) {
            uIndirizzo.setError("Inserisci un indirizzo valido!");
            valid = false;
        }
        if (!sCap.matches("\\d{5}")) {
            uCap.setError("Il CAP  deve essere formato da 5 cifre!");
            valid = false;
        }
        if (!sComuneRes.matches(PatternIndirizzo)) {
            uComuneRes.setError("Inserisci un Cognome valido!");
            valid = false;
        }
        if (!sProvRes.matches("[A-Za-z]{2}")) {
            uProvRes.setError("Devi inserire il codice della provincia (EE se risiedi all'estero)!");
            valid = false;
        }
        if (!sPsw.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+!?()<>/_€=])(?=\\S+$).{6,}$")) {
            uPsw.setError("la password deve contenere almeno 6 caratteri, di cui almeno un carattere maiuscolo, un numero e un carattere tra @#$%^&+!?()<>/_€=");
            valid = false;
        }

        if (!Objects.equals(sPswConf, sPsw)) {
            uPswConf.setError("La password e la password di conferma non coincidono!");
            valid = false;
        }
        if (!sEmail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            uEmail.setError("Non è una email valida!");
            valid = false;
        }
        if (!sIban.matches("^IT\\d{2}[A-Z]\\d{10}[0-9A-Z]{12}$")) {
            uIban.setError("Non è un IBAN italiano nel formato valido!");
            valid = false;
        }
        if (!sCell.matches("^((00|\\+)\\d{2}[\\. ]??)??3\\d{2}[\\. ]??\\d{6,7}([\\,\\;]((00|\\+)\\d{2}[\\. ]??)??3\\d{2}[\\. ]??\\d{6,7})*$")) {
            uCell.setError("Devi inserire un telefono cellulare corretto");
            valid = false;
        }
        return valid;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void initialize() {
        sFisso = uFisso.getText().toString().trim();
        sIndirizzo = uIndirizzo.getText().toString().trim();
        sComuneRes = uComuneRes.getText().toString().trim();
        sProvRes = uProvRes.getText().toString().trim();
        sCap = uCap.getText().toString().trim();
        sCell = uCell.getText().toString().trim();
        sEmail = uEmail.getText().toString().trim();
        sIban = uIban.getText().toString().trim();
        sPsw = uPsw.getText().toString().trim();
        sPswConf = uPswConf.getText().toString().trim();

        sNome = dipe2.getNome();
        sCognome = dipe2.getsCognome();
        sData_nascita = dipe2.getsData_nascita();
        sComune_nascita = dipe2.getsComune_nascita();
        sProv_nascita = dipe2.getsProv_nascita();
        sCf_dip = dipe2.getsCf_dip();
        sSesso = dipe2.getsSesso();
        switch (sSesso) {
            case "maschio":
                sex = 1;
                break;
            case "femmina":
                sex = 2;
                break;
            default:
                sex = 0;
                break;
        }
        if (!validate()) {
            Toast.makeText(this, "Inserisci i dati corretti!", Toast.LENGTH_SHORT).show();
        } else {
            db.aggiungiIscritto(sNome, sCognome, sData_nascita, sComune_nascita, sProv_nascita, sex, sCf_dip, sIndirizzo, sComuneRes, sCap, sProvRes,
                    sCell, sFisso, sEmail, sPsw, sIban);
            registrati();
        }

    }

    private void registrati() {
        Intent vaiAlLogIN = new Intent(IndirizziActivity.this, LoginActivity.class);
        startActivity(vaiAlLogIN);
    }

}