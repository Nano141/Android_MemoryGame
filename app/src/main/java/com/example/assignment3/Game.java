package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

class Card
{
    ImageView view;
    Integer back;
    Integer front;
    Boolean isFlipped;
public Card()
{

}
public Card(ImageView view,Integer back,Integer front,Boolean isFlipped)
{
    this.view = view;
    this.back = back;
    this.front = front;
    this.isFlipped = isFlipped;
}
}
class Utils {

    // Delay mechanism

    public interface DelayCallback{
        void afterDelay();
    }

    public static void delay(int secs, final DelayCallback delayCallback){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                delayCallback.afterDelay();
            }
        }, secs * 1000); // afterDelay will be executed after (secs*1000) milliseconds.
    }
}

public class Game extends AppCompatActivity {
    ArrayList<Card> cards = new ArrayList<Card>();
    ArrayList<Integer> images = new ArrayList<>();
    ArrayList<ImageView> views = new ArrayList<ImageView>();
    boolean secondImage = false;
    static Integer firstChoice;
    Card firstCard;
    int score = 0;
    String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        final TextView txt = findViewById(R.id.textTime);
        views.add((ImageView) findViewById(R.id.imageView));
        views.add((ImageView) findViewById(R.id.imageView2));
        views.add((ImageView) findViewById(R.id.imageView3));
        views.add((ImageView) findViewById(R.id.imageView5));
        views.add((ImageView) findViewById(R.id.imageView6));
        views.add((ImageView) findViewById(R.id.imageView7));
        views.add((ImageView) findViewById(R.id.imageView8));
        views.add((ImageView) findViewById(R.id.imageView9));
        views.add((ImageView) findViewById(R.id.imageView10));
        views.add((ImageView) findViewById(R.id.imageView11));
        views.add((ImageView) findViewById(R.id.imageView12));
        views.add((ImageView) findViewById(R.id.imageView13));
        images.add(R.drawable.eagle);
        images.add(R.drawable.fox);
        images.add(R.drawable.lion);
        images.add(R.drawable.monkey);
        images.add(R.drawable.panda);
        images.add(R.drawable.wolf);
        images.add(R.drawable.eagle);
        images.add(R.drawable.fox);
        images.add(R.drawable.lion);
        images.add(R.drawable.monkey);
        images.add(R.drawable.panda);
        images.add(R.drawable.wolf);

        Collections.shuffle(images);
        CountDownTimer countDown = new CountDownTimer(180000, 1000) {
            @Override
            public void onTick(long l) {
                txt.setText(Long.toString(l));
            }

            @Override
            public void onFinish() {
            }
        };
countDown.start();
        for (int i = 0; i<views.size();i++)
        {
            Card c = new Card(views.get(i),R.drawable.back,images.get(i),false);
            cards.add(c);
            final int finalI = i;
            views.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!secondImage && !cards.get(finalI).isFlipped) {
                        views.get(finalI).setImageResource(cards.get(finalI).front);
                        cards.get(finalI).isFlipped = true;
                        firstChoice = cards.get(finalI).front;
                        firstCard = cards.get(finalI);
                        secondImage = true;
                    }
                    else if (secondImage && !cards.get(finalI).isFlipped) {
                        views.get(finalI).setImageResource(cards.get(finalI).front);
                        if (firstChoice.equals(cards.get(finalI).front)){
                            cards.get(finalI).isFlipped = true;
                            score++;
                            secondImage = false;
                            if(score == 6)
                            {
                                time = txt.getText().toString();
                                Intent i = new Intent(Game.this,Result.class);
                                i.putExtra("Score",time);
                                startActivity(i);
                                finish();
                            }
                        }
                        else{

                            for(ImageView i:views)
                            {
                                i.setClickable(false);
                            }
                            Utils.delay(1, new Utils.DelayCallback() {
                                @Override
                                public void afterDelay() {
                                    for(ImageView i:views)
                                    {
                                        i.setClickable(true);
                                    }
                                    firstCard.isFlipped = false;
                                    cards.get(finalI).isFlipped = false;
                                    firstCard.view.setImageResource(R.drawable.back);
                                    views.get(finalI).setImageResource(R.drawable.back);
                                    secondImage = false;
                                }
                            });

                        }
                    }
                }
            });
        }

    }
}