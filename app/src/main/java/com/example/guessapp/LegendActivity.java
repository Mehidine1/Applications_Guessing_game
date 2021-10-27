package com.example.guessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import java.util.LinkedList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LegendActivity extends AppCompatActivity {

    LinkedList<String> images,names,options,selected;
    Button b1,b2,b3,b4;
    ImageView image;
    String correct;
    TextView txtScore,txtTimer;
    int score,count,timerCount;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        images = new LinkedList<>();
        names = new LinkedList<>();
        options = new LinkedList<>();
        selected = new LinkedList<>();
        score = 0;
        count = 0;
        timerCount = 60;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legend);

        txtScore = (TextView) findViewById(R.id.score_text);
        txtTimer = (TextView) findViewById(R.id.txt_timer);
        image = (ImageView) findViewById(R.id.img_logo);
        b1 = (Button) findViewById(R.id.btn_option1);
        b2 = (Button) findViewById(R.id.btn_option2);
        b3 = (Button) findViewById(R.id.btn_option3);
        b4 = (Button) findViewById(R.id.btn_option4);

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
            Matcher m = Pattern.compile(regex).matcher(s);
            String fulldata = "";
            while (m.find()) {
                String src = m.group();
                int startIndex = src.indexOf("src=") + 1;
                String srcTag = src.substring(startIndex, src.length());
                fulldata+= srcTag+"\n";
                images.add(srcTag);
            }


            String regex2 = "<h2 class=\"order-last md:order-first font-bold font-brand text-lg md:text-xl leading-normal w-full\">(.*?)</h2>";
            Matcher m2 = Pattern.compile(regex2).matcher(s);
            String fulldata2 = "";
            int count = 0;
            while(m2.find()){
                String src = m2.group();
                int startIndex = src.indexOf(">") + 1;
                int endIndex = src.indexOf("</h2>");
                String srcTag = src.substring(startIndex, endIndex);
                names.add(srcTag);
            }
            optionize();

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(b1.getText().toString().equalsIgnoreCase(correct)){
                        makeCorrectToast();
                        score+=2;
                        txtScore.setText(score+"");
                    }else{
                        makeWrongToast();
                        score--;
                        if(score<0)
                            score = 0;
                        txtScore.setText(score+"");
                    }
                    optionize();
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(b2.getText().toString().equalsIgnoreCase(correct)){
                        makeCorrectToast();
                        score+=2;
                        txtScore.setText(score+"");
                    }else{
                        makeWrongToast();
                        score--;
                        if(score<0)
                            score = 0;
                        txtScore.setText(score+"");
                    }
                    optionize();
                }
            });
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(b3.getText().toString().equalsIgnoreCase(correct)){
                        makeCorrectToast();
                        score+=2;
                        txtScore.setText(score+"");
                    }else{
                        makeWrongToast();
                        score--;
                        if(score<0)
                            score = 0;
                        txtScore.setText(score+"");
                    }
                    optionize();
                }
            });
            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(b4.getText().toString().equalsIgnoreCase(correct)){
                        makeCorrectToast();
                        score+=2;
                        txtScore.setText(score+"");
                    }else{
                        makeWrongToast();
                        score--;
                        if(score<0)
                            score = 0;
                        txtScore.setText(score+"");
                    }
                    optionize();
                }
            });

            timer = new CountDownTimer(60000,1000) {
                @Override
                public void onTick(long l) {
                    timerCount--;
                    txtTimer.setText(timerCount+"");
                }

                @Override
                public void onFinish() {
                    txtTimer.setText("GameOver");
                    b1.setOnClickListener(null);
                    b2.setOnClickListener(null);
                    b3.setOnClickListener(null);
                    b4.setOnClickListener(null);
                }
            }.start();

        } catch (Exception e) {
            Log.e("Fetch", "Error Fetching https://www.pcmag.com/picks/best-android-apps");
            e.printStackTrace();
        }
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }

    public void makeCorrectToast(){
        Toast.makeText(this, "Correct Answer!",
                Toast.LENGTH_SHORT).show();
    }
    public void makeWrongToast(){
        Toast.makeText(this, "Wrong Answer!",
                Toast.LENGTH_SHORT).show();
    }

    public void optionize(){
        if(count==103){
            //Game has ended
            txtTimer.setText("GameOver");
            b1.setOnClickListener(null);
            b2.setOnClickListener(null);
            b3.setOnClickListener(null);
            b4.setOnClickListener(null);
        }

        Random r = new Random();
        int i1 = r.nextInt(names.size())+12;
        int i2 = i1-12;
        correct = names.get(i2);
        if(selected.contains(correct)){
            optionize();
            return;
        }else{
            selected.add(correct);
        }
        image.setImageBitmap(getBitmapFromURL(images.get(i1)));
        options.add(correct);
        Log.v("Options List",options.toString());
        while(options.size()<4){
            int i =r.nextInt(names.size());
            if(!names.get(i).equalsIgnoreCase(correct))
                options.add(names.get(i));
            Log.v("Options List",options.toString());
        }
        int i = r.nextInt(options.size());
        b1.setText(options.remove(i));
        i = r.nextInt(options.size());
        b2.setText(options.remove(i));
        i = r.nextInt(options.size());
        b3.setText(options.remove(i));
        b4.setText(options.remove(0));
    }
}
