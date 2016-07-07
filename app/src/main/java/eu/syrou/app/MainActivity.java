package eu.syrou.app;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import eu.syrou.hodor.Hodor;
import eu.syrou.hodor.HodorCoordination;
import eu.syrou.hodor.anim.HodorAnimationSlideInFromLeft;
import eu.syrou.hodor.anim.HodorAnimationSlideInFromRight;
import eu.syrou.hodor.anim.HodorAnimationSlideInFromTop;

public class MainActivity extends AppCompatActivity {

    private FrameLayout upperFrameLayout;
    private Hodor.Builder hodorBulder;
    private RelativeLayout secondView;
    private RelativeLayout secondView2;
    private FrameLayout underFrameLayout;
    private RelativeLayout thirdView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d("onCreate", "onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");

        if (savedInstanceState == null) {


            upperFrameLayout = (FrameLayout) findViewById(R.id.upper_content_body);
            underFrameLayout = (FrameLayout) findViewById(R.id.under_content_body);


            final RelativeLayout firstView = (RelativeLayout) getLayoutInflater().inflate(R.layout.first_view, null);
            secondView = (RelativeLayout) getLayoutInflater().inflate(R.layout.second_view, null);
            final RelativeLayout thirdView = (RelativeLayout) getLayoutInflater().inflate(R.layout.third_view, null);

            final RelativeLayout firstView2 = (RelativeLayout) getLayoutInflater().inflate(R.layout.first_view, null);
            secondView2 = (RelativeLayout) getLayoutInflater().inflate(R.layout.second_view, null);
            thirdView2 = (RelativeLayout) getLayoutInflater().inflate(R.layout.third_view, null);

            hodorBulder = new Hodor.Builder();
            hodorBulder.activity(this).with(upperFrameLayout).addView(firstView).addView(secondView).transform(new HodorAnimationSlideInFromLeft()).build();

            Hodor.Builder hodorBulder2 = new Hodor.Builder();
            hodorBulder2.with(underFrameLayout).addView(firstView2).addView(secondView2)/*.addView(thirdView2)*/.rotate(1000, TimeUnit.MILLISECONDS, HodorCoordination.Direction.FORWARD).transform(new HodorAnimationSlideInFromLeft()).build();

            //final HodorStatesaveTestView test = new HodorStatesaveTestView(MainActivity.this);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Hodor.with(upperFrameLayout).transform(new HodorAnimationSlideInFromLeft()).addView(test);
                }
            });

            final Button pressButton = (Button) findViewById(R.id.press_button);
            pressButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //test.getTextView().setText(generateString(new Random(), "abcdefghjiklmnopqrstuvxyz", 7));
                }
            });
        }
    }

    public static String generateString(Random rng, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Hodor.with(upperFrameLayout).removeView(secondView);
        //Hodor.with(underFrameLayout).removeView(secondView2);
        //Hodor.with(underFrameLayout).removeView(thirdView2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Hodor.with(upperFrameLayout).transform(new HodorAnimationSlideInFromRight()).back();
        //super.onBackPressed();
    }
}

