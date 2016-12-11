package com.zhen.victor.circleview;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    private CircleView viewById;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            startActivity(new Intent(MainActivity.this,Second.class));
            overridePendingTransition(R.anim.enter,R.anim.out);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = (CircleView) findViewById(R.id.circle);
        Roate3dAnimation animation = new Roate3dAnimation(0.0f, 360.0f, 360f, 60f, -50f, true);
        animation.setDuration(10000);
        animation.setRepeatCount(3);
        viewById.setAnimation(animation);
        handler.sendEmptyMessageDelayed(0, 3000);
        showNote();
    }

    private void showNote() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("hello world");
        builder.setContentTitle("ceshi");
        builder.setContentText("hahahhahhahah");
        builder.setSubText("sdfsdfsfsdf");
        builder.setAutoCancel(true);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1,builder.build());

    }
}
