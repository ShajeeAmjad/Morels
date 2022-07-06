package board;

import java.util.*;
import cards.*;

public class Display implements Displayable {

	private ArrayList<Card> displayList = new ArrayList<Card>();

	@Override
	public void add(Card card) {
		displayList.add(card);
	}

	@Override
	public int size() {
		return displayList.size();
	}

	@Override
	public Card getElementAt(int index) {
		return displayList.get(index);
	}

	@Override
	public Card removeElement(int index) {
		Card cardToReturn = displayList.get(index);
		displayList.remove(index);
		return cardToReturn;
	}
}