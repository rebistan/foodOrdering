package com.mbhit.kyancafe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="po")
public class PlaceOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String table;
	private String car;
	private String takeaway;
	private PlaceMode pm;
	private int num;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public PlaceMode getPm() {
		return pm;
	}
	public void setPm(PlaceMode pm) {
		this.pm = pm;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getCar() {
		return car;
	}
	public PlaceOrder(Integer id, String table, String car, String takeaway, PlaceMode pm) {
		super();
		this.id = id;
		this.table = table;
		this.car = car;
		this.takeaway = takeaway;
		this.pm = pm;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public String getTakeaway() {
		return takeaway;
	}
	public void setTakeaway(String takeaway) {
		this.takeaway = takeaway;
	}
	
	
}
