package questions;
import java.util.List;

public class SingleAnswerQuestion extends Question {
	public SingleAnswerQuestion(String query) {
		super(query);
	}

	public SingleAnswerQuestion(String query, List<String> choices, List<String> answers) throws Exception {
		super(query, choices, answers);
	}

	@Override
	public void setAnswer(List<String> data) throws Exception {
		for (String answer : data) {
			if (!this.getChoices().contains(answer) || answer.isBlank()) {
				data.remove(answer);
			}
		}

		if (data.size() != 1) {
			throw new Exception("A single answer question may only have one answer.");
		}

		this.answers = data;
	}
}
