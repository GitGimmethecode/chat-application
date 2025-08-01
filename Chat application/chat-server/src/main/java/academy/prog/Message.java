package academy.prog;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*

	C-> 			S						List
				/add POST(JSON message) ->  0
				/get?from=3 GET				1
											2'
											3
 */

public class Message {
	private Date date = new Date();
	private String from;
	private String to;
	private String text;

	// Конструктор для публичного сообщения
	public Message(String from, String text) {
		this.from = from;
		this.text = text;
	}

	// Конструктор для личного сообщения
	public Message(String from, String to, String text) {
		this.from = from;
		this.to = to;
		this.text = text;
	}

	// Отправка сообщения на сервер
	public int send(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

		String json = this.toJSON();
		byte[] out = json.getBytes();

		try (OutputStream os = con.getOutputStream()) {
			os.write(out);
		}

		return con.getResponseCode();
	}

	public String toJSON() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(this);
	}

	public static Message fromJSON(String s) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.fromJson(s, Message.class);
	}

	@Override
	public String toString() {
		return new StringBuilder().append("[").append(date)
				.append(", From: ").append(from).append(", To: ").append(to)
				.append("] ").append(text)
				.toString();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}