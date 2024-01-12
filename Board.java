/* Η κλάση Board υλοποιεί το ταμπλό του παιχνιδιού. Αυτό έχει διαστάσεις (N, M), τετράγωνα (squares), φίδια (snakes), 
 * σκάλες (ladders) και δώρα (presents). Η κλάση περιέχει επίσης constructors, setters και getters για να μπορούμε 
 * να ορίζουμε τα χαρακτηριστικά του ταμπλό
 * */

public class Board{

	int N, M;
	int[][] squares;
	Snake[] snakes;
	Ladder[] ladders;
	Present[] presents;
	
	
	//κενός constructor (αρχικοποιεί τα πάντα σε 0)
	Board(){
		
		N = 0;
		M = 0;
		squares  = new int[N][M];
		snakes = new Snake[0];
		ladders = new Ladder[0];
		presents = new Present[0];
	}
	
	//constructor με ορίσματα
	Board(int N, int M, int snakeNum, int ladderNum, int presentNum) {
		
		this.N = N;
		this.M = M;		
		squares  = new int[N][M];
		
		snakes = new Snake[snakeNum];
		for(int i=0; i<snakeNum; i++)
			snakes[i] = new Snake();
		
		ladders = new Ladder[ladderNum];
		for(int i=0; i<ladderNum; i++)
			ladders[i] = new Ladder();
		
		presents = new Present[presentNum];
		for(int i=0; i<presentNum; i++)
			presents[i] = new Present();
	}
	
	//constructor με χρήση αντικειμένου Board
	Board(Board board){
		
		this.N = board.N;
		this.M = board.M;
		
		squares  = new int[N][M];
		for(int i=0; i<N; i++) {
			
			for(int j=0; j<M; j++)
				squares[i][j] = board.squares[i][j];
			
		}
		
		snakes = new Snake[board.snakes.length];
		for(int i=0; i<board.snakes.length; i++)
			snakes[i] = new Snake(board.snakes[i]);
		
		ladders = new Ladder[board.ladders.length];
		for(int i=0; i<board.ladders.length; i++)
			ladders[i] = new Ladder(board.ladders[i]);
		
		presents = new Present[board.presents.length];
		for(int i=0; i<board.presents.length; i++)
			presents[i] = new Present(board.presents[i]);
	}
	
	//setter για N
	void setN(int N){
		 this.N = N;
	}
	
	//getter για N
	int getN(){
		 
		return N;
	}
	
	//setter για M
	void setM(int M){
		
		this.M = M;
	}
	
	//getter για M
	int getM(){
		
		return M;
	}
	
	//setter για squares
	void setSquares(int N, int M){
		
		int count = 1;
		for(int i=N-1; i>=0; i--){
			
			if(i%2 == 0)
				for(int j=M-1; j>=0; j--){
					
					squares[i][j] = count;
					count++;
				}
			
			else 
				for(int j=0; j<M; j++){
					
					squares[i][j] = count;
					count++;
				}		
		}
	}
	
	//getter για squares
	int[][] getSquares(){
		
		return squares;
	}
	
	//setter για snakes
	void setSnakes(Snake[] snake){
		
		for(int i=0; i<snake.length; i++){
			
			snakes[i].snakeId = snake[i].snakeId;
			snakes[i].headId = snake[i].headId;
			snakes[i].TailId = snake[i].TailId;
		}
	}
	
	//getter για snakes
		Snake[] getSnakes(){
			
			return snakes;
		}
	
	//setter για snake
	void setSnake(Snake snake, int num){
		
		snakes[num].snakeId = snake.snakeId;
		snakes[num].headId = snake.headId;
		snakes[num].TailId = snake.TailId;
	}
	
	//getter για snake
	Snake getSnake(int num){
		
		return snakes[num];
	}
	
	//setter για ladders
	void setLadders(Ladder[] ladder){
		
		for(int i=0; i<ladders.length; i++) {
			
			ladders[i].ladderId = ladder[i].ladderId;
			ladders[i].topSquareId = ladder[i].topSquareId;
			ladders[i].bottomSquareId = ladder[i].bottomSquareId;
			ladders[i].broken = ladder[i].broken;
		}
	}
	
	//getter για ladders
		Ladder[] getLadders(){
			
			return ladders;
		}
		
	//setter για ladder
	void setLadder(Ladder ladder, int num){
		
		ladders[num].ladderId = ladder.ladderId;
		ladders[num].topSquareId = ladder.topSquareId;
		ladders[num].bottomSquareId = ladder.bottomSquareId;
		ladders[num].broken = ladder.broken;
	}
	
	//getter για ladder
	Ladder getLadder(int num){
		
		return ladders[num];
	}
	
	//setter για presents
	void setPresents(Present[] present){
		
		for(int i=0; i<presents.length; i++) {
			
			presents[i].presentId = present[i].presentId;
			presents[i].presentSquareId = present[i].presentSquareId;
			presents[i].points = present[i].points;
		}
	}
	
	//getter για presents
	Present[] getPresents() {
		
		return presents;
	}
	
	//setter για present
	void setPresent(Present present, int num) {
		
		presents[num].presentId = present.presentId;
		presents[num].presentSquareId = present.presentSquareId;
		presents[num].points = present.points;
	}
	
	//getter για present
	Present getPresent(int num) {
		
		return presents[num];
	}
	
	//δημιουργεί το ταμπλό
	void createBoard(){
		
		//δημιουργώ τα τετράγωνα του ταμπλο
		int count=1;
		for(int i=N-1; i>=0; i--){
			
			if(i%2 == 0) 
				for(int j=M-1; j>=0; j--){
					
					squares[i][j] = count;
					count++;
				}
			else 
				for(int j=0; j<M; j++){
					
					squares[i][j] = count;
					count++;
				}
			
		}
		
		for(int i=0; i<snakes.length; i++){
			
			snakes[i].snakeId = i; //απαριθμεί τα φίδια του ταμπλό
			snakes[i].TailId = (int)(Math.random()*(N*M-M-1)) + 1; //η ουρά δεν μπορεί να είναι στην κορυφαία γραμμή
			boolean overlapHead; //ελέγχει αν συμπίπτουν δύο κεφάλια ξεχωριστών φιδιών (ποιό θα φίδι ακολουθούσε αλλιώς;)
			do{
				
				overlapHead = false;
				
				//το κεφάλι μπαίνει κάπου πάνω από την ουρά
				snakes[i].headId = (int)(Math.random()*(N*M-1-snakes[i].TailId-1))+snakes[i].TailId+1;
				
				//αν το κεφάλι συμπίπτει με κάποιο άλλο ήδη υπάρχον κεφάλι η διαδικασία επαναλαμβάνεται
				for(int j=0; j<snakes[i].getSnakeId(); j++) 				
					if(snakes[i].headId == snakes[j].getHeadId()){
						
						overlapHead = true;
						break;
					}
			
			}while(overlapHead);
		}
		
		for(int i=0; i<ladders.length; i++){
			
			ladders[i].ladderId = i; //απαριθμεί τις σκάλες του ταμπλό
			boolean overlapHeadBottom, overlapBottoms;
			do{
				
				overlapHeadBottom = false; //ελέγχει αν συμπίπτουν κεφάλια φιδιών με πάτους σκαλών
				overlapBottoms = false; //ελέγχει αν συμπίπτουν πάτοι ξεχωριστών σκαλών
				ladders[i].bottomSquareId = (int)(Math.random()*(N*M-M-1)) + 1; //ο πάτος δεν μπορεί να είναι στην κορυφαία γραμμή
				
				//αν ο πάτος συμπίπτει με κάποιο κεφάλι φιδιού η διαδικασία επαναλαμβάνεται
				for(int j=0; j<snakes.length; j++) 
					if(snakes[j].getHeadId() == ladders[i].bottomSquareId){
						
						overlapHeadBottom = true;
						break;
					}
				
				//αν ο πάτος συμπίπτει με κάποιον άλλο ήδη υπάρχον πάτο η διαδικασία επαναλαμβάνεται
				for(int k=0; k<ladders[i].getLadderId(); k++){
					
					if(ladders[i].bottomSquareId == ladders[k].getBottomSquareId()) {
						
						overlapBottoms = true;
						break;
					}
				}
			}while(overlapHeadBottom || overlapBottoms);
			//η κορυφή της σκάλας πρέπει να είναι πιο ψηλά από τον πάτο της
			ladders[i].topSquareId = (int)(Math.random()*(N*M-1-ladders[i].bottomSquareId-1))+ladders[i].bottomSquareId+1;
		}
		
		for(int i=0; i<presents.length; i++){
			
			presents[i].presentId = i; //απαριθμεί τα δώρα του ταμπλό
			presents[i].presentSquareId = (int)(Math.random()*(N*M-1)) + 1;
			presents[i].points = (int)(Math.random()*50); //δίνει τυχαίους πόντους στο κάθε δώρο (έως 50)
		}
	}
	
	void createElementBoard(){
		
		String[][] elementBoardSnakes = new String[N][M]; //θέσεις φιδιών
		String[][] elementBoardLadders = new String[N][M]; //θέσεις σκαλών
		String[][] elementBoardPresents = new String[N][M]; //θέσεις δώρων
		
		for(int i=0; i<N; i++) 			
			for(int j=0; j<M; j++){
				
				elementBoardSnakes[i][j] = new String();
				elementBoardSnakes[i][j] = "---";
				
				for(int k=0; k<snakes.length; k++){
					
					//αν υπάρχει κεφάλι φιδιού στο συγκεκριμένο τετράγωνο το τυπώνει
					if(snakes[k].getHeadId() == squares[i][j]){
						
						elementBoardSnakes[i][j] = "SH" + snakes[k].snakeId;
						break;
					}
					//αν υπάρχει ουρά φιδιού στο συγκεκριμένο τετράγωνο την τυπώνει
					if(snakes[k].TailId == squares[i][j]){
						
						elementBoardSnakes[i][j] = "ST" + snakes[k].snakeId;
						break;
					}
				}
			}
		
		
		for(int i=0; i<N; i++)		
			for(int j=0; j<M; j++){
				
				elementBoardLadders[i][j] = new String();
				elementBoardLadders[i][j] = "---";
				
				for(int k=0; k<ladders.length; k++){
					
					//αν η σκάλα είναι σπασμένη δεν την τυπώνει
					if(ladders[k].broken)
						break;
					//αν υπάρχει κορυφή σκάλας στο συγκεκριμένο τεράγωνο την τυπώνει
					else if(ladders[k].topSquareId == squares[i][j]){
						
						elementBoardLadders[i][j] = "LU" + ladders[k].ladderId;
						break;
					}
					//αν υπάρχει πάτος σκάλας στο συγκεκριμένο τεράγωνο τον τυπώνει
					else if(ladders[k].bottomSquareId == squares[i][j]){
						
						elementBoardLadders[i][j] = "LD" + ladders[k].ladderId;
						break;
					}
				}
			}
		
		
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++){
				
				elementBoardPresents[i][j] = new String();
				elementBoardPresents[i][j] = "---";
				
				for(int k=-0; k<presents.length; k++){
					
					//αν υπάρχει δώρο στο συγκεκριμένο τετράγωνο το τυπώνει
					if(presents[k].presentSquareId == squares[i][j]) {
						
						elementBoardPresents[i][j] = "PR" + presents[k].presentId;
						break;
					}
				}
			}
		
		
		System.out.println("< elementBoardSnakes >");
		for(int i=0; i<N; i++){
			
			for(int j=0; j<M; j++) 
				System.out.print(" " + elementBoardSnakes[i][j] + " ");
			System.out.println();
		}
		System.out.println("< elementBoardLadders >");
		for(int i=0; i<N; i++){
			
			for(int j=0; j<M; j++) 
				System.out.print(" " + elementBoardLadders[i][j] + " ");
			System.out.println("");
		}
		System.out.println("< elementBoardPresents >");
		for(int i=0; i<N; i++){
			
			for(int j=0; j<M; j++) 
				System.out.print(" " + elementBoardPresents[i][j] + " ");
			System.out.println("");
		}
		System.out.println("< elementBoardAll >"); //προαιρετικό, για ταυτόχρονη προβολή όλων των στοιχείων του ταμπλό
		for(int i=0; i<N; i++){
			
			for(int j=0; j<M; j++) 
				System.out.print(" " + elementBoardPresents[i][j] + elementBoardLadders[i][j] + elementBoardSnakes[i][j] + " ");
			System.out.println("");
		}
	}
}
