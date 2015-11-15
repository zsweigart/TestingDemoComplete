package com.yodle.testingdemocomplete.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yodle.testingdemocomplete.R;
import com.yodle.testingdemocomplete.adapter.GpaRecyclerAdapter;
import com.yodle.testingdemocomplete.model.Course;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMoreViewHolder extends RecyclerView.ViewHolder {
    private GpaRecyclerAdapter gpaRecyclerAdapter;

    public AddMoreViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setContent(GpaRecyclerAdapter recyclerAdapter) {
        gpaRecyclerAdapter = recyclerAdapter;
    }

    @OnClick(R.id.add_more_row)
    public void addMoreRow() {
        gpaRecyclerAdapter.addCourse(new Course());
    }
}
