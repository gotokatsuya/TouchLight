package jp.goka.touch_light;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.TextView;

public class EndActivity extends Activity {

    public static final String KEY_SCORE = "key_score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        final int score = getIntent().getIntExtra(KEY_SCORE, 0);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                findViewById(R.id.end_retry_button).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      finish();
                      startActivity(new Intent(EndActivity.this, MainActivity.class));
                  }
              });
                /*findViewById(R.id.end_share_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT, score+"/10 #touchlight");
                        startActivity(intent);
                    }
                });*/

                TextView scoreText = (TextView)findViewById(R.id.end_points_text);
                scoreText.setText(score+"/10 points");
            }
        });
    }
}