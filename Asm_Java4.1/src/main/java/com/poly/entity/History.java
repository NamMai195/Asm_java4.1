package com.poly.entity;

import java.sql.Timestamp;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "History")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private int historyId;

    // Mối quan hệ ManyToOne với User
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private User user;

    // Mối quan hệ ManyToOne với Video
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "video_id", referencedColumnName = "video_id")
    private Videos video;

    // Trường viewDate được tự động tạo timestamp khi ghi nhận
    @Column(name = "viewDate")
    @CreationTimestamp
    private Timestamp viewDate;

    // Trường isLike là trạng thái like
    @Column(name = "isLike")
    private Boolean isliked;

    // Trường likeDate lưu thời gian người dùng thích video
    @Column(name = "likeDate")
    private Timestamp likeDate;
}
