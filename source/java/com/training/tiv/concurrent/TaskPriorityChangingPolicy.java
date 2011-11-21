package com.training.tiv.concurrent;

/**
 * Defines a policy of changing task priority during its execution.
 */
public interface TaskPriorityChangingPolicy {

  int getStartPriority();
  
  int getMediumPriority();
  
  int getEndPriority();
}
