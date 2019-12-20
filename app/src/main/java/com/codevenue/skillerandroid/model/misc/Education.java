package com.codevenue.skillerandroid.model.misc;

import com.codevenue.skillerandroid.model.utils.DatabaseAccessibleObject;

import java.io.Serializable;

public class Education implements Serializable, DatabaseAccessibleObject{
    private String degree;
    private String institutionName;
    private String description;

    private Date startDate;
    private Date endDate;

    private String databaseReference;

    public Education() {
        startDate = new Date();
        endDate = new Date();
    }


    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public String getDatabaseReference() {
        return databaseReference;
    }

    @Override
    public void setDatabaseReference(String databaseReference) {
        this.databaseReference = databaseReference;
    }

    @Override
    public String toString() {
        return degree + " "
                + institutionName + " "
                + description + " "
                + startDate.toString()
                + endDate.toString();
    }
}