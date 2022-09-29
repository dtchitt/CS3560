package questions;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

	@Override
	public List<String> generateAnswer() {
		List<String> generatedAnswer = new ArrayList<String>();

		generatedAnswer.add(this.getChoices().get(new Random().nextInt(this.getChoices().size())));

		return generatedAnswer;
	}
}
