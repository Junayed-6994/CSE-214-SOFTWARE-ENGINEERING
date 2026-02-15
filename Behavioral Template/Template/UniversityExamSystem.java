package Template;

// Abstract Class - Template
abstract class OnlineExam {
    
    // Template Method (final - algorithm skeleton cannot be changed)
    public final void conductExam() {
        studentLogin();
        displayInstructions();
        startTimer();
        displayQuestions();  // Different for each exam type
        submitExam();
        gradeExam();         // Different for each exam type
        showResults();
        studentLogout();
    }
    
    // Common methods (same for all exam types)
    private void studentLogin() {
        System.out.println("Student logged in successfully");
    }
    
    private void displayInstructions() {
        System.out.println("Displaying exam instructions...");
    }
    
    private void startTimer() {
        System.out.println("Timer started - 60 minutes remaining");
    }
    
    private void submitExam() {
        System.out.println("Exam submitted successfully");
    }
    
    private void showResults() {
        System.out.println("Results displayed to student");
    }
    
    private void studentLogout() {
        System.out.println("Student logged out\n");
    }
    
    // Abstract methods (must be implemented by subclasses)
    protected abstract void displayQuestions();
    protected abstract void gradeExam();
}

// Concrete Class 1 - MCQ Exam
class MCQExam extends OnlineExam {
    
    @Override
    protected void displayQuestions() {
        System.out.println("Showing 50 multiple-choice questions");
        System.out.println("   Q1: What is the capital of Bangladesh?");
        System.out.println("   Q2: Which data structure uses LIFO?");
        System.out.println("   ... (48 more questions)");
    }
    
    @Override
    protected void gradeExam() {
        System.out.println("Auto-grading MCQ answers...");
        System.out.println("   Correct: 42/50");
        System.out.println("   Score: 84%");
    }
}

// Concrete Class 2 - Programming Exam
class ProgrammingExam extends OnlineExam {
    
    @Override
    protected void displayQuestions() {
        System.out.println("Showing 3 coding problems");
        System.out.println("   Problem 1: Implement Binary Search");
        System.out.println("   Problem 2: Find longest palindrome");
        System.out.println("   Problem 3: Graph shortest path");
    }
    
    @Override
    protected void gradeExam() {
        System.out.println("Running test cases...");
        System.out.println("   Problem 1: 10/10 test cases passed ");
        System.out.println("   Problem 2: 8/10 test cases passed ");
        System.out.println("   Problem 3: 10/10 test cases passed ");
        System.out.println("   Total Score: 93.3%");
    }
}

// Concrete Class 3 - Viva Exam (Easy to extend!)
class VivaExam extends OnlineExam {
    
    @Override
    protected void displayQuestions() {
        System.out.println("Connecting to examiner via video call...");
        System.out.println("   Oral questions will be asked live");
    }
    
    @Override
    protected void gradeExam() {
        System.out.println("👨Examiner manually grading performance...");
        System.out.println("   Communication: 8/10");
        System.out.println("   Technical Knowledge: 9/10");
        System.out.println("   Total Score: 85%");
    }
}

// Main Class
public class UniversityExamSystem {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║    UNIVERSITY ONLINE EXAM SYSTEM         ║");
        System.out.println("╚═══════════════════════════════════════════╝\n");
        
        // Conduct MCQ Exam
        System.out.println("========== MCQ EXAM SESSION ==========");
        OnlineExam mcqExam = new MCQExam();
        mcqExam.conductExam();
        
        // Conduct Programming Exam
        System.out.println("========== PROGRAMMING EXAM SESSION ==========");
        OnlineExam codingExam = new ProgrammingExam();
        codingExam.conductExam();
        
        // Conduct Viva Exam (New exam type - easily extended!)
        System.out.println("========== VIVA EXAM SESSION ==========");
        OnlineExam vivaExam = new VivaExam();
        vivaExam.conductExam();
        
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║       ALL EXAMS COMPLETED                ║");
        System.out.println("╚═══════════════════════════════════════════╝");
    }
}
