package me.ahmedashour.skiller.constants;

public final class FirebaseKeys {
    public final static String CHILD_SKILLS = "skills";
    public final static String CHILD_TUTORS = "tutors";
    public final static String CHILD_USERS = "users";
    public final static String CHILD_INNER_SKILLS = "inner_skills";
    public final static String CHILD_STUDENTS = "students";
    public final static String CHILD_LESSONS_REQUESTS = "lessons/requests";
    public final static String CHILD_LESSONS_UPCOMING = "lessons/upcoming";
    public final static String CHILD_LESSONS_FINISHED = "lessons/finished";

    public final static class Skills {
        public final static String KEY_CHILD_SKILLS_SKILL_TITLE = "skillTitle";
        public final static String KEY_CHILD_SKILLS_BACKGROUND_IMAGE = "backgroundImage";
    }
    public final static class Address {
        public final String CHILD_ADDRESS = "location";
        public final String KEY_CHILD_ADDRESS_STREET = "street";
        public final String KEY_CHILD_ADDRESS_APARTMENT = "apartment";
        public final String KEY_CHILD_ADDRESS_MORE_INFO = "moreInfo";
        public final String KEY_CHILD_ADDRESS_CITY = "city";
        public final String KEY_CHILD_ADDRESS_COUNTRY = "country";
    }

    public final static class Contact {
        public final String CHILD_CONTACT = "contact";
        public final String KEY_CHILD_EMAIL= "email";
        public final String KEY_CHILD_PHONE = "phoneNumber";
        public final Address Address = new Address();
    }

    public final static class Date {
        public final String CHILD_DATE = "date";
        public final String KEY_CHILD_DATE_DAY = "day";
        public final String KEY_CHILD_DATE_MONTH = "month";
        public final String KEY_CHILD_DATE_YEAR = "year";
    }

    public final static class Education {
        public final String CHILD_EDUCATION = "education";
        public final String KEY_CHILD_EDUCATION_STATE = "state";
        public final String KEY_CHILD_EDUCATION_PLACE = "place";
        public final String CHILD_START_DATE = "startDate";
        public final String CHILD_END_DATE = "endDate";
    }

    public final static class Experience {
        public final String CHILD_EXPERIENCE = "education";
        public final String KEY_CHILD_EXPERIENCE_TITLE = "title";
        public final String KEY_CHILD_EXPERIENCE_COMPANY = "company";
        public final Date StartDate = new Date();
        public final Date EndDate = new Date();
    }

    public final static class Feedback {
        public final String CHILD_FEEDBACK = "feedback";
        public final String KEY_CHILD_FEEDBACK_RATING = "rating";
        public final String KEY_CHILD_FEEDBACK_REVIEW = "review";
    }

    public final static class Name {
        public final String CHILD_NAME = "name";
        public final String KEY_CHILD_NAME_FIRST_NAME = "firstName";
        public final String KEY_CHILD_NAME_MIDDLE_NAME = "middleName";
        public final String KEY_CHILD_NAME_LAST_NAME = "lastName";
    }

    public final static class User {
        public final String CHILD_USER = "user";
        public final String KEY_CHILD_USER_GENDER = "gender";
        public final String KEY_CHILD_USER_USER_TYPE = "userType";
        public final String CHILD_USER_SKILLS_LIST = "skillsList";
        public final String CHILD_USER_FEEDBACK_LIST = "feedBacksList";
        public final String CHILD_USER_COURSES_LIST = "coursesList";
        public final String CHILD_USER_EDUCATION = "educationList";
        public final String CHILD_USER_EXPERIENCE = "experienceList";
        public final String CHILD_USER_BIRTHDAY = "birthday";
        public final String CHILD_USER_CONTACT = "contact";
        public final String CHILD_USER_IMAGE_URL = "imageURL";
        public final String CHILD_USER_UID = "uid";


        public final Name Name = new Name();
        public final Contact Contact = new Contact();
        public final Date Date = new Date();
        public final Address Address = new Address();
    }

    public final static class Tutor {
        public final static String KEY_CHILD_TUTORS_BIO = "bio";
        public final static String CHILD_TUTORS_EXPERIENCE_LIST = "experienceList";
        public final static String KEY_CHILD_TUTORS_NUM_EXPERIENCE_HOURS = "numExperienceHours";
        public final static String KEY_CHILD_TUTORS_PRICE_PER_HOUR = "pricePerHour";
        public final static User User = new User();
    }

    public final static class Student {
        public final static String KEY_CHILD_TUTORS = "student";
        public final static User User = new User();
    }

    public final static class Course {
        public final String CHILD_COURSE = "course";
        public final String KEY_CHILD_COURSE_NUM_HOURS_PER_SESSION = "numHoursPerSession";
        public final String KEY_CHILD_COURSE_COURSE_TYPE = "courseType";
    }

    public final static class Package {
        public final static String CHILD_PACKAGE = "package";
        public final static String KEY_CHILD_PACKAGE_NUM_HOURS = "numHours";
        public final static String KEY_CHILD_PACKAGE_NUM_SESSIONS_PER_WEEK = "numSessionsPerWeek";
        public final static String KEY_CHILD_PACKAGE_TOTAL_PRICE = "totalPrice";
        public final static Course Course = new Course();
    }

    public final static class Session {
        public final static String CHILD_SESSION = "session";
        public final static String KEY_CHILD_SESSION_PRICE = "price";
        public final static Course Course = new Course();
    }
    public final static class Lesson{
        public final static String KEY_CHILD_TUTOR_UID = "tutorUid";
        public final static String KEY_CHILD_STUDENT_UID = "studentUid";
    }
}
