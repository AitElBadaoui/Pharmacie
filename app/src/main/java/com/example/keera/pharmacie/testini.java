package com.example.keera.pharmacie;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class testini extends Activity {

    DB db = new DB(this);

    ListView lst;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread t = new Thread(new Runnable() {

            public void run() {
                doInBackground();

            }
        });

        if(db.checkDB()== false){
            t.start();

        }
        db.Update();
        ArrayList<Pharmacie> Group = db.getAllelement();
        MyCustomAdapter myCustomAdapterr = new MyCustomAdapter(Group);
        ListView lv = (ListView) findViewById(R.id.list_view);

        lv.setAdapter(myCustomAdapterr);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txtname =(TextView) view.findViewById(R.id.txt_pharma);
                TextView txtdes =(TextView) view.findViewById(R.id.txt_quar);
                Intent i1 = new Intent( testini.this, MapsActivity.class );
                i1.putExtra("Adresse",txtdes.getText());
                i1.putExtra("Nom",txtname.getText());
                startActivityForResult(i1, 0);
                Toast.makeText(getApplicationContext(),txtdes.getText(),Toast.LENGTH_LONG).show();
            }
        });


    }
    private void showProgressDialogWithTitle() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please Wait..");
        progressDialog.setMessage("Preparing to download ...");
        progressDialog.show();
    }
    protected synchronized String doInBackground() {
        try {
            // Connect to the web site

            Document document = Jsoup.connect("http://www.telecontact.ma/services/pharmacies-de-garde/casablanca-Maroc").get();
            Element elemCourant = document.select(".p-garde-quartier").first();
            Pharmacie Phara[] = new Pharmacie[200];
            while(elemCourant != null && elemCourant.hasClass("p-garde-quartier") && elemCourant.tagName() != "section"){
                String quartierCourant = elemCourant.text();
                quartierCourant = quartierCourant.substring(10);
                int i=0;
                while(true){
                    elemCourant = elemCourant.nextElementSibling();
                    i++;
                    if (elemCourant == null || elemCourant.tagName() != "article" ) break;
                    elemCourant.select("h2 .results-number").remove();
                    String nom =elemCourant.select("h2").text();
                    String addr = elemCourant.select(".results-adress").text();
                    String tel = elemCourant.select(".results-telephone .tel").text();
                    Phara[i] = new Pharmacie(quartierCourant,nom,addr,tel);
                    db.insertDB(Phara[i]);
                    //Group.add(Phara[i]);
                    //test

                }
            }
            progressDialog.dismiss();
        } catch (IOException e) {e.printStackTrace();}
        return "Fin";
    }

    public class MyCustomAdapter extends BaseAdapter{

        ArrayList<Pharmacie> item = new ArrayList<Pharmacie>();
        MyCustomAdapter(ArrayList<Pharmacie> list) {
            this.item = list;
        }
        @Override
        public int getCount() {
            return item.size();
        }
        @Override
        public String getItem(int pos) {
            return item.get(pos).getNom();
        }

        @Override
        public long getItemId(int pos) {
            return pos;

        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(getBaseContext());
            View view1=inflater.inflate(R.layout.pharmacies,viewGroup,false);
            TextView txtname = (TextView) view1.findViewById(R.id.txt_pharma);
            TextView txtdetails = (TextView) view1.findViewById(R.id.txt_quar);
            TextView txttel = (TextView) view1.findViewById(R.id.txt_tel);
            txtname.setText(item.get(i).getNom());
            txtdetails.setText(item.get(i).getAdd());
            txttel.setText(item.get(i).getTel());


            return view1;
        }
    }
    /*public void showData(){
        ArrayList<String> listData = db.getAllelement();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listData);
        lst.setAdapter(arrayAdapter);

    }*/
}
