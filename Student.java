/*
 * ******************************************************
 * ********* Saves a students info
 * ********* 2016/02/15
 * ********* Created By Ross Keddy 200314382    *********
 * ******************************************************
 */
package registrationsystem;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

/**
 *
 * @author Ross
 */
public class Student {
    private String firstName;
    private String lastName;
    private String city;
    private String streetAddress;
    private String postalCode;
    private int studentNumber;
    private String program;
    private LocalDate yearEnrolled;
    private LocalDate birthDate;
    public ArrayList<CompletedCourse> completed;
    
    private boolean suspended; //student is in good standing yay/nay
    /**
     *
     * @param firstName is the students first name
     * @param lastName is the students last name
     * @param streetAddress is the students street address
     * @param city is the city the student lives in
     * @param postalCode is the student's postal code
     * @param program the student's major
     * @param studentNumber 
     * @param yearEnrolled the year the student enrolled
     * @param birthDate the students birthdate
     */
    public Student(String firstName, String lastName, String streetAddress, String city, String postalCode, 
    String program, int studentNumber, LocalDate yearEnrolled, LocalDate birthDate)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.postalCode = postalCode;
        this.program = program;
        this.studentNumber = studentNumber;
        this.yearEnrolled = yearEnrolled;
        setBirthday(birthDate);
        completed = new ArrayList<>();
    }//End of constructor
    
    /**
     * returns the students full address
     * @return
     */
    public String getAddress(){
        return streetAddress + " " + city + " " + postalCode;
    }//End of getAddress
    
    /**
     * returns the students birthyear
     * @return
     */
    public int getYearBorn(){
        return birthDate.getYear();
    }//End of getYearBorn
    
    /**
     * returns & validates the students age
     * @return
     */
    public int getAge(){
        LocalDate today = LocalDate.now();
        long diff = Period.between(birthDate, today).getYears();
        
        if(diff > 100){
            return 0;
        } else {
            return (int) diff;
        }
    }//End of getAge
    
    /**
     * validates the students age
     * @param newBirthDay
     */
    public void setBirthday(LocalDate newBirthDay){
        LocalDate today = LocalDate.now();
        long diff = Period.between(newBirthDay, today).getYears();
        
            if(diff > 100){
                throw new IllegalArgumentException(newBirthDay + " would make " + firstName + " over 100 years old");
            } else {
                birthDate = newBirthDay;
            }
    }//End of setBirthday
    
    public LocalDate getBirthday(){
        return birthDate;
    }//End of getBirthday
     
    /**
     * returns the year the student enrolled in
     * @return
     */
    public int getYearEnrolled(){
        return yearEnrolled.getYear();
    }//End of getYearEnrolled
    
    /**
     * changes the students address
     * @param streetAddress is the address
     * @param city is the city
     * @param postalCode is the postalcode
     */
    public void changeAddress(String streetAddress, String city, String postalCode){
        this.streetAddress = streetAddress;
        this.city = city;
        this.postalCode = postalCode;
    }//End of changeAddress
    
    /**
     * returns if the student is in good standing or not
     * @return suspended
     */
    public boolean inGoodStanding(){
        return suspended != true;
    }//End of inGoodStanding
    
    /**
     * suspends student
     * @return suspended = true
     */
    public boolean suspendStudent(){
        return suspended = true;
    }//End of suspendStudent
    
    /**
     * reinstates student
     * @return suspended = false
     */
    public boolean reinstateStudent(){
        return suspended = false;
    }//End of reinstateStudent

    /**
     * returns the students name and major
     * @return the students information
     */
    @Override
    public String toString(){
        return firstName + " " + lastName + ", student number: " + studentNumber;
    }//end of toString

    /**
     * Adds the completedcourse
     * @param addCourse
     * @param grade
     */
    public void addCompletedCourse(Course addCourse, int grade) {
        if(grade > 50 && grade < 100){
            CompletedCourse course = new CompletedCourse(addCourse, grade);
            completed.add(course);
        } else if (grade > 100 || grade < 0){
            throw new IllegalArgumentException("grade must be 0-100 inclusive");
        }
    }//End of addCompletedCourse
    
    /**
     *
     * @returns the students number
     */
    public int getStudentNumber(){
        return studentNumber;
    }//End of getStudentNumber
    
    /**
     *
     * @returns the courses the student has completed
     */
    public String getCompletedCourses() {
        String courses = "[";
        for (CompletedCourse course : completed){
            courses = courses + course.course + " grade=" + course.grade;
        }
        return courses + "]";
    }//End of getCompletedCourses
    
    /*•	hasCompleted – This accepts a String with a course code as an argument.  
    The method returns TRUE if the student has earned a mark of 50% or higher in the course, false otherwise.*/
    /**
     * 
     * @param course
     * @returns true or false if the student has passed the class
     */
    public boolean hasCompleted(String course){
        int grades = 0;
        
        for (CompletedCourse p : completed) {
            if(p.course.courseCode == course) {
                grades = p.grade;
                break;
            }
        }
        
        if(grades < 50){
            return false;
        } else {
            return true;
        }
    }//End of hasCompleted
}//End of Class
