/*
 * ******************************************************
 * ********* Creates the course instance
 * ********* 
 * ********* Created By Ross Keddy.             *********
 * ******************************************************
 */
package registrationsystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Ross
 */
public class Course {
    private Instructor instructor;
    public String courseCode, courseName;
    private String room;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private int maxStudents;
    public ArrayList<Student> students;
    public String reqCourseCode;
    
    /**
     *
     * @param instructor is the instructor for this course
     * @param courseCode the course code
     * @param courseName the name of the course
     * @param room the room the course is taught in
     * @param dayOfWeek what day the course is on
     * @param startTime when the course starts
     * @param maxStudents the max students the course can have = 4
     */
    public Course(Instructor instructor, String courseCode, String courseName, String room, DayOfWeek dayOfWeek, LocalTime startTime, int maxStudents) {
        this.instructor = instructor;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.room = room;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.maxStudents = maxStudents;
        
        setProf(instructor);
        setTime(startTime);
        setClassSize(maxStudents);
        students = new ArrayList<>();
    }//end of constructor
    
    /**
     * Second constructor
     * @param teacher
     * @param courseCode
     * @param courseName
     * @param room
     * @param courseDay
     * @param startTime
     * @param maxNum
     * @param reqCourseCode
     */
    public Course(Instructor teacher, String courseCode, String courseName, String room, DayOfWeek courseDay, LocalTime startTime, int maxNum, String reqCourseCode){
        this.courseCode = courseCode;
        setProf(teacher);
        this.courseName = courseName;
        this.room = room;
        this.dayOfWeek = courseDay;
        setTime(startTime);
        setClassSize(maxNum);
        students = new ArrayList();
        this.reqCourseCode = reqCourseCode;
    }//end of constructor
    
    /**
     *
     * @param addProf
     * @returns addProf if the teacher can teach the course
     */
    public Instructor setProf(Instructor addProf){
        if(addProf.canTeach(courseCode) == true){
            return addProf;
        } else {
            instructor = null;
            throw new IllegalArgumentException("Professor " + addProf.toString() + " is not qualified to teach " + courseCode);
        }
    }//end of setProf

    /**
     *
     * @param size
     * @returns the max size of a class = 4
     */
    public String setClassSize(int size){
        if(size > 40){
            size = 40;
            maxStudents = 40;
            return "Max class size = 40, it has been set to 40";
        }
     
        return "valid";
    }//end of setClassSize
    
    /**
     *
     * @returns classSize
     */
    public int getClassSize(){
        return maxStudents;
    }//End of getClassSize
    
    /**
     *
     * @param valid
     * @returns valid if the time is after 8am and before 6pm
     */
    public void setTime(LocalTime valid){
        if((valid.getHour()>= 8) && (valid.getHour() < 18)){
            this.startTime = valid;
        } else {
            throw new IllegalArgumentException("Course start time must be between 08:00-18:00");
        }
    }//end of setTime
    
    /**
     *
     * @returns what room the course is taught in
     */
    public String getRoom(){
        return room;
    }//end of getRoom
    
    /**
     *
     * @param addStudent is the student being added
     */
    public String addStudent(Student addStudent){
        String javaHurts = "";
        
        if(reqCourseCode != null){
            if(!addStudent.getCompletedCourses().contains(reqCourseCode)){
                javaHurts = "Student has not completed the prerequisite course: " + reqCourseCode;
            } else {
                students.add(addStudent);
            }  
        } else if(!addStudent.inGoodStanding()) {
            javaHurts = "The Student is not in good standing and cannot join the course.";
        } else if(students.size() >= maxStudents){
            javaHurts = "Student was not added because the course is full";
        } else {
            students.add(addStudent);
        }
        
        //System.out.print(reqCourseCode);
        return javaHurts;     
    } //end of addStudent
    
    /**
     *
     * @returns the class list
     */
    public String showClassList(){
        String classList = "";
        for (Student p : students){
            classList = classList + p;
        }
        return classList;
    }//end of showClassList
    
    /**
     *
     * @returns the average student time at the college
     */
    public double averageStudentTimeAtCollege(){
        double accumalated = 0;
        int counter = 0;
        LocalDate today = LocalDate.now();

        for (Student p : students){
            accumalated = accumalated + (today.getYear() - p.getYearEnrolled());
            counter = counter + 1;
        }
        double total = accumalated / counter;
        return total - 1;
    }//end of averageStudentTimeAtCollege
    
    /**
     *
     * @returns toString
     */
    @Override
    public String toString(){
        return courseCode + "-" + courseName;
    }//end of toString
    
    /**
     *
     * @returns the instructor
     */
    public Instructor getInstructor(){
        return instructor;
    }//end of getInstructor
    
    /**
     *
     * @returns the dayoftheweek the course is taught on
     */
    public DayOfWeek getDayOfCourse(){
        return dayOfWeek;
    }//end of getDayofCourse
    
    /**
     *
     * @return
     */
    public String getCourseDayAndTime(){
        String time = "";
        time = time + getDayOfCourse();
        time = time + "'s, starting at " + startTime();
        
        return time;
    }//End of getCourseDayAndTime
    
    /**
     *
     * @returns the classes startTime
     */
    public LocalTime startTime(){
        return startTime;
    }//end of startTime
    
    /**
     *
     * @return true if class is mature
     */
    public boolean matureClass(){
        double accumalated = 0;
        int counter = 0;

        for (Student p : students){
            accumalated = accumalated + p.getAge();
            counter = counter + 1;
        }
        double avg = accumalated / counter;

        if(avg > 25){
            return true;
        } else {
            return false;
        } 
    }//end of matureClass
    
    public String getPrerequisite(){
        return "" + reqCourseCode;
    }//end of method
}//end of class
