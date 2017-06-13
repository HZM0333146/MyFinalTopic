package com.example.dick.myfinaltopic;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import andtinder.model.CardModel;
import andtinder.view.CardContainer;
import andtinder.view.SimpleCardStackAdapter;

public class SituationWordCard extends AppCompatActivity {

   private CardContainer mCardContainer;
    ImageView back;
    TextView exerciseButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation_word_card);
        exerciseButton=(TextView)findViewById(R.id.exercise);
        exerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(SituationWordCard.this,PracticeSelection.class);
                startActivity(intent);
                finish();
            }
        });
        back=(ImageView) findViewById(R.id.situationwordcardback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(SituationWordCard.this,ContextTextAndEventSelection.class);
                startActivity(intent);
                finish();
            }
        });

        mCardContainer=(CardContainer)findViewById(R.id.laa);

        final Resources r = getResources();

        SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(this);

        for (int i = 0; i < TextBook.Cards.length; i++) {
            String uri = TextBook.Cards[i][14];
            int imageResource = getResources().getIdentifier(uri, "drawable", getPackageName());
            adapter.add(new CardModel(
                    TextBook.Cards[i][3],
                    TextBook.Cards[i][4],
                    TextBook.Cards[i][5],
                    TextBook.Cards[i][6],
                    TextBook.Cards[i][7],
                    TextBook.Cards[i][8],
                    TextBook.Cards[i][9],
                    TextBook.Cards[i][10],
                    TextBook.Cards[i][11],
                    TextBook.Cards[i][12],
                    TextBook.Cards[i][13],
                    r.getDrawable(imageResource)));
        }
        mCardContainer.setAdapter(adapter);
    }
}
