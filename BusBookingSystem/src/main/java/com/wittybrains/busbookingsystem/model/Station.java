package com.wittybrains.busbookingsystem.model;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//
//@Entity
//@Table(name = "stations")
//public class Station {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "station_code")
//    private String stationCode;
//
//    @OneToMany(mappedBy = "source")
//    private List<TravelSchedule> sourceSchedules;
//
//    @OneToMany(mappedBy = "destination")
//    private List<TravelSchedule> destinationSchedules;
//
//    public Station(String name, String stationCode) {
//        this.name = name;
//        this.stationCode = stationCode;
//    }
//
//    public Station() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
@Entity
@Table(name = "stations")
public class Station {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

 
  public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getStationCode() {
	return stationCode;
}


public void setStationCode(String stationCode) {
	this.stationCode = stationCode;
}


private String name;

  
  private String stationCode;
   
}