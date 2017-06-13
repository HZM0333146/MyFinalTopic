package andtinder.view;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dick.myfinaltopic.R;

import andtinder.model.CardModel;


public final class SimpleCardStackAdapter extends CardStackAdapter {

	public TextToSpeech tts;
	public TextView tvPinyin, tvTranslation;

	private String myLanguage = null;
	private String pinyin = null;
	private String translation = null;

	public SimpleCardStackAdapter(Context mContext) {
		super(mContext);
		tts = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
			@Override
			public void onInit(int status) {}
		});
	}

	@Override
	public View getCardView(int position, CardModel model, View convertView, ViewGroup parent) {

		if(convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.std_card_inner, parent, false);
			assert convertView != null;
		}

		((ImageView) convertView.findViewById(R.id.image)).setImageDrawable(model.getCardImageDrawable());
		((TextView) convertView.findViewById(R.id.description)).setText(model.getDescription());
		((TextView) convertView.findViewById(R.id.description2)).setText(model.getExample());


				pinyin = "PinYin";
				translation = "Translation";
				((TextView) convertView.findViewById(R.id.descriptionPE)).setText(model.getDescriptionE());


		((TextView) convertView.findViewById(R.id.description)).setOnClickListener(new ClickDescription(model));
		((TextView) convertView.findViewById(R.id.descriptionPE)).setOnClickListener(new ClickDescription(model));
		((TextView) convertView.findViewById(R.id.description2)).setOnClickListener(new ClickExample(model));


		return convertView;
	}

	class ClickTitle implements View.OnClickListener {

		private CardModel model;

		ClickTitle(CardModel model){
			this.model = model;
		}

		@Override
		public void onClick(View v) {
			String[] tmp = null;
			tmp = model.getTitle().split(" ");
			tts.speak(tmp[0], TextToSpeech.QUEUE_FLUSH, null);
		}
	}

	class ClickDescription implements View.OnClickListener {

		private CardModel model;

		ClickDescription(CardModel model){
			this.model = model;
		}

		@Override
		public void onClick(View v) {
			String[] tmp = null;
			tmp = model.getDescription().split("：");
			tts.speak(tmp[1], TextToSpeech.QUEUE_FLUSH, null);
		}
	}

	class ClickExample implements View.OnClickListener {

		private CardModel model;

		ClickExample(CardModel model){
			this.model = model;
		}

		@Override
		public void onClick(View v) {
			String[] tmp = null;
			tmp = model.getExample().split("：");
			tts.speak(tmp[1], TextToSpeech.QUEUE_FLUSH, null);
		}
	}

	class ClickPinYin implements View.OnClickListener {

		private View convertView;
		private CardModel model;

		ClickPinYin(View convertView, CardModel model){
			this.convertView = convertView;
			this.model = model;
		}

		@Override
		public void onClick(View v) {

			((TextView) convertView.findViewById(R.id.descriptionPE)).setText(model.getDescriptionP());
		}
	}

	class ClickTranslation implements View.OnClickListener {

		private View convertView;
		private CardModel model;

		ClickTranslation(View convertView, CardModel model){
			this.convertView = convertView;
			this.model = model;
		}

		@Override
		public void onClick(View v) {


					pinyin = "PinYin";
					translation = "Translation";
					((TextView) convertView.findViewById(R.id.descriptionPE)).setText(model.getDescriptionE());
		}
	}
}
