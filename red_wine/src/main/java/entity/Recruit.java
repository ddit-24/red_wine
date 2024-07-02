package entity;

//招贤纳士
public class Recruit {
	private int id; // id
	private String title; // 职位名称
	private String job_describe; // 职位描述
	private String requirement; // 招聘要求

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJob_describe() {
		return job_describe;
	}

	public void setJob_describe(String job_describe) {
		this.job_describe = job_describe;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
}
