package com.abd.coursesapp.Model.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.abd.coursesapp.Model.Liste;
import com.abd.coursesapp.Model.Modele;
import com.abd.coursesapp.Model.Produit;
import com.abd.coursesapp.Model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class Dao{

    private  DbHelper dbHelper;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SQLiteDatabase dbWritable, dbReadable;

    List<Modele> modeleList = new LinkedList<>();

    // Table comumns
    String[] columnsUser = {"id", "prenom", "nom", "tel", "login"};
    String[] columnsList = {"id", "libelle", "createdAt", "prix", "editedAt", "userId"};
    String[] columnsPrduit = {"id", "libelle", "photo", "quantiter", "montant", "accomplis", "listId"};

    public void classOfProject(){
        modeleList.add(new User());
        modeleList.add(new Liste());
        modeleList.add(new Produit());
    }


    public Dao(Context context){
        classOfProject();
        dbHelper = new DbHelper(context, modeleList);
        dbWritable = dbHelper.getWritableDatabase();
        dbReadable = dbHelper.getReadableDatabase();

    }

    public long inscrire(User user){
        ContentValues contentValue = new ContentValues();
        contentValue.put("prenom", user.getPrenom());
        contentValue.put("nom", user.getNom());
        contentValue.put("tel", user.getTel());
        contentValue.put("login", user.getLogin());
        contentValue.put("password", user.getPassword());
        return  dbWritable.insert("User", null, contentValue);
    }

    public Modele connexion(String login, String password){
        String where = "login = ? AND password = ?";
        String[] whereValue = {login, password};
        User user = null;
        Cursor cursor = dbReadable.query("User",columnsUser ,where, whereValue, null, null, null);
        while (cursor.moveToNext()){
            user = new User(cursor.getInt(0), cursor.getString(2), cursor.getString(1), cursor.getString(3), cursor.getString(4), "");
        }
        return user;
    }

    public long crateList(Liste liste){
        ContentValues contentValue = listContentValue(liste);
        return  dbWritable.insert("Liste", null, contentValue);
    }

    public List<Liste> getAllListUser(int userId){
        List<Liste> listeList = new LinkedList<>();
        String where = "userId = ?";
        String[] whereValue = {userId+""};
        Cursor cursor = dbReadable.query("Liste",columnsList ,where, whereValue, null, null, null);
        // Cursor cursor = dbReadable.rawQuery("SELECT * FROM Liste WHERE userId = " + userId, null);
        if (cursor.moveToFirst()){
            do{
                try {
                    listeList.add(new Liste(cursor.getInt(0), cursor.getString(1) , dateFormat.parse(cursor.getString(2)), cursor.getDouble(3), dateFormat.parse(cursor.getString(4)), userId));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }while(cursor.moveToNext());
        }
        cursor.close();
        return  listeList;
    }



    public long createPrduit(Produit produit){
        ContentValues contentValue = produitContentValue(produit);
        return  dbWritable.insert("Produit", null, contentValue);
    }

    public List<Produit> getAllProduittlist(int listId){
        List<Produit> produitList = new LinkedList<>();
        String where = "listId = ?";
        String[] whereValue = {""+listId};
        Cursor cursor = dbReadable.query("Produit",columnsPrduit ,where, whereValue, null, null, null);
        while (cursor.moveToNext()){
            boolean acomp = cursor.getInt(5) == 1 ? true : false;
            produitList.add(new Produit(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getDouble(4), acomp,cursor.getInt(6)));
        }
        return  produitList;
    }

    public int updateSomeData(String table, Modele modele){
        String[] whereValue ={modele.getId()+""};
        Log.d("udaeeeeeeeeeeeeee", "updateSomeData: " + modele.getId());
        ContentValues contentValues = null;
        if (table.equals("Liste")){
            contentValues  = listContentValue((Liste) modele);
        } else if (table.equals("Produit")){
            contentValues  = produitContentValue((Produit) modele);
            Log.d("udaeeeeeeeeeeeeee", "updateSomeData: " + ((Produit) modele).isAccomplis());
        }
        return  dbWritable.update(table, contentValues, "id =?", whereValue);
    }

    public int deleteSomeData(String tableName, int id){
        String[] whereValue ={id+""};
        return  dbWritable.delete(tableName, "id = ?", whereValue);
    }

    public ContentValues listContentValue(Liste liste){
        ContentValues contentValue = new ContentValues();
        contentValue.put("libelle", liste.getLibelle());
        contentValue.put("createdAt", dateFormat.format(liste.getCreatedAt()));
        contentValue.put("prix", liste.getPrix());
        contentValue.put("editedAt", dateFormat.format(liste.getEditedAt()));
        contentValue.put("userId", liste.getUserId());
        return  contentValue;
    }

    public ContentValues produitContentValue(Produit produit){
        ContentValues contentValue = new ContentValues();
        contentValue.put("libelle", produit.getLibelle());
        contentValue.put("photo", produit.getPhoto());
        contentValue.put("quantiter", produit.getQuantiter());
        contentValue.put("montant", produit.getMontant());
        contentValue.put("accomplis", produit.isAccomplis());
        contentValue.put("listId", produit.getListId());
        return contentValue;
    }

}
