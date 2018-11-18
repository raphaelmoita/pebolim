package com.momo.app2;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.momo.app2.domain.Team;

import static android.view.Gravity.CENTER_HORIZONTAL;
import static android.view.Gravity.CENTER_VERTICAL;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class TeamLayoutFactory {

    private Context context;

    public TeamLayoutFactory(Context context) {
        this.context = context;
    }
    
    private LinearLayout externalLinearLayout() {
        final LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(WRAP_CONTENT, MATCH_PARENT);

        final LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setGravity(CENTER_HORIZONTAL);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        return linearLayout;
    }

    private ImageView getBall() {
        final LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(150, 150);

        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable.soccer_ball);

        return imageView;
    }

    private ImageView getUnluckyBall() {
        final LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(150, 150);

        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable._soccer_red_ball);

        return imageView;
    }

    private TextView getPlayers(Team team) {

        final LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        layoutParams.leftMargin = 8;
        layoutParams.rightMargin = 8;
        layoutParams.gravity = CENTER_VERTICAL;

        final TextView textView = new TextView(context);
        textView.setLayoutParams(layoutParams);
        textView.setText(Html.fromHtml(String.format("<b>%s</b>&nbsp;-&nbsp;<b>%s</b>", team.getPlayer1(), team.getPlayer2())));
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);

        return textView;
    }

    private TextView getPlayer(int player) {

        final LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        layoutParams.leftMargin = 8;
        layoutParams.rightMargin = 8;
        layoutParams.gravity = CENTER_VERTICAL;

        final TextView textView = new TextView(context);
        textView.setLayoutParams(layoutParams);
        textView.setText(Html.fromHtml(String.format("<b>%s</b>", player)));
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);

        return textView;
    }
    
    public LinearLayout create(Team team) {

        final LinearLayout externalLinearLayout = externalLinearLayout();

        externalLinearLayout.addView(getBall());
        externalLinearLayout.addView(getPlayers(team));

        return externalLinearLayout;
    }

    public LinearLayout create(int player) {

        final LinearLayout externalLinearLayout = externalLinearLayout();

        externalLinearLayout.addView(getUnluckyBall());
        externalLinearLayout.addView(getPlayer(player));

        return externalLinearLayout;
    }
    
}
