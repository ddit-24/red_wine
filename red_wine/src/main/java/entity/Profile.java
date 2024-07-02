package entity;

//公司简介
public class Profile {
	private int id; // id
	private String content; // 简介內容
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
