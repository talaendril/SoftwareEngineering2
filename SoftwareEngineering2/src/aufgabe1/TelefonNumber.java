package aufgabe1;

public class TelefonNumber {

	private final String firstName;
	private final String lastName;
	private final String number;
	
	public TelefonNumber(String fName, String lName, String n) {
		this.firstName = fName;
		this.lastName = lName;
		this.number = n;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getNumber() {
		return this.number;
	}
}
