package com.example.dick.myfinaltopic;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import andtinder.model.CardModel;
import andtinder.view.CardContainer;
import andtinder.view.SimpleCardStackAdapter;

public class SituationWordCard extends AppCompatActivity {
    private CardContainer mCardContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation_word_card);

        mCardContainer=(CardContainer)findViewById(R.id.laa);

        final Resources r = getResources();

        SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(this);

        for (int i = 0; i < 2; i++) {
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
