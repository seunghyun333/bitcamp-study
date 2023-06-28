package bitcamp.util;



public class LinkedList implements List {
	Node head;
	Node tail;
	int size;
	
	
	static void print(LinkedList list) {
		Object[] arr = list.toArray();
		for (Object obj: arr) {
			System.out.print(obj);
			System.out.print(", ");
		}
		System.out.println();
	}
	
	@Override
	public boolean add(Object value) {
		Node node = new Node();
		node.value = value;
		
		if(head==null) {
			head = node;
		} else if (this.tail != null) {
			this.tail.next = node;
		}
		
		this.tail =node;
		this.size++;
		return true;
	}
	
	@Override
	public Object[] toArray() {
		Object[] arr = new Object[this.size];
		
		Node cursor = this.head;
		for (int i = 0; i < this.size; i++){
			arr[i] = cursor.value;
			cursor = cursor.next;
		}
		return arr;
	}
	
	@Override
	public Object get(int index) {
			if (!isValid(index)) {
				return null;
			}
			Node cursor = this.head;
		
		for (int i =0; i < index; i++) {
			cursor = cursor.next;
		}
		return cursor.value;
	}

	
	public boolean remove(Object value) {
		Node prev = null;
		Node cursor = this.head;
		
		while (cursor != null) {
			if(cursor.value.equals(value)) {
				if ( prev == null) {
					head = cursor.next;
					
					if (head == null) {
						tail = null;
					}
				} else if (cursor.next == null) {
					tail = prev;
					tail.next = null;
				} else {
					prev.next = cursor.next;
				}
				size--;
				cursor.next = null;
				cursor.value = null;
				return true;
			}
			prev = cursor;
			cursor = cursor.next;
		}
		return false;
	}
	
	public Object remove(int index) {
		if(!isValid(index)) {
			return null;
		}
		
		Node prev = null;
		Node cursor = this.head;
		
		for(int i =0; i < index; i++) {
			prev = cursor;
			cursor = cursor.next;
		}
		Object old = cursor.value;
		
		if (prev == null ) {
			head = cursor.next;
			if (head == null ) {
				tail = null;
			}
		} else if(cursor.next == null) {
			tail = prev;
			tail.next = null;
		} else {
			prev.next = cursor.next;
		}
		size--;
		cursor.next = null;
		cursor.value = null;
		
		return old;
	}
	
	public int size() {
		return this.size;
	}
	
	private boolean isValid(int index) {
		 return index >= 0 && index < this.size;
}
	 static class Node {
		    Object value;
		    Node next;
		  }
	 

}