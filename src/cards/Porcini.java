package cards;

import cards.CardType;

public class Porcini extends Mushroom {

	public Porcini(CardType type) {
		super(type, "porcini");
		this.flavourPoints = 3;
		this.sticksPerMushroom = 3;
	}
}