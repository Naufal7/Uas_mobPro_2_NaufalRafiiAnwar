package com.example.pets;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailView extends AppCompatActivity {

    private Menu action;
    private MediaPlayer mp;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_animal);

       TextView textDesc = findViewById(R.id.tv_desc);
        ImageView imageView = findViewById(R.id.img_item_photo);
        textDesc.setText(getIntent().getStringExtra("keterangan"));
        imageView.setImageResource(getIntent().getIntExtra("gambar",0));
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_action,menu);
        action = menu;
        action.findItem(R.id.pause).setVisible(false);
        return true;
    }

//    public boolean onOptionsItemSelected (MenuItem item){
//
//        switch (item.getItemId()){
//            case R.id.play:
//                mp = MediaPlayer.create(this,getIntent().getIntExtra("param3",0));
//                mp.start();
//                action.findItem(R.id.play).setVisible(false);
//                action.findItem(R.id.pause).setVisible(true);
//                return true;
//
//            case R.id.pause:
//                if (mp != null && mp.isPlaying()){
//                    mp.pause();
//                }
//                action.findItem(R.id.play).setVisible(true);
//                action.findItem(R.id.pause).setVisible(false);
//                return true;
//
//                default:
//                    return super.onOptionsItemSelected(item);
//        }
//
//    }


}
