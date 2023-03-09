public class SortedLinkedList implements SortedList
{
	private Node head;
	private String order;
	
	public SortedLinkedList()
	{
		this.head = null;
		this.order = "asc";
	}
	
	public int size()
	{
		if (this.head == null)
		{
			return 0;
		}
		
		Node current = this.head;
		int size = 1;
		while (current.getNext() != null)
		{
			current = current.getNext();
			size++;
		}
		return size;
	}

	
	public void add(String string)
	{
		if (string ==  null || string == "")
		{
			return;
		}
		for (int i = 0; i < string.length(); i++)
		{
			char letter = string.charAt(i);
			if (!Character.isLetter(letter))
			{
				return;
			}
		}
		
		if (size() == 0)
		{
			Node newNode = new Node(string);
			add(newNode);
		}
		
		Node current = this.head;
		String currentStr = (current.getString()).toLowerCase();
		if (currentStr.compareTo(string.toLowerCase()) == 0)
		{
			return;
		}
		while (current.getNext() != null)
		{
			current = current.getNext();
			currentStr = (current.getString()).toLowerCase();
			if (currentStr.compareTo(string.toLowerCase()) == 0)
			{
				return;
			}
		}

		Node newNode = new Node(string);
		add(newNode);
		
	}

	
	public void add(Node node)
	{
		if (size() == 0)
		{
			this.head = node;
		}
		else
		{
			node.setNext(this.head);
			head.setPrev(node);
			this.head = node;
			if (order == "desc")
			{
				orderDescending();
			}
			else
			{
				orderAscending();
			}
		}
	}

	
	public Node getFirst()
	{
		return this.head;
	}

	
	public Node getLast()
	{
		if (size() == 0)
		{
			return null;
		}
		
		Node current = this.head;
		while (current.getNext() != null)
		{
			current = current.getNext();
		}
		return current;
		
	}

	
	public Node get(int index)
	{
		if (index > size() - 1 || index < 0)
		{
			return null;
		}
		int counter = 0;
		Node current = this.head;
		while (counter < index && current.getNext() != null)
		{
			current = current.getNext();
			counter++;
		}
		return current;
	}

	
	public boolean isPresent(String string)
	{
		if (size() == 0)
		{
			return false;
		}
		
		Node current = this.head;
		if (current.getString() == string)
		{
			return true;
		}
		
		while (current.getNext() != null)
		{
			current = current.getNext();
			if (current.getString() == string)
			{
				return true;
			}
		}
		return false;
		
	}

	
	public boolean removeFirst()
	{
		if (size() == 0)
		{
			return false;
		}
		
		Node temp = this.head.getNext();
		if (temp != null)
		{
			temp.setPrev(null);
		}
		this.head = temp;
		return true;
		
	}

	
	public boolean removeLast()
	{
		if (size() == 0)
		{
			return false;
		}
		
		Node current = this.head;
		while (current.getNext() != null)
		{
			current = current.getNext();
		}
		
		Node temp = current.getPrev();
		if (temp != null)
		{
			temp.setNext(null);
		}
		return true;
	}
	

	
	public boolean remove(int index)
	{
		if (index > size() - 1 || index < 0)
		{
			return false;
		}
		if (index == 0)
		{
			return removeFirst();
		}
		int counter = 0;
		Node current = this.head;
		while (counter < index && current.getNext() != null)
		{
			current = current.getNext();
			counter++;
		}
		Node before = current.getPrev();
		Node after = current.getNext();
		if (before != null)
		{
			before.setNext(after);
		}
		if (after != null)
		{
			after.setPrev(before);
		}
		return true;
	}

	
	public boolean remove(String string)
	{
		if (size() == 0 || !isPresent(string))
		{
			return false;
		}
		if (this.head.getString() == string)
		{
			return removeFirst();
		}
		Node current = this.head;
		while (current.getString() != string)
		{
			current = current.getNext();
		}
		Node before = current.getPrev();
		Node after = current.getNext();
		if (before != null)
		{
			before.setNext(after);
		}
		if (after != null)
		{
			after.setPrev(before);
		}
		return true;
	}

	
	public void orderAscending()
	{
		int size = size();
		this.order = "asc";
		if (size == 0 || size == 1)
		{
			return;
		}
		sort();
		
		
	}

	
	public void orderDescending()
	{
		int size = size();
		this.order = "desc";
		if (size == 0 || size == 1)
		{
			return;
		}
		sort();
	}

	
	public void print()
	{
		if (size() == 0)
		{
			return;
		}
		Node current = this.head;
		System.out.println(current.getString());
		while (current.getNext() != null)
		{
			current = current.getNext();
			System.out.println(current.getString());
		}
	}
	
	
	public void sort()
	{
		int size = size();
		int swaps = -1;
		Node current = this.head;
		Node next = this.head.getNext();
		while (swaps != 0)
		{
			swaps = 0;
			
			for (int i = 0; i < size - 1; i++)
			{
				if (Compare(current, next) == 2)
				{
					swaps++;
					Node temp;
					temp = current.getPrev();
					current.setPrev(next);
					next.setPrev(temp);
					temp = next.getNext();
					next.setNext(current);
					current.setNext(temp);
					temp = current;
					current = next;
					next = temp;
					
					if (current.getPrev() != null)
					{
						Node before = current.getPrev();
						before.setNext(current);
					}
					if (next.getNext() != null)
					{
						Node after = next.getNext();
						after.setPrev(next);
					}
					
					
					if (i == 0)
					{
						this.head = current;
					}
				}
				current = next;
				next = next.getNext();
			}
			
			
			current = this.head;
			next = this.head.getNext();
		}
		
		
	}
	
	
	public int Compare(Node nodeOne, Node nodeTwo)
	{
		String one = (nodeOne.getString()).toLowerCase();
		String two = (nodeTwo.getString()).toLowerCase();
		int compare = one.compareTo(two);
		if (this.order == "desc")
		{
			compare *= -1;
		}
		
		if (compare <= 0)
		{
			return 1;
		}
		else
		{
			return 2;
		}
	}

	
	
	public static void main(String[] args) 
	{
		SortedLinkedList list = new SortedLinkedList();
		list.add("he");
		list.add("ha");
		list.add("hu");
		list.print();
		System.out.println();
		list.orderDescending();
		list.add("hh");
		list.print();
		System.out.println();
		list.orderAscending();
		list.print();
		System.out.println();
		System.out.println((list.remove("hg")));
		list.print();
		System.out.println();
		list.add("HES");
		list.print();
		System.out.println();
		list.add("  h");
		list.print();
	}

}
