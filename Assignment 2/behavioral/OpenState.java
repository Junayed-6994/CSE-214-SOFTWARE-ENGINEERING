import java.util.Scanner;

public class OpenState implements CourseState {

    @Override
    public boolean tryEnroll(Course course, Student student, RegistrarMediator mediator) 
    {
        if (mediator.isStudentEnrolled(course, student)) 
        {
            System.out.println("Already enrolled: " + student.name + " in " + course.code);
            return true;
        }
        
        if (mediator.getEnrolledCount(course) < course.getCapacity()) 
        {
            mediator.addEnrolledStudent(course, student);
            System.out.println("Enrolled: " + student.name + " in " + course.code);
            
            if (mediator.getEnrolledCount(course) >= course.getCapacity()) 
            {
                course.setStatus(CourseStatus.FULL);
                System.out.println(course.code + " is now FULL.");
            }
            return true;
        } 
        else 
        {
            // capacity reached while OPEN -> FULL and suggest waitlist
            course.setStatus(CourseStatus.FULL);
            System.out.println(course.code + " reached capacity; status set to FULL. Try waitlisting.");
            return false;
        }
    }

    @Override
    public boolean addToWaitlist(Course course, Student student, RegistrarMediator mediator) {
            System.out.println("Course is OPEN; try enrolling instead: " + course.code);
            return false;
    }

    @Override
    public boolean dropStudent(Course course, Student student, RegistrarMediator mediator) {
        return mediator.performDrop(course, student);
    }

    @Override
    public void changeStatus(Course course, CourseStatus newStatus, RegistrarMediator mediator) {
        switch (newStatus) {
            case CLOSED:
                System.out.println(course.code + " transitioned OPEN -> CLOSED");
                course.setStatus(CourseStatus.CLOSED);
                break;
            case DRAFT:
                System.out.println(course.code + " transitioned OPEN -> DRAFT");
                course.setStatus(CourseStatus.DRAFT);
                break;
            case CANCELLED:
                mediator.cancelCourse(course);
                //System.out.println(course.code + " transitioned OPEN -> CANCELLED");
                course.setStatus(CourseStatus.CANCELLED);
                break;
            default:
                System.out.println("Invalid transition from OPEN to " + newStatus);
        }
    }

    @Override
    public void changeStatusInteractive(Course course, CourseStatus newStatus, Scanner scanner, RegistrarMediator mediator) {
        changeStatus(course, newStatus, mediator);
    }

    @Override
    public CourseStatus getStatus() {
        return CourseStatus.OPEN;
    }

    @Override
    public boolean isVisibleToStudents() {
        return true;
    }

}
