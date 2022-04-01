package com.example.tugas3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class BrowseActivity extends AppCompatActivity {

    private EditText edtLink;
    private Button btnJalankan;
    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        init();

        btnJalankan.setOnClickListener(view -> {
            Uri uri = toUri();
            if(!uri.toString().equals("")){ // Cek string uri != ""
                tvError.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }else{
                tvError.setVisibility(View.VISIBLE);
            }
        });
    }

    private void init(){ // Inisialisasi komponen
        Objects.requireNonNull(getSupportActionBar()).setTitle("Browse Activity"); // Mengubah judul Action Bar

        edtLink = findViewById(R.id.edt_link);
        btnJalankan = findViewById(R.id.btn_jalankan);
        tvError = findViewById(R.id.tv_error);
    }

    private Uri toUri(){ // Parse url dari edtLink ke URI
        String url = edtLink.getText().toString();

        if(!url.startsWith("http://")){ // Jika url tidak di awali dengan http://
            if(!url.startsWith("https://")){ // Jika tidak url di awali dengan https://
                url = "http://" + url; // Tambahkan http:// di depan url
            }
        }

        if(!Patterns.WEB_URL.matcher(url).matches()){ // Cek pola url
            url = ""; // Jika tidak valid kosongkan url
        }

        return Uri.parse(url); // Return url yang sudah di parse ke URI
    }
}