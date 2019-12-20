package com.codevenue.skillerandroid.viewholders.lessons;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.codevenue.skillerandroid.ItemClickListener;
import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.adapters.lessons.LessonsRequestsRecyclerAdapter;
import com.ramotion.foldingcell.FoldingCell;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LessonsRequestsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public @BindView(R.id.item_lesson_request_fc)
    FoldingCell fcLessonRequest;
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
    public @BindView(R.id.item_lesson_request_title_view_tv_request_status)
    TextView tvTitleViewRequestStatus;
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
    public @BindView(R.id.item_lesson_request_content_view_btn_cancel)
    Button btnCancel;
    private ItemClickListener itemClickListener;

    public LessonsRequestsViewHolder(View itemView, final LessonsRequestsRecyclerAdapter.RequestsRecyclerAdapterListener mListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClickBtnCancel(view, getAdapterPosition());
            }
        });

        fcLessonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fcLessonRequest.toggle(false);
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
