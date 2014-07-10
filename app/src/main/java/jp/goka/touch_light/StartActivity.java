package jp.goka.touch_light;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
              findViewById(R.id.start_button).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      finish();
                      startActivity(new Intent(StartActivity.this, MainActivity.class));
                  }
              });

            }
        });
    }
}