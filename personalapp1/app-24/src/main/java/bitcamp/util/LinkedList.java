package bitcamp.util;

import java.lang.reflect.Array;

public class LinkedList<E> implements List<E> {
	Node<E> head;
	Node<E> tail;
	int size;
	
	
	static void print(LinkedList<Integer> list) {
		Object[] arr = list.toArray();
		for (Object obj: arr) {
			System.out.print(obj);
			System.out.print(", ");
		}
		System.out.println();
	}
	
	@Override
	public boolean add(E value) {
		Node<E> node = new Node<>();
		node.value = value;
		
		if(this.head==null) {
			this.head = node;
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
		
		Node<E> cursor = this.head;
		for (int i = 0; i < this.size; i++){
			arr[i] = cursor.value;
			cursor = cursor.next;
		}
		return arr;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] arr) {
		T[] values = null;
		
		if(arr.length < this.size) {
			values = (T[]) Array.newInstance(arr.getClass().getComponentType(), this.size);
		} else {
		values = arr;
		}
		
		Node<E> cursor = this.head;
		for (int i = 0; i < this.size; i++) {
			arr[i] = (T) cursor.value;
			cursor = cursor.next;
		}
		return values;
	}
	
	@Override
	public E get(int index) {
			if (!isValid(index)) {
				return null;
			}
			Node<E> cursor = this.head;
		
		for (int i =0; i < index; i++) {
			cursor = cursor.next;
		}
		return cursor.value;
	}

	@Override
	public boolean remove(E value) {
		Node<E> prev = null;
		Node<E> cursor = this.head;
		
		while (cursor != null) {
			if(cursor.value.equals(value)) {
				if ( prev == null) {
					this.head = cursor.next;
					
					if (this.head == null) {
						this.tail = null;
					}
				} else if (cursor.next == null) {
					this.tail = prev;
					this.tail.next = null;
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
	
	@Override
	public E remove(int index) {
		if(!isValid(index)) {
			return null;
		}
		
		Node<E> prev = null;
		Node<E> cursor = this.head;
		
		for(int i =0; i < index; i++) {
			prev = cursor;
			cursor = cursor.next;
		}
		E old = cursor.value;
		
		if (prev == null ) {
			this.head = cursor.next;
			if (this.head == null ) {
				this.tail = null;
			}
		} else if(cursor.next == null) {
			this.tail = prev;
			this.tail.next = null;
		} else {
			prev.next = cursor.next;
		}
		this.size--;
		cursor.next = null;
		cursor.value = null;
		
		return old;
	}
	
	@Override
	public int size() {
		return this.size;
	}
	
	private boolean isValid(int index) {
		 return index >= 0 && index < this.size;
}
	 static class Node<T> {
		    T value;
		    Node<T> next;
		  }
	 

}