/* H κλάση Ladder υλοποιεί τα αντικείμενα-σκάλες του παιχνιδιού. Οι σκάλες απαριθμούνται με το ladderId της εκάστοτε σκάλας. 
 * Η κορυφή και ο πάτος της σκάλας καταλαμβάνουν -χωριστά- τετράγωνα με προσδιοριστή topSquareId και bottomSquareId 
 * αντίστοιχα. Μια σκάλα μπρεί να βρίσκεται σε μια από τις δύο καταστάσεις: σπασμένη ή λειτουργηκή. Όταν μια σκάλα 
 * χρησιμοποιείται από κάποιον παίκτη, η κατάσταση της σκάλας μεταβάλλεται από λειτουρφική σε σπασμένη -και δεν μπορεί να 
 * ξαναχρησιμοποιηθεί. Η λειτουργία αυτή διέπεται από τη μεταβλητή τύπου boolean (broken). Η κλάση περιέχει επίσης 
 * constructors, setters και getters για να μπορούμε να ορίζουμε τα χαρακτηριστικά της εν λόγω σκάλας.  */

public class Ladder{

	int ladderId;
	int topSquareId;
	int bottomSquareId;
	boolean broken;
	
	//κενός constructor (αρχικοποιεί τα πάντα σε 0)
	Ladder(){
		
		ladderId = 0;
		topSquareId = 0;
		bottomSquareId = 0;
		broken = false;
	}
	
	//constructor με ορίσματα
	Ladder(int ladderId, int topSquareId, int bottomSquareId, boolean broken){
		
		this.ladderId = ladderId;
		this.topSquareId = topSquareId;
		this.bottomSquareId = bottomSquareId;
		this.broken = broken;
	}
	
	//constructor με χρήση αντικειμένου Ladder
	Ladder(Ladder ladder){
		
		this.ladderId = ladder.ladderId;
		this.topSquareId = ladder.topSquareId;
		this.bottomSquareId = ladder.bottomSquareId;
		this.broken = ladder.broken;
	}
	
	//setter για ladderId
	void setLadderId(int ladderId) {
		
		this.ladderId = ladderId;
	}
	
	//getter για ladderId
	int getLadderId() {
		
		return ladderId;
	}
	
	//setter για topSquareId
	void setTopSquareId(int topSquareId) {
		
		this.topSquareId = topSquareId;
	}
	
	//getter για topSquareId
	int getTopSquareId() {
		
		return topSquareId;
	}
	
	//setter για bottomSquareId
	void setBottomSquareId(int bottomSquareId) {
		
		this.bottomSquareId = bottomSquareId;
	}
	
	//getter για bottomSquareId
	int getBottomSquareId() {
		
		return bottomSquareId;
	}
	
	//setter για broken
	void setBroken(boolean broken) {
		
		this.broken = broken;
	}
	
	//getter για broken
	boolean getBroken() {
		
		return broken;
	}
}
