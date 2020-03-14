package me.ahmedashour.skiller.model.users;


import java.io.Serializable;
import java.util.ArrayList;

import me.ahmedashour.skiller.model.Course;
import me.ahmedashour.skiller.model.misc.Contact;
import me.ahmedashour.skiller.model.misc.Date;
import me.ahmedashour.skiller.model.misc.Education;
import me.ahmedashour.skiller.model.misc.Experience;
import me.ahmedashour.skiller.model.misc.Feedback;
import me.ahmedashour.skiller.model.misc.Name;
import me.ahmedashour.skiller.model.misc.Skill;

public class Tutor extends User implements Serializable {
    private String title;
    private String pricePerHour;

    public Tutor(String bio, String uid, String imageUrl, String gender, ArrayList<Skill> skillsList,
                 ArrayList<String> skillsTagsList, ArrayList<Feedback> feedBacksList,
                 ArrayList<Course> coursesList, ArrayList<Experience> experienceList, Name name, Contact contact,
                 Education education, Date birthday, String numExperienceHours,
                 String rating, String title, String pricePerHour) {
        super(bio, uid, imageUrl, gender, skillsList, skillsTagsList, feedBacksList, coursesList, experienceList,
                name, contact, education, birthday, numExperienceHours, rating);
        this.title = title;
        this.pricePerHour = pricePerHour;
    }


    public String getPricePerHour() {
        return pricePerHour + " LE/Hr";
    }

    public void setPricePerHour(String pricePerHour) {
        this.pricePerHour = pricePerHour;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "Tutor{" + '\'' +
                ", title='" + title + '\'' +
                ", pricePerHour='" + pricePerHour + '\'' +
                '}';
    }
}