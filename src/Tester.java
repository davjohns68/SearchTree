/* CS211 final Exam, December 2017
 * W.P. Iverson, instructor
 */
public class Tester {

	public static void main(String[] args) throws Exception {

		// Build the Search Tree from Summer 2017 exam
		SearchTree<Integer> test = new SearchTree<>();
                SearchTree<Integer> testEmpty = new SearchTree<>();
                SearchTree<Integer> testOne = new SearchTree<>();
		test.add(23); test.add(42); test.add(13); test.add(17); 
		test.add(99); test.add(62); test.add(11); test.add(20); test.add(30);
                testOne.add(23);
		test.showTree(); // maybe a better print (use System.out)
		System.out.print("\n#2 How many nodes: "); // return int
		System.out.println(test.countNodes());
		System.out.print("\n#2a How many nodes (Empty list): "); // return int
		System.out.println(testEmpty.countNodes());
		System.out.print("\n#2b How many nodes (Single entry list): "); // return int
		System.out.println(testOne.countNodes());
		System.out.print("\n#3 How many levels: "); // return int
		System.out.println(test.countLevels());
		System.out.print("\n#3 How many levels (Empty list): "); // return int
		System.out.println(testEmpty.countLevels());
		System.out.print("\n#3 How many levels (Single entry list): "); // return int
		System.out.println(testOne.countLevels());
		System.out.print("\n#4 Return all data on leaves: "); // return List
		System.out.println(test.returnLeaves());
		System.out.print("\n#4 Return all data on leaves (Empty list): "); // return List
		System.out.println(testEmpty.returnLeaves());
		System.out.print("\n#4 Return all data on leaves (Single entry list): "); // return List
		System.out.println(testOne.returnLeaves());
		System.out.print("\n#5 Return data on level zero (root): "); // return List
		System.out.println(test.returnLevel(0));
		System.out.print("\n#5b Return data on level 2: "); // return List
		System.out.println(test.returnLevel(2));
		System.out.print("\n#5b Return data on level 3: "); // return List
		System.out.println(test.returnLevel(3));
        
	}

}
