package questions;
import java.util.List;

public class MultiAnswerQuestion extends Question {
	public MultiAnswerQuestion(String query) {
		super(query);
	}

	public MultiAnswerQuestion(String query, List<String> choices, List<String> answers) throws Exception {
		super(query, choices, answers);
	}

	@Override
	public void setAnswer(List<String> data) throws Exception {
		for (String answer : data) {
			if (!this.getChoices().contains(answer) || answer.isBlank()) {
				data.remove(answer);
			}
		}

		if (data.size() < 1) {
			throw new Exception("A multi-answer question must have at least one answer.");
		}

		this.answers = data;
	}
}
