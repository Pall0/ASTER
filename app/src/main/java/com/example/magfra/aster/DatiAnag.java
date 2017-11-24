package com.example.magfra.aster;

import java.io.Serializable;

/**
 * Created by magfra on 26/10/2017.
 */

class DatiAnag implements Serializable{
    private final String nome;
    private final String sCognome;
    private final String sData_nascita;
    private final String sComune_nascita;
    private final String sProv_nascita;
    private final String sSesso;
    private final String sCf_dip;

    DatiAnag(String nome, String sCognome, String sData_nascita, String sComune_nascita, String sProv_nascita, String sSesso, String sCf_dip) {
        this.nome = nome;
        this.sCognome = sCognome;
        this.sData_nascita = sData_nascita;
        this.sComune_nascita = sComune_nascita;
        this.sProv_nascita = sProv_nascita;
        this.sSesso = sSesso;
        this.sCf_dip = sCf_dip;
    }

    public String getNome() {
        return nome;
    }

    public String getsCognome() {
        return sCognome;
    }

    public String getsData_nascita() {
        return sData_nascita;
    }

    public String getsCf_dip() {
        return sCf_dip;
    }

    public String getsComune_nascita() {
        return sComune_nascita;
    }

    public String getsProv_nascita() {
        return sProv_nascita;
    }

    public String getsSesso() {
        return sSesso;
    }

}
