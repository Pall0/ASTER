package com.example.magfra.aster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DatiAnagraficiActivity extends AppCompatActivity {


    private EditText nome, cognome, data_nascita, comune_nascita, prov_nascita, cf_dip;
    private String sNome, sCognome, sData_nascita, sComune_nascita, sProv_nascita, sCf_dip, sSesso;
    private int sex;
    private Button conferma;
    private RadioGroup sesso;
    private RadioButton maschio, femmina;
    private DatiAnag dipe;

    // private DbHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dati_anagrafici);

       // db = new DbHelper(this);

        nome = (EditText) findViewById(R.id.nome_ins);
        cognome = (EditText) findViewById(R.id.cognome_ins);
        maschio = (RadioButton) findViewById(R.id.maschio);
        femmina = (RadioButton) findViewById(R.id.femmina);
        sesso = (RadioGroup) findViewById(R.id.sesso);
        sSesso = "";
        if (maschio.isChecked()) {
            sSesso = "maschio";
        } else if (femmina.isChecked()) {
            sSesso = "femmina";
        }


        //dati per la data di nascita
        data_nascita = (EditText) findViewById(R.id.data_nascita);

        comune_nascita = (EditText) findViewById(R.id.comune_nascita);
        prov_nascita = (EditText) findViewById(R.id.prov_nascita);
        cf_dip = (EditText) findViewById(R.id.cf_dip);


        conferma = (Button) findViewById(R.id.conf_anagraf);
        conferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });


    }

    private void register() {

        initialize();

    }

    private boolean validate() {
        boolean valid = true;
        String PatternNome = "[A-Z a-z]{2,32}";
        String PatternCF = "[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";
        String PatternData = "(0[1-9]|[12][0-9]|3[01])[-/.](0[1-9]|1[012])[- /.](19|20)\\d\\d";
        if (!sNome.matches(PatternNome)) {
            nome.setError("Inserisci un nome valido!");
            valid = false;
        }
        if (!sComune_nascita.matches(PatternNome)) {
            comune_nascita.setError("Inserisci il Comune o lo Stato, se nato/a all'estero!");
            valid = false;
        }
        if (!sCognome.matches(PatternNome)) {
            cognome.setError("Inserisci un Cognome valido!");
            valid = false;
        }
        if (!sCf_dip.matches(PatternCF)) {
            cf_dip.setError("Il codice fiscale non è nel formato valido!");
            valid = false;
        }
        if (!sProv_nascita.matches("[A-Za-z]{2}")) {
            prov_nascita.setError("Inserisci il codice della provincia o EE se nato all'Estero!");
            valid = false;
        }
        if (!sData_nascita.matches(PatternData)) {
            data_nascita.setError("Inserisci la data di nascita nel formato gg/mm/aaaa !");
            valid = false;
        }
        if (sesso.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Devi selezionare se sei maschio o femminna!", Toast.LENGTH_LONG).show();
            valid = false;
        }
        return valid;
    }

    private void vaiAvanti() {
        // definisco l'intenzione di aprire l'Activity "Page1.java"
        Intent openPage1 = new Intent(DatiAnagraficiActivity.this, IndirizziActivity.class);
        openPage1.putExtra( "datiAnagrafici",  dipe);
// passo all'attivazione dell'activity page1.java
        startActivity(openPage1);
    }

    private void initialize() {
        sNome = nome.getText().toString().trim();
        sCognome = cognome.getText().toString().trim();
        sData_nascita = data_nascita.getText().toString().trim();
        sComune_nascita = comune_nascita.getText().toString().trim();
        sProv_nascita = prov_nascita.getText().toString().trim();
        sCf_dip = cf_dip.getText().toString().trim();

        dipe = new DatiAnag(sNome, sCognome, sData_nascita, sComune_nascita, sProv_nascita, sSesso, sCf_dip);
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
            //db.aggiungiIscritto(sNome, sCognome, sData_nascita, sComune_nascita, sProv_nascita, sex, sCf_dip);
            vaiAvanti();
        }


    }

}


