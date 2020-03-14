package me.ahmedashour.skiller.model;


import java.io.Serializable;

import me.ahmedashour.skiller.model.misc.Date;
import me.ahmedashour.skiller.model.utils.DatabaseAccessibleObject;

public class Course implements Serializable, DatabaseAccessibleObject {

    private String courseTitle;
    private String price;
    private String skillName;
    private String timeStart;
    private String timeEnd;
    private Date dateStart;
    private Date dateEnd;
    private String location;
    private String description;
    private String numSessions;
    private String numHours;
    private String numHoursPerSession;
    private String notes;

    private String databaseReference;

    private String tutorUid;

    public Course(String courseTitle, String price, String skillName, String timeStart, String timeEnd,
                  Date dateStart, Date dateEnd, String location, String description, String numSessions,
                  String numHours, String numHoursPerSession, String notes, String databaseReference,
                  String tutorUid) {
        this.courseTitle = courseTitle;
        this.price = price;
        this.skillName = skillName;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.location = location;
        this.description = description;
        this.numSessions = numSessions;
        this.numHours = numHours;
        this.numHoursPerSession = numHoursPerSession;
        this.notes = notes;
        this.databaseReference = databaseReference;
        this.tutorUid = tutorUid;
    }

    public String getNumSessions() {
        if (numSessions != null)
            return numSessions;
        else
            return "0";
    }

    public void setNumSessions(String numSessions) {
        this.numSessions = numSessions;
    }

    public String getTutorUid() {
        return tutorUid;
    }

    public void setTutorUid(String tutorUid) {
        this.tutorUid = tutorUid;
    }


    public Course() {
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumHours() {
        return numHours;
    }

    public void setNumHours(String numHours) {
        this.numHours = numHours;
    }


    public String getNumHoursPerSession() {
        if (numHoursPerSession != null)
            return numHoursPerSession;
        else
            return "0";
    }


    public void setNumHoursPerSession(String numHoursPerSession) {
        this.numHoursPerSession = numHoursPerSession;
    }


    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getNotes() {
        if (notes == null)
            return "No additional notes added.";
        else
            return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatabaseReference() {
        return databaseReference;
    }

    public void setDatabaseReference(String databaseReference) {
        this.databaseReference = databaseReference;
    }
}