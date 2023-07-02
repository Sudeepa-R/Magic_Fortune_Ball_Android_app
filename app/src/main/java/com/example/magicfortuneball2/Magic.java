package com.example.magicfortuneball2;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;
public class Magic extends AppCompatActivity implements
        View.OnClickListener, TextToSpeech.OnInitListener {
    private TextView Tv;
    private Button btn_randm;
    private ImageButton ibbc;
    EditText emailll;
    int count=0;
    int c=0;
    TextToSpeech texttospeech;
    private String item[]={"CONCENTRATE AND ASK AGAIN",
            "Yes, definitely",
            "As I see it, yes",
            "Signs point to yes",
            "Better not tell you now",
            "It is certain",
            "Reply is unclear, ask again",
            "Outlook not so good",
            "Cannot predict now",
            "My sources say no",
            "Very Doubtful",
            "YES, FOR SURE",
            "MY ANSWER IS NO",
            "ASK ME LATER",
            "Without a doubt",
            "I AM PROGRAMMED TO SAY YES",
            "THE STARS SAY YES, BUT I SAY NO",
            "I DUNNO MAYBE",
            "FOCUS AND ASK ONCE MORE",
            "DOUBTFUL, VERY DOUBTFUL",
            "AFFIRMATIVE",
            "YES, THOUGH YOU MAY NOT LIKE IT",
            "The answer will reveal itself in time",
            "It's up to you",
            "The stars say yes",
            "The stars say no",
            "It's in the hands of fate",
            "Ask your heart for the answer",
            "It's a mystery",
            "It's too soon to tell",
            "You'll have to wait and see",
            "The answer lies in your actions",
            "The answer is hidden",
            "Trust your instincts",
            "Take a leap of faith",
            "NO, BUT YOU MAY WISH IT WAS SO"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic);
        Tv=findViewById(R.id.tv);
        btn_randm= findViewById(R.id.btn_random);
        btn_randm.setOnClickListener(this);
        texttospeech=new TextToSpeech(getBaseContext(),this);
        texttospeech.setLanguage(Locale.ENGLISH);
        emailll=findViewById(R.id.email);
        ibbc=findViewById(R.id.ibc);
        ibbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpeakNow(view);
            }
        });
//            btn_randm.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Random rndm = new Random();
//                    int i = rndm.nextInt(item.length);
//                    Tv.setText(item[i]);
//                    String text= Tv.getText().toString();
//                    texttospeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
////                    if (TextUtils.isEmpty(emaill)){
////                        Toast.makeText(getBaseContext(),"Input your question", Toast.LENGTH_LONG).show();
////
////
////                    }else {
////                        Tv.setText(item[i]);
////                    }
//
//
//                }
//            });
    }
    private void SpeakNow(View view){
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Start Speaking..");
        startActivityForResult(intent,111);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==111 && resultCode==RESULT_OK){
            emailll.setText(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0));

        }
    }
    @Override
    public void onClick(View v) {
        String emaill=emailll.getText().toString();
        if (TextUtils.isEmpty(emaill)){
                        Toast.makeText(getBaseContext(),"Input your question", Toast.LENGTH_LONG).show();


                    }else {
//                        Tv.setText(item[i]);
            count=count+1;
                if(count>c) {

                Random rndm = new Random();
                int i = rndm.nextInt(item.length);
                Tv.setText(item[i]);
                String text = Tv.getText().toString();
                texttospeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);

                c=count+1;

                    }else {
                Toast.makeText(getBaseContext(),"Input your next question", Toast.LENGTH_LONG).show();
                emailll.getText().clear();
                
            }
        }
    }
    @Override
    public void onInit(int status) {
        if(status!=TextToSpeech.ERROR){

            Toast.makeText(getBaseContext(),"Success",Toast.LENGTH_SHORT).show();
        }

    }
}