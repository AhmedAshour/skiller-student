/*

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.codevenue.skillerandroid.ItemClickListener;
import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.adapters.CoursesRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoursesViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tv_courses_course_title) TextView tvCourseTitle;
    @BindView(R.id.tv_courses_num_sessions) TextView tvNumSessions;
    @BindView(R.id.tv_courses_hrs_per_session) TextView tvHrsPerSession;
    @BindView(R.id.tv_courses_total_hours) TextView tvTotalHrs;
    @BindView(R.id.tv_courses_price) TextView tvPrice;
    @BindView(R.id.tv_courses_choose) TextView tvChoose;
    private int lastPosition = -1;

    public CoursesViewHolder(View itemView, final CoursesRecyclerViewListener mListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);


        tvChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.tvChooseOnClick(view, getAdapterPosition());
                lastPosition = getAdapterPosition();
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

    public interface CoursesRecyclerViewListener{
        public void tvChooseOnClick(View view, int postition);
    }
}
*/
