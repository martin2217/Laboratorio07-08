package com.example.martin.laboratorio07_08;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ReclamoActivity extends AppCompatActivity{

    private EditText txtDesc;
    private EditText txtTel;
    private EditText txtMail;
    private Button btnReclamar;
    private Button btnAdjuntar;
    static final int CODIGO_RESULTADO_ADJUNTAR_IMG = 3;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reclamo_activity);

        Bundle extras = getIntent().getExtras();
        final LatLng ubicacion = (LatLng) extras.get("coordenadas");

        txtDesc = (EditText) findViewById(R.id.txtDescripcion);
        txtTel = (EditText) findViewById(R.id.txtTelefono);
        txtMail = (EditText) findViewById(R.id.txtMail);
        path="";

        btnReclamar = (Button) findViewById(R.id.btnReclamar);
        btnReclamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reclamo rec = new Reclamo(txtDesc.getText().toString(),
                        txtTel.getText().toString(),txtMail.getText().toString());
                rec.setLatitud(ubicacion.latitude);
                rec.setLongitud(ubicacion.longitude);
                rec.setImagenPath(path);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",rec);
                setResult(MainActivity.RESULT_OK,returnIntent);
                finish();
            }
        });

        btnAdjuntar = (Button) findViewById(R.id.btnAdjuntar);
        btnAdjuntar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        Uri photoURI = FileProvider.getUriForFile(ReclamoActivity.this,
                                "com.example.android.fileprovider",
                                photoFile);
                        // Por un bug, el intent retorna vacío a onActivityResult, asi que lo esquivo usando una variable
                        path=photoURI.toString();
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, CODIGO_RESULTADO_ADJUNTAR_IMG);
                    }
                }
            }
        });
    }
    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODIGO_RESULTADO_ADJUNTAR_IMG && resultCode == RESULT_OK) {
            // Bug hace que vuelva vacío (Si se manda MediaStore.EXTRA_OUTPUT, el intent vuelve null)
            //path=data.getExtras().get(MediaStore.EXTRA_OUTPUT).toString();
        }
    }
}
