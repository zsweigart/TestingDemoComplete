package com.yodle.testingdemocomplete.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yodle.testingdemocomplete.R;
import com.yodle.testingdemocomplete.model.Course;
import com.yodle.testingdemocomplete.view.AddMoreViewHolder;
import com.yodle.testingdemocomplete.view.CourseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class GpaRecyclerAdapter extends RecyclerView.Adapter {
    private static final int COURSE_ROW = 1;
    private static final int ADD_MORE_ROW = 2;
    private static final int INITIAL_NUM_COURSES = 4;

    private Context context;
    private List<Course> courseList;

    public GpaRecyclerAdapter(Context context) {
        this.context = context;
        courseList = new ArrayList<>();

        for(int i = 0; i< INITIAL_NUM_COURSES; i++) {
            courseList.add(new Course());
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == COURSE_ROW) {
            view = LayoutInflater.from(context).inflate(R.layout.course_row, parent, false);
            return new CourseViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.add_more_row, parent, false);
            return new AddMoreViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position < courseList.size()) {
            ((CourseViewHolder)holder).setContent(courseList.get(position));
        } else {
            ((AddMoreViewHolder)holder).setContent(this);
        }
    }

    @Override
    public int getItemCount() {
        return courseList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position < courseList.size()) {
            return COURSE_ROW;
        }

        return ADD_MORE_ROW;
    }

    public void addCourse(Course course) {
        courseList.add(course);
        notifyItemInserted(courseList.size() - 1);
    }

    public List<Course> getCourses() {
        return courseList;
    }
}
