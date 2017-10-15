package com.example.keera.pharmacie;

import java.util.ArrayList;
import java.util.Collection;

public class Pharmacie {
    private String quartier;
    private String nom ;
    private String adresse;
    private String tel;
    private double latitude=0.0d;
    private double longitude =0.0d;


    public Pharmacie(String quartier, String nom, String adresse , String tel){
        this.nom = nom;
        this.adresse = adresse;
        this.quartier = quartier;
        this.tel= tel;
    }
    public void setQuartier(String quartier){
        this.quartier=quartier;
    }

    public void setNom(String Nom){
        this.nom=Nom;
    }

    public void setAddresse(String Adresse){
        this.adresse=Adresse;
    }

    public void setLatitude(double Lat){
        this.latitude=Lat;
    }
    public void setTel(String tel){
        this.tel=tel;
    }
    public void setLong(double lon){
        this.longitude=lon;
    }
    public String getNom(){
        return nom;
    }
    public String getQuartier(){
        return quartier;
    }
    public String getAdd(){
        return adresse;
    }
    public double getLa(){
        return latitude;
    }
    public double getLon(){
        return longitude;
    }
    public String getTel(){
        return tel;
    }
    public String toString(){
        return quartier + ":" + nom + "->" + adresse;
    }
}
