package com.example.dick.myfinaltopic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ContextTextAndEventSelection extends AppCompatActivity {
    TextView situationalLearning,situationWordCard;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_text_and_event_selection);
        situationWordCard=(TextView)findViewById(R.id.situationWordCard);
        situationalLearning=(TextView)findViewById(R.id.situationalLearning);
        back=(ImageView)findViewById(R.id.contexttextandeventselectionback);
        situationWordCard.setText("情境字卡");
        situationalLearning.setText("情境學習");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(ContextTextAndEventSelection.this,ContextSelection.class);
                startActivity(i);
                finish();
            }
        });
        situationWordCard.setOnClickListener(situationWordCardButton);
        situationalLearning.setOnClickListener(situationalLearningButton);

    }
    View.OnClickListener situationalLearningButton=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent();
            i.setClass(ContextTextAndEventSelection.this,SituationalLearning.class);
            startActivity(i);
            finish();
        }
    };
    View.OnClickListener situationWordCardButton=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent();
            i.setClass(ContextTextAndEventSelection.this,SituationWordCard.class);
            startActivity(i);
            finish();
        }
    };
}
