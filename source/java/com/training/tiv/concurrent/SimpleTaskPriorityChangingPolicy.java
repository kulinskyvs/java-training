package com.training.tiv.concurrent;

public class SimpleTaskPriorityChangingPolicy implements TaskPriorityChangingPolicy {

  private int startPriority = Thread.NORM_PRIORITY;
  private int mediumPriority = Thread.NORM_PRIORITY;
  private int endPriority = Thread.NORM_PRIORITY;
  
  public SimpleTaskPriorityChangingPolicy(int priority) {
     this.startPriority = priority;
     this.mediumPriority = priority;     
     this.endPriority = priority;     
  }
  
  public SimpleTaskPriorityChangingPolicy(int startPriority, int mediumPriority, int endPriority) {
     this.startPriority = startPriority;
     this.mediumPriority = mediumPriority;     
     this.endPriority = endPriority;     
  }  
  
  public int getStartPriority() {
     return startPriority;
  }
  
  public int getMediumPriority() {
     return mediumPriority;
  }
  
  public int getEndPriority() {
     return endPriority;
  }

}
