package org.imdb.platform.technicalassignment.model;


import lombok.Data;
import org.technical.assignment.component.enums.LifeStatus;

@Data
public class Person {
    private final String id;
    private final String name;
    private final Short birthYear;
    private final Short deathYear;
    private final LifeStatus lifeStatus;

  public boolean isAlive(){
      return lifeStatus == LifeStatus.ALIVE;
  }
}
