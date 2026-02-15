import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Course {
    public final String code;
    public final String title;
    private int capacity;
    public CourseStatus status;
    private CourseState state;
    private final List<Student> enrolled = new ArrayList<>();
    private final LinkedList<Student> waitlist = new LinkedList<>();

    public Course(String code, String title, int capacity, CourseStatus status) {
        this.code = code;
        this.title = title;
        this.capacity = Math.max(0, capacity);
        this.status = status;
        this.state = createStateFromStatus(status);
    }

    private CourseState createStateFromStatus(CourseStatus status) 
    {
        switch (status) 
        {
            case DRAFT:
                return new DraftState();
            case OPEN:
                return new OpenState();
            case FULL:
                return new FullState();
            case CLOSED:
                return new CloseState();
            case CANCELLED:
                return new CancelledState();
            default:
                throw new IllegalArgumentException("Unknown CourseStatus: " + status);
        }
    }

    public boolean isVisibleToStudents() {
        return state.isVisibleToStudents();
    }

    public void setStatus(CourseStatus newStatus) 
    {
        this.status = newStatus;
        this.state = createStateFromStatus(newStatus);
    }
    LinkedList<Student> getWaitlistedStudents() 
    {
        return waitlist;
    }
    List<Student> getEnrolledStudents() 
    {
        return enrolled;
    }
    CourseState getState() 
    {
        return state;   
    }


    public void setCapacity(int newCapacity) {
        if (newCapacity < 0) newCapacity = 0;
        System.out.println("Setting capacity of " + code + " to " + newCapacity);
        this.capacity = newCapacity;
        if (status == CourseStatus.CANCELLED) {
            System.out.println("Course is CANCELLED; capacity change has no effect.");
            return;
        }
        // Adjust status based on capacity and current enrolled
        if (enrolled.size() < capacity) {
            if (status != CourseStatus.DRAFT) {
                setStatus(CourseStatus.OPEN);

                System.out.println(code + " status changed to OPEN (capacity allows enrollment).");
            }
        } else if (enrolled.size() == capacity) {
            setStatus(CourseStatus.FULL);
            System.out.println(code + " status changed to FULL (at capacity).");
        } else {
            // more enrolled than capacity: keep FULL, admin must resolve
            setStatus(CourseStatus.FULL);
            System.out.println(code + " over capacity; remains FULL.");
        }
    }

    public void setStatusAdmin(CourseStatus newStatus) {
        if (newStatus == null) return;
        if (newStatus == status) {
            System.out.println("No change: " + code + " already " + status);
            return;
        }

        state.changeStatus(this, newStatus, RegistrarMediator.getInstance());
    }

    // Interactive version for admin with Scanner (prompts for capacity increase)
    public void setStatusAdminInteractive(CourseStatus newStatus, Scanner scanner) {
        if (newStatus == null) return;
        if (newStatus == status) {
            System.out.println("No change: " + code + " already " + status);
            return;
        }

        state.changeStatusInteractive(this, newStatus, scanner, RegistrarMediator.getInstance());
    }


    public void printRoster() {
        System.out.println("Roster for " + code + " - " + title + " (" + status + ", cap=" + capacity + "):");
        if (enrolled.isEmpty()) {
            System.out.println("  [no enrolled]");
        } else {
            for (Student s : enrolled) {
                System.out.println("  " + s.id + " - " + s.name);
            }
        }
    }

    public void printWaitlist() {
        System.out.println("Waitlist for " + code + ":");
        if (waitlist.isEmpty()) {
            System.out.println("  [no waitlisted]");
        } else {
            for (Student s : waitlist) {
                System.out.println("  " + s.id + " - " + s.name);
            }
        }
    }

    // Exposed getters for UI/reporting
    public int getCapacity() { return capacity; }
    public int getEnrolledCount() { return enrolled.size(); }
    public int getWaitlistCount() { return waitlist.size(); }

}
