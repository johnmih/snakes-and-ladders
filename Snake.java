/* H κλάση Snake υλοποιεί τα αντικείμενα-φίδια του παιχνιδιού. Τα φίδια απαριθμούνται με το snakeId του εκάστοτε φιδιού. 
 * Το κεφάλι και η ουρα του φιδιού καταλαμβάνουν -χωριστά- τετράγωνα με προσδιοριστή headId και tailId αντίστοιχα. Η κλάση 
 * περιέχει επίσης constructors, setters και getters για να μπορούμε να ορίζουμε τα χαρακτηριστικά του εν λόγω φιδιού.  */

public class Snake{

	int snakeId;
	int headId;
	int TailId;
	
	//κενός constructor (αρχικοποιεί τα πάντα σε 0)
	Snake(){
		
		snakeId = 0;
		headId = 0;
		TailId = 0;
	}
	
	//constructor με ορίσματα
	Snake(int snakeId, int headId, int TailId){
		
		this.snakeId = snakeId;
		this.headId = headId;
		this.TailId = TailId;
	}
	
	//constructor με χρήση αντικειμένου Snake
	Snake(Snake snake){
		
		this.snakeId = snake.snakeId;
		this.headId = snake.headId;
		this.TailId = snake.TailId;
	}
	
	//setter για snakeId
	void setSnakeId(int snakeId) {
		
		this.snakeId = snakeId;
	}
	
	//getter για snakeId
	int getSnakeId() {
		
		return snakeId;
	}
	
	//setter για headId
	void setHeadId(int headId) {
		
		this.headId = headId;
	}
	
	//getter για headId
	int getHeadId() {
		
		return headId;
	}
	//setter για tailId
	void setTailId(int TailId) {
		
		this.TailId = TailId;
	}
	
	//getter για tailId
	int getTailId() {
		
		return TailId;
	}
}

