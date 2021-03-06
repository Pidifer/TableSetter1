package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameView extends AppCompatActivity {
    private RecyclerView mCatolog;
    private tagAdapter mAdapter;
    private RecyclerView.LayoutManager mlayout;
    private Game gameEntery;
    private ArrayList<Tags>listoftags;

    final DatabaseHelper dbHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);

        Intent intent = getIntent();
        this.gameEntery = intent.getParcelableExtra("Game");
        this.listoftags = intent.getParcelableArrayListExtra("Tags");


        createrecycler();

        ImageButton edit = findViewById(R.id.editButton);

        edit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                edit();
            }
        });

        ImageButton deleteButton = findViewById(R.id.imageView7);

        deleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                deleteGame();
            }
        });

        ImageView pic = findViewById(R.id.imageView5);

        TextView name = findViewById(R.id.textView5);

        TextView summary = findViewById(R.id.textView8);

        name.setText(this.gameEntery.getName());

        summary.setText(this.gameEntery.getNotes());

    }

    public void createrecycler(){
        //recylceview is here
        mCatolog = findViewById(R.id.tagList);
        mCatolog.setHasFixedSize(true);
        mlayout = new LinearLayoutManager(this);
        mAdapter = new tagAdapter(gameEntery.getTagArray()) ;

        mCatolog.setLayoutManager(mlayout);
        mCatolog.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new catalogAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int itemPos) {

                //Toast.makeText(this,gameEntery.getTagArray().get(itemPos).getNotes(),Toast.LENGTH_LONG).show();

            }
        });



    }

    public void open(Class des){
        Intent intent = new Intent(this,des);
        startActivity(intent);
    }


    public void close(){
        //open(MainActivity.class);
        finish();
    }

    public void edit(){
        Intent intent = new Intent(this,Add_andor_edit.class);
        intent.putExtra("Tags",this.listoftags);
        intent.putExtra("Game", this.gameEntery);
        startActivity(intent);
    }

    public void deleteGame(){
        Intent intent = new Intent(this,MainActivity.class);
        dbHelper.deleteGameData(gameEntery.getID());
        startActivity(intent);
    }
}
