 /**
 * StrLinkedList public class.
 * 
 * StrLinkedList is a linked list of integers. The StrLinkedList class holds the 
 * head node of the linked list, and a set of list operations. 
 * 
 * An inner-class stores information about each node;
 * @see Node#Node(String v)
 * 
 */
public class StrLinkedList{

	// Stores the value of the head node.
	Node head;

	/**
	 * Node private class.
	 * 
	 * Node is an object in the linked list of integers. The Node inner-class 
	 * holds the value of a node, and a link to the next node in the linked list.	 
	 */
	private class Node{		

		// Stores the value of a node, and its link to the next node.
		String value;
		Node next;

		/**
		 * Node constructor.
		 * 
		 * Constructs a node with the value passed in,
		 * and then sets the next node to null.
		 * 
		 * @param v Specifies the value of the node as a String. 
		 */
		public Node(String v){

			value = v;
			next = null;
		}
	}

	/**
	 * StrLinkedList constructor.
	 * 
	 * Constructs and empty linked list, and sets the head of the list as null.	 * 	 
	 */
	public StrLinkedList(){

		head = null;
	}	

	/**
	 * isEmpty()
	 * 
	 * Checks if the linked list is empty, and returns a boolean value.
	 * 
	 * @return true if the head is null.
	 */
	public boolean isEmpty(){

		while(head != null){
			return false;
		}
		return true;
	}

	/**
	 * getLength()
	 * 
	 * Gets the amount of nodes in the linked list, and returns an int.
	 * 
	 * This method calls the following supporting methods:
	 * @see #getValueAt(int i)
	 * @see #isEmpty()	
	 * 
	 * @return the count of nodes as an integer.
	 */
	public int getLength(){

		Node currentNode = head.next;
		int count = 0;

		if(!isEmpty()){
		 	count++;
			
			while(currentNode != null){
				currentNode = currentNode.next;
				count++;
			}
			return count;		
		}		
		return count;
	}


	 /** 
	 * hasValue(String s)
	 * 
	 * Checks if the linked list contains a node that has the specified value,
	 * and returns a boolean.
	 * 
	 * @param s Specifies the possible value of a node as a String.
	 * @return true if there is a node with the same value as s.
	 */
	public boolean hasValue(String s){ 

		Node currentNode = head;

		while(currentNode != null){
			if(currentNode.value == s){
				return true;
			}
			currentNode = currentNode.next;
		}
		return false;
	}

	 /** 
	  * getValueAt(int i) 
	  * 
	  * Gets the value of the node at a specific index i, and then 
	  * returns the String
	  * 
	  * This method calls the following supporting method:
	  * @see #isEmpty()	
	  * @see #getLength()	
	  * 
	  * @param i Specifies the position of a node as an integer.
	  * @return the String value of the node at position i.
	  */
	public String getValueAt(int i){

		int index = 0;
		Node currentNode = head; 		

		if(i >= getLength() || isEmpty()){
			System.out.println("Index " + i + " is out of range.");
			return null;	
		}	

		while(currentNode != null){			
			if(index == i){
				return currentNode.value;	
			}
			index++;
			currentNode = currentNode.next;
		}
		return null;	
	}

	 /** 
	  * add(String s)
	  * 
	  * Adds a new node to the head of the linked list, then links the next node 
	  * to the head and increments the count.
	  * 
	  * @param s Specifies the value of the new node as a String. 
	  */
	public void add(String s){

		Node newNode = new Node(s);
		newNode.next = head;
		head = newNode;	

	}

	 /**
	 * remove(String s)
	 * 
	 * Removes the first node that has the specified value, and maintains the 
	 * order of the linked list.
	 * 
	 * This method calls the following supporting methods:
	 * @see #hasValue(String s)
	 * 	 
	 * @param s Specifies the value of the removed node as a String. 
	 */
	public void remove(String s){
	
		// Sets two nodes to use for moving across the linked list.
		Node previousNode = head;
		Node currentNode = head.next;

		if(hasValue(s)){				
		 	
			// Checks if the head node has s, replaces it with the current node.
			if(previousNode.value == s) {
				head = currentNode;
				return;				
			}	

			// Loops through the rest of the list until there's no more nodes.
			while(currentNode != null){

				// Checks if the current node has s, remove the link if so. 
				if(currentNode.value == s){
					previousNode.next = currentNode.next;			
					return;
				}
				// Moves through the list, linking in order.
				previousNode = currentNode;
				currentNode = currentNode.next;			
			} 
		}
		System.out.println("[No value could be removed.]");
		return;			
	}

	 /**
	 * print()
	 * 
	 * Prints each node to the system with pointers.
	 * 	
	 * This method calls the following supporting method:
	 * @see #isEmpty()	
	 */
	public void print(){
		
		// Sets the current node as the head to begin printing.
		Node currentNode = head;
		//currentNode.next = head.next;

		// Checks if the list isn't empty before printing.
		if(!isEmpty()){
			
			// While there is a current node, print it and shift to the next node.
			while(currentNode != null){
				System.out.print(currentNode.value + "->");
				currentNode = currentNode.next;	

				// Checks if it's the final node, and prints without an arrow.
				if(currentNode.next == null) {
					System.out.print(currentNode.value);
					System.out.println();
					System.out.println();
					return;
				}		
			}
		}
	}

	 /**
	 * printDown()
	 * 
	 * Prints each node to the system on a new line.
	 * 
	 * This method calls the following supporting method:
	 * @see #isEmpty()		 
	 */
	public void printDown(){
	
		// Sets the current node as the head to begin printing.
		Node currentNode = head;
		//currentNode.next = head.next;
		System.out.println();

		// Checks if the list isn't empty before printing.
		if(!isEmpty()){
			
			// While there is a current node, print it and shift to next node.
			while(currentNode != null){
				System.out.println(currentNode.value);
				currentNode = currentNode.next;	

				// Checks if it's the final node, and prints a message.
				if(currentNode.next == null) {
					System.out.println(currentNode.value);
					System.out.println();
					return;
				}		
			}
		}
	}

}


	