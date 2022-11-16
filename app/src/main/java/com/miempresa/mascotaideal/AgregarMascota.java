package com.miempresa.mascotaideal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class AgregarMascota extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("mascotas");

    FirebaseStorage storage;
    StorageReference storageReference;

    private final int PICK_IMAGE_REQUEST = 22;
    ControladorMascotas controladorMascotas;
    Button agregar, agregarImagen;
    EditText etNombre, etRaza, etTamano, etEdad, etCiudad;
    ImageView imagenMascota;
    private Uri ubicacionImagen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_mascota);
        agregar = findViewById(R.id.button2);
        controladorMascotas = new ControladorMascotas(getApplicationContext());
        etNombre = findViewById(R.id.etnombrein);
        etRaza = findViewById(R.id.etrazain);
        etEdad = findViewById(R.id.etedadin);
        etTamano = findViewById(R.id.ettamanoin);
        etCiudad = findViewById(R.id.etciudadin);
        agregarImagen = findViewById(R.id.button4);
        imagenMascota = findViewById(R.id.imageView);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        agregarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               cargarImagen();
            }
        });

        imagenMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionarImagen();
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre.getText().toString();
                int edad = Integer.parseInt(etEdad.getText().toString());
                Log.d("prueba", "" + edad);
                String raza = etRaza.getText().toString();
                String tamano = etTamano.getText().toString();
                String ciudad = etCiudad.getText().toString();

                Mascota m = new Mascota();
                m.setNombre(nombre);
                m.setCiudad(ciudad);
                m.setTamano(tamano);
                m.setEdad(edad);
                m.setRaza(raza);

                controladorMascotas.agregarMascota(m);
                myRef.child(nombre).setValue(m);

                Toast.makeText(getApplicationContext(), "La mascota se agrego con extio", Toast.LENGTH_LONG).show();
                etNombre.setText("");
                etRaza.setText("");
                etEdad.setText("");
                etTamano.setText("");
                etCiudad.setText("");



            }
        });

    }

    public void seleccionarImagen(){

        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,"Seleccione la imagen"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST
        && resultCode == RESULT_OK && data != null
        && data.getData() != null) {
            ubicacionImagen = data.getData();

            try{
                Bitmap imagen = MediaStore.Images.Media.getBitmap(getContentResolver(), ubicacionImagen);
                imagenMascota.setImageBitmap(imagen);

            }catch(IOException e){
                e.printStackTrace();

            }

        }

    }


    public void cargarImagen(){

        if(ubicacionImagen != null){

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Cargando");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            ref.putFile(ubicacionImagen).addOnSuccessListener(
                    new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(AgregarMascota.this, "Se cargo la image", Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
            );


        }
    }

}







