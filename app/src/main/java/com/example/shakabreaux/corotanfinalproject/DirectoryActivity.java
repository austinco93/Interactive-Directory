package com.example.shakabreaux.corotanfinalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.PointF;
import android.graphics.Matrix;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.FloatMath;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DirectoryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private PinView directoryView;
    private Toolbar toolbar, toolbar2;
    private FrameLayout frame, frame2, frame3;
    private TextView facname,office,phonenum, email;
    private Spinner spinner;
    private Button homeBtn, resetBtn, infoBtn;
    private int check = 0;
    Context ctx = this;
    DatabaseOperations dataB;
    List<String> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        //setupUI(findViewById(R.id.parent));
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        dataB = new DatabaseOperations(ctx);
        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getBackground().setAlpha(80);

        frame = (FrameLayout) findViewById(R.id.framelayout);
        assert frame != null;
        frame.bringToFront();
        //frame2.getBackground().setAlpha(80);

        homeBtn = (Button) findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(hClickListener);
        resetBtn = (Button) findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(rClickListener);
        infoBtn = (Button) findViewById(R.id.infoBtn);
        infoBtn.setOnClickListener(iClickListener);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        directoryView = (PinView) findViewById(R.id.imageView1);
        directoryView.setPanLimit(SubsamplingScaleImageView.PAN_LIMIT_CENTER);
        directoryView.setZoomEnabled(true);
        directoryView.setPanEnabled(true);
        directoryView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
        directoryView.setMinScale(0.45f);
        directoryView.setImage(ImageSource.resource(R.drawable.directorybackground));

        dataB.putInformation(dataB, 491, "Shameem", "Ahmed", "Shameem.Ahmed@wwu.edu", "(360)650-2624", 650, 450);
        dataB.putInformation(dataB, 477, "David", "Bover", "David.Bover@wwu.edu", "(360)650-3798", 150, 1000);
        dataB.putInformation(dataB, 489, "Aran", "Clauson", "Aran.Clauson@wwu.edu", "(360)650-3819", 550, 400);
        dataB.putInformation(dataB, 493, "Perry", "Fizzano", "Perry.Fizzano@wwu.edu", "(360)650-3807", 800, 520);
        dataB.putInformation(dataB, 479, "James", "Hearne", "James.Hearne@wwu.edu", "(360)650-3792", 150, 850);
        dataB.putInformation(dataB, 475, "Brian", "Hutchinson", "Brian.Hutchinson@wwu.edu", "(360)650-4894", 150, 1100);
        dataB.putInformation(dataB, 461, "Filip", "Jagodzinski", "Filip.Jagodzinski@wwu.edu", "(360)650-3800", 470, 1720);
        dataB.putInformation(dataB, 467, "James", "Johnson", "James.Johnson@wwu.edu", "(360)650-3967", 100, 1720);
        dataB.putInformation(dataB, 499, "Rameesh", "Kumaar", "Rameesh.Kumaar@wwu.edu", "(360)650-2625", 800, 1200);
        dataB.putInformation(dataB, 483, "Yudong", "Liu", "Yudong.Liu@wwu.edu", "(360)650-4831", 150, 630);
        dataB.putInformation(dataB, 469, "Geoffrey", "Matthews", "Geoffrey.Matthews@wwu.edu", "(360)650-3797", 100, 1500);
        dataB.putInformation(dataB, 473, "Michael", "Meehan", "Michael.Meehan@wwu.edu", "(360)650-3795", 150, 1220);
        dataB.putInformation(dataB, 471, "Phil", "Nelson", "Phil.Nelson@wwu.edu", "(360)650-3035", 150, 1350);
        dataB.putInformation(dataB, 465, "David", "Palzer", "David.Palzer@wwu.edu", "(360)650-2625", 200, 1720);
        dataB.putInformation(dataB, 491, "Catherine", "Potts", "Catherine.Potts@wwu.edu", "(360)650-2625", 650, 450);
        dataB.putInformation(dataB, 463, "Chris", "Reedy", "Chris.Reedy@wwu.edu", "(360)650-4838", 300, 1720);
        dataB.putInformation(dataB, 485, "Julian", "Rrushi", "Julian.Rrushi@wwu.edu", "(360)650-4221", 150, 520);
        dataB.putInformation(dataB, 465, "Moushumi", "Sharmin", "Moushumi.Sharmin@wwu.edu", "(360)650-2249", 200, 1720);
        dataB.putInformation(dataB, 481, "Jianna", "Zhang", "Jianna.Zhang@wwu.edu", "(360)650-3845", 150, 770);

        categories = new ArrayList<String>();
        categories.add("Faculty");
        categories.add("Shameem Ahmed");
        categories.add("David Bover");
        categories.add("Aran Clauson");
        categories.add("Perry Fizzano");
        categories.add("James Hearne");
        categories.add("Brian Hutchinson");
        categories.add("Filip Jagodzinski");
        categories.add("James Johnson");
        categories.add("Rameesh Kumaar");
        categories.add("Yudong Liu");
        categories.add("Geoffrey Matthews");
        categories.add("Michael Meehan");
        categories.add("Phil Nelson");
        categories.add("David Palzer");
        categories.add("Catherine Potts");
        categories.add("Chris Reedy");
        categories.add("Julian Rrushi");
        categories.add("Moushumi Sharmin");
        categories.add("Jianna Zhang");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // this part is needed for hiding the original view
                View view = super.getView(position, convertView, parent);
                view.setVisibility(View.GONE);

                return view;
            }
        };
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private View.OnClickListener rClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SubsamplingScaleImageView.AnimationBuilder animationBuilder = directoryView.animateScaleAndCenter(0.45f, new PointF(2100, 919));
            animationBuilder.withDuration(1000).start();
            directoryView.reset();
            directoryView.removePin();
            check = 1;
        }
    };

    private View.OnClickListener hClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          finish();
        }
    };

    private View.OnClickListener iClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            directoryView.removePin();
        }
    };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String item = parent.getItemAtPosition(position).toString();
            if(!item.equals("Faculty")){
                String[] name = item.split(" ");
                String last = name[1];
                Cursor CR = dataB.getFacultyInfo(dataB, last);
                if (!(CR.moveToFirst()) || CR.getCount() == 0) {
                    Toast.makeText(getBaseContext(), "Faculty Not Found", 10);
                } else {
                    CR.moveToFirst();
                    int roomNum = CR.getInt(0);
                    String first = CR.getString(1);
                    String lastname = CR.getString(2);
                    String mEmail = CR.getString(3);
                    String mPhoneNum = CR.getString(4);
                    int width = CR.getInt(5);
                    int height = CR.getInt(6);
                    String profname = first.concat(" " + lastname);

                    moveImage(lastname,width,height);
                    parent.setSelection(0);
                }
            }
    }

    public void moveImage(String lastname, int width, int height){
        PointF point = new PointF(width, height);
        SubsamplingScaleImageView.AnimationBuilder animationBuilder = directoryView.animateScaleAndCenter(2f, point);
        animationBuilder.withDuration(1000).withInterruptible(false).start();
        directoryView.setPin(lastname,point);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
