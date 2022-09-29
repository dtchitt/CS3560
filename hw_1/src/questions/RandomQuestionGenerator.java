package questions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * QuestionService can be used to generate questions.
 * Questions can be randomly generated using pre defined queries or they can be
 * manually generated wit user defined queries and elements.
 */
public class RandomQuestionGenerator {
	private List<String> queries;
	private Map<String, Integer> questionTypeCount;

	public RandomQuestionGenerator() {
		this.queries = new LinkedList<>(Arrays.asList(
				"If you were invisible for a day, what would you choose to do with that power?",
				"What did you think of me the first time you saw me?",
				"If you could be a model for any product, what would you choose and why?",
				"Lets pretend you won the lottery, what is the first thing you would buy?",
				"If you had to go into the witness protection program, what would you choose as a new name, where would you ask to go, and what would you choose to do?",
				"What crime would you commit if you could get away with it?",
				"Which song would you sing to audition for American Idol?",
				"What is your favorite smell?",
				"Whats something I do that grosses you out the most?",
				"If you could get drunk with a historical figure, who would it be?",
				"Whats an app you wish existed but doesnt currently?",
				"If someone went through your phone, what would they find that would be surprising?",
				"You are locked in Target overnight: what would you do?",
				"If you could turn any activity into an Olympic sport so that you had a chance at the gold, what would it be?",
				"What takes up too much of your time?",
				"What TV show do you refuse to watch?",
				"Whats one thing you really want but cant afford?",
				"What website do you visit the most?",
				"If life is a game, what is the #1 rule?",
				"If you had a personal flag, what would it look like?",
				"Whats one of the strongest flaws about you?",
				"If you were forced to relive the same day of your life over and over again, what day would you pick?",
				"Whats the most illegal thing youve done?",
				"What lie do you tell most often?"));

		this.questionTypeCount = new HashMap<String, Integer>();
	}

	/**
	 * Generates a random question of any type (multi/single) with random choices and answer(s)
	 * The choices and answers don't necessarily mean anything.
	 * 
	 * @return a random question
	 * @throws Exception
	 */
	public Question randomChoice() throws Exception {
		// Okay, Maybe this isnt the best way to do this but basically this will make it
		// so each type has ~50% chance to be selected each time this function is run
		// If you read this please give me a suggestion on how I can acheive this without
		// violating the OCP
		if (Math.random() > 0.5) {
			return multiChoice();
		} else {

			return singleChoice();
		}
	}

	/**
	 * Generates a random multiple choice question. This can have any number of choices and answers as long as there is at least 1 of each.
	 * 
	 * @param question the query
	 * @param choices  the possible choice(s)
	 * @param answers  the correct answer(s)
	 * @return a Question
	 * @throws Exception
	 */
	public Question multiChoice() throws Exception {
		if (this.queries.isEmpty())
			throw new Exception("No questions found in list, Please restart the app or add questions");

		List<String> choices = setChoices();
		List<String> answers = setAnswers(choices);
		Question question = new MultiAnswerQuestion(this.setQuery(), choices, answers);
		this.questionTypeCount.merge("MultiAnswerQuestion", 1, Integer::sum);
		return question;
	}

	/**
	 * Generates a random single choice question. This can have any number of choices, but only 1 answer
	 * 
	 * @param question the query
	 * @param choices  the possible choice(s)
	 * @param answers  the correct answer
	 * @return a Question
	 * @throws Exception
	 */
	public Question singleChoice() throws Exception {
		if (this.queries.isEmpty())
			throw new Exception("No questions found in list, Please restart the app or add questions");

		List<String> choices = setChoices();
		List<String> answers = new LinkedList<String>();
		answers.add(choices.get(new Random().nextInt(choices.size())));
		Question question = new SingleAnswerQuestion(this.setQuery(), choices, answers);
		this.questionTypeCount.merge("SingleAnswerQuestion", 1, Integer::sum);
		return question;
	}

	/**
	 * @return the number of queries remaining in the pre-defined list
	 */
	public int getQueryQty() {
		return this.queries.size();
	}

	/**
	 * Total type of each question generated
	 * @return
	 */
	public String getTotals() {
		return "Multi-Answer Questions: " + this.questionTypeCount.get("MultiAnswerQuestion").toString() + "\n"
				+ "Single-Answer Questions: " + this.questionTypeCount.get("SingleAnswerQuestion").toString();
	}

	private String setQuery() {
		return this.queries.remove(new Random().nextInt(this.queries.size()));
	}

	private List<String> setChoices() {
		// Create our list of possible answers
		List<String> choices = new LinkedList<String>(Arrays.asList("A", "B", "C", "D"));

		// The next 2 lines removes 0-3 choices
		int num = (int) Math.floor(Math.random() * (choices.size() - 1) + 2);
		choices = choices.subList(0, num);
		return choices;
	}

	private List<String> setAnswers(List<String> choices) {
		List<String> answers = new LinkedList<String>();

		answers.addAll(choices);

		for (int i = 0; i < Math.floor(answers.size() / 2); i++) {
			if (((int) Math.floor(Math.random() * 10 + 1) <= 5) && answers.size() > 1) {
				answers.remove(i);
			}
		}

		return answers;
	}
}