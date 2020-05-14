package com.example.mahasiswacrud;

public class konfigurasi {
    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    public static final String URL_ADD="http://192.168.1.4/Android/mahasiswa/tambahMhs.php";
    public static final String URL_GET_ALL = "http://192.168.1.4/Android/mahasiswa/tampilSemuaMhs.php";
    public static final String URL_GET_MHS = "http://192.168.1.4/Android/mahasiswa/tampilMhs.php?id=";
    public static final String URL_UPDATE_MHS = "http://192.168.1.4/Android/mahasiswa/updateMhs.php";
    public static final String URL_DELETE_MHS = "http://192.168.1.4/Android/mahasiswa/hapusMhs.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_MHS_ID_CAMABA = "id_camaba";
    public static final String KEY_MHS_NAMA_LENGKAP = "nama_lengkap";
    public static final String KEY_MHS_EMAIL = "email"; //desg itu variabel untuk posisi
    public static final String KEY_MHS_TELP = "telp"; //salary itu variabel untuk gajih
    public static final String KEY_MHS_AGAMA = "agama";
    public static final String KEY_MHS_ASAL_SEKOLAH = "asal_sekolah";
    public static final String KEY_MHS_ALAMAT = "alamat";
    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID_CAMABA = "id_camaba";
    public static final String TAG_NAMA_LENGKAP = "nama_lengkap";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_TELP = "telp";
    public static final String TAG_AGAMA = "agama";
    public static final String TAG_ASAL_SEKOLAH = "asal_sekolah";
    public static final String TAG_ALAMAT = "alamat";


    public static final String MHS_ID = "mhs_id";
}
