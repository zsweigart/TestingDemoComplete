package com.yodle.testingdemocomplete.persistence;

import com.yodle.testingdemocomplete.model.Student;

import java.util.Set;

public interface Datastore {
    void persistStudent(Student student);
    Set<Student> getStudents();
}
