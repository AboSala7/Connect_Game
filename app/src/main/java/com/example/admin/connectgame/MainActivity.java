package com.example.admin.connectgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.id;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;
import static com.example.admin.connectgame.R.id.linearLayout;

public class MainActivity extends AppCompatActivity {

    int activePic = 0 ; // 0 = yellow & 1 = red
    int[] places = {2,2,2,2,2,2,2,2,2}; // 2 = empty
    int[][] winingPos = {{0,4,8},{2,4,6},{1,4,7},{3,4,5}};
    LinearLayout la ;
    GridLayout gl ;
    TextView textView ;

    int count ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        la =  (LinearLayout) findViewById(R.id.l);
        textView = (TextView)findViewById(R.id.textView);
    }
    public void drop (View view){

        Button counter = (Button) view ;
        int place = Integer.parseInt(counter.getTag().toString());

        if((activePic == 0)&&(places[place]== 2)) {
            counter.setTranslationY(-1000f);
            counter.setBackgroundResource(R.drawable.yellow);
            places[place]= activePic ;
            count++;
            activePic = 1 ;
            counter.animate().translationYBy(1000f).rotation(720f).setDuration(500);
        }
        else if (((activePic == 1)&&(places[place]== 2))) {
            counter.setTranslationY(-1000f);
            counter.setBackgroundResource(R.drawable.red);
            places[place]= activePic;
            count++ ;
            activePic = 0;
            counter.animate().translationYBy(1000f).rotation(720f).setDuration(500);
        }

        for (int[]win : winingPos) {
            if ((places[win[0]] == places[win[1]]) && (places[win[0]] == places[win[2]]) && places[win[0]] != 2) {
              textView.setText("Winner");
                la.setVisibility(View.VISIBLE);

                GridLayout gl = (GridLayout) findViewById(R.id.g);
                for(int i = 0 ; i < gl.getChildCount(); i++) {
                    ((Button) gl.getChildAt(i)).setClickable(false);
                }

                if (places[win[0]]==0){
                     la.setBackgroundColor(YELLOW);
                }
                if (places[win[0]]==1){
                    la.setBackgroundColor(RED);
                }

            }

        }

            if (count == 9) {
                if(la.getVisibility()!= View.VISIBLE) {
                    la.setVisibility(View.VISIBLE);
                    textView.setText("no winner");
                    count = 0;
                }
            }

    }
    public void tryAgain(View view){

        la.setVisibility(View.GONE);
        GridLayout gl = (GridLayout) findViewById(R.id.g);
        for(int i = 0 ; i < gl.getChildCount(); i++){
            ((Button) gl.getChildAt(i)).setBackgroundResource(R.color.white_greyish);
            ((Button) gl.getChildAt(i)).setClickable(true);
            places[i] = 2 ;
        }
        activePic = 0 ;


    }
}
