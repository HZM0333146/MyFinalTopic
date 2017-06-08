/**
 * AndTinder v0.1 for Android
 *
 * @Author: Enrique López Mañas <eenriquelopez@gmail.com>
 * http://www.lopez-manas.com
 *
 * TAndTinder is a native library for Android that provide a
 * Tinder card like effect. A card can be constructed using an
 * image and displayed with animation effects, dismiss-to-like
 * and dismiss-to-unlike, and use different sorting mechanisms.
 *
 * AndTinder is compatible with API Level 13 and upwards
 *
 * @copyright: Enrique López Mañas
 * @license: Apache License 2.0
 */

package andtinder.model;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class CardModel {

	private String title;
	private String description;
	private String descriptionP;
	private String descriptionE;
	private String descriptionJ;
	private String descriptionI;
	private String example;
	private String exampleP;
	private String exampleE;
	private String exampleJ;
	private String exampleI;
	private Drawable cardImageDrawable;
	private Drawable cardLikeImageDrawable;
	private Drawable cardDislikeImageDrawable;


	private OnCardDismissedListener mOnCardDismissedListener = null;

	private OnClickListener mOnClickListener = null;

	public interface OnCardDismissedListener {
		void onLike();
		void onDislike();
	}

	public interface OnClickListener {
		void OnClickListener();
	}

	public CardModel() {
		this(null, null, null, null, null, null, null, null, null, null, null, (Drawable)null);
	}

	public CardModel(String title, String description, String descriptionP, String descriptionE, String descriptionJ, String descriptionI, String example, String exampleP, String exampleE, String exampleJ, String exampleI,Drawable cardImage) {
		this.title = title;
		this.description = description;
		this.descriptionP = descriptionP;
		this.descriptionE = descriptionE;
		this.descriptionJ = descriptionJ;
		this.descriptionI = descriptionI;
		this.example = example;
		this.exampleP = exampleP;
		this.exampleE = exampleE;
		this.exampleJ = exampleJ;
		this.exampleI = exampleI;
		this.cardImageDrawable = cardImage;
	}

	public CardModel(String title, String description, String descriptionP, String descriptionE, String descriptionJ, String descriptionI, String example, String exampleP, String exampleE, String exampleJ, String exampleI, Bitmap cardImage) {
		this.title = title;
		this.description = description;
		this.descriptionP = descriptionP;
		this.descriptionE = descriptionE;
		this.descriptionJ = descriptionJ;
		this.descriptionI = descriptionI;
		this.example = example;
		this.exampleP = exampleP;
		this.exampleE = exampleE;
		this.exampleJ = exampleJ;
		this.exampleI = exampleI;
		this.cardImageDrawable = new BitmapDrawable(null, cardImage);
	}

	public String getTitle() {return title;}

	public void setTitle(String title) {this.title = title;}

	public String getDescription() {return description;}

	public void setDescription(String description) {this.description = description;}

	public String getDescriptionP() {
		return descriptionP;
	}

	public void setDescriptionP(String descriptionP) {
		this.descriptionP = descriptionP;
	}

	public String getDescriptionE() {
		return descriptionE;
	}

	public void setDescriptionE(String descriptionE) {
		this.descriptionE = descriptionE;
	}

	public String getDescriptionJ() {return descriptionJ;}

	public void setDescriptionJ(String descriptionJ) {this.descriptionJ = descriptionJ;}

	public String getDescriptionI() {return descriptionI;}

	public void setDescriptionI(String descriptionI) {this.descriptionI = descriptionI;}

	public String getExample() {return example;}

	public void setExample(String example) {this.example = example;}

	public String getExampleP() {return exampleP;}

	public void setExampleP(String exampleP) {this.exampleP = exampleP;}

	public String getExampleE() {
		return exampleE;
	}

	public void setExampleE(String exampleE) {this.exampleE = exampleE;}

	public String getExampleJ() {
		return exampleJ;
	}

	public void setExampleJ(String exampleJ) {this.exampleJ = exampleJ;}

	public String getExampleI() {
		return exampleI;
	}

	public void setExampleI(String exampleI) {this.exampleI = exampleI;}

	public Drawable getCardImageDrawable() {return cardImageDrawable;}

	public void setCardImageDrawable(Drawable cardImageDrawable) {this.cardImageDrawable = cardImageDrawable;}

	public Drawable getCardLikeImageDrawable() {
		return cardLikeImageDrawable;
	}

	public void setCardLikeImageDrawable(Drawable cardLikeImageDrawable) {this.cardLikeImageDrawable = cardLikeImageDrawable;}

	public Drawable getCardDislikeImageDrawable() {
		return cardDislikeImageDrawable;
	}

	public void setCardDislikeImageDrawable(Drawable cardDislikeImageDrawable) {this.cardDislikeImageDrawable = cardDislikeImageDrawable;}

	public void setOnCardDismissedListener( OnCardDismissedListener listener ) {this.mOnCardDismissedListener = listener;}

	public OnCardDismissedListener getOnCardDismissedListener() {return this.mOnCardDismissedListener;}

	public void setOnClickListener( OnClickListener listener ) {this.mOnClickListener = listener;}

	public OnClickListener getOnClickListener() {
		return this.mOnClickListener;
	}
}
