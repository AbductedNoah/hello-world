

public class NumberList {

	NumberNode head = new NumberNode(0, null);
	private int len;

	public NumberList() {

	}
	// done
	public String toString() {
		String str = "{";
		NumberNode current = head;
		for( int j = 0 ; j < len; j ++) {
			if(j == 0) {
			str = str + head.getNum();
			}
			else {
				str = str + "," + current.getNum();
			}
			current= current.getNext();
		}
		
		str = str + "}";
		return str; 
	}

	// work on this one
	public void sort() {
		
		System.out.println("Starting sort: \n" + this.toString());
		
		NumberList left = new NumberList(); 
		NumberList right = new NumberList();
		
		//NumberList result = this; 
		if (len <= 1) {
			// need to do something here.
			return;
			//System.out.println("BUCKETS: " + this.toString());
		} else { 
			
		int mid = (len /2); 

		NumberNode curr = head;
		
		for ( int i = 0; i < mid; i ++) {
			left.add(curr.getNum(), i);
			curr = curr.getNext();
		}

		int i = 0; 
		for ( int j = mid ; j < len ; j ++) {
			
			
			right.add(curr.getNum(), i);
			curr = curr.getNext();
			i++;
		}
		
		left.sort();
		right.sort();
		
		}
		
	    head  = merge(left, right).head;

	}

	private NumberList merge(NumberList left, NumberList right) {
		
		NumberNode leftNode = left.head;
//		System.out.print("This is p1: ");
//		leftNode.printList(leftNode);
		
		NumberNode rightNode = right.head; 
//		System.out.print("This is p2: ");
//		rightNode.printList(rightNode);
		
		NumberList p = new NumberList();
		int index1 = 0; 
		
		while (leftNode != null && rightNode != null) {
						
			if(leftNode.getNum() <= rightNode.getNum()) {
				p.add(leftNode.getNum(), index1);
				leftNode = leftNode.getNext();
			} else {
				p.add(rightNode.getNum(), index1);
				rightNode = rightNode.getNext();
			}
			index1++;
		}
		
		while(leftNode != null) {
			p.add(leftNode.getNum(), index1);
			leftNode = leftNode.getNext();
			index1++;
		}
		
		while(rightNode != null) {
			p.add(rightNode.getNum(), index1);
			rightNode = rightNode.getNext();
			index1++;
		}

		System.out.println("THIS IS P: " + p.toString());
		return p; 
	}
	
	// done
	public void add(int value, int index) {
		// Find the index before our index
		if (index < 0 || index > len) {
			System.out.println("This index is out of bounds for add. Length: " + len);
		} else if (index == 0) {
			//head.num = value;
			NumberNode current = new NumberNode(value, null); 
			head = current;
			current.num = value;
			len = len + 1;
		}
		else {
			NumberNode current = head;
			NumberNode before = getNodeBefore(index); 
			NumberNode after = before.getNext();
			NumberNode insertNode = new NumberNode( value, after);
			before.setNext(insertNode); 
			len = len + 1;
			}
		
	}

	public void remove(int index) {
		if (index > len || index < 0) {
			System.out.println("Error removing!");
		}
		else if (index == 0) {
			head.num = 0; 
		}
		else if (index == len) {
			NumberNode before = getNodeBefore(index);
			before.setNext(null);
		}
		else {
		NumberNode n = getNodeBefore(index);
		n.setNext(n.getNext().getNext());
		}
		len = len - 1;
	}
	// done
	private NumberNode getNodeBefore(int index) {
		NumberNode result = head;
		for (int i = 1; i < index; i++) {
			result = result.getNext();
		}
		return result;
	}
	// done
	public int get(int index) {
		return head.getHelper(index);
	}

	// done
	public int indexOf(int value) {
		if(head.getNum() == value) {
			return 0;
		}
		else {
			NumberNode current = head; 
			for(int i =0; i < len; i++) {
				if(current.getNum() == value) {
					return i;
				}
				current = current.getNext();
			}
		}
		return -1;
		
	}
	
	final static IntegerFunction doubleN = new IntegerFunction() {
		public int apply(int n) {
			return n * 2;
		}
	};

	final static IntegerFunction square = new IntegerFunction() {
		public int apply(int n) {
			return n * n;
		}
	};

	final static IntegerBinaryFunction addition = new IntegerBinaryFunction() {
		public int apply(int n, int m) {
			return n + m;
		}
	};
	final static IntegerBinaryFunction multiplication = new IntegerBinaryFunction() {
		public int apply(int n, int m) {
			return n * m;
		}
	};

	// work on this one
	public NumberList reverse() {
		NumberNode current = head;
		NumberList result = new NumberList();
		int [] storage = new int[len];
		for(int i = len-1; i >= 0; i--) {
			int val = current.getNum();
			System.out.print(val);
			storage[i] = val; 
			current = current.getNext();
		}
		for(int i = 0; i < len; i ++) {
			result.add(storage[i], i);
		}
		return result;
	}

	// done
	public NumberList map(IntegerFunction f) {
		
		NumberList result = this;
		NumberNode pos = head; 
		// First, apply f to this node's number field to get the new node's
		// number field.
		for(int i = 0; i < len; i++) {
			result.set(f.apply(pos.getNum()),i); 
			pos = pos.getNext();
		}
		// Create and return the new list node.
		return result; 
	}

	// done
	public int reduce(IntegerBinaryFunction op, int dft) {
		int result = 0;
		NumberNode current = head;
		for(int i =0; i < len; i++) {
			result = op.apply(result, current.getNum());
			current = current.getNext();
		}
		result = result + dft;
		return result;
	}
	// done
	public void set(int value, int index) {
		if ( index < 0 || index > len) {
			System.out.println("Error!");
		}
		else if (index == 0) {
			head.num = value; 
		}
		else {
			NumberNode current = getNodeBefore(index).getNext();
			current.num = value;
		}
		

	}

	static class NumberNode {

		public int num;
		private NumberNode next;

		public NumberNode(int num, NumberNode next) {
			this.num = num;
			this.next = next;
		}

		public NumberNode(int num) {
			this(num, null);
		}

		public int get(int n) {
			NumberNode pos = this;
			for (int i = 0; i < n; i++) {
				pos = pos.getNext();
				if (pos == null) {
					System.out.println("Threshold Reached");
					return pos.getNum();
				}
			}
			return pos.getNum();
		}

		public NumberNode setNext(NumberNode set) {
			this.next = set; 
			return this.next;
		}

		public int lengthHelper() {
			if (next == null) {
				return 1;
			} else {
				return 1 + getNext().lengthHelper();
			}
		}

		public int getHelper(int i) {
			if (i == 0) {
				return getNum();
			} else {
				// check that getNext() is not null
				return getNext().getHelper(i - 1);
			}
		}

		public void printList(NumberNode n) {
			System.out.print("[");
			int i = 0;
			for (NumberNode pos = n; pos != null; pos = pos.getNext()) {
				if (i == 0) {
					System.out.print(pos.getNum());

				} else {
					System.out.print("," + pos.getNum());
				}
				i++;

			}
			System.out.println("]");
		}

		public int getNum() {
			int x = 0;
			for (NumberNode pos = this; pos != null; pos = pos.getNext()) {
				x = pos.num;
				return x;
			}
			return x;

		}

		public NumberNode getNext() {
			NumberNode pos = this;
			return pos.next;
		}

		public void insertAfter(int value) {

			NumberNode pos = this;
			if (pos == null) {
				return;
			}
			NumberNode newPos = new NumberNode(value);
			newPos.next = pos.next;
			pos.next = newPos;

		}

		public NumberNode reverse() {

			// This is the for loop version.
			NumberNode currentNode = this;
			NumberNode previousNode = null;
			NumberNode nextNode = null;

			while (currentNode != null) {
				nextNode = currentNode.next;
				currentNode.next = previousNode;
				previousNode = currentNode;
				currentNode = nextNode;
			}
			return previousNode;

		}

		// This is the recursive method.
		public NumberNode reverseRecursive() {
			NumberNode node = this;
			if (node == null || node.next == null) {
				return node;
			}

			NumberNode remaining = node.next.reverseRecursive();
			node.next.next = node;
			node.next = null;
			return remaining;

		}

		/**
		 * public NumberNode reverse(){
		 * 
		 * return revHelp(null); } This is the helper method public NumberNode
		 * revHelp(NumberNode prev){ final NumberNode oldNext = getNext(); Need to
		 * create setNext() method. setNext(prev); if( oldNextt == null){ return this; }
		 * else{ return oldNext.revHelp(this); } }
		 * 
		 * @return
		 */

		public NumberNode newReversed() {
			return revHelp(null);
		}

		public NumberNode revHelp(NumberNode acc) {
			// The value of acc is the new next point, so in the beginning, we pass in null.
			// This makes the first node we create the last
			// in the list.
			final NumberNode newAcc = new NumberNode(getNum(), acc);
			NumberNode ourNext = getNext();
			if (ourNext == null) {
				return newAcc;
			}
			else
				return ourNext.revHelp(newAcc);
		}

		public static void main(String[] arg) {
			NumberList list = new NumberList();
			list.add(1, 0);
			list.add(2, 1);
			list.add(3, 2);
			list.add(4, 3);

			NumberList single = new NumberList();
			single.add(200, 0);
			
			NumberList many = new NumberList();
			many.add(1, 0);
			many.add(2, 1);
			many.add(3, 2);
			many.add(4, 3);
			many.add(5, 4);
			many.add(6, 5);
			many.add(7, 6);
			many.add(8, 7);
			many.add(9, 8);
			many.add(19, 9);
			many.add(30, 10);

			NumberList empty = new NumberList();
			
			NumberList outOfOrder = new NumberList();
			outOfOrder.add(100, 0);
			outOfOrder.add(1, 1);
			outOfOrder.add(41234, 2);
			outOfOrder.add(5, 3);
			outOfOrder.add(4, 4);
			outOfOrder.add(6, 5);
			outOfOrder.add(234, 6);
			outOfOrder.add(3, 7);


			System.out.println("This is a test of method toString, with NumberList list. Expect: {1,2,3,4}. Get: "
					+ list.toString());
			System.out.println("This is a test of method to String, with NumberList single. Expect: {200}. Get: "
					+ single.toString());
			System.out.println(
					"This is a test of method to String, with NumberList many. Expect: {1,2,3,4,5,6,7,8,9,19,30}. Get: "
							+ many.toString());
			System.out.println(
					"This is a test of method toString, with NumberList empty. Expect: {}. Get: " + empty.toString());
			System.out.println(
					"----------------------------------------------------------------------------------------------------");
			System.out
					.println("This is a test of method indexOf, with NumberList list, and value 100. Expect: -1. Get: "
							+ list.indexOf(100));
			System.out
					.println("This is a test of method indexOf, with NumberList single, and value 200. Expect: 0. Get "
							+ single.indexOf(200));
			System.out.println("This is a test of method indexOf, with NumberList many, and value 19. Expect: 9. Get: "
					+ many.indexOf(19));
			System.out
					.println("This is a test of method indexOf, with NumberList empty, and value 20. Expect: -1. Get: "
							+ empty.indexOf(20));
			System.out.println(
					"----------------------------------------------------------------------------------------------------");
			System.out.print(
					"This is a test of method add, with NumberList list, and adding 5, at index 3.  Expect: {1,2,3,5,4}. Get: ");
			list.add(5, 2);
			System.out.println(list.toString());
//			System.out.print(
//					"This is a test of method add, with NumberList single, and adding 1, at index 0.  Expect: {1,200}. Get: ");
//			single.add(1, 0);
			System.out.println(single.toString());
			System.out.print(
					"This is a test of method add, with NumberList many, and adding 1000, at index 5.  Expect: {1,2,3,4,5,1000,6,7,8,9,19,30}. Get: ");
			many.add(1000, 5);
			System.out.println(many.toString());
			System.out.print(
					"This is a test of method add, with NumberList empty, and adding 1000, at index 0. Expect: {1000}. Get: ");
			empty.add(1000, 0);
			System.out.println(empty.toString());
			System.out.println(
					"----------------------------------------------------------------------------------------------------");
			System.out.print(
					"This is a test of method remove, with NumberList list, and removing index 3. Expect: {1,2,3,4}. Get: ");
			list.remove(2);
			System.out.println(list.toString());
			System.out.print(
					"This is a test of method remove, with NumberList single, and removing index 0. Expect: {}. Get: ");
			single.remove(0);
			System.out.println(single.toString());
			System.out.print(
					"This is a test of method remove, with NumberList many, and removing index 5. Expect: {1,2,3,4,5,6,7,8,9,19,30}. Get: ");
			many.remove(5);
			System.out.println(many.toString());
			System.out.print(
					"This is a test of method remove, with NumberList empty, and removing index 2. Expect: Exception. Get: ");
			empty.remove(2);
			System.out.println(empty.toString());
			System.out.println(
					"----------------------------------------------------------------------------------------------------");
			System.out.println(
					"This is a test of method get, with NumberList list, with index 1. Expect: 2. Get: " + list.get(1));
			System.out.println("This is a test of method get, with NumberList single, with index 0. Expect: 200. Get: "
					+ single.get(0));
			System.out.println(
					"This is a test of method get, with NumberList many, with index 3. Expect: 4. Get: " + many.get(3));
			System.out.println("This is a test of method get, with NumberList empty, with index 1000. Epect: 0. Get: "
					+ empty.get(0));
			System.out.println(
					"----------------------------------------------------------------------------------------------------");
			System.out.print(
					"This is a test of method set, with NumberList list, and value 100, index 2. Expect: {1,2,100,4}. Get: ");
			list.set(100, 2);
			System.out.println(list.toString());
			System.out.print( 
					"This is a test of method set, with NumberList single, and value 20, index 1. Expect: Error!. Get: ");
			single.set(20, 1);
			System.out.println(single.toString());
			System.out.print(
					"This is a test of method set, with NumberList many, and value 0, index 2. Expect: {1,2,0,4,5,6,7,8,9,19,30}. Get: ");
			many.set(0, 2);
			System.out.println(many.toString());
			System.out.print(
					"This is a test of method set, with NumberList empty, and value 20, index 4. Expect: Error!. Get: ");
			empty.set(20, 4);
			System.out.println(empty.toString());
			System.out.println(
					"----------------------------------------------------------------------------------------------------");
			System.out.println("This is a test of method reverse, with NumberList list. Expect: {4,100,2,1}. Get: " + list.reverse().toString());
			System.out.println("This is a test of method reverse, with NumberList single. Expect: Empty. Get" + single.reverse().toString());
			System.out.println("This is a test of method reverse, with NumberList many. Expect: {30,19,9,8,7,6,5,4,3,2,1}. Get: " + many.reverse().toString());
			System.out.println(
					"----------------------------------------------------------------------------------------------------");
			System.out.println("This is a test of method map, with NumberList list and function +. Expect: {2,4,200,8}. Get: " + list.map(doubleN));
			System.out.println(
					"----------------------------------------------------------------------------------------------------");
			System.out.println("This is a test of method reduce, with NumberList list and z=1. Expect: 215. Get: " + list.reduce(addition, 1));
			System.out.println(
					"----------------------------------------------------------------------------------------------------");
			System.out.print("This is a test of method sort, with NumberList outOfOrder. Expect: {1,2,5,100}. Get: ");
			outOfOrder.sort();
			System.out.println(outOfOrder.toString());
		}

	}
}
