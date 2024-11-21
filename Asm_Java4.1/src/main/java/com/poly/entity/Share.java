package com.poly.entity;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "Shares")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Share {

    @Id
    @Column(name = "share_id")
    private String shareId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "video_id", referencedColumnName = "video_id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Videos video;

    @Column(name = "recipient_email")
    private String recipientEmail;

    @Column(name = "share_date")
    private Date shareDate;
}
