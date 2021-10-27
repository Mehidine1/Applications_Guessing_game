package com.example.guessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MediumActivity extends AppCompatActivity {

    ArrayList<String> appImages,appNames,appChoices;
    Button button1,button2,button3,button4;
    ImageView image;
    String correctAnswer;
    TextView scoreTxt,timerTxt;
    int score,count;

    public static Bitmap getBitmapFromURL(String src) {
        try {

            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        

        

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium);
        //initialized after oncreate cause app was crashing....


        appImages = new ArrayList<String>();
        appNames = new ArrayList<String>();
        appChoices = new ArrayList<String>();
        
        score = 0;
        count = 0;


        image = (ImageView) findViewById(R.id.img_logo);
        scoreTxt = (TextView) findViewById(R.id.score_text);
        timerTxt = (TextView) findViewById(R.id.txt_timer);
        
        
        button1 = (Button) findViewById(R.id.btn_option1);
        button2 = (Button) findViewById(R.id.btn_option2);
        button3 = (Button) findViewById(R.id.btn_option3);
        button4 = (Button) findViewById(R.id.btn_option4);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        
        //URL connection

        try {

            URL google = new URL("https://www.pcmag.com/picks/best-android-apps");
            BufferedReader in = new BufferedReader(new InputStreamReader(google.openStream()));
            String input;
            StringBuffer stringBuffer = new StringBuffer();
            while ((input = in.readLine()) != null) {
                stringBuffer.append(input);
            }
            in.close();


            String s = stringBuffer.toString();


            String regex = "http(s?)://([\\w-]+\\.)+[\\w-]+(/[\\w- ./]*)+\\.(?:[gG][iI][fF]|[jJ][pP][gG]|[jJ][pP][eE][gG]|[pP][nN][gG]|[bB][mM][pP])";
            Matcher matcher1 = Pattern.compile(regex).matcher(s);
            String imageSrc = "";
            while (matcher1.find()) {
                String src = matcher1.group();
                int startIndex = src.indexOf("src=") + 1;
                String srcTag = src.substring(startIndex, src.length());
                imageSrc+= srcTag+"\n";
                appImages.add(srcTag);
            }


            String regex2 = "<h2 class=\"order-last md:order-first font-bold font-brand text-lg md:text-xl leading-normal w-full\">(.*?)</h2>";
            Matcher matcher2 = Pattern.compile(regex2).matcher(s);
            
            String appName = "";
            int count = 0;
            while(matcher2.find()){
                String src = matcher2.group();
                int startIndex = src.indexOf(">") + 1;
                int endIndex = src.indexOf("</h2>");
                String srcTag = src.substring(startIndex, endIndex);
                appNames.add(srcTag);
            }
            optionize();

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(button1.getText().toString().equalsIgnoreCase(correctAnswer)){
                        
                        score+=2;
                        scoreTxt.setText(score+"");
                    }else{
                        
                        score--;
                        if(score<0){
                            score = 0;
                        }

                        scoreTxt.setText(score+"");
                    }
                    optionize();
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(button2.getText().toString().equalsIgnoreCase(correctAnswer)){

                        score+=2;
                        scoreTxt.setText(score+"");
                    }else{

                        score--;
                        if(score<0){
                            score = 0;
                        }

                        scoreTxt.setText(score+"");
                    }
                    optionize();
                }
            });
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(button3.getText().toString().equalsIgnoreCase(correctAnswer)){

                        score+=2;
                        scoreTxt.setText(score+"");
                    }else{

                        score--;
                        if(score<0){
                            score = 0;
                        }

                        scoreTxt.setText(score+"");
                    }
                    optionize();
                }
            });
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(button4.getText().toString().equalsIgnoreCase(correctAnswer)){

                        score+=2;
                        scoreTxt.setText(score+"");
                    }else{
                       
                        score--;
                        if(score<0){
                            score = 0;
                        }
                        scoreTxt.setText(score+"");
                    }
                    optionize();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void optionize(){
        if(count==103){
            timerTxt.setText("GameOver");
            button1.setOnClickListener(null);
            button2.setOnClickListener(null);
            button3.setOnClickListener(null);
            button4.setOnClickListener(null);
        }

        count++;
        Random rand = new Random();
        int i1 = rand.nextInt(appNames.size())+12;
        int i2 = i1-12;
        image.setImageBitmap(getBitmapFromURL(appImages.get(i1)));
        correctAnswer = appNames.get(i2);
        appChoices.add(correctAnswer);
      
        while(appChoices.size()<4){
            int i =rand.nextInt(appNames.size());
            if(!appNames.get(i).equalsIgnoreCase(correctAnswer))
                appChoices.add(appNames.get(i));
            
        }
        int i = rand.nextInt(appChoices.size());
        button1.setText(appChoices.remove(i));
        i = rand.nextInt(appChoices.size());
        button2.setText(appChoices.remove(i));
        i = rand.nextInt(appChoices.size());
        button3.setText(appChoices.remove(i));
        button4.setText(appChoices.remove(0));
    }
}