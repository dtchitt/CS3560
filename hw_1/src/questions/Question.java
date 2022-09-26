package questions;
import java.util.List;

public abstract class Question {
	private String query;
	private List<String> choices;
	protected List<String> answers;

	/**
	 * Create a question that only contaians a query. Choices and answers can be
	 * added at a later time.
	 * 
	 * @param query
	 */
	public Question(String query) {
		this.query = query;
	}

	/**
	 * Create a question that has choices and answers
	 * 
	 * @param query
	 * @param choices
	 * @param answers
	 * @throws Exception
	 */
	public Question(String query, List<String> choices, List<String> answers) throws Exception {
		this.query = query;
		this.setChoices(choices);
		this.setAnswer(answers);
	}

	/**
	 * Change the query of this Question
	 * 
	 * @param query
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * @return the query of this Question
	 */
	public String getQuery() {
		return this.query;
	}

	/**
	 * This will set the choices allowed for this Question. If an invalid choice is
	 * passed in, it will be removed.
	 * Invalid choices are blank choices or choices with only whitespace
	 * 
	 * @param data the List<String> of choices
	 * @throws Exception
	 */
	public void setChoices(List<String> data) throws Exception {
		for (String string : data) {
			if (string.isBlank()) {
				data.remove(string);
			}
		}

		if (data.size() < 2) {
			throw new Exception("A question must have at least two choices.");
		}

		this.choices = data;
	}

	public List<String> getChoices() {
		return this.choices;
	}

	/**
	 * Adds answers to a question. This will check if an answer is valid. If valid,
	 * it will be added to accepted answers.
	 * To be a valid answer it must be a question choice and not a duplicate answer.
	 * @param data list of unverified answers to be validated and set an the answer
	 * @throws Exception
	 */
	public abstract void setAnswer(List<String> data) throws Exception;

	public List<String> getAnswer() {
		return this.answers;
	}

	public String toString() {
		String str = "Query: " + this.query + "\n";
		str += "Choices: " + this.choices.toString() + "\n";
		str += "Answers: " + this.answers.toString();

		return str;
	}
}
