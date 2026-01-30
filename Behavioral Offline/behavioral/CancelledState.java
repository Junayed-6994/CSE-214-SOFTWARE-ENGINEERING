import java.util.Scanner;

public class CancelledState implements CourseState {
    @Override
    public boolean tryEnroll(Course course, Student student, RegistrarMediator mediator)
    {
        System.out.println("Cannot enroll; course is CANCELLED: " + course.code);
        return false;
    }

    @Override
    public boolean addToWaitlist(Course course, Student student, RegistrarMediator mediator)
    {
        System.out.println("Cannot waitlist; course is CANCELLED: " + course.code);
        return false;
    }

    @Override
    public boolean dropStudent(Course course, Student student, RegistrarMediator mediator) 
    {
        System.out.println("Cannot drop; course is DRAFT (not visible): " + course.code);
        return false;
    }

    @Override
    public void changeStatus(Course course, CourseStatus newStatus, RegistrarMediator mediator) 
    {
        switch(newStatus)
        {
            case DRAFT:
                System.out.println(course.code + " transitioned CANCELLED -> DRAFT " + "(reinstating course)");
                course.setStatus(CourseStatus.DRAFT);
                break;
            default:
                System.out.println("Invalid: CANCELLED can only transition to DRAFT for " + course.code);
        }   
    }

    @Override
    public void changeStatusInteractive(Course course, CourseStatus newStatus, Scanner scanner, RegistrarMediator mediator) {
        changeStatus(course, newStatus, mediator);
    }

    @Override
    public CourseStatus getStatus() {
        return CourseStatus.CANCELLED;
    }


}
