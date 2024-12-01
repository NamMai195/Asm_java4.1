package com.poly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VideoLikedInfo {
  private String videoId;
  private String title;
  private String href;
  private Integer views;
  private Integer totalLike;
  private Integer totalShare;
}
