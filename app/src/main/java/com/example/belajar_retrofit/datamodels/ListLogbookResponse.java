package com.example.belajar_retrofit.datamodels;

import java.util.List;

public class ListLogbookResponse{
	private String reportTitle;
	private String endAt;
	private Object linkSeminar;
	private int proposalId;
	private Object attendeesList;
	private int supervisorId;
	private Object newsEvent;
	private int seminarRoomId;
	private Object certificate;
	private List<LogbooksItem> logbooks;
	private int studentId;
	private Object createdAt;
	private Object startAt;
	private Object cancellationReason;
	private Object internshipScore;
	private String seminarDate;
	private Object reportReceipt;
	private String updatedAt;
	private String grade;
	private Object workReport;
	private Object seminarDeadline;
	private int id;
	private Object activityReport;
	private int status;

	public String getReportTitle(){
		return reportTitle;
	}

	public String getEndAt(){
		return endAt;
	}

	public Object getLinkSeminar(){
		return linkSeminar;
	}

	public int getProposalId(){
		return proposalId;
	}

	public Object getAttendeesList(){
		return attendeesList;
	}

	public int getSupervisorId(){
		return supervisorId;
	}

	public Object getNewsEvent(){
		return newsEvent;
	}

	public int getSeminarRoomId(){
		return seminarRoomId;
	}

	public Object getCertificate(){
		return certificate;
	}

	public List<LogbooksItem> getLogbooks(){
		return logbooks;
	}

	public int getStudentId(){
		return studentId;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public Object getStartAt(){
		return startAt;
	}

	public Object getCancellationReason(){
		return cancellationReason;
	}

	public Object getInternshipScore(){
		return internshipScore;
	}

	public String getSeminarDate(){
		return seminarDate;
	}

	public Object getReportReceipt(){
		return reportReceipt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getGrade(){
		return grade;
	}

	public Object getWorkReport(){
		return workReport;
	}

	public Object getSeminarDeadline(){
		return seminarDeadline;
	}

	public int getId(){
		return id;
	}

	public Object getActivityReport(){
		return activityReport;
	}

	public int getStatus(){
		return status;
	}
}