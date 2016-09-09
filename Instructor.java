/*
 * ******************************************************
 * ********* Creates an Instructor
 * ********* 2016/02/27
 * ********* Created By Ross Keddy.             *********
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
public class Instructor {
    public String firstName, lastName;
    private int employeeNumber;
    private String streetAddress, city, postalCode;
    private LocalDate hireDate, birthday;
    public ArrayList<String> Courses;
            
    /**
     *
     * @param firstName is the instructors first name
     * @param lastName is the students last name
     * @param employeeNumber stores the employees number
     * @param streetAddress the instructors address
     * @param city ^^^
     * @param postalCode^^
     * @param hireDate is when he was hired
     * @param birthday the instructors birthday
     */
    public Instructor(String firstName, String lastName, int employeeNumber, String streetAddress, String city, String postalCode, LocalDate hireDate, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeNumber = employeeNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.postalCode = postalCode;
        this.hireDate = hireDate;
        
        setHireDate(hireDate);
        setBirthday(birthday);
        Courses = new ArrayList<>();
    }//End of constructor
    
    /**
     * lists the subject the instructor can teach
     * @return
     */
    public String listOfSubjectsCertifiedToTeach(){
        String classList = "[";
        int counter = 0;
        
        for (String c : Courses){
            counter = counter + 1;
            classList = classList + c;
            if (counter == 1){
                classList = classList + ", ";
            }
        }
        
        classList = classList + "]";
        
        if(Courses.isEmpty()){
            return "not qualified to teach courses yet.";
        } else {
            return classList;
        }
        
    }//End of listOfSubjectsCertifiedToTeach
    
    /**
     *
     * @param addCourse gets added to the course list
     */
    public void addCourseToAbilities(String addCourse){
        if(!Courses.contains(addCourse)){
            Courses.add(addCourse);
        }
    }//end of addCourseToAbilities
    
    /**
     *
     * @param courseCode
     * @returns true if the teacher can teach the course
     */
    public boolean canTeach(String courseCode){
        if(Courses.contains(courseCode)){
            return true;
        } else {
            return false;
        }
    }//End of canTeach
    
    /**
     *
     * @returns the hireDate 
     */
    private void setHireDate(LocalDate input){
        LocalDate today = LocalDate.now();
        long diff = Period.between(input, today).getYears();
        
            if(diff > 80){
                throw new IllegalArgumentException(input + " as a hire date would mean Anita started working over " + 80 + " years ago");
            } else {
                hireDate = input;
            }
    }//End of setHireDate
    
    /**
     *
     * @returns the birthday, if invalid sets it to null
     */
    public void setBirthday(LocalDate input){
        LocalDate today = LocalDate.now();
        long diff = Period.between(input, today).getYears();
        
            if(diff > 100){
                throw new IllegalArgumentException(input + " would make " + firstName + " " + lastName + " over 100 years old");
            } else {
                birthday = input;
            }
    }//End of setBirthday
    
    /**
     *
     * @returns how long the instructor has been at the college for
     */
    public int yearsAtCollege(){
        LocalDate today = LocalDate.now();
        long diff = Period.between(hireDate, today).getYears();
        
        return (int) diff;
    }//end of yearsAtCollege
    
    /**
     *
     * @returns the age
     */
    public int getAgeInYears(){
        LocalDate today = LocalDate.now();
        long diff = Period.between(birthday, today).getYears();
        
        return (int) diff;
    }//end of getAgeInYears
    
    /**
     * sets address
     * @param streetAddress
     * @param city
     * @param postalCode
     */
    public void changeAddress(String streetAddress, String city, String postalCode){
        this.streetAddress = streetAddress;
        this.city = city;
        this.postalCode = postalCode;
    }//end of changeAddress
    
    /**
     *
     * @returns the address as string
     */
    public String getAddress(){
        return streetAddress + ", " + city + ", " + postalCode;
    }//end of getAddress
    
    /**
     *
     * @returns toString
     */
    @Override
    public String toString(){
        return firstName + " " + lastName;
    }//end of toString
}//end of class
