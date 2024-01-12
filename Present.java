/* H κλάση Present υλοποιεί τα αντικείμενα-δώρα του παιχνιδιού. Τα δώρα απαριθμούνται με το presentId του εκάστοτε δώρου. 
 * Το δώρο καταλαμβάνει ένα τετράγωνο με προσδιοριστή presentSquareId και αποδίδει ορισμένους πόντους (points) στον 
 * παίκτη που το συλλέγει. Κάθε δώρο συλλέγεται μόνο μία φορα (κάθε επόμενη φορά αποδίδει στονπαίκτη 0 πόντους). Η κλάση 
 * περιέχει επίσης constructors, setters και getters για να μπορούμε να ορίζουμε τα χαρακτηριστικά του εν λόγω δώρου.  */

public class Present{

	int presentId;
	int presentSquareId;
	int points;
	
	//κενός constructor (αρχικοποιεί τα πάντα σε 0)
	Present(){
		
		presentId = 0;
		presentSquareId = 0;
		points = 0;
	}
	
	//constructor με ορίσματα
	Present(int presentId, int presentSquareId, int points){
		
		this.presentId = presentId;
		this.presentSquareId = presentSquareId;
		this.points = points;
	}
	
	//constructor με χρήση αντικειμένου Present
	Present(Present present){
		
		this.presentId = present.presentId;
		this.presentSquareId = present.presentId;
		this.points = present.points;
	}
	
	//setter για presentId
	void setPresentId(int presentId){
		
		this.presentId = presentId;
	}
	
	//getter για presentId
	int getPresentId(){
		
		return presentId;
	}
	
	//setter για presentSquareId
	void setPresentSquareId(int presentSquareId){
		
		this.presentSquareId = presentSquareId;
	}
	
	//getter για presentSquareId
	int getPresentSquareId(){
		
		return presentSquareId;
	}
	
	//setter για points
	void setPoints(int points){
		
		this.points = points;
	}
	
	//getter για points
	int getPoints() {
		
		return points;
	}
}
