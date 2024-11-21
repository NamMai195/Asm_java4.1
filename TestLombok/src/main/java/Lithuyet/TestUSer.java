package Lithuyet;

public class TestUSer {
public static void main(String[] args) {
	User a=new User();
	   User user = User.builder()
               .a("Value for A")
               .b("Value for B")
               .build();
	System.out.println(user.a);
}
}
