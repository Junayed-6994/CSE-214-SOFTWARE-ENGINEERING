import java.util.Scanner;

public class CloseState implements CourseState {
    @Override
    public boolean tryEnroll(Course course, Student student, RegistrarMediator mediator)
    {
        System.out.println("Cannot enroll; course is CLOSED: " + course.code);
        return false;
    }

    @Override
    public boolean addToWaitlist(Course course, Student student, RegistrarMediator mediator)
    {
        System.out.println("Cannot waitlist; course not accepting waitlist: " + course.code);
        return false;
    }

    @Override
    public boolean dropStudent(Course course, Student student, RegistrarMediator mediator)
    {
        return mediator.performDrop(course, student);
    }

    @Override
    public void changeStatus(Course course, CourseStatus newStatus, RegistrarMediator mediator)
    {
        switch (newStatus) {
            case OPEN:
                System.out.println(course.code + " transitioned CLOSED -> OPEN");
                course.setStatus(CourseStatus.OPEN);
                break;
            case DRAFT:
                System.out.println(course.code + " transitioned CLOSED -> DRAFT");
                course.setStatus(CourseStatus.DRAFT);
                break;
            case CANCELLED:
                mediator.cancelCourse(course);
                System.out.println(course.code + " transitioned CLOSED -> CANCELLED");
                course.setStatus(CourseStatus.CANCELLED);
                break;
            default:
                System.out.println("Invalid transition from CLOSED to " + newStatus);
        }
    }

    @Override
    public void changeStatusInteractive(Course course, CourseStatus newStatus, Scanner scanner, RegistrarMediator mediator) {
        changeStatus(course, newStatus, mediator);
    }

    @Override
    public CourseStatus getStatus() {
        return CourseStatus.CLOSED;
    }

    @Override
    public boolean isVisibleToStudents() {
        return true;
    }

}
