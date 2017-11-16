
import java.util.Random;

// Basic tests for Chapter 17 Exercises
// upgraded into generic Search Tree use
// CS211, March 2017, W.P. Iverson, instructor
public class Post {

	public static void main(String[] args) {

		SearchTree<Double> empty = new SearchTree<>();
		SearchTree<Integer> test = new SearchTree<>();
                SearchTree<Integer> bigTree = new SearchTree<>();
		test.add(4);  test.add(12);
                Random rand = new Random();
                int removeMe = 0;
                for (int i = 0; i < 10; i++) {
                    removeMe = rand.nextInt(1000);
                    bigTree.add(removeMe);
                }
		
//		A.  Exercise #7   isFull();		
		System.out.println(test.isFull());	// false
		System.out.println(empty.isFull());	// true
                System.out.println(bigTree.isFull());   // false
		
//		B.  Exercise #9   equals(t2);		
		System.out.println(test.equals(test));	// true
		System.out.println(test.equals(empty)); // false
		
//		D.  Exercise #12    removeLeaves();
		empty.removeLeaves();	empty.print();	// nothing to print
		test.removeLeaves();	test.print();   // 4
                bigTree.print();
                System.out.println("----------------------");
                bigTree.removeLeaves();
                bigTree.print();
		
//		C.  remove process explained in detail via PowerPoint at BJP site		
		empty.remove(42.);  empty.print();	// nothing to print
		test.remove(4);  test.print(); // all gone
                System.out.println("----------------------");
                bigTree.remove(removeMe);
                bigTree.print();
	}
}
