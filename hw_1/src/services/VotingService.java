package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import questions.Question;
import users.Student;

public class VotingService {
	private Map<Student, List<String>> studentAnswers;
	private Question question;

    public VotingService(Question question) {
		this.question = question;
		this.studentAnswers = new HashMap<Student, List<String>>();
    }

    public void setAnswerForStudent(Student student, List<String> answer) {
        this.studentAnswers.put(student, answer);
		System.out.println("Student: " + student.getID() + " submitted answer: " + answer);
    }

	public void printQuestion() {
		System.out.println(this.question.toString());
	}

	public boolean studentAnswered(Student student) {
		return this.studentAnswers.containsKey(student);
	}

	public String getAnswerStatistics() {
		int correctAnswers = 0;
		int inCorrectAnswers = 0;
		Map<String, Integer> answersData = new HashMap<String, Integer>();

		for (List<String> answer : this.studentAnswers.values()) {
			if (answer.equals(question.getAnswer())) {
				correctAnswers++;
			} else {
				inCorrectAnswers++;
			}

			for (String string : answer) {
				answersData.merge(string, 1, Integer::sum);
			}
		}

		String results = "Correct Answers: " + correctAnswers + "\nIncorrect Answers: " + inCorrectAnswers + "\n";
		results += "Number of submissions per choice: " + answersData.toString();
		return results;
	}
}