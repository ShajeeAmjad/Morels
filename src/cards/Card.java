package cards;

import cards.CardType;

public abstract class Card {

	protected CardType type;
	protected String cardName;

	public Card(CardType cardtype, String cardname) {
		type = cardtype;
		cardName = cardname;
	}

	public CardType getType() {
		return type;
	}

	public String getName() {
		return cardName;
	}

}