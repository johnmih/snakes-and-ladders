/* Η κλάση Game υλοποιεί το παιχνίδι. Το παιχνίδι διαιρείται ανά γύρους (round). Η κλάση περιέχει επίσης constructors, 
 * setters και getters για να μπορούμε να ορίζουμε τα χαρακτηριστικά του παιχνιδιού.  */

import java.util.ArrayList;

public class Game{

	int round;
	
	//κενός constructor (αρχικοποιεί τα πάντα σε 0)
	Game(){
		
		round =0;
	}
	
	//constructor με όρισμα
	Game(int round){
		
		this.round = round;
	}
	
	//setter για round
	void setRound(int round){
		
		this.round = round;
	}
	
	//getter για round
	int getRound(){
		
		return round;
	}
	
	//ορίζει τη σειρά με την οποία παίζουν οι παίκτες
	static int[] setTurns(ArrayList<Player> players){
		
		
		/*δεν χρησιμοποίησα HashMap, διότι δεν βρήκα κάποια συνάρτηση που να με εξυπηρετεί στο να προσπελάσω 
		 * τα περιεχόμενά του αφότου τον έχω ταξινομήσει. Αντ' αυτού προτίμησα δυο απλούς πίνακες τύπου int. 
		 * Η λειτουργία της συνάρτησης δεν αλλοιώνεται*/
		int[] dices = new int[players.size()]; //πίνακας με τις ζαριές των παικτών
		int[] priority = new int[players.size()]; //πίνακας με τα ids των παικτών
		
		//αντιστοιχώ τυχαίες ζαριες (από το ένα ως το έξι) στον κάθε παίκτη
		for(int i=0; i<players.size(); i++){
				
			dices[i] = (int)((Math.random()*5) + 1);
			priority[i] = players.get(i).playerId;
		}
		
		//με αλγόριθμο BubbleSort ταξινομώ κατά αύξουσα σειρά τις ζαριές και τους αντίστοιχους παίκτες
		int swap1 = 0, swap2 = 0;
		for (int i=0; i<players.size()-1; ++i)
			for(int j=0; j<players.size()-i-1; ++j)
				if(dices[j+1]<dices[j]){

					swap1 = dices[j];
					dices[j] = dices[j+1];
					dices[j+1] = swap1;
	                    
					swap2 = priority[j];
					priority[j] = priority[j+1];
					priority[j+1] = swap2;
				}
		
		//επιστρέφω τη σειρά προτεραιότητας. Σε περίπτωση ισοβαθμίας παίζει πρώτος αυτός που ρίχνει πρώτος ζάρι
		return priority;
	}
	
	public static void main(String[] args){

		Game game = new Game(1);
		Board board = new Board(10, 20, 3, 3, 6);
		Player player1 = new Player(1, "John", 0, board);
		HeuristicPlayer player2 = new HeuristicPlayer(2, "Machine", 0, board);

		board.createBoard();
		board.createElementBoard();

		int round = 0;
		int id1 = 1;
		int id2 = 1;
		int[] p1 = new int[4];
		
		ArrayList<Player> players = new ArrayList<>();
		players.add(player1);
		players.add(player2);
		int[] priority = new int[players.size()];
		priority = setTurns(players);
		
		if(player1.playerId == priority[0]){
			
			System.out.println();
			System.out.println("*Player 1 acts first!*");
			System.out.println();
			do{

				round++;
				game.setRound(round);

				p1 = player1.move(id1, (int)(Math.random()*5)+1);
				id1 = p1[0];
				id2 = player2.getNextMove(id2);
				player2.statistics(game.getRound());

				} while(game.getRound() <= 100 && id1<board.getN()*board.getM() && id2<board.getN()*board.getM());
		}
		if(player1.playerId == priority[1]){
			
			System.out.println();
			System.out.println("*Player 2 acts first!*");
			System.out.println();
			do{

				round++;
				game.setRound(round);

				id2 = player2.getNextMove(id2);
				player2.statistics(game.getRound());
				p1 = player1.move(id1, (int)(Math.random()*5)+1);
				id1 = p1[0];
				
				} while(game.getRound() <= 100 && id1<board.getN()*board.getM() && id2<board.getN()*board.getM());
		}
		
		//εξασφαλίζει πως κανένας παίκτης δεν θα βγει εκτός του ταμπλό
		if(id1 > board.getN()*board.getM())
			id1 = board.getN()*board.getM();
		if(id2 > board.getN()*board.getM())
			id2 = board.getN()*board.getM();

		double final1 = 0.65*id1 + 0.35*player1.getScore();
		double final2 = 0.65*id2 + 0.35*player2.getScore();

		System.out.println();
		System.out.println();

		System.out.println("At round " + game.getRound() + " player "+ player1.playerId + " is at position " + id1 + " and has a score of " + 
		player1.getScore() + ". Player " + player2.playerId + " is at position " + id2 + " and has a score of " + player2.getScore());

		if(final1==final2){
			
			if(id1==id2)
				System.out.println("The game resulted in a draw!");
			else if(final1>final2)
				System.out.println("Player 1 won!");
			else
				System.out.println("Player 2 won!");
		}	
		else if(id1>id2)
			System.out.println("Player 1 won!");
		else
			System.out.println("Player 2 won!");
		
		/* Σύντομο Σχόλιο: ο "έξυπνος" παίκτης 2 είναι σχεδόν ανίκητος από τον "απλό" παίκτη 1, ο οποίος συνήθως τερματίζει 
		 * με σκορ 0. Αυτό συμβαινει επειδή ο έξυπνος παίκτης μαζεύει όλα τα δώρα πρώτος και τα μηδενίζει, άρα ό,τι δώρο κι
		 * αν πάρει ο πρώτος παίκτης είναι μηδενισμένο. Επίσης, ο έξυπνος παίκτης προφταίνει και σπάει όλες τις καλές σκάλες,
		 * μειώνοντας περαιτέρω την πιθανότητα να νικήσει ο αντίπαλός του.*/
	}
}

