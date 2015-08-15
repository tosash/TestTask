package com.kido.testtask.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.kido.testtask.R;
import com.kido.testtask.adapters.PagerAdapter;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    private ViewPager pager;
    private TabLayout tabLayout;
    private Timer mTimer;
    private long lastBackPressTime = 0;
    private Toast toast;

    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            Context context = getApplicationContext();

            Intent notificationIntent = new Intent(context, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(context,
                    0, notificationIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT);

            Resources res = context.getResources();
            Notification.Builder builder = new Notification.Builder(context);

            builder.setContentIntent(contentIntent)
                    .setSmallIcon(R.drawable.ic_mail_green_500_18dp)
                            // большая картинка
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_account_circle_orange_700_24dp))
                    .setTicker("Привіт")
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true)
                    .setContentTitle("Уведомление")
                    .setContentText("Вам пришло новое сообщение.");

            Notification notification = builder.build();

            long[] vibrate = new long[]{500, 1000};
            notification.vibrate = vibrate;

            NotificationManager notificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);

            int min = 10;
            int max = 90;

            Random r = new Random();
            int id = r.nextInt(max - min + 1) + min;

            notificationManager.notify(id, notification);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initApp();
    }

    public void initApp() {
        pager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);


        PagerAdapter adapter = new PagerAdapter(getFragmentManager());

        adapter.addFragment(new OneFragment(), "1");
        adapter.addFragment(new TwoFragment(), "2");

        pager.setAdapter(adapter);

        tabLayout.setVisibility(View.VISIBLE);
        tabLayout.setupWithViewPager(pager);

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }

        }, 60000, 60000);

    }

    private void TimerMethod() {
        this.runOnUiThread(Timer_Tick);
    }

    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this, "Press back again to close this app", 4000);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }
            super.onBackPressed();
        }
    }
}


