package com.example.notific;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class ImagenArticulo extends AppCompatActivity {
    ImageView photo_pet;
    Button btn_add;
    Button btn_cu_photo, btn_r_photo;
    LinearLayout linearLayout_image_btn;
    EditText name, des, prec, precio_vacuna;
    private FirebaseFirestore mfirestore;
    private FirebaseAuth mAuth;
    StorageReference storageReference;
    String storage_path = "pet/*";

    private static final int COD_SEL_STORAGE = 200;
    private static final int COD_SEL_IMAGE = 300;

    private Uri image_url;
    String photo = "photo";
    String idd;

    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_articulo);
        progressDialog = new ProgressDialog(this);
        String id = getIntent().getStringExtra("id_pet");
        mfirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        linearLayout_image_btn = findViewById(R.id.images_btn);
        name = findViewById(R.id.nombre);
        des = findViewById(R.id.descripcion);
        prec = findViewById(R.id.precio);
        photo_pet = findViewById(R.id.pet_photo);
        btn_cu_photo = findViewById(R.id.btn_photo);
        btn_r_photo = findViewById(R.id.btn_remove_photo);
        btn_add = findViewById(R.id.btn_add);

        btn_cu_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPhoto();
            }
        });

        btn_r_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("photo", "");
                mfirestore.collection("pet").document(idd).update(map);
                Toast.makeText(ImagenArticulo.this, "Foto eliminada", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void uploadPhoto() {
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");

        startActivityForResult(i, COD_SEL_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if (requestCode == COD_SEL_IMAGE){
                image_url = data.getData();
                subirPhoto(image_url);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void subirPhoto(Uri image_url) {
        progressDialog.setMessage("Actualizando foto");
        progressDialog.show();
        String rute_storage_photo = storage_path + "" + photo + "" + mAuth.getUid() +""+ idd;
        StorageReference reference = storageReference.child(rute_storage_photo);
        reference.putFile(image_url).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful());
                if (uriTask.isSuccessful()){
                    uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String download_uri = uri.toString();
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("photo", download_uri);
                            mfirestore.collection("pet").document(idd).update(map);
                            Toast.makeText(ImagenArticulo.this, "Foto actualizada", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ImagenArticulo.this, "Error al cargar foto", Toast.LENGTH_SHORT).show();
            }
        });
    }


}