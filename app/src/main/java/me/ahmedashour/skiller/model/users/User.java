package me.ahmedashour.skiller.model.users;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import me.ahmedashour.skiller.model.Course;
import me.ahmedashour.skiller.model.misc.Contact;
import me.ahmedashour.skiller.model.misc.Date;
import me.ahmedashour.skiller.model.misc.Education;
import me.ahmedashour.skiller.model.misc.Experience;
import me.ahmedashour.skiller.model.misc.Feedback;
import me.ahmedashour.skiller.model.misc.Name;
import me.ahmedashour.skiller.model.data.Skill;
import me.ahmedashour.skiller.model.utils.DatabaseAccessibleObject;

public abstract class User implements Serializable, DatabaseAccessibleObject {

    private String bio;
    private String databaseReference;
    private String imageURL;
    private String gender;
    private ArrayList<Skill> skillsList;
    private ArrayList<String> skillsTagsList;
    private ArrayList<Feedback> feedBacksList;
    private ArrayList<Course> coursesList;
    private ArrayList<Experience> experienceList;
    private Name name;
    private Contact contact;
    private Education education;
    private Date birthday;
    private String numExperienceHours;
    private String rating;

    public User(String bio, String databaseReference, String imageURL, String gender, ArrayList<Skill> skillsList,
                ArrayList<String> skillsTagsList, ArrayList<Feedback> feedBacksList, ArrayList<Course> coursesList,
                ArrayList<Experience> experienceList, Name name, Contact contact, Education education,
                Date birthday, String numExperienceHours, String rating) {
        this.bio = bio;
        this.databaseReference = databaseReference;
        this.imageURL = imageURL;
        this.gender = gender;
        this.skillsList = skillsList;
        this.skillsTagsList = skillsTagsList;
        this.feedBacksList = feedBacksList;
        this.coursesList = coursesList;
        this.experienceList = experienceList;
        this.name = name;
        this.contact = contact;
        this.education = education;
        this.birthday = birthday;
        this.numExperienceHours = numExperienceHours;
        this.rating = rating;
    }


    public User() {
        this.bio = bio;
        this.imageURL = imageURL;
        this.gender = gender;
        this.skillsList = skillsList;
        this.skillsTagsList = skillsTagsList;
        this.feedBacksList = feedBacksList;
        this.coursesList = coursesList;
        this.experienceList = experienceList;
        this.name = name;
        this.contact = contact;
        this.education = education;
        this.birthday = birthday;
        this.numExperienceHours = numExperienceHours;
        this.rating = rating;
    }

    public String getDatabaseReference() {
        return databaseReference;
    }

    public void setDatabaseReference(String databaseReference) {
        this.databaseReference = databaseReference;
    }

    public String getRating() {
        /*if (rating.isEmpty())
            return "No Rating";
        else
            return rating;*/
return "";
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getFullName() {
        if (name == null)
            return " ";
        else if (name.getFirstName() != null && name.getLastName() == null)
            return name.getFirstName();
        else
            return name.getFirstName() + " " + name.getLastName();
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public ArrayList<Feedback> getFeedBacksList() {
        return feedBacksList;
    }

    public void setFeedBacksList(ArrayList<Feedback> feedBacksList) {
        this.feedBacksList = feedBacksList;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<Skill> getSkillsList() {
        return skillsList;
    }

    public void setSkillsList(ArrayList<Skill> skillsList) {
        this.skillsList = skillsList;
    }

    public ArrayList<String> getSkillsTagsList() {
        return skillsTagsList;
    }

    public void setSkillsTagsList(ArrayList<String> skillsTagsList) {
        this.skillsTagsList = skillsTagsList;
    }

    public ArrayList<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(ArrayList<Course> coursesList) {
        this.coursesList = coursesList;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public ArrayList<Experience> getExperienceList() {
        return experienceList;
    }

    public void setExperienceList(ArrayList<Experience> experienceList) {
        this.experienceList = experienceList;
    }

    public String getNumExperienceHours() {
        return numExperienceHours;
    }

    public void setNumExperienceHours(String numExperienceHours) {
        this.numExperienceHours = numExperienceHours;
    }


    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("gender", this.gender);
        map.put("name", this.name);
        map.put("contact", this.contact);
        map.put("education", this.education);
        map.put("birthday", this.birthday);
        return map;
    }

}