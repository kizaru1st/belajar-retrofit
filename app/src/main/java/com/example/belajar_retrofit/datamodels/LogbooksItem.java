package com.example.belajar_retrofit.datamodels;

public class LogbooksItem{
	private String date;
	private Object note;
	private int internshipId;
	private String updatedAt;
	private String activities;
	private String createdAt;
	private int id;
	private int status;

	public String getDate(){
		return date;
	}

	public Object getNote(){
		return note;
	}

	public int getInternshipId(){
		return internshipId;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getActivities(){
		return activities;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public int getStatus(){
		return status;
	}
}
