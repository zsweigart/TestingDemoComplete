package com.yodle.testingdemocomplete.persistence;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yodle.testingdemocomplete.model.Student;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class SharedPrefsDatastore implements Datastore {
    public final static String PREFRENCES_NAME = "TestingDemoSharedPreferences";

    private final static String STUDENTS = "TestingDemo:Students";

    SharedPreferences sharedPreferences;
    Gson gson;

    public SharedPrefsDatastore(Application app, Gson gson) {
        sharedPreferences = app.getSharedPreferences(PREFRENCES_NAME, Activity.MODE_PRIVATE);
        this.gson = gson;
    }

    public void persistStudent(Student student) {
        Set<Student> students = getStudents();

        for(Student currentStudent : students) {
            if(currentStudent.getEmail().equals(student.getEmail())) {
                students.remove(currentStudent);
                students.add(student);
                persistStudentString(students);
                return;
            }
        }

        students.add(student);
        persistStudentString(students);
    }

    private void persistStudentString(Set<Student> students) {
        String studentString = gson.toJson(students);
        sharedPreferences
                .edit()
                .putString(STUDENTS, studentString)
                .apply();
    }

    public Set<Student> getStudents() {
        String studentString = sharedPreferences.getString(STUDENTS, "");
        Type token = new TypeToken<Set<Student>>() {}.getType();
        Set<Student> students = gson.fromJson(studentString, token);
        students = students == null ? new HashSet<Student>() : students;
        return students;
    }
}
