package com.example.mahasiswacrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class tampilMahasiswa extends AppCompatActivity implements View.OnClickListener{

    private EditText vte_id_camaba;
    private EditText vte_nama_lengkap;
    private EditText vte_email;
    private EditText vte_telp;
    private EditText vte_agama;
    private EditText vte_asal_Sekolah;
    private EditText vte_alamat;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_mahasiswa);

        Intent intent = getIntent();

        id = intent.getStringExtra(konfigurasi.MHS_ID);

        vte_id_camaba = (EditText) findViewById(R.id.tem_id_camaba);
        vte_nama_lengkap = (EditText) findViewById(R.id.tem_nama_lengkap);
        vte_email = (EditText) findViewById(R.id.tem_email);
        vte_telp = (EditText) findViewById(R.id.tem_telp);
        vte_agama = (EditText) findViewById(R.id.tem_agama);
        vte_asal_Sekolah = (EditText) findViewById(R.id.tem_asal_sekolah);
        vte_alamat = (EditText) findViewById(R.id.tem_alamat);


        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        vte_id_camaba.setText(id);

        getMahasiswa();
    }

    private void getMahasiswa(){
        class GetMahasiswa extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(tampilMahasiswa.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showMahasiswa(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_MHS,id);
                return s;
            }
        }
        GetMahasiswa ge = new GetMahasiswa();
        ge.execute();
    }

    private void showMahasiswa(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String nama_lengkap = c.getString(konfigurasi.TAG_NAMA_LENGKAP);
            String email = c.getString(konfigurasi.TAG_EMAIL);
            String telp = c.getString(konfigurasi.TAG_TELP);
            String agama = c.getString(konfigurasi.TAG_AGAMA);
            String asal_sekolah = c.getString(konfigurasi.TAG_ASAL_SEKOLAH);
            String alamat = c.getString(konfigurasi.TAG_ALAMAT);

            vte_nama_lengkap.setText(nama_lengkap);
            vte_email.setText(email);
            vte_telp.setText(telp);
            vte_agama.setText(agama);
            vte_asal_Sekolah.setText(asal_sekolah);
            vte_alamat.setText(alamat);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateMahasiswa(){
        final String nama_lengkap = vte_nama_lengkap.getText().toString().trim();
        final String email = vte_email.getText().toString().trim();
        final String telp = vte_telp.getText().toString().trim();
        final String agama = vte_agama.getText().toString().trim();
        final String asal_sekolah = vte_asal_Sekolah.getText().toString().trim();
        final String alamat = vte_alamat.getText().toString().trim();

        class UpdateMahasiswa extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(tampilMahasiswa.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(tampilMahasiswa.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi.KEY_MHS_ID_CAMABA,id);
                hashMap.put(konfigurasi.KEY_MHS_NAMA_LENGKAP,nama_lengkap);
                hashMap.put(konfigurasi.KEY_MHS_EMAIL,email);
                hashMap.put(konfigurasi.KEY_MHS_TELP,telp);
                hashMap.put(konfigurasi.KEY_MHS_AGAMA,agama);
                hashMap.put(konfigurasi.KEY_MHS_ASAL_SEKOLAH,asal_sekolah);
                hashMap.put(konfigurasi.KEY_MHS_ALAMAT,alamat);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(konfigurasi.URL_UPDATE_MHS,hashMap);

                return s;
            }
        }

        UpdateMahasiswa ue = new UpdateMahasiswa();
        ue.execute();
    }

    private void deleteMahasiswa(){
        class DeleteMahasiswa extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(tampilMahasiswa.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(tampilMahasiswa.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_DELETE_MHS, id);
                return s;
            }
        }

        DeleteMahasiswa de = new DeleteMahasiswa();
        de.execute();
    }

    private void confirmDeleteMahasiswa(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Mahasiswa ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteMahasiswa();
                        startActivity(new Intent(tampilMahasiswa.this,tampilSemuaMhs.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateMahasiswa();
        }

        if(v == buttonDelete){
            confirmDeleteMahasiswa();
        }
    }
}