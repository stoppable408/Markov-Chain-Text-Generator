
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class MarkovChain {

	public static String getString() {
		return markovChain(addFileToArray());
	}

	public static ArrayList<String> addFileToArray() {
		String line = null;
		String[] words = new String[100];
		ArrayList<String> arraylist = new ArrayList<String>();
		try {
			FileReader reader = new FileReader(
					"C:\\Users\\Solomon\\Documents\\GitHub\\Miscellaneous\\Directory\\Plato.txt");
			BufferedReader bufferedreader = new BufferedReader(reader);
			while ((line = bufferedreader.readLine()) != null) {
				words = line.split(" ");
				for (int j = 0; j < words.length; j++) {
					if (!words[j].equalsIgnoreCase(""))
						arraylist.add(words[j]);
				}
			}
			bufferedreader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return arraylist;
	}

	public static String markovChain(ArrayList<String> words) {
		ArrayList<String> finalSentence = new ArrayList<String>();
		StringBuilder sentence = new StringBuilder();
		int wordSeed;
		String firstSeed;
		String previousWord;
		Calendar calendar = Calendar.getInstance();
		long seed = calendar.getTimeInMillis();
		Random random = new Random(seed);
		HashMap<String, ArrayList<String>> wordProbability = new HashMap<String, ArrayList<String>>();
		for (int i = 0; i < words.size() - 2; i++) {
			String firstWord = words.get(i);
			String secondWord = words.get(i + 1);
			String newFirstWord = firstWord + " " + secondWord;
			String thirdWord = words.get(i + 2);
			if (wordProbability.containsKey(newFirstWord)) {
				wordProbability.get(newFirstWord).add(thirdWord);
			} else {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(thirdWord);
				wordProbability.put(newFirstWord, temp);
			}
		}
		do {
			wordSeed = random.nextInt(words.size());

			firstSeed = words.get(wordSeed);
			previousWord = words.get(wordSeed - 1);
		} while ((previousWord.indexOf(".") == -1));

		String space = " ";
		String secondSeed = words.get((wordSeed + 1));
		String trueSeed = firstSeed + space + secondSeed;

		int randomNumber, arrayMax;
		finalSentence.add(trueSeed);

		do {
			ArrayList<String> temp = wordProbability.get(trueSeed);
			arrayMax = temp.size();
			randomNumber = random.nextInt(arrayMax);
			finalSentence.add(" ");
			finalSentence.add(temp.get(randomNumber));
			trueSeed = secondSeed + " " + temp.get(randomNumber);
			secondSeed = temp.get(randomNumber);
		} while (secondSeed.indexOf(".") == -1);

		for (int i = 0; i < finalSentence.size(); i++) {
			sentence.append(finalSentence.get(i));
		}
		return sentence.toString();
	}

}
