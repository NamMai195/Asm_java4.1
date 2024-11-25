package com.poly.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Videos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Videos {

    @Id
    @Column(name = "video_id")
    private String videoId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "href")
    private String href;

    @Column(name = "views")
    private int views;

    @Column(name = "isActive")
    private Boolean isActive;

    // OneToMany relationship with Share
   

}
