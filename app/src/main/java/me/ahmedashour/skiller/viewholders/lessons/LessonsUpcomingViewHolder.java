package me.ahmedashour.skiller.viewholders.lessons;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ahmedashour.skiller.ItemClickListener;
import me.ahmedashour.skiller.R;
import me.ahmedashour.skiller.adapters.lessons.LessonsUpcomingRecyclerAdapter;

public class LessonsUpcomingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public @BindView(R.id.item_lesson_upcoming_fc)
    FoldingCell fcLessonUpcoming;
    public @BindView(R.id.item_lesson_title_view_cv_iv_photo)
    ImageView ivTitleProfilePic;
    public @BindView(R.id.item_lesson_title_view_cv_tv_student_name)
    TextView tvTitleStudentName;
    public @BindView(R.id.item_lesson_title_view_cv_tv_price)
    TextView tvTitleViewPrice;
    public @BindView(R.id.item_lesson_title_view_cv_tv_currency)
    TextView tvTitleViewCurrency;
    public @BindView(R.id.item_lesson_title_view_cv_tv_skill)
    TextView tvTitleViewSkill;
    public @BindView(R.id.item_lesson_title_view_cv_tv_date)
    TextView tvTitleViewDate;
    public @BindView(R.id.item_lesson_title_view_cv_tv_location)
    TextView tvTitleViewLocation;
    public @BindView(R.id.item_lesson_upcoming_title_view_btn_unbook)
    Button btnTitleViewUnbook;
    public @BindView(R.id.item_lesson_content_view_cv_info_tutor_iv_photo)
    ImageView ivProfilePicture;
    public @BindView(R.id.item_lesson_content_view_cv_info_tutor_tv_name)
    TextView tvName;
    public @BindView(R.id.item_lesson_content_view_cv_info_tutor_tv_rate)
    TextView tvRate;
    public @BindView(R.id.item_lesson_content_view_cv_info_tutor_img_btn_enter_profile)
    ImageButton btnEnterProfile;
    public @BindView(R.id.item_lesson_content_view_cv_info_lesson_tv_skill)
    TextView tvSkillName;
    public @BindView(R.id.item_lesson_content_view_cv_info_lesson_tv_time_start)
    TextView tvTimeStart;
    public @BindView(R.id.item_lesson_content_view_cv_info_lesson_tv_time_end)
    TextView tvTimeEnd;
    public @BindView(R.id.item_lesson_content_view_cv_info_lesson_tv_date)
    TextView tvDate;
    public @BindView(R.id.item_lesson_content_view_cv_info_lesson_tv_location)
    TextView tvLocation;
    public @BindView(R.id.item_lesson_content_view_cv_info_payment_tv_price)
    TextView tvPrice;
    public @BindView(R.id.item_lesson_content_view_cv_info_payment_tv_currency)
    TextView tvCurrency;
    public @BindView(R.id.item_lesson_content_view_cv_info_payment_tv_payment_method)
    TextView tvPaymentMethod;
    public @BindView(R.id.item_cv_notes_tv_notes)
    TextView tvNotes;
    public @BindView(R.id.item_lesson_upcoming_content_view_btn_unbook)
    Button btnUnbook;
    private ItemClickListener itemClickListener;

    public LessonsUpcomingViewHolder(View itemView, final LessonsUpcomingRecyclerAdapter.UpcomingRecyclerAdapterListener mListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);

        btnUnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClickBtnUnbook(view, getAdapterPosition());
            }
        });
        fcLessonUpcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fcLessonUpcoming.toggle(false);
            }
        });
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onItemClickListener(v, getAdapterPosition(), false);
    }
}