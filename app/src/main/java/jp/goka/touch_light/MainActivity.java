package jp.goka.touch_light;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

    private int playCount = 10;
    private int totalScore = 0;
    private int untilStartTime = 3;
    private TextView untilStartTimeCountText;
    private View untilStartTimeCountView;

    private Button[] buttons = new Button[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playCount = 10;
        untilStartTime = 3;

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                untilStartTimeCountView = findViewById(R.id.count_view);
                untilStartTimeCountText = (TextView)findViewById(R.id.count_text);
                untilStartTimeCountText.setText(""+untilStartTime);

                buttons[0] = (Button)findViewById(R.id.btn1);
                buttons[1] = (Button)findViewById(R.id.btn2);
                buttons[2] = (Button)findViewById(R.id.btn3);
                buttons[3] = (Button)findViewById(R.id.btn4);
                buttons[4] = (Button)findViewById(R.id.btn5);
                buttons[5] = (Button)findViewById(R.id.btn6);
                buttons[6] = (Button)findViewById(R.id.btn7);
                buttons[7] = (Button)findViewById(R.id.btn8);
                buttons[8] = (Button)findViewById(R.id.btn9);

                allToggle(false);

                startCount();
            }
        });
    }

    private void startCount(){
        new Handler().postDelayed(new StartCountHandle(), 1000);
    }

    class StartCountHandle implements Runnable{
        @Override
        public void run() {
            if(untilStartTime <= 1){
                untilStartTimeCountView.setVisibility(View.GONE);
                setAllListener();
                continueRandom();
            }else {
                untilStartTime--;
                untilStartTimeCountText.setText(""+untilStartTime);
                new Handler().postDelayed(this, 1000);
            }
        }
    }

    private void continueRandom(){
       new Handler().postDelayed(new ContinueHandle(), 500);
    }

    class ContinueHandle implements Runnable{
        @Override
        public void run() {
            if(playCount <= 0){
                allToggle(false);
                finish();
                Intent intent = new Intent(MainActivity.this, EndActivity.class);
                intent.putExtra(EndActivity.KEY_SCORE, totalScore);
                startActivity(intent);
            }else {
                randomToggle();
                new Handler().postDelayed(this, 500);
            }
        }
    }


    private void setAllListener(){
        for(Button button : buttons){
            clickListener(button);
        }
    }

    private void clickListener(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.isEnabled()){
                    totalScore++;
                }
            }
        });

    }



    private int previousRandomValue = -1;


    private void randomToggle(){
        playCount--;

        for(Button button : buttons){
            button.setEnabled(false);
        }

        int random = new Random().nextInt(9);
        if(previousRandomValue != -1){
            if(random == previousRandomValue){
                random = new Random().nextInt(9);
            }
        }

        buttons[random].setEnabled(true);

        previousRandomValue = random;
    }


    private void allToggle(boolean enable){
        for(Button button : buttons){
            button.setEnabled(enable);
        }
    }

}