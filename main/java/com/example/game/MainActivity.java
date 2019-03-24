package com.example.game;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons=new Button[3][3];
    private Button resetbtn;
    private boolean player1turn = true;

    private int roundcount=0;
    private int player1points=0;
    private int player2points=0;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Tic Tac Toe");
//        getSupportActionBar().setIcon(getDrawable(R.drawable.ttt));

        textViewPlayer1 = findViewById(R.id.textview1);
        textViewPlayer2 = findViewById(R.id.textview2);

        resetbtn = (Button)findViewById(R.id.btnreset);
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetgame();
            }
        });
        buttons[0][0] = (Button)findViewById(R.id.btn00);
        buttons[0][1] = (Button)findViewById(R.id.btn01);
        buttons[0][2] = (Button)findViewById(R.id.btn02);
        buttons[1][0] = (Button)findViewById(R.id.btn10);
        buttons[1][1] = (Button)findViewById(R.id.btn11);
        buttons[1][2] = (Button)findViewById(R.id.btn12);
        buttons[2][0] = (Button)findViewById(R.id.btn20);
        buttons[2][1] = (Button)findViewById(R.id.btn21);
        buttons[2][2] = (Button)findViewById(R.id.btn22);

        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                buttons[i][j].setOnClickListener(this);
            }
        }

    }

//   private void setSupportActionBar(Toolbar toolbar) {
//    }

    @Override
    public void onClick(View v) {
        Button b=(Button)v;

        if (!b.getText().toString().equals("")){
            return;
        }
        if (player1turn)
        {
            b.setText("x");

        }
        else {
            b.setText("0");

        }
        roundcount++;
        if (checkforWin()){
            if (player1turn){
                player1Wins();
            } else {
                player2Wins();
            }
        }else if(roundcount==9){
            draw();
        } else {
            player1turn = !player1turn;
        }
    }

    private void player1Wins() {
        player1points++;
        Toast.makeText(this, "player 1 wins!", Toast.LENGTH_SHORT).show();
        updatepointText();
        resetboard();
    }

    private void player2Wins() {
        player2points++;
        Toast.makeText(this, "player 2 win!", Toast.LENGTH_SHORT).show();
        updatepointText();
        resetboard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetboard();
    }

    private void updatepointText() {
        textViewPlayer1.setText("player 1: "+player1points);
        textViewPlayer2.setText("player 2: "+player2points);
    }

    private void resetboard(){
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
        roundcount=0;
        player1turn=true;

    }
    private void resetgame(){
        player1points=0;
        player2points=0;
        updatepointText();
        resetboard();
    }
    private boolean checkforWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i=0;i<3;i++) {
            if (field[i][0].equals(field[i][1])&&
                    field[i][0].equals(field[i][2])&&
                    !field[i][0].equals("")){
                return true;
            }
        }
        for (int i=0;i<3;i++) {
            if (field[0][i].equals(field[1][i])&&
                    field[0][i].equals(field[2][i])&&
                    !field[0][i].equals("")){
                return true;
            }
        }

        //diagonal
        if (field[0][0].equals(field[1][1])&&
                field[0][0].equals(field[2][2])&&
                !field[0][0].equals("")){
            return true;
        }

        //diagonal
        if (field[0][2].equals(field[1][1])&&
                field[0][2].equals(field[2][0])&&
                !field[0][2].equals("")){
            return true;
        }
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item1:
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/panktisugandhi"));
                startActivity(intent);
               // Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                Intent intent1=new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("https://github.com/panktisugandhi"));
                startActivity(intent1);
                //Toast.makeText(getApplicationContext(),"Item 2 Selected",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
