package com.example.myfilmratesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddFilm extends AppCompatActivity {
    private EditText entradaTexto;
    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_film);
    }

    public void startShare(View view) {
        final EditText textoIntroducido = (EditText) findViewById(R.id.editText_texto);
        String texto=textoIntroducido.getText().toString();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, texto);
        sendIntent.setType("text/plain");
// Verify that the intent will resolve to an activity
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }


    public void removeText(View view) {
        final EditText entradaTexto = (EditText) findViewById(R.id.editText_texto);
        entradaTexto.setText("");
    }
}
