package com.codevenue.skillerandroid.views.booking;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.model.misc.Lesson;
import com.codevenue.skillerandroid.model.users.Student;
import com.codevenue.skillerandroid.model.users.Tutor;
import com.codevenue.skillerandroid.viewmodels.LessonsViewModel;
import com.codevenue.skillerandroid.viewmodels.StudentViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class BookingConfirmationFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    @BindView(R.id.btn_request_confirmation_fragment)
    Button btnConfirmRequest;
    @BindView(R.id.btn_cancel_confirmation_fragment)
    Button btnCancelRequest;
    @BindView(R.id.civ_confirmation_fragment)
    CircleImageView civProfileImage;
    @BindView(R.id.tv_tutor_title_confirmation_fragment)
    TextView tvTutorTitle;
    @BindView(R.id.tv_tutor_name_confirmation_fragment)
    TextView tvTutorName;
    @BindView(R.id.tv_course_title_confirmation_fragment)
    TextView tvCourseTitle;
    @BindView(R.id.tv_course_price_confirmation_fragment)
    TextView tvCoursePrice;
    @BindView(R.id.tv_num_sessions_confirmation_fragment)
    TextView tvCourseNumSessions;
    @BindView(R.id.tv_course_num_hours_confirmation_fragment)
    TextView tvCourseNumHours;
    @BindView(R.id.tv_course_num_hours_per_session_confirmation_fragment)
    TextView tvCourseNumHoursPerSession;
    @BindView(R.id.tv_date_confirmation_fragment)
    TextView tvDate;
    @BindView(R.id.tv_location_confirmation_fragment)
    TextView tvLocation;


    private Student currentStudent;
    private Tutor tutor;
    private LessonsViewModel lessonsViewModel;
    private StudentViewModel studentViewModel;

    public BookingConfirmationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_booking_payment, container, false);
        ButterKnife.bind(this, v);

        initViewModel();
        populateViews(v);
        return v;
    }

    private void initViewModel() {
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.getStudentData().observe(this, new Observer<Student>() {
            @Override
            public void onChanged(@Nullable Student student) {
                currentStudent = student;
            }
        });
    }


    private void populateViews(View v) {
        Lesson lesson = Lesson.getInstance();

        if (tutor != null) {
            //Tutor info
            Glide.with(getActivity()).load(tutor.getImageURL()).into(civProfileImage);
            tvTutorName.setText(tutor.getFullName());
            tvTutorTitle.setText(tutor.getTitle());

            //Course
            tvCourseTitle.setText(lesson.getCourse().getCourseTitle());
            tvCoursePrice.setText(lesson.getCourse().getPrice() + " LE");
            tvCourseNumHours.setText("(" + lesson.getCourse().getNumHours() + " Hrs Total)");
            tvCourseNumHoursPerSession.setText(lesson.getCourse().getNumHoursPerSession() + " Hrs/session");
            tvCourseNumSessions.setText(lesson.getCourse().getNumSessions() + " Sessions");

            //Date
            tvDate.setText(lesson.getDate().toString() + " - " + lesson.getStartTime().toString() + " to " + lesson.getEndTime().toString());

            //Location
            tvLocation.setText(lesson.getLocation().toString());
        }
    }

    @OnClick(R.id.btn_request_confirmation_fragment)
    public void onClickRequest(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String studentUid = user.getUid();
        Lesson lesson = Lesson.getInstance();

        //Setting lesson
        if (tutor != null)
            lesson.setTutorUid(tutor.getDatabaseReference());

        lesson.setStudentUid(studentUid);
        lesson.setTutor(tutor);
        lesson.setStudent(currentStudent);

        lessonsViewModel = ViewModelProviders.of(this).get(LessonsViewModel.class);
        lessonsViewModel.writeLessonRequest(lesson);
        Toast.makeText(getActivity(), "Request Submitted.", Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @OnClick(R.id.btn_cancel_confirmation_fragment)
    public void onClickCancel(View view) {

        Lesson lesson = Lesson.getInstance();
        lesson = null;

        getActivity().finish();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BookingAvailableCoursesFragment.OnFragmentInteractionListener) {
            mListener = (BookingConfirmationFragment.OnFragmentInteractionListener) context;
            tutor = mListener.getTutor();
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        Tutor getTutor();

    }
}
