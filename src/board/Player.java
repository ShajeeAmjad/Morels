package board;

import java.util.*;
import cards.*;

public class Player {

	private Hand h = new Hand();
	private Display d = new Display();
	private int score = 0;
	private int handlimit = 8;
	private int sticks = 0;

	public Player() {
		addCardtoDisplay(new Pan());
	}

	public int getScore() {
		return this.score;
	}

	public int getHandLimit() {
		return this.handlimit;
	}

	public int getStickNumber() {
		return this.sticks;
	}

	public void addSticks(int no_of_sticks){
		int count = 0;
		d = this.getDisplay();
		while (count < no_of_sticks) {
			addCardtoDisplay(new Stick());
			count += 1;
		}
		this.sticks += no_of_sticks;
	} 

	public void removeSticks(int no_of_sticks){
		int count = 0;
		d = this.getDisplay();
		while (count < no_of_sticks) {
			for (int x=0; x<d.size(); x++) {
				if (d.getElementAt(x).getType().equals(CardType.STICK)) {
					d.removeElement(x);
					count += 1;
					break;
				}
			}
		}
		this.sticks -= no_of_sticks;
	}

	public Hand getHand() {
		return this.h;
	}

	public Display getDisplay() {
		return this.d;
	}

	public void addCardtoHand(Card card) {
		this.h.add(card);
	}

	public void addCardtoDisplay(Card card) {
		this.d.add(card);
	}

	public boolean takeCardFromTheForest(int card_pos) {

		boolean is_taken = false;
		handlimit = this.getHandLimit();
		h = this.getHand();
		d = this.getDisplay();
		sticks = this.getStickNumber();
		CardList forest = Board.getForest();
		int sticks_needed = 0;

		switch(card_pos) {
			case 1:
				sticks_needed = 0;
				break;
			case 2:
				sticks_needed = 0;
				break;
			case 3:
				sticks_needed = 1;
				break;
			case 4:
				sticks_needed = 2;
				break;
			case 5:
				sticks_needed = 3;
				break;
			case 6:
				sticks_needed = 4;
				break;
			case 7:
				sticks_needed = 5;
				break;
			case 8:
				sticks_needed = 6;
				break;
			default:
				return false;
		}

		if (h.size() == handlimit) {
			return false;
		}

		if (card_pos == 1 || card_pos == 2) {
			if (forest.getElementAt(8 - card_pos).getType().equals(CardType.BASKET)) {
				addCardtoDisplay(forest.removeCardAt(8 - card_pos));
				handlimit += 2;
				is_taken = true;
			} else if (h.size() < handlimit){
				addCardtoHand(forest.removeCardAt(8 - card_pos));
				return true;
			} else {
				return false;
			}
		} else if (card_pos > 2) {
			if (sticks < sticks_needed) {
				return false;
			} else if (forest.getElementAt(8 - card_pos).getType().equals(CardType.BASKET)) {
				addCardtoDisplay(forest.removeCardAt(8 - card_pos));
				handlimit += 2;
				removeSticks(sticks_needed);
				is_taken = true;
			} else if (h.size() < handlimit){
				addCardtoHand(forest.removeCardAt(8 - card_pos));
				removeSticks(sticks_needed);
				return true;
			} else {
				return false;
			}
		}
		return is_taken;
	}	

	public boolean takeFromDecay() {
		int num_of_baskets = 0;
		int num_of_other = 0;
		h = this.getHand();
		handlimit = this.getHandLimit();
		ArrayList<Card> decayPile = Board.getDecayPile();

		for (int x=0; x<decayPile.size(); x++) {
			if (decayPile.get(x).getType().equals(CardType.BASKET)) {
				num_of_baskets += 1;
			} else {
				num_of_other += 1;
			}
		}

		handlimit += (num_of_baskets*2);

		for (int i=0; i<h.size(); i++) {
			if (h.getElementAt(i).getType().equals(CardType.BASKET)) {
				this.addCardtoDisplay(h.removeElement(i));
				handlimit += 2;
			} 

		}

		if (h.size() + num_of_other > handlimit + (num_of_baskets*2)) {
			return false;
		} else {
			for (int j=0; j<decayPile.size(); j++) { 
				if (decayPile.get(j).getType().equals(CardType.BASKET)) {
					this.addCardtoDisplay(decayPile.get(j));
				} else if (decayPile.size() != 0) {
					if (h.size() >= handlimit) {
						return false;
					}
					this.addCardtoHand(decayPile.get(j));
				}
			}
		}
		return true; 
	}

	public boolean cookMushrooms(ArrayList<Card> cook_list) {
		score = this.getScore();
		h = this.getHand();
		d = this.getDisplay();
		int num_of_mushrooms = 0;
		int flavour_points = 0;
		int num_needed = 0;
		boolean pan_in_hand = false;
		boolean pan_in_d = false;
		boolean is_identical = true;

		for (int x=0; x<cook_list.size(); x++) {
			if (cook_list.get(x).getType().equals(CardType.PAN)) {
				cook_list.remove(x);
				pan_in_hand = true;
			}
		}

		for (int x=0; x<d.size(); x++) {
			if (d.getElementAt(x).getType().equals(CardType.PAN)) {
				pan_in_d = true;
			} else {
				pan_in_d = false;
			}
		}

		ArrayList<Card> mushroom_l = new ArrayList<Card>();
		ArrayList<Card> extra_l = new ArrayList<Card>();

		for (int x=0; x<cook_list.size(); x++) {
			if (cook_list.get(x).getType().equals(CardType.DAYMUSHROOM) || cook_list.get(x).getType().equals(CardType.NIGHTMUSHROOM)) {
				mushroom_l.add(cook_list.get(x));
			} else if (cook_list.get(x).getType().equals(CardType.BUTTER) || cook_list.get(x).getType().equals(CardType.CIDER)) {
				extra_l.add(cook_list.get(x));
			} else {
				return false;
			}
		}

		for (int x=0; x<cook_list.size(); x++) {
			if (mushroom_l.equals(cook_list)) {
				is_identical = false;
			} else {
				is_identical = true;
			}
		}

		for (int x=0; x<mushroom_l.size(); x++) {
			if (mushroom_l.get(x).getType().equals(CardType.DAYMUSHROOM)) {
				num_of_mushrooms += 1;
			} else {
				num_of_mushrooms += 2;
			}
		}

		if (pan_in_hand || pan_in_d) {
			if (num_of_mushrooms < 3 && extra_l.size() == 0) {
				return false;
			} else if (is_identical && pan_in_d) {
				for (int x=0; x<extra_l.size(); x++) {
					if (extra_l.get(x).getType().equals(CardType.BUTTER)) {
						flavour_points += 3;
						num_needed += 4;
					} else if (extra_l.get(x).getType().equals(CardType.CIDER)) {
						flavour_points += 5;
						num_needed += 5;
					}
				}	
			}

			if (num_of_mushrooms < num_needed) {
				return false;
			} else {
				if (mushroom_l.get(0) instanceof Mushroom) {
					Mushroom local_mushroom = (Mushroom)mushroom_l.get(0);
					flavour_points += num_of_mushrooms * local_mushroom.getFlavourPoints(); 
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
		this.score = flavour_points;

		if (pan_in_hand) {
			for (int x=0; x<h.size(); x++) {
				if (h.getElementAt(x).getType().equals(CardType.PAN)) {
					h.removeElement(x);
					break;
				}
			}
		} else {
			for (int x=0; x<h.size(); x++) {
				if (d.getElementAt(x).getType().equals(CardType.PAN)) {
					d.removeElement(x);
					break;
				}
			}
		}

		cook_list.clear();

		return true;

	}

	public boolean sellMushrooms(String mushroom_name, int num_to_sell) {
		
		boolean is_valid = false;
		int num_in_hand = 0;
		Hand hand = this.getHand();
		h = this.getHand();

		ArrayList<String> mushroom_list = new ArrayList<String>();
		mushroom_list.add("shiitake"); mushroom_list.add("birchbolete"); mushroom_list.add("treeear"); mushroom_list.add("chanterelle"); mushroom_list.add("lawyerswig"); mushroom_list.add("morel"); mushroom_list.add("henofwoods"); mushroom_list.add("honeyfungus"); mushroom_list.add("porcini");  
		mushroom_name = mushroom_name.toLowerCase();
		mushroom_name = mushroom_name.replaceAll(" ", "");
	
		if (mushroom_list.contains(mushroom_name)) {
			is_valid = true;
		} else {
			is_valid = false;
			return false;
		}

		ArrayList<Card> n_mushroom_list = new ArrayList<Card>();

		if (is_valid) {
			for (int x=0; x<h.size(); x++) {
				if (h.getElementAt(x).getName().equals(mushroom_name) && h.getElementAt(x).getType().equals(CardType.DAYMUSHROOM)) {
					n_mushroom_list.add(h.getElementAt(x));
					num_in_hand += 1;
				} else if (h.getElementAt(x).getName().equals(mushroom_name) && h.getElementAt(x).getType().equals(CardType.NIGHTMUSHROOM)) {
					n_mushroom_list.add(h.getElementAt(x));
					num_in_hand += 2;
				}
			}

			if (num_to_sell > num_in_hand) {
				return false;
			}

			if (num_to_sell < 2) {
				return false;
			}

			for(int x=0; x<n_mushroom_list.size(); x++){
				for (int y=0; y<hand.size(); y++){
					if (hand.getElementAt(y).equals(n_mushroom_list.get(x))){
						hand.removeElement(x);
					}
				}
			}

			Mushroom mushrooms_taken = (Mushroom) n_mushroom_list.get(0);

			int sticks_earned = mushrooms_taken.getSticksPerMushroom();
			
			this.addSticks(sticks_earned * num_in_hand);

			return true;
		}
		return false;
	}

	public boolean putPanDown() {
		h = this.getHand();

		for (int y=0; y<h.size(); y++) {
			if (h.getElementAt(y).getType().equals(CardType.PAN)) {
				this.addCardtoDisplay(h.removeElement(y));
				return true;
			}
		}
		return false;
	} 
}