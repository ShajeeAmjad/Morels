package board;

import java.util.*;
import cards.*;

public class CardPile {

	private Stack<Card> cPile = new Stack<Card>();

	public CardPile() {		
	}

	public void addCard(Card card){
		cPile.push(card);
	}

	public Card drawCard() {
		return cPile.pop();
	}

	public void shufflePile() {
		Collections.shuffle(cPile);
	}

	public int pileSize() {
		return cPile.size();
	}

	public boolean isEmpty() {
		if (cPile.size() == 0) {
			return true;
		}
		else {
			return false;
		}
	}

}