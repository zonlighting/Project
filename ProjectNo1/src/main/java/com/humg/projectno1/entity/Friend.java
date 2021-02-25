package com.humg.projectno1.entity;

import lombok.Data;

@Data
public class Friend {
  private long userId;
  private long friendId;
  private int status;
}
