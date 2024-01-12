/* Η κλάση Player υλοποιεί τον παίκτη του παιχνιδού. Οι παίκτες έχουν αρίθμιση (playerId), όνομα (name), σκορ (score) 
 * και παίζουν σε κάποιο ταμπλό (board). Η κλάση περιέχει επίσης constructors, setters και getters για να μπορούμε να 
 * ορίζουμε τα χαρακτηριστικά του εν λόγω παίκτη.  */

public class Player {

	int playerId;
	String name;
	int score;
	Board board;
	
	//κενός constructor (αρχικοποιεί τα πάντα σε 0)
	Player(){
		
		playerId = 0;
		name = "";
		score = 0;
		board = null;
	}
	
	//constructor με ορίσματα
	Player(int playerId, String name, int score, Board board){
		
		this.playerId = playerId;
		this.name = name;
		this.score = score;
		this.board = board;
	}
	
	//setter για playerId
	void setPlayerId(int playerId) {
		
		this.playerId = playerId;
	}
	
	//getter για playerId
	int getPlayerId() {
		
		return playerId;
	}
	
	//setter για name
	void setName(String name) {
		
		this.name = name;
	}
	
	//getter για name
	String getName() {
		
		return name;
	}
	
	//setter για score
	void setScore(int score) {
		
		this.score = score;
	}
	
	//getter για score
	int getScore() {
		
		return score;
	}
	
	//setter για board
	void setBoard(Board board) {
		
		this.board = board;
	}
	
	//getter για board
	Board getBoard() {
		
		return board;
	}
	
	//μέθοδος που υλοποιεί την κίνηση του παίκτη
	int[] move(int id, int die) {
		
		/* Αποθηκεύει σε αυτή τη σειρά τη θέση του παίκτη μετά την κίνησητα, τα τσιμπήματα από φίδια, τις σκάλες που 
		 * χρησιμοποιήθηκαν και τα δώρα που συλλέκτηκαν και. Υπάρχει πίνακας και σε προσωρινή μορφή, ώστε ο αλγόριθμος 
		 * να έχει αναδρομικό χαρακτήρα*/
		int[] outcome = {0, 0, 0, 0};
		int[] temp = {0, 0, 0, 0};
		id += die; //το πλακίδιο όπου βρίσκεται ο παίκτης μετά τη ζαριά
		
		///δώρα
		 for(int i=0; i<board.presents.length; i++)			 
				if(id == board.presents[i].presentSquareId){
					 
					System.out.println("Player " + playerId + " collected a present!");
					score += board.presents[i].points; //προσμετρά τους πόντους του δώρου
					board.presents[i].points = 0; //μηδενίζει το δώρο
					outcome[3]++; //αποθηκεύει πόσα δώρα συλλέκτηκαν
					break;
				}
		 
		///φίδια
		for(int i=0; i<board.snakes.length; i++)		 
			if(id == board.snakes[i].headId){
				 
				System.out.println("Player " + playerId + " got bitten by a snake!");
				id = board.snakes[i].TailId; //ο παίκτης μετακινείται στην ουρά του φιδιού
				outcome[1]++; //αποθηκεύει τα δαγκώματα από φίδια
				temp = move(id, 0); 
				for(int j=0; j<4; j++)
					outcome[j] += temp[j];
				break;
			}
		 
		///σκάλες
		for(int i=0; i<board.ladders.length; i++)	 
			if(id == board.ladders[i].bottomSquareId && board.ladders[i].broken==false) {
				 
				System.out.println("Player " + playerId + " climbed a stair!");
				id = board.ladders[i].topSquareId; //ο παίκτης μετακινείται στην κορυφή της σκάλας
				board.ladders[i].broken = true; //η σκάλα σπάει
				outcome[2]++; //αποθηκεύει τις αναβάσεις σε σκάλες
				temp = move(id, 0); 
				for(int j=0; j<4; j++)
					outcome[j] += temp[j];
				break;
			}
		
		System.out.println();
		/* ο αλγόριθμος τρέχει όσες φορές χρειαστεί, ώστε να εξασφαλιστεί πως θα ληφθούν υπ' όψην όλες οι διαδράσεις 
		 * του παίκτη με το ταμπλό. Αυτές αποθηκεύονται προσωρινά τον πίνακα temp κι έπειτα προστίθεμται στον τελικό 
		 * πίνακα εξόδου.*/
		
		outcome[0] = id;
		return outcome;
	 }
	
	//κάνει ό,τι και η move, χωρίς να εκτυπώνει όμως τα στατιστικά του παίκτη
	int[] strippedMove(int id, int die) {
		
		int[] outcome = {0, 0, 0, 0};
		int[] temp = {0, 0, 0, 0};
		id += die;
		
		for(int i=0; i<board.presents.length; i++)			 
			if(id == board.presents[i].presentSquareId){
					 					
				score += board.presents[i].points;
				board.presents[i].points = 0;
				outcome[3]++;
				break;
			}
		 
		for(int i=0; i<board.snakes.length; i++)		 
			if(id == board.snakes[i].headId){
				 
				id = board.snakes[i].TailId;
				outcome[1]++;
				temp = strippedMove(id, 0);
				for(int j=0; j<4; j++)
					outcome[j] += temp[j];
				break;
			}
		 
		for(int i=0; i<board.ladders.length; i++)	 
			if(id == board.ladders[i].bottomSquareId && board.ladders[i].broken == false) {
				 
				id = board.ladders[i].topSquareId;
				board.ladders[i].broken = true;
				outcome[2]++;
				temp = strippedMove(id, 0);
				for(int j=0; j<4; j++)
					outcome[j] += temp[j];
				break;
			}
		
		outcome[0] = id;
		return outcome;
	 }
}
