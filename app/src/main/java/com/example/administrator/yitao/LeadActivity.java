package com.example.administrator.yitao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.administrator.yitao.Activity.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeadActivity extends AppCompatActivity {
    Timer mTimer;
    @BindView(R.id.le_tv)
    TextView mLeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);

        ButterKnife.bind(this);

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                jumpTntent();
            }
        }, 3000);

    }

    public void jumpTntent() {
        Intent mIntent = new Intent(this, MainActivity.class);
        startActivity(mIntent);
        finish();
    }

    @OnClick(R.id.le_tv)
    public void onViewClicked() {
        mTimer.cancel();
        jumpTntent();
    }

    @Override
    protected void onDestroy() {
        mTimer = null;
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        mTimer.cancel();
        finish();
        super.onBackPressed();
    }
}
