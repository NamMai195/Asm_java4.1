package poly.phunglv.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Employee {
	String id; 
	String name; 
	boolean gender;
	Double salary;
}
