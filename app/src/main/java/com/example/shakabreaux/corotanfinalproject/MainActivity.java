package com.example.shakabreaux.corotanfinalproject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity {

    private Button directoryBtn;
    private Button insBtn;
    private Button exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        directoryBtn = (Button)findViewById(R.id.directoryBtn);
        directoryBtn.setOnClickListener(mClickListener);
        insBtn = (Button)findViewById(R.id.insBtn);
        insBtn.setOnClickListener(mClickListener);
        exitBtn = (Button)findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(mClickListener);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            insBtn.setEnabled(false);
        }
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == directoryBtn) {
                startActivity(new Intent(MainActivity.this, DirectoryActivity.class));
            } else if(v == insBtn) {
                startActivity(new Intent(MainActivity.this, InstructionsActivity.class));
            }else{
                finish();
                System.exit(0);
            }
        }
    };
}
