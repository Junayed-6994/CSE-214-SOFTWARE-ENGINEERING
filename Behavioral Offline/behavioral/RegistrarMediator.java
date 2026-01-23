import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RegistrarMediator {

    private static RegistrarMediator instance;

    private RegistrarMediator() {}

    public static RegistrarMediator getInstance() 
    {
        if (instance == null) instance = new RegistrarMediator();
        return instance;
    }

    public boolean isStudentEnrolled(Course course, Student student) 
    {
        return course.getEnrolledStudents().contains(student);
    }


    public int getEnrolledCount(Course course) 
    {
        return course.getEnrolledStudents().size();
    }


    public void enrollStudentInCourse(Student student, Course c) 
    {
        c.getState().tryEnroll(c, student, this);
    }

    public void addEnrolledStudent(Course course, Student student) 
    {
        course.getEnrolledStudents().add(student);
        student.addEnrolledCourseDirect(course);
    }

    public boolean isStudentInWaitlist(Course course, Student student) 
    {
        return course.getWaitlistedStudents().contains(student);
    }

    public int getWaitlistCount(Course course) 
    {
        return course.getWaitlistedStudents().size();
    }

    public void waitlistStudentForCourse(Student student, Course c) 
    {
        c.getState().addToWaitlist(c, student, this);
    }

    public void addWaitlistedStudent(Course course, Student student) 
    {
        course.getWaitlistedStudents().add(student);
        student.addWaitlistCourseDirect(course);
    }

    public void dropStudentFromCourse(Student student, Course c) 
    {
        c.getState().dropStudent(c, student, this);
    }

    // Perform drop operation with promotion logic
    public boolean performDrop(Course course, Student student) 
    {
        boolean changed = false;
        
        if (isStudentEnrolled(course, student)) 
        {
            course.getEnrolledStudents().remove(student);
            student.removeCourseDirect(course);
            System.out.println("Dropped from enrolled: " + student.name + " from " + course.code);
            changed = true;
            
            //Trying promote from waitlist if space available
            promoteFromWaitlist(course);
            
        } 
        
        else if (isStudentInWaitlist(course, student)) 
        {
            course.getWaitlistedStudents().remove(student);
            student.removeCourseDirect(course);
            System.out.println("Removed from waitlist: " + student.name + " for " + course.code);
            changed = true;
        } 
        else
        {
            System.out.println(student.name + " is neither enrolled nor waitlisted for " + course.code);
        }
        
        return changed;
    }
    
    // Promote one student from waitlist if space available
    private void promoteFromWaitlist(Course course) {
        CourseStatus status = course.getState().getStatus();
        
        if (status == CourseStatus.CANCELLED || status == CourseStatus.DRAFT) 
        {
            return;
        }
        
        if (getEnrolledCount(course) < course.getCapacity()) {
            if (!course.getWaitlistedStudents().isEmpty()) {
                Student promoted = course.getWaitlistedStudents().poll();
                if (promoted != null) {
                    course.getEnrolledStudents().add(promoted);
                    promoted.addEnrolledCourseDirect(course);
                    promoted.removeCourseDirect(course); // ensure waitlist removed too
                    System.out.println("Promoted from waitlist: " + promoted.name + " into " + course.code);
                }
            }
            
            // Update state after promotion
            if (getEnrolledCount(course) >= course.getCapacity()) {
                course.setStatus(CourseStatus.FULL);
            }
        }
    }
    
    // Cancel a course - clear all enrollments and waitlist
    public void cancelCourse(Course course) {
        
        for (Student s : new ArrayList<>(course.getEnrolledStudents())) {
            s.removeCourseDirect(course);
        }
        course.getEnrolledStudents().clear();
        

        for (Student s : new ArrayList<Student>(course.getWaitlistedStudents())) {
            s.removeCourseDirect(course);
        }
        course.getWaitlistedStudents().clear();
        
        System.out.println(course.code + " has been CANCELLED. All students dropped and waitlist cleared.");
    }
    
    // Close course with random waitlist selection
    public void closeWithRandomWaitlistSelection(Course course, int targetCapacity) {
        //System.out.println(course.code + " transitioned FULL -> CLOSED");
        
        if (!course.getWaitlistedStudents().isEmpty()) {
            int availableSlots = targetCapacity - getEnrolledCount(course);
            if (availableSlots > 0) {
                Random random = new Random();
                List<Student> waitlistCopy = new ArrayList<>(course.getWaitlistedStudents());
                int promotionCount = Math.min(availableSlots, waitlistCopy.size());
                
                System.out.println("Randomly selecting " + promotionCount + " student(s) from waitlist:");
                for (int i = 0; i < promotionCount; i++) {
                    int randomIndex = random.nextInt(waitlistCopy.size());
                    Student promoted = waitlistCopy.remove(randomIndex);
                    course.getWaitlistedStudents().remove(promoted);
                    course.getEnrolledStudents().add(promoted);
                    promoted.addEnrolledCourseDirect(course);
                    System.out.println("  Randomly selected: " + promoted.name + " for " + course.code);
                }
            }
        }
    }


    
}
