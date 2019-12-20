package com.codevenue.skillerandroid.model.misc;

import com.codevenue.skillerandroid.model.courses.Course;
import com.codevenue.skillerandroid.model.users.Student;
import com.codevenue.skillerandroid.model.users.Tutor;
import com.codevenue.skillerandroid.model.utils.DatabaseAccessibleObject;

import java.io.Serializable;

public class Lesson implements Serializable, DatabaseAccessibleObject{

    private static final Lesson ourInstance = new Lesson();

    private String tutorUid;
    private String studentUid;
    private Location location;
    private Date date;
    private Time startTime;
    private Time endTime;
    private Course course;
    private String hashId;
    private Tutor tutor;
    private boolean doneTutor;
    private boolean doneStudent;
    private Student student;

    private String databaseReference;

    private Lesson() {
    }

    public static Lesson getInstance() {
        return ourInstance;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTutorUid() {
        return tutorUid;
    }

    public void setTutorUid(String tutorUid) {
        this.tutorUid = tutorUid;
    }

    public String getStudentUid() {
        return studentUid;
    }

    public void setStudentUid(String studentUid) {
        this.studentUid = studentUid;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    public boolean isDoneTutor() {
        return doneTutor;
    }

    public void setDoneTutor(boolean doneTutor) {
        this.doneTutor = doneTutor;
    }

    public boolean isDoneStudent() {
        return doneStudent;
    }

    public void setDoneStudent(boolean doneStudent) {
        this.doneStudent = doneStudent;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    @Override
    public String getDatabaseReference() {
        return databaseReference;
    }

    @Override
    public void setDatabaseReference(String databaseReference) {
        this.databaseReference = databaseReference;
    }
}
