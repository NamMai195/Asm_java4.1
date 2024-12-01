package com.poly.entity;

import javax.persistence.*;

import com.poly.constant.NamedStored;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = NamedStored.FIND_USER_LIKED_BYHREF, procedureName = "sp_selectUserisLikedVideoByVideoHref", resultClasses = {
				User.class }, parameters = @StoredProcedureParameter(name = "videohref", type = String.class)) })
@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@Column(name = "user_id")
	private String id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "role")
	private Boolean role; // Kiểm tra lại kiểu dữ liệu

	@Column(name = "isActive")
	private Boolean isActive;

//	@OneToMany(mappedBy = "user")
//	private List<Share> shares;
}