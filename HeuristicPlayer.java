/* Η κλάση HeuristicPlayer υλοποιεί έναν "έξυπνο" παίκτη που έχει τη δυνατότητα να ορίζει ο ίδιος το ζάρι που ρίχνει. 
 * αξιολογεί τις κινήσεις του και εκτελεί την κίνηση που τον συμφέρει περισσότερο -σύμφωνα πάντα με τον δοθέντα αλγόριθμο
 * (δεν υπολογίζει σε βάθος κινήσεων).*/

import java.util.ArrayList;
import java.util.HashMap;

public class HeuristicPlayer extends Player{

	ArrayList<Integer[]> path;
	
	HeuristicPlayer(){
		
		super(); // κληρονομεί την κλάση Player
		path = new ArrayList<Integer[]>();
	}
	
	HeuristicPlayer(int playerId, String name, int score, Board board){
		
		this.playerId = playerId;
		this.name = name;
		this.score = score;
		this.board = board;
		path = new ArrayList<Integer[]>();
	}
	
	//αξιολογεί την κίνηση του έξυπνου παίκτη
	double evaluate(int currentPos, int dice) {
		
		int[] end = {0,0,0,0};
		boolean[] ladderState = new boolean[board.ladders.length]; //σώζει την κατάσταση των σκαλών πριν την εκτέλεση της μεθόδου
		int[] presentPoints = new int[board.presents.length]; //σώζει τους πόντους των δώρων πριν την εκτέλεση της μεθόδου
		int steps, temp1, temp2;
		double evaluate;
		
		for(int i=0; i<board.ladders.length; i++)
			ladderState[i] = board.ladders[i].broken;
		for(int i=0; i<board.presents.length; i++)
			presentPoints[i] = board.presents[i].points;
		
		temp1 = score; //το σκορ του παίκτη πριν την κίνηση
		end = strippedMove(currentPos, dice);
		steps = end[0] - currentPos;
		temp2 = score; //το σκορ του παίκτη μετά την κίνηση
		temp2 -= temp1; //το σκορ που κερδίζει ο παίκτης με την κίνηση
		evaluate = steps * 0.65 + temp2 * 0.35; //συνάρτηση αξιολόγησης
		
		/*το σκορ παραμένει ίδιο, δεν πρόκειται παρά για μια αξιολόγηση! Επίσης επαναφέρουμε τη κατάσταση των σκαλών και 
		 * τους πόντους των δώρων που ενδεχομένως χρησημοποιήθηκαν*/
		score = temp1; 
		for(int i=0; i<ladderState.length; i++)
			board.ladders[i].broken = ladderState[i];
		for(int i=0; i<presentPoints.length; i++)
			board.presents[i].points = presentPoints[i];
		
		return evaluate;
	}
	
	/* int getNextMove(int currentPos) {
		 
		 Integer[] p = new Integer[6]; 
		 double[] moves = new double[6]; //αντιπροσοπεύει το σκορ κάθε μίας από τις έξι ζαριές
		 for(int i=0; i<6; i++)
			 moves[i] = evaluate(currentPos, i); //γεμίζει τον πίνακα με τα σκορ
		 
		 int bestDie = 0; //η καλύτερη ζαριά
		 double max = moves[1]; //το μέγιστο σκορ από κάποια ζαριά
		 for(int i=0; i<6; i++)
			 if(moves[i] > max){
				 
				 max = moves[i];
				 bestDie = i;	 
			 }
		 
		 int temp = score;
		 int[] outcome = {0, 0, 0, 0};
		 outcome = strippedMove(currentPos, bestDie);
		 
		 p[0] = bestDie; //βάζει στο path το κλύτερο ζάρι
		 p[1] = score - temp; //βάζει στο path το σκορ που κέρδισε λόγω της κίνησης
		 p[2] = Math.abs(outcome[0] - currentPos); //βάζει στο path τον αριθμό των βημάτων
		 p[3] = outcome[3]; //βάζει στο path τον αριθμό των δώρων που συνέλεξε ο παίκτης
		 p[4] = outcome[1]; //βάζει στο path τον αριθμό των ιιών που τσίμπησαν τον παίκτη
		 p[5] = outcome[2]; //βάζει στο path τον αριθμό των σκαλών που χρησιμοποίησε ο παίκτης
		 path.add(p);
		 
		 return outcome[0];
	 }*/
	int getNextMove(int currentPos) {
		 
		 HashMap<Integer, Double> evaluation = new  HashMap<Integer, Double>();
		 int temp;
		 int[] outcome = {0,0,0,0};
		 Integer[] p = new Integer[6];
		 
		 //αξιολογώ κάθε κίνηση
		 for(int i=1; i<7; i++)
			 evaluation.put(i, evaluate(currentPos, i));
		 
		 //αποθηκεύω την καύτερη ζαριά 
		 int bestDie = 1;
		 for(int i=2; i<7; i++) {
			 
			 if(evaluation.get(i) > evaluation.get(bestDie))
				 bestDie = i;
		 }
		 
		 temp = score;
		 outcome = strippedMove(currentPos, bestDie);
		 
		 p[0] = bestDie; //βάζει στο path το κλύτερο ζάρι
		 p[1] = score - temp; //βάζει στο path το σκορ που κέρδισε λόγω της κίνησης
		 p[2] = Math.abs(outcome[0] - currentPos); //βάζει στο path τον αριθμό των βημάτων
		 p[3] = outcome[3]; //βάζει στο path τον αριθμό των δώρων που συνέλεξε ο παίκτης
		 p[4] = outcome[1]; //βάζει στο path τον αριθμό των ιιών που τσίμπησαν τον παίκτη
		 p[5] = outcome[2]; //βάζει στο path τον αριθμό των σκαλών που χρησιμοποίησε ο παίκτης
		 path.add(p);
		 
		 return outcome[0];
	 }

	 
	 //εκτυπώνει στην οθόνη τα τεκτενόμενα του παιχνιδιού
	 void statistics(int round){

		Integer[] p = new Integer[6];
		int snakes=0, ladders=0, presents=0;

		for(int i=0; i<path.size(); i++){

			p = path.get(i);
			snakes += p[4];
			ladders += p[5];
			presents += p[3];
			if(i+1 == round) {
				
				System.out.println("Player " + playerId + ", at round " + round + ", set the dice equal to: " + p[0]);
				System.out.println("He got bitten by " + p[4] + " snakes, climbed " + p[5] + " ladders and collected " + p[3] + " presents.");
			}
				
		}

		System.out.println("Summary: Player " + playerId + " between rounds 1 and " + round + " got bitten by " + snakes + 
				" snakes, climbed " + ladders + " ladders and collected " + presents + " presents.");
		System.out.println();
	}
}

