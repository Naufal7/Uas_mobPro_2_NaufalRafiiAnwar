package com.example.pets;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

public class RescueActivity extends AppCompatActivity {

    private final int PICK_IMAGE_REQUEST = 22;

    private ImageView iv_pic, back, btn_choose;
    private TextView nama_hewan, jenis_hewan, edit_publish, edit_keterangan;
    private Button btn_upload, btn_kembali;
    private ProgressBar loading;
    private String key, pic;
    private SharedPreferences pref;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private Uri filePath;
    private Boolean upload_pic;

    private Calendar myCalendar = Calendar.getInstance();

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_rescue);

        pref = getSharedPreferences("indopetrescue", MODE_PRIVATE);
        key = pref.getString("key", null);
        pic = "-";
        upload_pic = false;

        nama_hewan = findViewById(R.id.nm_hewan);
        jenis_hewan = findViewById(R.id.jns_hewan);

        edit_publish = findViewById(R.id.tgl_hewan);
        iv_pic = findViewById(R.id.iv_pic);
        btn_choose = findViewById(R.id.bt_upload);
        edit_keterangan = findViewById(R.id.edt_keterangan);
        btn_upload = findViewById(R.id.bt_simpan);
        btn_kembali = findViewById(R.id.bt_kembali);
        loading = findViewById(R.id.loading);

        databaseReference = FirebaseDatabase.getInstance().getReference("hewan");

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        edit_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RescueActivity.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nama_hewan.getText().toString().isEmpty()
                        || nama_hewan.getText().toString().isEmpty()
                        || jenis_hewan.getText().toString().isEmpty()
                        || edit_publish.getText().toString().isEmpty()
                        || edit_keterangan.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Seluruh konten harap diisi", Toast.LENGTH_LONG).show();
                } else {
                    loading.setVisibility(View.VISIBLE);
                    if (upload_pic) {
                        uploadImage();
                    } else {
                        insertData(pic);
                    }
                }

            }
        });
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edit_publish.setText(sdf.format(myCalendar.getTime()));
    }

    private void selectImage() {
        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    private void uploadImage() {

        if (filePath != null) {

            // Code for showing progressDialog while uploading
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            final StorageReference ref = storageReference.child("news/" + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Log.d("INI TAG WOY", "onSuccess: uri= " + uri.toString());
                                    pic = uri.toString();
                                    progressDialog.dismiss();
                                    Log.d("TAG", "File telah di upload");
                                    insertData(pic);
                                    upload_pic = false;
                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                        // Progress Listener for loading
                        // percentage on the dialog box
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            upload_pic = true;
            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                iv_pic.setImageBitmap(bitmap);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    private void insertData(String gambar) {
        loading.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(key)) {
            key = databaseReference.push().getKey();
        }

        String nama = nama_hewan.getText().toString();
        String jenis = jenis_hewan.getText().toString();
        String tanggal = edit_publish.getText().toString();
        String keterangan = edit_keterangan.getText().toString();

        //Hewan(String key, String nama, String jenis, String keterangan, String tanggal, String gambar){
        Hewan hewan = new Hewan(key, nama, jenis, keterangan, tanggal, gambar);
        databaseReference.child(key).setValue(hewan);
        databaseReference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                loading.setVisibility(View.GONE);
                Hewan lk = dataSnapshot.getValue(Hewan.class);
                if (lk == null) {
                    Log.e("TAG", "Data kosong");
                    return;
                }

                Log.d("TAG", "Data berhasil diupload");
                onBackPressed();
                //Toast.makeText(getApplicationContext(), "Data berhasil diinputkan", Toast.LENGTH_SHORT);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                loading.setVisibility(View.GONE);
                Log.e("TAG", "Data gagal diupload");
            }
        });

    }

}
