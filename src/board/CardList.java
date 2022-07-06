package board;

import java.util.*;
import cards.*;

public class CardList {

	private ArrayList<Card> cList;

	public CardList() {
		this.cList = new ArrayList<Card>();
	}

	public void add(Card card) {
		this.cList.add(card);
	}

	public int size() {
		return this.cList.size();
	}

	public Card getElementAt(int index) {
		return this.cList.get(index);
	}

	public Card removeCardAt(int index) {
		Card card_back = this.cList.get(index);
		this.cList.remove(index);
		return card_back;
	}
}