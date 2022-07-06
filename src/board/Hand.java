package board;

import java.util.*;
import cards.*;

public class Hand implements Displayable {

	private ArrayList<Card> handList = new ArrayList<Card>();

	@Override
	public void add(Card card) {
		this.handList.add(card);
	}

	@Override
	public int size() {
		return this.handList.size();
	}

	@Override
	public Card getElementAt(int index) {
		return this.handList.get(index);
	}

	@Override
	public Card removeElement(int index) {
		Card card_back = this.handList.get(index);
		this.handList.remove(index);
		return card_back;
	}
}