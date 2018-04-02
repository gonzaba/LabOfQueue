package classes;

import interfaces.Queue;

/** 
   A partial implementation of the Queue using a singly linked list with references 
   to the first and to the last node.
**/
public class SLLQueue<E> implements Queue<E> {

	private static class Node<E> {   // Inner class for nodes. 
		private E element; 
		private Node<E> next; 
		public Node() { 
			element = null; 
			next = null; 
		}
		public Node(E data, Node<E> next) { 
			this.element = data; 
			this.next = next; 
		}
		public Node(E data)  { 
			this.element = data; 
			next = null; 
		}
		public E getElement() {
			return element;
		}
		public void setElement(E data) {
			this.element = data;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		}
		public void clean() { 
			element = null; 
			next = null; 
		}
	}	
	private Node<E> first, last;   // references to first and last node
	private int size; 
	
	public SLLQueue() {           // initializes instance as empty queue
		first = last = null; 
		size = 0; 
	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public E first() {
		if (isEmpty()) return null;
		return first.getElement(); 
	}
	
	public E dequeue() {
		if (isEmpty()) return null;		
		Node<E> ntr = first;
		first = first.getNext();
		if(size==1)
			last=first;
		size--;
		E etr = ntr.getElement();
		ntr.clean();
		return etr;
	}
	public void enqueue(E e) {
		if (size == 0) 
			first = last = new Node<>(e); 
		else { 
			Node<E> temp = new Node<E>(e);
			last.setNext(temp);
			last=temp;
		}
		size++; 
	}

}
