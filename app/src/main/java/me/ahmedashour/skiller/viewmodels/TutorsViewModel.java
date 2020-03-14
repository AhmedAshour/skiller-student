package me.ahmedashour.skiller.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.annotation.Nullable;

import java.util.List;

import me.ahmedashour.skiller.datamanagers.TutorsDataManager;
import me.ahmedashour.skiller.model.Course;
import me.ahmedashour.skiller.model.misc.Experience;
import me.ahmedashour.skiller.model.misc.Feedback;
import me.ahmedashour.skiller.model.users.Tutor;

public class TutorsViewModel extends ViewModel {
    private MediatorLiveData<List<Tutor>> tutorsList;
    private MediatorLiveData<Tutor> tutorLiveData;
    private MediatorLiveData<List<Feedback>> feedbacksLiveData;
    private MediatorLiveData<List<Experience>> experiencesLiveData;
    private MediatorLiveData<List<Course>> coursesLiveData;
    private TutorsDataManager dataManager;



    public TutorsViewModel() {
        tutorsList = new MediatorLiveData<>();
        tutorLiveData  = new MediatorLiveData<>();
        feedbacksLiveData = new MediatorLiveData<>();
        experiencesLiveData = new MediatorLiveData<>();
        coursesLiveData = new MediatorLiveData<>();
        dataManager = new TutorsDataManager();

    }

    public LiveData<List<Tutor>> getTutors() {
        if (tutorsList.getValue() == null) {
            tutorsList.addSource(dataManager.getTutorsFromDatabase(), new Observer<List<Tutor>>() {
                @Override
                public void onChanged(@Nullable List<Tutor> tutors) {
                    tutorsList.setValue(tutors);
                }
            });
        }
        return tutorsList;
    }


    public LiveData<List<Tutor>> getTutorsFilter(String filterString) {
        if (tutorsList.getValue() == null) {
            tutorsList.addSource(dataManager.getTutorsFilteredFromDatabase(filterString.trim()), new Observer<List<Tutor>>() {
                @Override
                public void onChanged(@Nullable List<Tutor> tutors) {
                    tutorsList.setValue(tutors);
                }
            });
        }
        return tutorsList;
    }
    public LiveData<Tutor> getTutorInfo(String tutorID) {
        if(tutorLiveData.getValue() == null) {
            tutorLiveData.addSource(dataManager.getTutorInfoFromDatabase(tutorID), new Observer<Tutor>() {
                @Override
                public void onChanged(@Nullable Tutor tutor) {
                    tutorLiveData.setValue(tutor);
                }
            });
        }
        return tutorLiveData;
    }

    public LiveData<List<Tutor>> getTopTenTutors(){
        if (tutorsList.getValue() == null){
            tutorsList.addSource(dataManager.getTopTenTutorsFromDatabase(), new Observer<List<Tutor>>() {
                @Override
                public void onChanged(@Nullable List<Tutor> tutors) {
                    tutorsList.setValue(tutors);
                }
            });
        }
        return tutorsList;
    }
    public LiveData<List<Experience>> getExperience(String tutorID) {
        if(experiencesLiveData.getValue() == null){
            experiencesLiveData.addSource(dataManager.getExperiences(tutorID), new Observer<List<Experience>>() {
                @Override
                public void onChanged(@Nullable List<Experience> experiences){
                    experiencesLiveData.setValue(experiences);
                }
            });
        }
        return experiencesLiveData;
    }
    public LiveData<List<Feedback>> getFeedbacks(String tutorID) {
        if(feedbacksLiveData.getValue() == null){
            feedbacksLiveData.addSource(dataManager.getFeedbacks(tutorID.trim()), new Observer<List<Feedback>>() {
                @Override
                public void onChanged(@Nullable List<Feedback> feedbacks){
                    feedbacksLiveData.setValue(feedbacks);
                }
            });
        }
        return feedbacksLiveData;
    }

    public LiveData<List<Course>> getCourses(String firebaseID) {
        if(coursesLiveData.getValue() == null){
            coursesLiveData.addSource(dataManager.getCoursesFromDatabase(firebaseID.trim()), new Observer<List<Course>>() {
                @Override
                public void onChanged(@Nullable List<Course> courses) {
                    coursesLiveData.setValue(courses);
                }
            });
        }
        return coursesLiveData;
    }
    public void setTutorRating(String tutorRating, float rating) {
        dataManager.setTutorRatingInDB(tutorRating, rating);
    }

    public void addToFeedbacksList(String tutorUid, float rating, String strReview) {
        dataManager.addToFeedbacksListInDB(tutorUid, rating, strReview);
    }

}