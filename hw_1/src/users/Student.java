package users;

import java.util.List;

import services.VotingService;

/**
 * I decided to extend user because there could be different types of users. For example, an admin. Just in case I ever add to this
 */
public class Student extends User {
	public Student() {
		super();
	}

	/**
	* #2 in the prompt says students need to be able to submit answers to vote service, so this is what I came up with
	* I would have liked to just do this in the sim driver or vote service.
	*/
	public void submitAnswer(VotingService service, List<String> answer) {
		service.setAnswerForStudent(this, answer);
	}
}
