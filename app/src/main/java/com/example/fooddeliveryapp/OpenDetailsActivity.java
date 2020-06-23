package com.example.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class OpenDetailsActivity extends AppCompatActivity {
    private static final String TAG = "OpenDetailsActivity";
    TextView name,description,price,counterTxt;
    ImageView imageView;
   // Uri mImageUri;
    Button minusBtn,plusBtn,addToCart;
    private int counter;
    DatabaseReference databaseItems;


    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.minus :
                    downCounter();
                    break;
                case R.id.plus:
                    upCounter();
                    break;

            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.opendetails);
        Log.d(TAG, "onCreate: started.");
        getIncomingIntent();
        databaseItems = FirebaseDatabase.getInstance().getReference("cartItems");
        name = (TextView) findViewById(R.id.name1);
        description = (TextView) findViewById(R.id.description2);
        price = (TextView) findViewById(R.id.price2);
        imageView = (ImageView) findViewById(R.id.imageView);
        counterTxt = (TextView) findViewById(R.id.count);
        addToCart = (Button) findViewById(R.id.addToCart);
        minusBtn =(Button) findViewById(R.id.minus);
        minusBtn.setOnClickListener(clickListener);
        plusBtn = (Button) findViewById(R.id.plus);
        plusBtn.setOnClickListener(clickListener);
        initCounter();
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
                Intent intent = new Intent(OpenDetailsActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

    }

    private void addToCart(){
        String Name = name.getText().toString().trim();
        String Price = price.getText().toString().substring(7);
        String Desc = description.getText().toString();
        String ID = databaseItems.push().getKey();
        String quantity = counterTxt.getText().toString().trim();
        Cart cart = new Cart(ID,Desc,Name,Price,quantity);
        databaseItems.child(ID).setValue(cart);

    }

//    private String getFileExt(Uri uri){
//        ContentResolver contentResolver = getContentResolver();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
//
//    }




    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("image_desc") && getIntent().hasExtra("image_name") && getIntent().hasExtra("image_price")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String imageUrl = getIntent().getStringExtra("image_url");
            String imageName = getIntent().getStringExtra("image_name");
            String imageDesc = getIntent().getStringExtra("image_desc");
            String imagePrice = getIntent().getStringExtra("image_price");
            setImage(imageUrl,imageName,imageDesc,imagePrice);

        }
    }

    private void setImage(String imageUrl,String imageName,String imageDesc,String imagePrice){
        TextView name = (TextView) findViewById(R.id.name1);
        name.setText(imageName);
        TextView description = (TextView) findViewById(R.id.description2);
        description.setText(imageDesc);
        TextView price = (TextView) findViewById(R.id.price2);
        price.setText("Price: "+imagePrice);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.get().load(imageUrl).into(imageView);

    }


    private void initCounter(){
        counter = 0;
        counterTxt.setText(counter+"");
    }

    private void upCounter(){
        counter++;
        counterTxt.setText(counter+"");
    }

    private void downCounter(){
        counter--;
        counterTxt.setText(counter+"");
    }




}
