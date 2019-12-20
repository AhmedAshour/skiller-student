package com.codevenue.skillerandroid.model.users;

import com.codevenue.skillerandroid.model.courses.Course;
import com.codevenue.skillerandroid.model.misc.Contact;
import com.codevenue.skillerandroid.model.misc.Date;
import com.codevenue.skillerandroid.model.misc.Education;
import com.codevenue.skillerandroid.model.misc.Experience;
import com.codevenue.skillerandroid.model.misc.Feedback;
import com.codevenue.skillerandroid.model.misc.Name;
import com.codevenue.skillerandroid.model.misc.Skill;

import java.io.Serializable;
import java.util.ArrayList;

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

    public Tutor() {
        super();

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