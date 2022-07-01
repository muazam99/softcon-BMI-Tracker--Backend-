package com.example.softcon.model;



public class Tracker {
	private int id;
	private double bmi;
	private double weight;
	private double height;
	private int userId;

	
	public Tracker() {

	}

	public Tracker(int id, double bmi, double weight, double height, int userId) {
		super();
		this.id = id;
		this.bmi = bmi;
		this.weight = weight;
		this.height = height;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getBmi() {
		return bmi;
	}
	
	public void setBmi(double bmi) {
		this.bmi = bmi;
	}

	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	public int getuserId() {
		return userId;
	}

	public void setuserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return String.format(
				"Tracker [id=%s, bmi=%s, weight=%s, height=%s, taskCondition=%s]", id, bmi,
				weight, height, userId);
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Task other = (Task) obj;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		return true;
//	}

}