package academy.prog;

import java.util.*;

public class MessageStorage {
    private Set<String> logins;
    private Map<String, List<Message>> messagesByLogin;

    public MessageStorage() {
        logins = new HashSet<>();
        messagesByLogin = new HashMap<>();
    }

    public boolean addLogin(String login) {
        return logins.add(login);
    }

    public boolean loginExists(String login) {
        return logins.contains(login);
    }

    public void saveMessage(String login, Message m) {
        messagesByLogin.computeIfAbsent(login, k -> new ArrayList<>()).add(m);
    }

    public List<Message> getMessages(String login){
        return messagesByLogin.getOrDefault(login, new ArrayList<>());
    }

    public Set<String> getLogins() {
        return logins;
    }
}

