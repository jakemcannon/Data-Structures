import java.util.ArrayList;
import java.util.List;

public class IndexNode  {
	String word;
	int occurences;
	List<Integer> list;

	IndexNode left;
	IndexNode right;

	// Constructor should take in a word and a line number
	// it should initialize the list and set occurrences to 1
	public IndexNode (String word, int lineNumber) {
		this.word = word;
		occurences += 1;
		list = new ArrayList<>();
		list.add(lineNumber);
	}

	// Complete This
	// return the word, the number of occurrences, and the lines it appears on.
	// string must be one line
	public String toString(){

		String output = "";
		output += "Word: " + "'" + word + "' | ";
		output += "Number of occurences: " + occurences + " | ";
		output += "Lines:" ;
		output += list;
		return output;
	}
}
