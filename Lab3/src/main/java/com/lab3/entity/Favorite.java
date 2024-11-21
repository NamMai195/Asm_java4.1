package com.lab3.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "FavoriteId")
	    private Integer favoriteId;

	    @ManyToOne
	    @JoinColumn(name = "UserId", nullable = false)
	    private User user;

	    @ManyToOne
	    @JoinColumn(name = "VideoId", nullable = false)
	    private Video video;

	    @Column(name = "LikedDate", columnDefinition = "DATETIME DEFAULT GETDATE()")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date likedDate;
}