package com.poly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoLikedInfo {
  private String videoId;
  private String title;
  private String href;
  private Integer totalLike;
}
