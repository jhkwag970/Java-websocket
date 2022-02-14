package chat_log;

public class logVO {
	private int chat_id;
	private String from_name, to_name, chat;

	/**
	 * @param from_name
	 * @param to_name
	 * @param chat
	 */
	public logVO(int chat_id, String from_name, String to_name, String chat) {
		this.chat_id = chat_id;
		this.from_name = from_name;
		this.to_name = to_name;
		this.chat = chat;
	}
	public logVO() {}
	
	public int getChat_id() {
		return chat_id;
	}
	public void setChat_id(int chat_id) {
		this.chat_id = chat_id;
	}
	public String getFrom_name() {
		return from_name;
	}
	public void setFrom_name(String from_name) {
		this.from_name = from_name;
	}
	public String getTo_name() {
		return to_name;
	}
	public void setTo_name(String to_name) {
		this.to_name = to_name;
	}
	public String getChat() {
		return chat;
	}
	public void setChat(String chat) {
		this.chat = chat;
	}

}
