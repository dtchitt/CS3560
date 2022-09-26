package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import questions.Question;
import users.Student;

public class VotingService {
	private Map<Student, List<String>> answers;
	private Question question;

    public VotingService(Question question) {
		this.question = question;
		this.answers = new HashMap<Student, List<String>>();
    }

    public void setAnswerForStudent(Student student, List<String> answer) {
        this.answers.put(student, answer);
		System.out.println("Student: " + student.getID() + " submitted answer: " + answer);
    }

	public void printQuestion() {
		System.out.println(this.question.toString());
	}

	public boolean studentAnswered(Student student) {
		return this.answers.containsKey(student);
	}

	public String getAnswerStatistics() {
		int correctAnswers = 0;
		int inCorrectAnswers = 0;

		for (List<String> answer : this.answers.values()) {
			if (answer.equals(question.getAnswer())) {
				correctAnswers++;
			} else {
				inCorrectAnswers++;
			}
		}

		return "Correct Answers: " + correctAnswers + "\nIncorrect Answers: " + inCorrectAnswers;
	}
}