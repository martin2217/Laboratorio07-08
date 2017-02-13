package com.example.martin.laboratorio07_08;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;

public class MarcadoresDialog extends AppCompatActivity{

    Button btnAceptar;
    EditText txtKms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marcadores_dialog);

        final Bundle extras = getIntent().getExtras();

        txtKms = (EditText) findViewById(R.id.editTextKilometros);

        btnAceptar = (Button) findViewById(R.id.btnMarcar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float res = Float.valueOf(txtKms.getText().toString());

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",res);
                returnIntent.putExtra("posicion",(LatLng)extras.get("posicion"));
                setResult(MainActivity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}
