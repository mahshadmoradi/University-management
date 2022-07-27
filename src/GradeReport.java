public class GradeReport {
    private Student student;
    private Course course;
    private double grade;

    GradeReport(Student student,Course course){
        this.student=student;
        this.course=course;
    }
    public Student getStudent() {
        return student;
    }
    public Course getCourse() {
        return course;
    }
    public void setGrade(double grade) {
        this.grade = grade;
    }
    public double getGrade() {
        return grade;
    }
    @Override
    public String toString() {
        return this.getStudent().getStudent()+" : "+this.getCourse()+" -> "+this.getGrade();
    }
}
