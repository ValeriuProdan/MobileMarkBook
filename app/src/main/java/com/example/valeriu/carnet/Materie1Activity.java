package com.example.valeriu.carnet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Materie1Activity extends AppCompatActivity {

    int number[] = new int[5];
    int teza = 0, medie, medieCopie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materie1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Context context = getApplication().getBaseContext();

        try {
            Scanner scanner = new Scanner(new File(context.getFilesDir(), "DBFile.txt"));


        // find the next int token and print it
        // loop for the whole scanner
        int i;
        medie=scanner.nextInt();
        for(i=0;i<5;i++) {

            // if the next is a int, print found and the int
            if (scanner.hasNextInt()) {
                number[i]=scanner.nextInt();
            }
            // if no int is found, print "Not Found:" and the token
            //scanner.next();
        }
        teza=scanner.nextInt();

        EditText Casuta =(EditText) findViewById(R.id.MateNota1);
        if (number[0] != 0) { Casuta.setText(Integer.toString(number[0]));}

        Casuta =(EditText) findViewById(R.id.MateNota2);
        if (number[1] != 0) {Casuta.setText(Integer.toString(number[1]));}

        Casuta =(EditText) findViewById(R.id.MateNota3);
        if (number[2] != 0) {Casuta.setText(Integer.toString(number[2]));}

        Casuta =(EditText) findViewById(R.id.MateNota4);
        if (number[3] != 0) {Casuta.setText(Integer.toString(number[3]));}

        Casuta =(EditText) findViewById(R.id.MateNota5);
        if (number[4] != 0) {Casuta.setText(Integer.toString(number[4]));}

        Casuta =(EditText) findViewById(R.id.MateTeza);
        if (teza != 0) {Casuta.setText(Integer.toString(teza));}

        TextView CasutaTeza =(TextView) findViewById(R.id.MateMedie);
        if (medie != 0) {CasutaTeza.setText(Integer.toString(medie));}
            //scanner.next();



        // close the scanner
        scanner.close();
        }catch (FileNotFoundException e){

        }
    }


    static boolean isValidNumber(String value) {
        // Loop over all characters in the String.
        // ... If isDigit is false, this method too returns false.
        if (value.length() == 0) return false;
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    int calculeazaNota(int n, int sum, int medie, int teza) {
        int medienoua = 0;
        int i = 0;
        for (i = 1; i <= 10; i++) {
            medienoua = calculeazaMedia(n + 1, sum + i, teza);
            if (medienoua > medie) return i;
        }
        return 0;
    }

    int calculeazaMedia(int n, int sum, int teza) {
        int medie = 0;
        float medieReala;
        if (teza != 0) {
            medieReala = ((float)sum * 3/n + teza) / 4;
        }
        else {
            medieReala = sum / n;
        }
        medie = (int)(medieReala + 0.5);
        return medie;
    }

    public void calculeazaMedie(View view) {

        String message[] = new String[5];
        //int number[] = new int[5];
        int nrnote = 0;
        String Teza;

        EditText editText= (EditText) findViewById(R.id.MateNota1);
        message[0] = editText.getText().toString();

        editText = (EditText) findViewById(R.id.MateNota2);
        message[1] = editText.getText().toString();

        editText = (EditText) findViewById(R.id.MateNota3);
        message[2] = editText.getText().toString();

        editText = (EditText) findViewById(R.id.MateNota4);
        message[3] = editText.getText().toString();

        editText = (EditText) findViewById(R.id.MateNota5);
        message[4] = editText.getText().toString();

        editText = (EditText) findViewById(R.id.MateTeza);
        Teza = editText.getText().toString();

        int sum = 0;
        int i, j, k;


        for (i = 0; i < message.length; i++) {
           // number[i] = 0;
            if (isValidNumber(message[i])) {
                nrnote++;
                number[i] = Integer.parseInt(message[i]);
            }
            sum += number[i];
        }
        //int teza = 0, medie, medieCopie;
        if (isValidNumber(Teza)) {
            teza = Integer.parseInt(Teza);
            float medieReala = ((float)sum * 3/nrnote + teza) / 4;
            medie = (int)(medieReala + 0.5);
        }
        else
        {
            float medieReala = ((float)sum /nrnote);
            medie = (int)(medieReala + 0.5);
        }

        String result = Integer.toString(medie), textSugestii = "";
        int medienoua;


        TextView tv1 = (TextView) findViewById(R.id.MateMedie);
        tv1.setText(result);

        TextView Sugestii = (TextView) findViewById(R.id.MateSugestii);

        medieCopie = medie;
        int first, second, third;
        first = second = third = 0;

        for (i = 1; i <= 10; i++) {
            medienoua = calculeazaMedia(nrnote + 1, sum + i, teza);
            if (medienoua > medieCopie)
            {
                medieCopie = medienoua;
                textSugestii = textSugestii + " + " + Integer.toString(i) + " -> " + Integer.toString(medieCopie) + "\n";
            }
        }

        for (i = 1; i <= 10; i++) {
            for (j = i; j <= 10; j++) {
                medienoua = calculeazaMedia(nrnote + 2, sum + i + j, teza);
                if (medienoua > medieCopie) {
                    medieCopie = medienoua;
                    first = (i + j) / 2; second = (i + j) / 2;
                    if ((first + second) % 2 == 1) second += 1;
                    textSugestii = textSugestii + " + " + Integer.toString(first) + "+" + Integer.toString(second) + " -> " + Integer.toString(medieCopie) + "\n";
                }
            }
        }
        for (i = 1; i <= 10; i++) {
            for (j = i; j <= 10; j++) {
                for (k = j; k <= 10; k++) {
                    medienoua = calculeazaMedia(nrnote + 3, sum + i + j + k, teza);
                    if (medienoua > medieCopie) {

                        medieCopie = medienoua;
                        first = second = third = (i + j + k) / 3;
                        if ((i + j + k) % 3 == 1) third += 1;
                        if ((i + j + k) % 3 == 2) {
                            second += 1;
                            third += 1;
                        }
                        textSugestii = textSugestii + " + " + Integer.toString(first) + "+" + Integer.toString(second) + "+" + Integer.toString(third) + " -> " + Integer.toString(medieCopie) + "\n";
                    }
                }
            }
        }


        // Sugestii.setText(textSugestii);

        Context context = getApplication().getBaseContext();

        String filename = "DBFile.txt";
        File file = new File(context.getFilesDir(), filename);
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            if (medie < 10)
                outputStream.write(medie + '0');
            else {
                outputStream.write('1');
                outputStream.write('0');
            }
            outputStream.write(' ');
            for(i = 0; i < 5; i++){
                //textSugestii = textSugestii + Integer.toString(number[i]);
                if (number[i] < 10)
                    outputStream.write(number[i] + '0');
                else {
                    outputStream.write('1');
                    outputStream.write('0');
                };
                outputStream.write(' ');

                //outputStream.write(number[i]);


            }
            if (teza < 10)
                outputStream.write(teza + '0');
            else {
                outputStream.write('1');
                outputStream.write('0');
            }


            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Sugestii.setText(textSugestii);
       // Sugestii.setText(Integer.toString(number[3]));

/*        intent.putExtra(EXTRA_MESSAGE, result);
        startActivity(intent);*/
    }


}








