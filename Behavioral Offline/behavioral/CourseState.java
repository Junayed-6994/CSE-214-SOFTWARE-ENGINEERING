import java.util.Scanner;

public interface CourseState {
    boolean tryEnroll(Course course, Student student,RegistrarMediator mediator);
    boolean addToWaitlist(Course course, Student student,RegistrarMediator mediator);
    boolean dropStudent(Course course, Student student,RegistrarMediator mediator   );
    void changeStatus(Course course, CourseStatus newStatus,RegistrarMediator mediator);
    void changeStatusInteractive(Course course, CourseStatus newStatus,Scanner scanner,RegistrarMediator mediator);
    CourseStatus getStatus();

    boolean isVisibleToStudents();
}
