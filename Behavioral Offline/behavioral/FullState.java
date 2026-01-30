import java.util.Scanner;

public class FullState implements CourseState {
    @Override
    public boolean tryEnroll(Course course, Student student, RegistrarMediator mediator) 
    {
        System.out.println("Cannot enroll; course is FULL. You may waitlist: " + course.code);
        return false;
    }

    @Override
    public boolean addToWaitlist(Course course, Student student, RegistrarMediator mediator) {

        if (course.isStudentEnrolled(student)) 
        {
            System.out.println("Already enrolled; no need to waitlist: " + student.name + " for " + course.code);
            return false;
        }

        if (course.isStudentInWaitlist(student)) 
        {
            System.out.println("Already waitlisted: " + student.name + " for " + course.code);
            return true;
        }
        
        mediator.addWaitlistedStudent(course, student);
        System.out.println("Waitlisted: " + student.name + " for " + course.code);
        return true;
    }

    @Override
    public boolean dropStudent(Course course, Student student, RegistrarMediator mediator) {
        boolean dropped = mediator.performDrop(course, student);

        if (dropped) 
        {
            // Recalculate status: FULL -> OPEN if space
            if (course.getEnrolledCount() < course.getCapacity()) 
            {
                course.setStatus(CourseStatus.OPEN);
                System.out.println(course.code + " status changed to OPEN due to available capacity.");
            }
        }

        return dropped;
    }

    @Override
    public void changeStatus(Course course, CourseStatus newStatus, RegistrarMediator mediator) {
        switch (newStatus)
        {
            case CLOSED:
                mediator.closeWithRandomWaitlistSelection(course, course.getCapacity());
                System.out.println(course.code + " transitioned FULL -> CLOSED"); 
                course.setStatus(CourseStatus.CLOSED);
                break;
            case CANCELLED:
                mediator.cancelCourse(course);
                //System.out.println(course.code + " transitioned FULL -> CANCELLED");
                course.setStatus(CourseStatus.CANCELLED);
                break;
            default:
                System.out.println("Invalid transition from FULL to " + newStatus);
        }
    }

    @Override
    public void changeStatusInteractive(Course course, CourseStatus newStatus, Scanner scanner, RegistrarMediator mediator) {
        if (newStatus == CourseStatus.CLOSED) 
        {
            if (course.getWaitlistCount() > 0) 
            {
                System.out.println(course.code + " has " + course.getWaitlistCount() + " student(s) on waitlist.");
                System.out.print("Do you want to increase capacity before closing? (Enter new capacity, or 0 to not increase): ");
                try 
                {
                    int newCapacity = Integer.parseInt(scanner.nextLine().trim());
                    if (newCapacity > 0) 
                    {
                        if (newCapacity > course.getCapacity()) 
                        {
                            course.setCapacity(newCapacity);
                            System.out.println("Capacity increased to " + newCapacity);
                            mediator.closeWithRandomWaitlistSelection(course, newCapacity);
                        } 
                        else 
                        {
                            System.out.println("New capacity must be greater than current capacity (" + course.getCapacity() + "). No change.");
                            mediator.closeWithRandomWaitlistSelection(course, course.getCapacity());
                        }
                    } 
                    else 
                    {
                        System.out.println("No capacity increase.");
                        mediator.closeWithRandomWaitlistSelection(course, course.getCapacity());
                    }
                } 
                catch (NumberFormatException e) 
                {
                    System.out.println("Invalid input. Closing without capacity increase.");
                    mediator.closeWithRandomWaitlistSelection(course, course.getCapacity());
                }
            } 
            else
            {
                mediator.closeWithRandomWaitlistSelection(course, course.getCapacity());
            }
            course.setStatus(CourseStatus.CLOSED);
        }
        changeStatus(course, newStatus, mediator);
    }

    @Override
    public CourseStatus getStatus() {
        return CourseStatus.FULL;
    }

}
