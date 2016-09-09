/*
 * ******************************************************
 * ********* Description.
 * ********* Date.
 * ********* Created By Ross Keddy.             *********
 * ******************************************************
 */
package registrationsystem;

/**
 *
 * @author Ross
 */
public class CompletedCourse {
    Course course;
    int grade;
    
    /**
     *
     * @param course
     * @param grade
     */
    public CompletedCourse(Course course, int grade){
        this.course = course;
        setGrade(grade);
    }//End of constructor
    
    /**
     * sets the grade
     */
    private void setGrade(int grade){
        if(grade > 100 || grade < 0){
            throw new IllegalArgumentException("The Lab Tech is not qualified to host the lab");
        } else {
            this.grade = grade;
        }
    }//End of setGrade
    
    /**
     *
     * @return
     */
    public int getGrade(){
        return grade;
    }//End of getGrade
    
    /**
     *
     * @return
     */
    public Course getCourse(){
        return course;
    }//End of getCourse
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return "[" + getCourse() + " =" + getGrade();
    }//end of toString
}//End of Class
