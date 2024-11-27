package poly.phunglv.bean;

public class Student {
	String fullname = "Phạm Thị Nở";
	String password = "123456";
	int age = 20;
	double mark = 7.5;
	boolean gender = false;
	boolean status = true;
	String country = "VN";
	String note = "Công cha như núi Thái sơn";
	
	public Student() {
		super();
	}
	
	public Student(String fullname, String password, int age, double mark, boolean gender, boolean status,
			String country, String note) {
		super();
		this.fullname = fullname;
		this.password = password;
		this.age = age;
		this.mark = mark;
		this.gender = gender;
		this.status = status;
		this.country = country;
		this.note = note;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getMark() {
		return mark;
	}
	public void setMark(double mark) {
		this.mark = mark;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
