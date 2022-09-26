import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import questions.MultiAnswerQuestion;
import questions.Question;
import questions.RandomQuestionGenerator;
import questions.SingleAnswerQuestion;
import services.VotingService;
import users.Student;

public class SimulationDriver {
	public static void main(String[] args) throws Exception {
		//Create a random multi or single choice question
		RandomQuestionGenerator qGen = new RandomQuestionGenerator();
		Question question = qGen.randomChoice();

		//Create vote service
		VotingService service = new VotingService(question);

		//Create x students with unique id's
		int numStudent = 60;
		Student[] students = new Student[numStudent];
		for (int i = 0; i < numStudent; i++) {
			students[i] = new Student();
		}

		service.printQuestion();

		Timer timer = new Timer();
		int tickRate = 100;
		//Task to run on each timer tick, to simulate a round running over a duration
		//Each tick a student has a ~50% chance to submit an answer
		//If a student submits an answer for a second time, only the newest answer is recorded
		TimerTask onTick = new TimerTask() {
			int duration = 1000;

			public void run() {
				if (duration > 0) {
					for (Student student : students) {
						if (Math.random() > 0.5) {
							student.submitAnswer(service, generateAnswer(question, student));
						}
					}

					duration -= tickRate;
				} else {
					//If a student has not answered yet, force them to answer
					for (Student student : students) {
						if (!service.studentAnswered(student)) {
							student.submitAnswer(service, generateAnswer(question, student));
						}
					}

					System.out.println(question.toString());
					System.out.println(service.getAnswerStatistics());
					timer.cancel();
				}
			}
		};

		timer.schedule(onTick, 0, tickRate);
	}

	private static List<String> generateAnswer(Question question, Student student) {
		List<String> answers = new LinkedList<String>();
		List<String> choices = question.getChoices();

		if (question.getClass() == MultiAnswerQuestion.class) {
			//This does make it possible to submit a blank answer, which will be counted as wrong.
			for (String string : choices) {
				if (Math.random() > 0.1) {
					answers.add(string);
				}
			}
		} else if (question.getClass() == SingleAnswerQuestion.class) {
			answers.add(choices.get(new Random().nextInt(choices.size())));
		}
		return answers;
	}
}