package com.example.nishant.savage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button play;

    String flag;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play= (Button) findViewById(R.id.play);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,gamecontainer.class);
                flag="button";
                intent.putExtra("flag", flag);
                startActivity(intent);

            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_home_drawer, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.controller:
                showAlert("CONTROLLER");

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void  showAlert(String str) {


        CharSequence colors[] = new CharSequence[] {"On Screen Button", "Moving Bar"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle(str)
                .setItems(colors, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        switch ( id ){
                            case 0:{
                                flag = "button";
                                Intent intent = new Intent(MainActivity.this, gamecontainer.class);
                                intent.putExtra("flag", flag);
                                startActivity(intent);

                                break;
                            }

                            case 1:{
                                flag = "lane";
                                Intent intent = new Intent(MainActivity.this, gamecontainer.class);
                                intent.putExtra("flag", flag);
                                startActivity(intent);

                                break;
                            }
                        }
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog alert11 = builder.create();
        alert11.show();

    }

}
