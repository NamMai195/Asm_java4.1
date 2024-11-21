package Lithuyet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor //tạo ra 1 contructor không tham số
@AllArgsConstructor //tạo ra contructor fulll tham số
@Data //tạo ra các phương thức get set
//@Getter //tạo phương thức getters cho các field
//@Setter //tạo ra các phương thức setttt cho field
@Builder //tạo ra các phương thức pattern desgin của một buider
public class User {
String a;
String b;
}
