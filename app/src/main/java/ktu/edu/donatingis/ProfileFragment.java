package ktu.edu.donatingis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {

    EditText prname, praddress;
    Spinner prbloodtype;
    Button prsbutton, choose, upload;
    DatabaseReference reff;
    Member member;
    ProfileFragment context = this;
    ImageView img;
    FirebaseStorage storage;
    StorageReference storageReference;

    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);
        prname = (EditText) root.findViewById(R.id.prName);
        praddress = (EditText) root.findViewById(R.id.prAddress);
        prbloodtype = (Spinner) root.findViewById(R.id.prbloodtype);
        prsbutton = (Button) root.findViewById(R.id.prsbutton);
        choose = (Button) root.findViewById(R.id.btnChoose);
        upload = (Button) root.findViewById(R.id.btnUpload);
        img = (ImageView) root.findViewById(R.id.primage);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        member = new Member();

        prsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                member.setName((prname.getText().toString().trim()));
                member.setAddress(praddress.getText().toString().trim());
                member.setBloodType(prbloodtype.getSelectedItem().toString().trim());

                reff.push().setValue(member);
                Toast.makeText(getActivity(), "Sucessful!", Toast.LENGTH_SHORT).show();
                prsbutton.setVisibility(View.INVISIBLE);

            }
        });

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choseImage();

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
        return root;
    }



    private void uploadImage() {

        if(filePath != null){
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Uploaded sucessful!", Toast.LENGTH_SHORT).show();
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Upload failed!" +e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded" +(int)progress+"%");
                        }
                    });
        }
    }

    private void choseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),filePath);
                img.setImageBitmap(bitmap);
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}
