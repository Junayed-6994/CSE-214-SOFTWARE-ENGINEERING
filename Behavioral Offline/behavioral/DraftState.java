import java.util.Scanner;

public class DraftState implements CourseState {
    @Override
    public boolean tryEnroll(Course course, Student student, RegistrarMediator mediator) 
    {
        System.out.println("Cannot enroll; course is DRAFT (not visible): " + course.code);
        return false;
    }

    @Override
    public boolean addToWaitlist(Course course, Student student, RegistrarMediator mediator) {
        System.out.println("Cannot waitlist; course is DRAFT (not visible): " + course.code);
        return false;
    }

    @Override
    public boolean dropStudent(Course course, Student student, RegistrarMediator mediator) {
        System.out.println("Cannot drop; course is DRAFT (not visible): " + course.code);
        return false;
    }

    @Override
    public void changeStatus(Course course, CourseStatus newStatus, RegistrarMediator mediator) {
        switch(newStatus)
        {
            case OPEN:
                System.out.println(course.code + " transitioned DRAFT -> OPEN");
                course.setStatus(CourseStatus.OPEN);
                break;
            case CLOSED:
                System.out.println(course.code + " transitioned DRAFT -> CLOSED");
                course.setStatus(CourseStatus.CLOSED);
                break;
            case CANCELLED:
                mediator.cancelCourse(course);
                System.out.println(course.code + " transitioned DRAFT -> CANCELLED");
                course.setStatus(CourseStatus.CANCELLED);
                break;
            default:
                System.out.println("Invalid transition from DRAFT to " + newStatus);
        }
    }

    @Override
    public void changeStatusInteractive(Course course, CourseStatus newStatus, Scanner scanner, RegistrarMediator mediator) {
         changeStatus(course, newStatus, mediator);
    }

    @Override
    public CourseStatus getStatus() {
        return CourseStatus.DRAFT;
    }

}
