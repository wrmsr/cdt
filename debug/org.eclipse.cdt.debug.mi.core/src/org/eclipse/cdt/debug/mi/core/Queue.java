/*
 * (c) Copyright QNX Software Systems Ltd. 2002.
 * All Rights Reserved.
 */
package org.eclipse.cdt.debug.mi.core;
 
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Simple thread-safe Queue implemetation.
 */
public class Queue {

	protected List list;

	public Queue() {
		list = Collections.synchronizedList(new LinkedList());
	}

	public Object removeItem() throws InterruptedException {
		//print("in removeItem() - entering");
		synchronized (list) {
			while (list.isEmpty()) {
				//print("in removeItem() - about to wait()");
				list.wait();
				//print("in removeItem() - done with wait()");
			}

			// extract the new first cmd
			Object item = list.remove(0);

			//print("in removeItem() - leaving");
			return item;
		}
	}

	public void addItem(Object item) {
		//print("in addItem() - entering");
		synchronized (list) {
			// There will always be room to add to this List
			// because it expands as needed.
			list.add(item);
			//print("in addItem - just added: '" + cmd + "'");

			// After adding, notify any and all waiting
			// threads that the list has changed.
			list.notifyAll();
			//print("in addItem() - just notified");
		}
		//print("in addItem() - leaving");
	}

	public Object[] clearItems() {
		Object[] array;
		synchronized (list) {
			array = list.toArray();
			list.clear();
		}
		return array;
	}

//	private static void print(String msg) {
//		String name = Thread.currentThread().getName();
//		System.out.println(name + ": " + msg);
//	}
}
