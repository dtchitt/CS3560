package users;
import java.util.UUID;

/**
 * User class is responsible for giving each user a unique ID.
 */
public class User {
	private final String id; // I think an ID should not change.

	public User() {
		this.id = UUID.randomUUID().toString();
	}

	public String getID() {
		return this.id;
	}
}
