public class Main {
    
    public static void main(String[] args) {
                // ---------- Build content ----------
        Lesson l1 = new Lesson("Intro to Java",    2.0, 15.0);
        Lesson l2 = new Lesson("OOP Concepts",     3.0, 20.0);
        Lesson l3 = new Lesson("Data Structures",  4.0, 25.0);
        Lesson l4 = new Lesson("Algorithms",       3.0, 20.0);

        Course c1 = new Course("Java Basics");
        c1.addLesson(l1);
        c1.addLesson(l2);       // 5 hrs, $35

        Course c2 = new Course("Advanced Java");
        c2.addLesson(l3);
        c2.addLesson(l4);       // 7 hrs, $45

        Module m1 = new Module("Java Programming");
        m1.addCourse(c1);
        m1.addCourse(c2);       // 12 hrs, $80

        Lesson l5 = new Lesson("Python Basics", 1.5, 12.0);
        Lesson l6 = new Lesson("Web Scraping",  2.0, 18.0);

        Course c3 = new Course("Python Starter");
        c3.addLesson(l5);
        c3.addLesson(l6);       // 3.5 hrs, $30

        Module m2 = new Module("Python Essentials");
        m2.addCourse(c3);       // 3.5 hrs, $30

        // ---- Scenario 1: Single lesson, no discounts ----
        System.out.println(">>> Scenario 1: Single lesson, regular student");
        Cart cart1 = new Cart(false);
        cart1.addItems(l1);
        cart1.generateReceipt();

        // ---- Scenario 2: One module (5+ hrs) + developing country student ----
        System.out.println(">>> Scenario 2: One module with add-on, developing country student");
        Cart cart2 = new Cart(true);
        Module m1WithAddOn = new PracticeSet(m1);   // $80 + $10 = $90
        cart2.addItems(m1WithAddOn);
        // Duration 12 hrs → SpecialDiscount applies (-$12)
        // DevelopingCountry applies (-$10)
        // MultiModule does NOT apply (only 1 module)
        // Total: $90 - $12 - $10 = $68
        cart2.generateReceipt();

        // ---- Scenario 3: Two modules, developing country, stacked add-ons ----
        System.out.println(">>> Scenario 3: Two modules + stacked add-ons + all discounts");
        Cart cart3 = new Cart(true);
        Module m1Stacked = new LiveMentorSupport(new PracticeSet(m1)); // $80+$10+$20 = $110
        cart3.addItems(m1Stacked);
        cart3.addItems(m2);
        // Subtotal: $110 + $30 = $140
        // Duration: 12 + 3.5 = 15.5 hrs → SpecialDiscount applies (-$12)
        // MultiModule applies (-$15, 2 modules)
        // DevelopingCountry applies (-$10)
        // Total: $140 - $15 - $12 - $10 = $103
        cart3.generateReceipt();
    }
}
