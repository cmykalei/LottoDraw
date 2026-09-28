/**
 * LottoDraw public class.
 * 
 * LottoDraw is a program that simulates a lottery draw.
 * The LottoDraw class holds a main method for implementaion, and a collection 
 * of supporting methods. 
 * The simulation will do a series of calculations to help the user determine 
 * the feasibility of the lotto draw as a fundraiser.
 * 
 * This class utilises multiple instances of the StrLinkedList class.
 * @see StrLinkedList#SStrLinkedList()
 */
public class LottoDraw{
	
	// Initialises variables that will specify the settings of this lotto draw.
	String drawName;
	int drawLength = 0;
	int maxRange = 0;
	int ticketLength = 0;
	int ticketPrice = 0;

	// Initialises empty StrLinkedLists to store the ticket values of this draw.
	StrLinkedList numbersList = new StrLinkedList();
	StrLinkedList ticketSet = new StrLinkedList();
	StrLinkedList winningTicket = new StrLinkedList();
	StrLinkedList financialResults = new StrLinkedList();
	StrLinkedList drawOutcome = new StrLinkedList();

	/**
	 * main(String args[])
	 * 
	 * The main method creates instance of LottoDraw, which will simulate a 
	 * draw with specified settings, and then prints the outcome of the draw 
	 * to the system.
	 * 
	 */
	public static void main(String args[]){

		// Settings in order: name, length, range, ticket size, ticket price.
		LottoDraw draw1 = new LottoDraw("Draw 1", 100, 40, 6, 10); // Default
	}

	/**
	 * LottoDraw Constructor.
	 * 
	 * Constructs a simulated lotto draw by taking the values passed in, then 
	 * setting them into the appropriate class variables.
	 * 
	 * This constructor is dependent on the following supporting methods:
	 * @see #generateGuessRange()
	 * @see #generateTicketSet()
	 * @see #makeTicket()
	 * 
	 * @param n Specifies the String name of this draw.
	 * @param l Specifies the int amount of tickets in a draw.
	 * @param r Specifies the int max range of possible numbers.
	 * @param t Specifies the int length of ticket.
	 * @param c Specifies the int cost of a ticket.
	 */
	public LottoDraw(String n, int l, int r, int t, int c){

		this.drawName = n;
		this.drawLength = l;
		this.maxRange = r;
		this.ticketLength = t;
		this.ticketPrice = c;
		
		this.numbersList = generateGuessRange();
		this.ticketSet = generateTicketSet();
		this.winningTicket = makeTicket();

		this.printDrawOutcome();
		this.printDraw();	
	}

	/**
	 * generateNumber()
	 * 
	 * Generates a random number from a range given by a chosen draw size, then 
	 * casts the value to return an int.
	 * 
	 * @return the randomly generated number as an int.
	 */
	public int generateNumber(){
		
		int i = (int)(Math.random()*(this.maxRange));
		return i;
	}

	/**
	 * generateGuessRange()
	 * 
	 * Creates the guess range of a lotto draw by looping until the specified 
	 * max range is reached, and adding the int value of each [iteration + 1]
	 * as a String value to a StrLinkedList.
	 * 	 
	 * @return the StrLinkedList "guessRange" of all possible numbers in a draw.
	 */
	public StrLinkedList generateGuessRange(){

		StrLinkedList guessRange = new StrLinkedList();
		for(int i = 0; i < this.maxRange; i++){
			String value = String.valueOf(i+1);			
			guessRange.add(value);
		}
		return guessRange;
	}

	/**
	 * makeTicket()
	 * 
	 * Makes a lotto ticket by looping until the specified ticket length, then 
	 * adds the iteration number as a String value to the StrLinkedList.
	 * Each value comes from a list of possible numbers in the this draw's range,
	 * and is selected by getting the value from the list that is located at a 
	 * randomly generated index.
	 * 
	 * This method calls the following supporting methods:
	 * @see #getValueAt(int)
	 * @see #generateNumber()
	 * 
	 * @return the StrLinkedList "newticket" containing random numbers.
	 */
	public StrLinkedList makeTicket(){

		StrLinkedList newTicket = new StrLinkedList();
		for(int i = 0; i < this.ticketLength; i++){
			newTicket.add(this.numbersList.getValueAt(generateNumber()));
		}
		return newTicket;
	}

	/**
	 * generateTicketSet()
	 * 
	 * Generates the a set with the amount of tickets given by the chosen draw 
	 * size, with the length of each ticket given by the chosen ticket length.
	 * 
	 * This method calls the following supporting methods:
	 * @see #getValueAt(int)
	 * @see #makeTicket()	 
	 * 
	 * @return the StrLinkedList "ticketSet" of all tickets in a draw.
	 */
	public StrLinkedList generateTicketSet(){
		
		StrLinkedList ticketSet = new StrLinkedList();
		StrLinkedList newTicket = new StrLinkedList();

		for(int i = 0; i < this.drawLength; i++){
			newTicket = makeTicket();

			for(int j = 0; j < this.ticketLength; j++){
				ticketSet.add(newTicket.getValueAt(j));
			}			
		}
		return ticketSet;
	}

	/**
	 * findTicket(int i)
	 * 
	 * Finds the ticket located at a specified index in this draw's ticket set.
	 * The set of all tickets contains every value in this lotto draw in order,
	 * so a single ticket is found by multiplying the specified index by this 
	 * draw's ticket length, then copying each consecutive value until that
	 * length is reached. 
	 * 
	 * This method calls the following supporting methods:
	 * @see #getValueAt(int i)
	 * @see #isEmpty()	
	 * 
	 * @param i Specifies the int index to find the ticket at.
	 * @return the StrLinkedList "foundTicket" of the ticket at index i.
	 * */
	public StrLinkedList findTicket(int i){
	
		if(this.ticketLength > 0){
			if(!(this.ticketSet.isEmpty())){

				// Sets the list to hold the ticket, and variables for its index.
				StrLinkedList foundTicket = new StrLinkedList();
				int ticketStart = this.ticketLength * i;
				int ticketEnd = this.ticketLength + ticketStart;

				// Loops until the length of the ticket, starting at the index.
				while(ticketStart < ticketEnd){

					// Copies each value until the end of the ticket length.
					foundTicket.add(this.ticketSet.getValueAt(ticketStart));
					ticketStart++;
				}
				return foundTicket;
			}
			System.out.println("No ticket found: ticket set appears empty.");
			return null;
		}
		System.out.println("No ticket found: check ticket length settings.");
		return null;		
	}

	/**
	 * checkTicket(StrLinkedList c)
	 * 
	 * Checks the ticket passed in against the values of this draw's winning 
	 * ticket, then uses the count of matching values to calculate the prize.
	 * 
	 * This method calls the following supporting methods:
	 * @see #getValueAt(int i)
	 * @see #isEmpty()	
	 * @see #hasValue(String s)	
	 * 
	 * @param c Specifies the StrLinkedList current ticket to check.
	 * */
	public int checkTicket(StrLinkedList c){

		// Initialises int variables count the matches and calculate prize money.
		int matchCount = 0;
		int prize = 0;

		// Checks if there's a ticket then loops until this draw's ticket length.
		if(!(c.isEmpty())){		
			
			for(int i = 0; i < this.ticketLength; i++){

				// Checks it against the winning ticket's value at iteration i.
				if(c.hasValue(this.winningTicket.getValueAt(i))){
					matchCount++;				
				}		
			}

			// Checks if there are more than 2 matches, then returns the prize. 
			if(matchCount > 2){
				prize = (int)Math.pow(10, (matchCount-2));
				return prize;
			}			
		}
		return prize;
	}

	/**
	 * calculatePayouts()
	 * 
	 * Calculates the int sum of money to pay all the winners in this lotto draw 
	 * by looping until length of the draw, then finding the ticket at iteration
	 * i, and finally adding every amount owed before returning the total debt.
	 * 
	 * This method calls the following supporting methods:
	 * @see #checkTicket(int)
	 * @see #findTicket(int)
	 * 
	 * @return the int "debt" to pay for a winning ticket.
	 */
	public int calculatePayouts(){

		int debt = 0;
		for(int i = 0; i < this.drawLength; i++){
			debt += checkTicket(findTicket(i));
		}
		return debt;
	}


	/**
	 * formatToString(String s, int x)
	 * 
	 * Takes the values from parameters passed in and formats them to a 
	 * concatenated String. The int is converted to a float so that it can be
	 * displayed like a currency, and then rounded to 2 decimal places by 
	 * utilising the format function.
	 * 
	 * @param s Specifies the String description of the value added.
	 * @param i Specifies the int value to concat to the String.
	 * @return the String "output" of the formatted parameters passed in.
	 */
	public String formatToString(String s, int i){

		String output = s + String.format("%.2f", Float.valueOf(i));
		return output;
	}


	/**
	 * printDrawOutcome()
	 * 
	 * Prints the financial outcome of a lotto draw to the system.
	 * Calculates the sales, payouts, and total profits, before formatting the
	 * results and then adding them to this draw's StrLinkedList's containing
	 * its outcomes and financial stats.
	 * 
	 * This method calls the following supporting methods:
	 * @see #calculatePayouts(int i)
	 * @see #formatToString(String s, int i)
	 * @see #checkTicket(StrLinkedList c)
	 * @see #findTicket(int i)
	 * @see StrLinkedList#printDown()
	 * 
	 * */
	public void printDrawOutcome(){

		if(this.drawLength > 0 && this.ticketPrice > 0){
					
			// Sets variables to each result by calculating the sales of this draw.
			int totalSales = (this.drawLength) * (this.ticketPrice);
			int totalPayouts = calculatePayouts();
			int totalProfit = totalSales - totalPayouts;

			// Initialises this draw's StrLinkedLists to store the drawOutcome.
			this.drawOutcome = new StrLinkedList();
			this.financialResults = new StrLinkedList();

			// Formats the sales stats adds each to the list of financialResults.
			this.financialResults.add(formatToString("Price $", this.ticketPrice));
			this.financialResults.add(formatToString("Tickets Qty ", drawLength));			
			this.financialResults.add(formatToString("Sales $", totalSales));	
			this.financialResults.add(formatToString("Payouts $", totalPayouts));
			this.financialResults.add(formatToString("Profits $", totalProfit));
				
			// Loops until the end of the draw, then gets the values of each ticket.	
			int i = 0;
			while(i < this.drawLength){	
				StrLinkedList currentTicket = findTicket(i);
				String ticketInfo = "Ticket " + String.valueOf(i+1) + "\t" + "won $";
				int prize = checkTicket(currentTicket);

				// Checks if the current ticket is a winner before including it.
				if(prize > 1){
					drawOutcome.add(formatToString(ticketInfo, prize));	
				}		
				i++;
			}	
			// Prints the lists of outcomes to the system.
			System.out.println("-----------------------------");
			System.out.println("Draw sales of " + this.drawName);
			this.financialResults.printDown();
			System.out.println("Winning tickets in " + this.drawName);
			this.drawOutcome.printDown();
			System.out.println("-----------------------------");
		}
	}

	/**
	 * printDraw()
	 * 
	 * Prints the tickets of a lotto draw to the system.
	 * The list of possible numbers in a draw and the values of the winning ticket
	 * are printed first, then aach ticket in the draw is printed by looping
	 * through this draw's ticket set, alongside a number to represent its index.
	 * 
	 * This method calls the following supporting methods:
	 * @see StrLinkedList#print()
	 * @see #findTicket(int)
	 */
	public void printDraw(){	

		System.out.println("Printing the tickets of [" + this.drawName + "]");	
		System.out.println("List of all possible lotto numbers in this draw: ");
		this.numbersList.print();
		System.out.println("Winning ticket for [" + this.drawName + "]");
		this.winningTicket.print();
		System.out.println("Printing all tickets in [" + this.drawName + "]");

		// Loops until the end of the draw length and prints each ticket.
		for(int i = 0; i < this.drawLength; i++){	
			StrLinkedList currentTicket = findTicket(i);
			System.out.print("(" + String.valueOf(i+1) + ") ");	
			currentTicket.print();
		}			
	}
}
	