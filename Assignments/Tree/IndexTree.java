import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Your class. Notice how it has no generics.
// This is because we use generics when we have no idea what kind of data we are getting
// Here we know we are getting two pieces of data:  a string and a line number
public class IndexTree {
	private IndexNode root;
	
	// Make your constructor
	// It doesn't need to do anything
    public  IndexTree() {

    }
	
	// complete the methods below
	
	// this is your wrapper method
	// it takes in two pieces of data rather than one
	// call your recursive add method
	public void add(String word, int lineNumber){
		this.root = add(this.root, word, lineNumber);
	}
	// your recursive method for add
	// Think about how this is slightly different the the regular add method
	// When you add the word to the index, if it already exists, 
	// you want to  add it to the IndexNode that already exists
	// otherwise make a new indexNode
	private IndexNode add(IndexNode root, String word, int lineNumber){
		if(root == null) {
		    return new IndexNode(word, lineNumber);
        }
        int compareResult = word.compareToIgnoreCase(root.word);
//        System.out.println("root.word is " + root.word);
//        System.out.println("Testing this method: " + word + " compareTo " + root.word);

        if (compareResult == 0) {
            root.occurences += 1;
            root.list.add(lineNumber);
            return root;
        } else if (compareResult < 0) { //this means the word is BEFORE the current local root node
            root.left = add(root.left, word, lineNumber);
            return root;
        } else {//this is compareResult > 0
            root.right = add(root.right, word, lineNumber);
            return root;
        }
	}
	
	public boolean contains(String word) {
	    return contains(this.root, word);
    }
	
	
	// returns true if the word is in the index
	public boolean contains(IndexNode root, String word){
	    if (root == null) {
	        return false;
        } else {
            int compareTo = word.compareToIgnoreCase(root.word);
            if (compareTo == 0) {
                return true;
            } else if (compareTo < 0) {
                return contains(root.left, word);
            } else {
                return contains(root.right, word);
            }
        }
	}
	
	// call your recursive method
	// use book as guide
	public void delete(String word){
		this.root = delete(this.root, word);
	}
	
	// your recursive case
	// remove the word and all the entries for the word
	// This should be no different than the regular technique.
	private IndexNode delete(IndexNode root, String word){
		if (root == null) {
		    return null;
        }
        int compareResult = word.compareToIgnoreCase(root.word);
		if (compareResult < 0) {
		    root.left = delete(root.left, word);
		    return root;
        } else if (compareResult > 0) {
		    root.right = delete(root.right, word);
		    return root;
        } else { //root is the item we want to delete

		    //case 1, root is a leaf
            if(root.left == null && root.right == null) {
                return null;
            } else if (root.left != null && root.right == null) { //case 2, root has only one child
                return root.left;
            } else if (root.left == null && root.right != null) {
                return root.right;
            } else {
                IndexNode rootOfLeftSubtree = root.left;
                IndexNode parentOfPredecessor = root.right;
                IndexNode predecessor = null;

                if (rootOfLeftSubtree.right == null) {
                    root.word = rootOfLeftSubtree.word;
                    root.left = rootOfLeftSubtree.left;
                    return root;
                } else {
                    parentOfPredecessor = rootOfLeftSubtree;
                    IndexNode current = rootOfLeftSubtree.right;
                    while (current.right != null) {
                        parentOfPredecessor = current;
                        current = current.right;
                    }
                    predecessor = current;
                    root.word = predecessor.word;
                    parentOfPredecessor.right = predecessor.left;
                    return root;
                }
            }
        }
	}

    private void printIndex(IndexNode root) {
        if (root != null) {
            printIndex(root.left);
            System.out.println(root);
            printIndex(root.right);
        }
    }
	
	public static void main(String[] args){

		IndexTree myTree = new IndexTree();

        // add all the words to the tree
        String fileName = "pg100.txt";
        try {
            Scanner scanner = new Scanner(new File(fileName));
            int lineNumber = 0;
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                lineNumber ++;
                if (line.length() == 0) {
                    continue;
                }
                String[] words = line.split("\\s+");
                for(String word : words){
                    word = word.replaceAll("\\s", "");
                    word = word.replaceAll(":", "");
                    word = word.replaceAll(",", "");
                    word = word.replaceAll("'", "");
                    word = word.replaceAll("\\.", "");
                    myTree.add(word, lineNumber);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

		// print out the index
        myTree.printIndex(myTree.root);
		
		// test removing a word from the index
        myTree.delete("zwaggerd");
	}
}
