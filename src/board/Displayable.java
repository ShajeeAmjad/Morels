package board;

import cards.*;

public interface Displayable {
	public void add(Card card);
	public int size();
	public Card getElementAt(int index);
	public Card removeElement(int index);
}