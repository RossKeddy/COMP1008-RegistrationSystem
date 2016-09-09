/*
 * ******************************************************
 * ********* Description.
 * ********* Date.
 * ********* Created By Ross Keddy.             *********
 * ******************************************************
 */
package registrationsystem;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 *
 * @author Ross
 */
public class CourseWithLab extends Course {
    private Instructor prof;
    private String courseCode, courseName, room;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private int numberOfStudents;
    private Instructor labTech;
    private String labRoom;
    private DayOfWeek labDayofWeek;
    private LocalTime labStartTime;
    private String prerequisite;
    
    /**
     *
     * @param prof
     * @param courseCode
     * @param courseName
     * @param room
     * @param dayOfWeek
     * @param startTime
     * @param numberOfStudents
     * @param labTech
     * @param labRoom
     * @param labDayofWeek
     * @param labStartTime
     */
    public CourseWithLab (Instructor prof, String courseCode, String courseName, String room, DayOfWeek dayOfWeek, LocalTime startTime, int numberOfStudents, 
                    Instructor labTech, String labRoom, DayOfWeek labDayofWeek, LocalTime labStartTime) {
        
        super(prof, courseCode, courseName, room, dayOfWeek, startTime, numberOfStudents);
        this.prof = prof;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.room = room;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.numberOfStudents = numberOfStudents;
        
        setLabTech(labTech);
        this.labRoom = labRoom;
        this.labDayofWeek = labDayofWeek;
        setLabStartTime(labStartTime);
    }//End of first constructor

    /**
     * Second constructor
     * @param prof
     * @param courseCode
     * @param courseName
     * @param room
     * @param dayOfWeek
     * @param startTime
     * @param numberOfStudents
     * @param prerequisite
     * @param labTech
     * @param labRoom
     * @param labDayofWeek
     * @param labStartTime
     */
    public CourseWithLab (Instructor prof, String courseCode, String courseName, String room, DayOfWeek dayOfWeek, LocalTime startTime, int numberOfStudents, String prerequisite, Instructor labTech, String labRoom, DayOfWeek labDayofWeek, LocalTime labStartTime) {
        super(prof, courseCode, courseName, room, dayOfWeek, startTime, numberOfStudents, prerequisite);
        this.prof = prof;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.room = room;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.numberOfStudents = numberOfStudents;
        this.prerequisite = prerequisite;
        
        setLabTech(labTech);
        this.labRoom = labRoom;
        this.labDayofWeek = labDayofWeek;
        setLabStartTime(labStartTime);
    }//End of second constructor
    
    /**
     * sets the lab tech
     * @param valid
     */
    public void setLabTech(Instructor valid){
        if(valid.canTeach(courseCode+"-LAB")){
            this.labTech = valid;
        } else {
            throw new IllegalArgumentException("The Lab Tech is not qualified to host the lab");
        }
    }//End of setLabTech
    
    /**
     * returns lab tech
     * @return
     */
    public Instructor getLabTech(){
        return labTech;
    }//end of getAgeInYears
    
    /**
     * returns the time for the lab
     * @return
     */
    public String getLabClassAndTime(){
        String time = "room: " + labRoom + ", ";
        time = time + labDayofWeek + " starting at " + labStartTime;
        
        return time;
    }//end of getLabClass&Time
    
    /**
     * sets the labs start time
     * @param valid
     */
    public void setLabStartTime (LocalTime valid){
        LocalTime eightAM = LocalTime.of(8, 0, 00);
        LocalTime sixPM = LocalTime.of(18, 0, 00);
        int eightAM2 = eightAM.getHour();
        int sixPM2 = sixPM.getHour();
        
        if(valid.getHour() > eightAM2 && valid.getHour() < sixPM2){
            this.labStartTime = valid;
        } else {
            throw new IllegalArgumentException("The lab start time must be between 08:00-18:00");
        }
    }//end of setTime
    
    
    /**
     * grabs course toString and appends
     * @return 
     */
    @Override
    public String toString(){
        return super.toString()+ " with lab";
    }//end of toString
}//End of Class
