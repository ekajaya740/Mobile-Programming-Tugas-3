package com.example.tugas3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class MessageActivity extends AppCompatActivity {

    private TextView tvBalasan;
    private TextView tvError;
    private EditText edtPesan;
    private Button btnKirimPesan;

    public static final String MSG_KEY = "MSG_2"; // Key untuk intent Extra

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        init();

        String msg = getIntentString();
        tvBalasan.setText(msg);

        btnKirimPesan.setOnClickListener(this::sendMessage);
    }

    private void init(){ // Inisialisasi komponen
        Objects.requireNonNull(getSupportActionBar()).setTitle("Message Activity"); // Mengubah judul Action Bar

        tvBalasan = findViewById(R.id.tv_balasan);
        tvError = findViewById(R.id.tv_error);
        edtPesan = findViewById(R.id.edt_pesan);
        btnKirimPesan = findViewById(R.id.btn_kirim_pesan);
    }

    private String getIntentString(){ // Mengambil String Extra dari intent
        Intent intent = getIntent();
        return intent.getStringExtra(MainActivity.MSG_KEY);
    }

    private void sendMessage(View view){ // Fungsi mengirim pesan ke MessageActivity
        String msg = edtPesan.getText().toString();
        if(!Objects.equals(msg, "")){ // Cek apakah variabel msg == ""
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(MSG_KEY, msg);
            startActivity(intent);
            finish(); // Digunakan agar tidak bisa kembali ke activity sebelumnya
        }else{
            tvError.setVisibility(View.VISIBLE);
        }
    }
}