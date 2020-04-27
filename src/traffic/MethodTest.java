package traffic;

import java.util.LinkedList;
import java.util.Queue;

public class MethodTest {

	public static void main(String[] args) {
		Queue<Integer> q = new LinkedList<>();

		// Adds elements {0, 1, 2, 3, 4} to queue
		for (int i = 0; i < 5; i++)
			q.add(i);

		// Display contents of the queue.
		System.out.println("Elements of queue-" + q);

		// To remove the head of queue.
		int removedele = q.remove();
		System.out.println("removed element-" + removedele);

		System.out.println(q);

		// To view the head of queue
		int head = q.element();
		System.out.println("head of queue-" + head);
		q.poll();
		
		q.add(8);
		
		// To view the head of queue
		System.out.println("head of queue-" + q.element());
		
		// Display contents of the queue.
		System.out.println("Elements of queue-" + q);

		// Rest all methods of collection interface,
		// Like size and contains can be used with this
		// implementation.
		int size = q.size();
		System.out.println("Size of queue-" + size);
	}

}
