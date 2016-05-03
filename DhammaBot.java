import java.util.Scanner;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class DhammaBot {
	public static void main(String[] args) throws TwitterException {
		Scanner input = new Scanner(System.in);

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("nJwg17iLSS9fPnG46ba659QDH")
				.setOAuthConsumerSecret("zQHxVlJOXS6Iz4of6mEDGXm3LyVSuv3wl5shRJgY130mOL9ekp")
				.setOAuthAccessToken("722162630882889728-O2HXUZxMZIlntV5D2SMLEm6I37f0Yr7")
				.setOAuthAccessTokenSecret("MUpT5nnxZAWvdk3Z4W46zSl8wUmRFrbllvmV8ASSlUk0n");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		twitter.updateStatus(sentenceCheck(input));
		System.out.println("Tweet sent!");
		System.out.println("See your tweet now on twitter!");
		System.out.println("https://twitter.com/dhamma_bot");
	}

	public static String sentenceCheck(Scanner input) {
		String response, sentence;
		do {
			sentence = MarkovChain.getString();
			System.out.println("Your sentence is: " + sentence);
			System.out.print("Confirm (y/n)?: ");
			response = input.next();
		} while (!response.equalsIgnoreCase("y"));

		return sentence;
	}

}
