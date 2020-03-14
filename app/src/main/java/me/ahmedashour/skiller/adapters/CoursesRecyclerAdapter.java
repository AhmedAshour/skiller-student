package me.ahmedashour.skiller.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ahmedashour.skiller.R;
import me.ahmedashour.skiller.model.Course;

public class CoursesRecyclerAdapter extends RecyclerView.Adapter<CoursesRecyclerAdapter.CoursesViewHolder> {

    private CoursesRecyclerViewListener mListener;
    private List<Course> courseArrayList;
    private Context context;
    private int lastPosition = -1;


    public CoursesRecyclerAdapter(Context context, List<Course> courseArrayList, CoursesRecyclerViewListener mListener) {
        this.courseArrayList = courseArrayList;
        this.context = context;
        this.mListener = mListener;
    }

    public CoursesRecyclerAdapter() {
    }

    @NonNull
    @Override
    public CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.available_courses_item, parent, false);
        return new CoursesViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesViewHolder holder, int position) {
        Course course = courseArrayList.get(position);
        try {
            if (lastPosition == position)
                holder.setChosenCourse();
            else
                holder.setNotChosenCourse();
            holder.setNumSessions(course.getNumSessions());
            holder.setPrice(course.getPrice());
            holder.setCourseTitle(course.getCourseTitle());
            holder.setHrsPerSession(course.getNumSessions(), course.getNumHoursPerSession());

        } catch (Exception e) {
        }
    }

    @Override
    public int getItemCount() {
        if (courseArrayList == null) return 0;
        return courseArrayList.size();
    }

    public class CoursesViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cv_courses_item)
        CardView cvCoursesItem;
        @BindView(R.id.tv_courses_course_title)
        TextView tvCourseTitle;
        @BindView(R.id.tv_courses_num_sessions) TextView tvNumSessions;
        @BindView(R.id.tv_courses_hrs_per_session) TextView tvHrsPerSession;
        @BindView(R.id.tv_courses_total_hours) TextView tvTotalHrs;
        @BindView(R.id.tv_courses_price) TextView tvPrice;
        @BindView(R.id.tv_courses_choose) TextView tvChoose;

        public CoursesViewHolder(View itemView, final CoursesRecyclerViewListener mListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);


            tvChoose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.tvChooseOnClick(view, getAdapterPosition());
                    lastPosition = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }

        public void setCourseTitle(String courseTitle){
            tvCourseTitle.setText(courseTitle);
        }

        public void setNumSessions(String numSessions){
            tvNumSessions.setText(numSessions+" Sessions");
        }

        public void setHrsPerSession(String numSessions,String hrsPerSession){
            int calcTotalHrs = Integer.valueOf(numSessions) * Integer.valueOf(hrsPerSession);
            tvHrsPerSession.setText(hrsPerSession+" Hrs/session");
            tvTotalHrs.setText("("+String.valueOf(calcTotalHrs)+" Hrs Total)");
        }

        public void setPrice(String price){
            tvPrice.setText(price+" LE");
        }
        public void setChosenCourse(){
            cvCoursesItem.setCardBackgroundColor(itemView.getResources().getColor(R.color.colorAccent));
            tvCourseTitle.setTextColor(itemView.getResources().getColor(R.color.color_white));
            tvNumSessions.setTextColor(itemView.getResources().getColor(R.color.color_white_74));
            tvHrsPerSession.setTextColor(itemView.getResources().getColor(R.color.color_white_74));
            tvTotalHrs.setTextColor(itemView.getResources().getColor(R.color.color_white_74));
            tvPrice.setTextColor(itemView.getResources().getColor(R.color.color_white));

        }
        public void setNotChosenCourse(){
            cvCoursesItem.setCardBackgroundColor(itemView.getResources().getColor(R.color.color_white));
            tvCourseTitle.setTextColor(itemView.getResources().getColor(R.color.color_black));
            tvNumSessions.setTextColor(itemView.getResources().getColor(R.color.color_black_74));
            tvHrsPerSession.setTextColor(itemView.getResources().getColor(R.color.color_black_74));
            tvTotalHrs.setTextColor(itemView.getResources().getColor(R.color.color_black_74));
            tvPrice.setTextColor(itemView.getResources().getColor(R.color.colorPrimary));


        }


    }
    public interface CoursesRecyclerViewListener{
        void tvChooseOnClick(View view, int postition);
    }
}
