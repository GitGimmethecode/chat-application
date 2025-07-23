package academy.prog;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.*;

public class MessageList {
	private static final MessageList instance = new MessageList();

	private final List<Message> list = new ArrayList<>();
	private final Set<String> users = new HashSet<>();
	private final Gson gson;

	private MessageList() {
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	}

	public static MessageList getInstance() {
		return instance;
	}

	public synchronized void add(Message m) {
		list.add(m);

		// Регистрируем отправителя
		if (m.getFrom() != null && !m.getFrom().isEmpty()) {
			users.add(m.getFrom());
		}

		// Регистрируем получателя, если это личное сообщение
		if (m.getTo() != null && !m.getTo().isEmpty()) {
			users.add(m.getTo());
		}
	}

	public synchronized String toJSON(int n) {
		if (n < 0 || n >= list.size()) return null;
		return gson.toJson(new JsonMessages(list, n));
	}

	public synchronized boolean isUserRegistered(String login) {
		return users.contains(login);
	}

	public synchronized List<Message> getMessages() {
		return list;
	}
}
