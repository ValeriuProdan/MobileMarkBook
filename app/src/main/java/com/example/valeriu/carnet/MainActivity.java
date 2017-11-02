package com.example.valeriu.carnet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplication().getBaseContext();
        try {
            Scanner scanner = new Scanner(new File(context.getFilesDir(),"DBFile.txt"));
            int medie = scanner.nextInt();
            TextView Medie = (TextView) findViewById(R.id.medie1);
            Medie.setText(Integer.toString(medie));

        } catch (FileNotFoundException e) {


            File file = new File(context.getFilesDir(), "DBFile.txt");
            FileOutputStream outputStream;
            int i;
            try {
                outputStream = openFileOutput("DBFile.txt", Context.MODE_PRIVATE);

                for(i=0;i<8;i++){
                    outputStream.write('0');
                    outputStream.write(' ');
                }
                outputStream.close();
            }catch (Exception exep) {
                exep.printStackTrace();
            }
        }
    }

    /** Called when the user clicks the Send button */
    public void openMaterie1(View view) {
        Intent intent = new Intent(this, Materie1Activity.class);
        startActivity(intent);
    }
}
