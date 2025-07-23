package academy.prog;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MessageStorage storage = new MessageStorage();

		while (true) {
			System.out.println("\nChoose an action:");
			System.out.println("Add login (type «1»)");
			System.out.println("Send message (type «2»)");
			System.out.println("View inbox messages (type «3»)");
			System.out.println("Exit (type «4»)");

			String choice = scanner.nextLine();

			if (choice.equals("1")) {
				addLogin(scanner, storage);
			} else if (choice.equals("2")) {
				sendMessage(scanner, storage);
			} else if (choice.equals("3")) {
				showMessages(scanner, storage);
			} else if (choice.equals("4")) {
				System.out.println("Goodbye!");
				break;
			} else {
				System.out.println("Invalid choice. Try again.");
			}
		}
	}

	private static void addLogin(Scanner scanner, MessageStorage storage) {
		while (true) {
			System.out.print("Enter a login: ");
			String login = scanner.nextLine().trim();

			if (login.isEmpty()) {
				System.out.println("Login cannot be empty.");
				continue;
			}

			if (storage.addLogin(login)) {
				System.out.println("Login saved.");
			} else {
				System.out.println("Login already exists.");
			}

			System.out.print("Do you want to add another login? (yes/no): ");
			String answer = scanner.nextLine().trim().toLowerCase();
			if (!answer.equals("yes")) break;
		}
	}

	private static void sendMessage(Scanner scanner, MessageStorage storage) {
		System.out.print("Enter recipient login: ");
		String to = scanner.nextLine().trim();

		if (!storage.loginExists(to)) {
			System.out.println("No such login. Available logins: " + storage.getLogins());
			return;
		}

		System.out.print("Enter your login (sender): ");
		String from = scanner.nextLine().trim();

		if (!storage.loginExists(from)) {
			System.out.println("No such login. Available logins: " + storage.getLogins());
			return;
		}

		System.out.print("Enter your message: ");
		String text = scanner.nextLine();

		Message message = new Message(from, text);
		storage.saveMessage(to, message);
		System.out.println("Message saved.");
	}

	private static void showMessages(Scanner scanner, MessageStorage storage) {
		Set<String> logins = storage.getLogins();

		if (logins.isEmpty()) {
			System.out.println("No logins available.");
			return;
		}

		System.out.println("Available logins: " + logins);
		System.out.print("Enter the login to view their messages: ");
		String login = scanner.nextLine().trim();

		if (!storage.loginExists(login)) {
			System.out.println("No such login. Try again.");
			return;
		}

		List<Message> messages = storage.getMessages(login);

		if (messages.isEmpty()) {
			System.out.println("No messages for '" + login + "'.");
		} else {
			System.out.println("Messages for '" + login + "':");
			for (Message m : messages) {
				System.out.println(m);
			}
		}
	}
}