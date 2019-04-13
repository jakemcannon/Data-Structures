import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.*;


public class hangman {


    public static HashMap<Integer, ArrayList<String>> getInitialWordLists() throws FileNotFoundException {
        String fileName = "words.txt";
        Scanner in = new Scanner(new File(fileName));
        HashMap<Integer, ArrayList<String>> map = new HashMap<>();

        while (in.hasNextLine()) {
            String line = in.nextLine();
            int length  = line.length();
            if (!map.containsKey(length)) {
                ArrayList<String> s = new ArrayList<>();
                s.add(line);
                map.put(length, s);
            } else {
                ArrayList<String> temp = map.get(length);
                temp.add(line);
                map.put(length, temp);
            }
        }
        return map;
    }

    public static String createHiddenKey(String s, HashSet<Character> guessedLetters) {
        String toReturn = "";
        String x = s.toLowerCase();
        for (int i = 0; i < x.length(); i++) {
            if (!guessedLetters.contains(x.charAt(i))) {
                toReturn += "_";
            } else {
                toReturn += x.charAt(i);
            }
        }
        return toReturn;
    }

    // Takes in
    // Set of guesses letters
    // An ArrayList of "word list"
    // Returns a HashMap of <String, List<String>>
    // I think the hashmap is like <_a__, List<All words that look like _a__ but not missing characters>
    public static HashMap<String, List<String>> generateWordFamalies(HashSet<Character> guessedLetters, List<String> wordList){

        HashMap<String, List<String>> wordFamalies = new HashMap<>();
        for (String word: wordList) {
            String hidden = createHiddenKey(word, guessedLetters);
            if (!wordFamalies.containsKey(hidden)) {
                List<String> listForWordFam = new ArrayList<>();
                listForWordFam.add(word);
                wordFamalies.put(hidden, listForWordFam);
            } else {
                List<String> temp = wordFamalies.get(hidden);
                temp.add(word);
                wordFamalies.put(hidden, temp);
            }
        }
        return wordFamalies;
    }

    ///Iterate through the hashmap and get the list with the greatest size
    public static List<String> getNewWordList(HashMap<String, List<String>> wordFamilies, HashSet<Character> guessedLetters) {
        int max = 0;
        List<String> newWordList = new ArrayList<>();

        for (String word : wordFamilies.keySet()) {
            List<String> temp = wordFamilies.get(word);
            int length = temp.size();
            if (length == 1 && checkSet(guessedLetters, temp.get(0))) {
                System.out.println(checkSet(guessedLetters, temp.get(0)));
                newWordList = temp;
            }
            if (length > max) {
                max = length;
                newWordList = temp;
            }
        }
        return newWordList;
    }

    //currently word I am getting is in ee_ form not eel
    public static boolean checkSet(HashSet<Character> guessedLetters, String word) {

        for (Character c: word.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    public static String getHiddenKey(HashMap<String, List<String>> wordFamilies, HashSet<Character> guessedLetters) {
        int max = 0;
        String hidden = "";

        for (String word : wordFamilies.keySet()) {
            List<String> temp = wordFamilies.get(word);
            int length = temp.size();

            if (length == 1 && checkSet(guessedLetters, temp.get(0))) {
                hidden = temp.get(0);

            } else if (length > max) {
                hidden = word;
                max = length;
            }
        }
        return hidden;
    }

    public static String getRandomWord(List<String> wordlist) {
        Random random = new Random();
        return wordlist.get(random.nextInt(wordlist.size()));
    }

    public static boolean gameWon(List<String> wordList, HashSet<Character> guessedLetters) {

        if (wordList.size() == 1) {
            System.out.println("WORDLIST FROM GAMEWON METHOD " + wordList);
            String word = getHiddenKey(generateWordFamalies(guessedLetters, wordList), guessedLetters);
            for (Character c : word.toCharArray()) {
                if (guessedLetters.contains(c)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void gameLogic() throws FileNotFoundException {
        HashSet<Character> guessedLetters = new HashSet<>();
        boolean won = false;
        String playAgain = "";
        getInitialWordLists();

        //Initial word length and number of guesses
        Scanner userInput = new Scanner(System.in);
        System.out.println("Choose a word size!");
        int userWordLength = userInput.nextInt();
        System.out.println("Choose your number of guesses: ");
        int numberOfGuesses = userInput.nextInt();

        //Create dict copy of initial word length with length n, load into an Arraylist
        Map<Integer, ArrayList<String>> wordListCopy = getInitialWordLists();
        List<String> wordList = new ArrayList<>();
        for (String word: wordListCopy.get(userWordLength)) {
            wordList.add(word);
        }

        System.out.println("Word list: " + wordList);
        int guessCounter = 0;

        while (guessCounter < numberOfGuesses) {
            System.out.println("Guess a letter: ");
            char guess = userInput.next().charAt(0);
            if(guessedLetters.contains(guess)) {
                System.out.println("You have already made that guess, please make a new guess! ");
                guess = userInput.next().charAt(0);
                guessedLetters.add(guess);
            }
            guessedLetters.add(guess); //add letter to the set of guessed letters
            generateWordFamalies(guessedLetters, wordList); // generate word families

            System.out.println("Word families: " + generateWordFamalies(guessedLetters, wordList));
            System.out.println("New word list is: " + getNewWordList(generateWordFamalies(guessedLetters, wordList), guessedLetters));
            String hiddenKey = getHiddenKey(generateWordFamalies(guessedLetters, wordList), guessedLetters);
            if (hiddenKey.contains("_")) {
                System.out.println("Word is " + hiddenKey);
            } else if(!hiddenKey.contains("_")) {
                won = true;
                System.out.println("You Won! " + hiddenKey);
                break;
            }

            System.out.println("You have " + (numberOfGuesses - guessCounter) + " guesses left");
            System.out.println("You have currently guessed " + guessedLetters);
            System.out.println(" ");

            wordList = getNewWordList(generateWordFamalies(guessedLetters, wordList), guessedLetters);
            guessCounter ++;
        }

        if (!won) {
            String randomWord = getRandomWord(wordList);
            System.out.println("Out of guesses! The word was: " + randomWord);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner userInput = new Scanner(System.in);
        String playAgain = "y";
        do {
            gameLogic();
            System.out.println("Would you like to play another game? Enter 'y' for yes or 'n' for no: ");
            playAgain = userInput.next();
        } while (!playAgain.equals("n"));

    }
}
