import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {	
	
	/**
	 * A method for recursively search for a String in an array of Strings
	 * @param word - The word of type String that we are looking for.
	 * @param words - The String array containing all the words
	 * @param lo - The lower bound for the search
	 * @param hi - The upper bound for the search
	 * @return the index of the found word in the words array
	 * (10 marks) ********************************************
	 */
	public static int recursiveBinarySearch(String word, String[] words, int lo, int hi){		
		//TODO: Complete
		if (lo > hi)
            return -1;

        int mid = lo + (hi - lo) / 2;
        int cmp = word.compareTo(words[mid]);

        if (cmp < 0)
            return recursiveBinarySearch(word, words, lo, mid - 1);
        else if (cmp > 0)
            return recursiveBinarySearch(word, words, mid + 1, hi);
        else
            return mid;
	}
	
	/**
	 * @param word - The String which its characters will be shuffled around
	 * @return The shuffled String
	 * (5 marks) ********************************************
	 */
	public static String mixCharacterOrder(String word){	
		//TODO: Complete	
		char[] chars = word.toCharArray();
        Random rnd = new Random();
        for (int i = chars.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            char temp = chars[index];
            chars[index] = chars[i];
            chars[i] = temp;
        }
        return new String(chars);
	}
	/**
	 * The conundrum solver that uses the array dictionary, mixCharacterOrder 
	 * and recursive binary search.
	 * @param conundrum - The current conundrum that needs to be solved.
	 * @param words - The String array containing all the dictionary words
	 * @return A valid word that can be found in the conundrum
	 * (5 marks) ********************************************
	 */
	public static String solveConundrum1(String conundrum, String[] words){				
		//TODO: Complete		
		conundrum = mixCharacterOrder(conundrum);
        Arrays.sort(words);
        int index = recursiveBinarySearch(conundrum, words, 0, words.length - 1);
        return index != -1 ? words[index] : "No valid word found";
	}
	
	/**
	 * The conundrum solver that uses the DList dictionary, mixCharacterOrder 
	 * and the DList search.
	 * @param conundrum - The current conundrum that needs to be solved.
	 * @param words - The String array containing all the dictionary words
	 * @return A valid word that can be found in the conundrum
	 */
	public static String solveConundrum2(String conundrum, DList<String> words){		
		while(words.search(conundrum) == null){
			conundrum = mixCharacterOrder(conundrum);
		}
		return conundrum;				
	}
	/**
	 * @param path - The location of the dictionary
	 * @return The String array of words  containing all the dictionary words
	 * (5 marks) ********************************************
	 */
	public static String[] loadPotentialDicitonary1(String path){
		//TODO: Complete	
		//path="C:\\Users\\General\\Downloads\\PRC3\\src";
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.lines().toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
	}	
	
	/**
	 * @param path - The location of the dictionary
	 * @return The String array of words  containing all the dictionary words
	 */
	public static DList<String> loadPotentialDictionary2(String path){	
		//path="C:\\Users\\General\\Downloads\\PRC3\\src";
		DList<String> wordsList = new DList<String>();
		File f = new File(path);		
		 
		try {
			Scanner s = new Scanner(f);
			
			while(s.hasNextLine()){
				wordsList.addLast(s.nextLine());					
			}			
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		System.out.println(wordsList.size()+ " entries loaded");
		return wordsList;
	}
	/*
	 * The main method
	 * //(10 marks for execution) ********************************************
	 */
	public static void main(String[] args) {
		final String PATH ="C:\\Users\\General\\Downloads\\PRC3\\src\\test.dict";; 
		String conundrum = "ionlitabo";
		
		System.out.println("Dictionary Load 1 begin");
		long startTime = System.currentTimeMillis();
		String[] words = loadPotentialDicitonary1(PATH);
		long endTime = System.currentTimeMillis();
		double totalTime = (endTime - startTime)/1000.0;
		System.out.println("Dictionary Load 1 completed in "+ totalTime+" seconds");
		
		System.out.println("Dictionary Load 2 begin");
		startTime = System.currentTimeMillis();
		DList<String> wordList = loadPotentialDictionary2(PATH);
		endTime = System.currentTimeMillis();
		totalTime = (endTime - startTime)/1000.0;
		System.out.println("Dictionary Load 2 completed in "+ totalTime+" seconds");
		
		System.out.println("Algorithm 1 Test begin");
		startTime = System.currentTimeMillis();
		//System.out.println("The found word is: "+solveConundrum1(conundrum, words));
		endTime = System.currentTimeMillis();
		totalTime = (endTime - startTime)/1000.0;
		System.out.println("Algorithm 1 Test completed in "+ totalTime+" seconds");
		
		System.out.println("Algorithm 2 Test begin");
		startTime = System.currentTimeMillis();
		System.out.println("The found word is: "+solveConundrum2(conundrum, wordList));
		endTime = System.currentTimeMillis();
		totalTime = (endTime - startTime)/1000.0;
		System.out.println("Algorithm 2 Test completed in "+ totalTime+" seconds");
	}

}
