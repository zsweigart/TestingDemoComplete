package com.yodle.testingdemocomplete.model;

public class Course {
    private String courseName;
    private int numCredits;
    private GRADE grade;

    public Course() {
    }

    public Course(String courseName, int numCredits, GRADE grade) {
        this.courseName = courseName;
        this.numCredits = numCredits;
        this.grade = grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getNumCredits() {
        return numCredits;
    }

    public void setNumCredits(int numCredits) {
        this.numCredits = numCredits;
    }

    public GRADE getGrade() {
        return grade;
    }

    public void setGrade(GRADE grade) {
        this.grade = grade;
    }

    public enum GRADE {
        A(4),
        A_MINUS(3.67),
        B_PLUS(3.33),
        B(3.0),
        B_MINUS(2.67),
        C_PLUS(2.33),
        C(2),
        C_MINUS(1.67),
        D_PLUS(1.33),
        D(1.0),
        D_MINUS(.67),
        F(0);

        double gpaPoints;

        GRADE(double gpaPoints) {
            this.gpaPoints = gpaPoints;
        }

        public double getGpaPoints() {
            return gpaPoints;
        }

        public static GRADE getGradeByPosition(int position) {
            for(GRADE grade : GRADE.values()) {
                if(grade.ordinal() == position) {
                    return grade;
                }
            }
            throw new IllegalArgumentException("Unable to find Grade with position: " + position);
        }
    }
}
