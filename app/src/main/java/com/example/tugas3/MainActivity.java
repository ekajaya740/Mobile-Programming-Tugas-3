/*
Nama    : I Putu Ekajaya Awidya Putra
NIM     : 200030658

note:   Support light mode dan dark mode.
        Beberapa style saya terapkan pada themes.xml dan themes.xml (night) agar otomatis menyesuaikan
        dengan tema pada handphone pengguna.

        - themes.xml adalah tema light mode
        - themes.xml (night) adalah tema dark mode
 */

package com.example.tugas3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextView tvBalasanHeader;
    private TextView tvBalasan;
    private TextView tvError;
    private TextView tvNama;
    private TextView tvNIM;
    private EditText edtPesan;
    private Button btnKirimPesan;
    private Button btnBrowsing;

    public static final String MSG_KEY = "MSG_1"; // Key untuk intent Extra

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        String msg = getIntentString();
        viewMessage(msg);

        btnKirimPesan.setOnClickListener(this::sendMessage);
        btnBrowsing.setOnClickListener(view -> startActivity(new Intent(this, BrowseActivity.class)));


    }

    private void init(){ // Inisialisasi komponen
        Objects.requireNonNull(getSupportActionBar()).setTitle("Main Activity"); // Mengubah judul Action Bar

        tvBalasanHeader = findViewById(R.id.tv_balasan_header);
        tvBalasan = findViewById(R.id.tv_balasan);
        tvError = findViewById(R.id.tv_error);
        tvNama = findViewById(R.id.tv_nama);
        tvNIM = findViewById(R.id.tv_nim);
        edtPesan = findViewById(R.id.edt_pesan);
        btnKirimPesan = findViewById(R.id.btn_kirim_pesan);
        btnBrowsing = findViewById(R.id.btn_browsing);

    }

    private String getIntentString(){ // Mengambil String Extra dari intent
        Intent intent = getIntent();
        return intent.getStringExtra(MessageActivity.MSG_KEY);
    }

    private void sendMessage(View view){ // Fungsi mengirim pesan ke MessageActivity
        String msg = edtPesan.getText().toString();
        if(!Objects.equals(msg, "")){ // Cek apakah variabel msg == ""
            Intent intent = new Intent(this, MessageActivity.class);
            intent.putExtra(MSG_KEY, msg);
            startActivity(intent);
            finish(); // Digunakan agar tidak bisa kembali ke activity sebelumnya
        }else{
            tvError.setVisibility(View.VISIBLE);
        }
    }

    private void viewMessage(String msg){ // Memilih view antara Nama atau pesan
        if(msg != null){ // Jika msg == null maka tampilkan Nama dan NIM
            tvNama.setVisibility(View.GONE);
            tvNIM.setVisibility(View.GONE);
            tvBalasanHeader.setVisibility(View.VISIBLE);
            tvBalasan.setVisibility(View.VISIBLE);

            tvBalasan.setText(msg);
        }else{ // Jika msg != null maka tampilkan tvBalasanHeader dan tvBalasan
            tvNama.setVisibility(View.VISIBLE);
            tvNIM.setVisibility(View.VISIBLE);
            tvBalasanHeader.setVisibility(View.GONE);
            tvBalasan.setVisibility(View.GONE);
        }
    }

}