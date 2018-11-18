package com.momo.app2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.momo.app2.builder.TeamBuilder;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_LIMIT = 99;
    private static final int MIN_LIMIT = 0;

    private AtomicInteger counter = new AtomicInteger(0);

    private TeamComponentFactory teamComponentFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showCredits(View view) {
        Intent intent = new Intent(this, OtherActivity.class);
        intent.putExtra("name", "Raphael Moita");
        intent.putExtra("email", "raphael.moita@gmail.com");
        startActivity(intent);
    }

    public void createTeams(View view) {

        final LinearLayout container = findViewById(R.id.container_linear_layout);
        container.removeAllViews();

        teamComponentFactory = new TeamComponentFactory(getApplicationContext());

        final TeamBuilder teamBuilder = new TeamBuilder(counter.get());

        List<RelativeLayout> teams = teamComponentFactory.create(teamBuilder.getTeams());
        for (RelativeLayout team : teams) {
            container.addView(team);
        }

        if (teamBuilder.hasUnluckyPlayer()) {
            RelativeLayout unluckyPlayer = teamComponentFactory.create(valueOf(teamBuilder.getUnluckyPlayer()));
            container.addView(unluckyPlayer);
        }

        findViewById(R.id.teams).setVisibility(View.VISIBLE);
    }

    public void increment(View view) {
        if (counter.get() < MAX_LIMIT) {
            display(counter.incrementAndGet());
        } else {
            makeText(getApplicationContext(),
                    "Maximum number of players reached!",
                    LENGTH_LONG).show();
        }
    }

    public void decrement(View view) {
        if (counter.get() > MIN_LIMIT) {
            display(counter.decrementAndGet());
        }

    }

    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(valueOf(number));
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity.super.onBackPressed();
                        finish();
                    }
                }).create().show();
    }
}
