package poly.phunglv.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	String fullname = "Nguyễn Văn Tèo";
	String password = "123456";
	String country = "VN";
	boolean gender = false;
	String notes = "Công cha như núi thái sơn";
}
