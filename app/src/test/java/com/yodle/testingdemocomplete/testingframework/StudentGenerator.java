package com.yodle.testingdemocomplete.testingframework;

import com.yodle.testingdemocomplete.model.Student;

import java.util.HashSet;
import java.util.Set;

public class StudentGenerator {
    public Student first;
    public Student second;

    public Set<Student> getValidStudentLogins() {
        Set<Student> students = new HashSet<>();
        first = new Student("First", "First", "first@first.com", "asdf1234", 21, Student.Year.JUNIOR);
        second = new Student("Second", "Second", "second@second.com", "password", 19, Student.Year.FRESHMAN);

        students.add(first);
        students.add(second);

        return students;
    }
}
