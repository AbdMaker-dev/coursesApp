package com.abd.coursesapp.framents;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.abd.coursesapp.Model.Dao.Dao;
import com.abd.coursesapp.Model.Produit;
import com.abd.coursesapp.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class AjoutProduitFrament extends Fragment {


    private static final int RESULT_LOAD_IMG = 1;
    ListView listProduit;
    ImageView image_view;
    ProgressDialog progressDialog;
    Button btnajoutP, btnphoto;
    EditText libelle, quantiter, montant;
    String photo = "";
    int listId;
    public AjoutProduitFrament() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.add_produit_fragment, container, false);
        btnajoutP = view.findViewById(R.id.btnajoutP);
        btnphoto = view.findViewById(R.id.btnphoto);
        libelle = view.findViewById(R.id.libelle);
        quantiter = view.findViewById(R.id.quantiter);
        montant = view.findViewById(R.id.montant);
        image_view = view.findViewById(R.id.image_view);
        listId = getActivity().getIntent().getIntExtra("listId", 0);
        if (savedInstanceState != null){
            listId = savedInstanceState.getInt("listId");
        }

        btnajoutP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*progressDialog = new ProgressDialog(getActivity());
                progressDialog.show();
                progressDialog.setContentView(R.layout.add_list_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );*/
                if (!libelle.getText().toString().equals("") && !quantiter.getText().toString().equals("") && !montant.getText().toString().equals("")){
                    Produit produit = new Produit(libelle.getText().toString(), photo, Integer.parseInt(quantiter.getText().toString()), Double.parseDouble(montant.getText().toString()), false, listId);
                    Dao dao = new Dao(getContext());
                   long result =  dao.createPrduit(produit);
                   if (result >0){
                       libelle.setText("");
                       quantiter.setText("");
                       montant.setText("");
                       image_view.setImageBitmap(null);
                       Toast.makeText(getContext(), "Inserer avec succes!!!", Toast.LENGTH_SHORT).show();
                   }else {
                       Log.d("InsertLIste", "onClick: insert No oki");
                       Toast.makeText(getContext(), "Insertion no reussit", Toast.LENGTH_SHORT).show();
                   }
                }else {
                    Toast.makeText(getContext(), "Tous les champs sont aubligatoire", Toast.LENGTH_SHORT).show();
                }
            }
        });
        openGalery();
        return view;
    }


    public void openGalery(){
        btnphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                image_view.setImageBitmap(selectedImage);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                photo = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getActivity(), "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("listId", listId);
    }
}
