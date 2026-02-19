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
        ModuleComponent m1WithAddOn = new PracticeSet(m1);   // $80 + $10 = $90

        //Add a addons of practice set to m1 module
        cart2.addItems(m1WithAddOn);
        // Duration 12 hrs → SpecialDiscount applies (-$12)
        // DevelopingCountry applies (-$10)
        // MultiModule does NOT apply (only 1 module)
        // Total: $90 - $12 - $10 = $68
        cart2.generateReceipt();

        // ---- Scenario 3: Two modules, developing country, stacked add-ons ----
        System.out.println(">>> Scenario 3: Two modules + stacked add-ons + all discounts");
        Cart cart3 = new Cart(true);
        ModuleComponent m1Stacked = new LiveMentorSupport(new PracticeSet(m1)); // $80+$10+$20 = $110
        cart3.addItems(m1Stacked);
        cart3.addItems(m2);
        // Subtotal: $110 + $30 = $140
        // Duration: 12 + 3.5 = 15.5 hrs → SpecialDiscount applies (-$12)
        // MultiModule applies (-$15, 2 modules)
        // DevelopingCountry applies (-$10)
        // Total: $140 - $15 - $12 - $10 = $103
        cart3.generateReceipt();

        // ---- Scenario 4: Multiple courses without module wrapper ----
        System.out.println(">>> Scenario 4: Multiple courses (no module), regular student");
        Cart cart4 = new Cart(false);
        cart4.addItems(c1);     // 5 hrs, $35
        cart4.addItems(c2);     // 7 hrs, $45
        // Subtotal: $80, Duration: 12 hrs
        // SpecialDiscount applies (-$12, duration >= 10)
        // MultiModule does NOT apply (courses, not modules)
        // Total: $80 - $12 = $68
        cart4.generateReceipt();

        // ---- Scenario 5: Mixed lessons and courses ----
        System.out.println(">>> Scenario 5: Mixed individual lessons + course");
        Cart cart5 = new Cart(false);
        cart5.addItems(l1);     // 2 hrs, $15
        cart5.addItems(l2);     // 3 hrs, $20
        cart5.addItems(c3);     // 3.5 hrs, $30
        // Subtotal: $65, Duration: 8.5 hrs
        // No SpecialDiscount (< 10 hrs)
        // Total: $65
        cart5.generateReceipt();

        // ---- Scenario 6: Triple decorator stack ----
        System.out.println(">>> Scenario 6: Triple stacked add-ons (if supported)");
        Cart cart6 = new Cart(true);
        Lesson l7 = new Lesson("Advanced Algorithms", 5.0, 40.0);
        Lesson l8 = new Lesson("System Design",      6.0, 50.0);
        Course c4 = new Course("Mastery Track");
        c4.addLesson(l7);
        c4.addLesson(l8);
        Module m3 = new Module("Expert Series");
        m3.addCourse(c4);       // 11 hrs, $90
        
        try {
            ModuleComponent m3TripleDecorated = new LiveMentorSupport(
                new PracticeSet(
                    new PracticeSet(m3)
                )
            );  // $90 + $10 + $10 + $20 = $130
            cart6.addItems(m3TripleDecorated);
            // Subtotal: $130, Duration: 11 hrs
            // SpecialDiscount applies (-$12, >= 10 hrs)
            // DevelopingCountry applies (-$10)
            // Total: $130 - $12 - $10 = $108
            cart6.generateReceipt();
        } catch (Exception e) {
            System.out.println("Triple decorator not supported: " + e.getMessage());
        }

        // ---- Scenario 7: Four modules with selective decorators ----
        System.out.println(">>> Scenario 7: Four modules, mixed decorators, developing country");
        Cart cart7 = new Cart(true);
        
        // Module 1: Java (decorated)
        ModuleComponent m1Decorated = new PracticeSet(m1);  // $90
        
        // Module 2: Python (no decorator)
        // m2 already exists: $30
        
        // Module 3: New Web Dev module
        Lesson l9 = new Lesson("HTML/CSS Basics",   2.5, 18.0);
        Lesson l10 = new Lesson("JavaScript",       4.0, 35.0);
        Course c5 = new Course("Web Frontend");
        c5.addLesson(l9);
        c5.addLesson(l10);
        Module m4 = new Module("Web Development");
        m4.addCourse(c5);       // 6.5 hrs, $53
        
        // Module 4: Database module
        Lesson l11 = new Lesson("SQL Fundamentals",  3.0, 25.0);
        Lesson l12 = new Lesson("Database Design",   4.0, 35.0);
        Course c6 = new Course("Database Essentials");
        c6.addLesson(l11);
        c6.addLesson(l12);
        Module m5 = new Module("Database Mastery");
        m5.addCourse(c6);       // 7 hrs, $60
        
        ModuleComponent m5Decorated = new LiveMentorSupport(m5);  // $80
        
        cart7.addItems(m1Decorated);
        cart7.addItems(m2);
        cart7.addItems(m4);
        cart7.addItems(m5Decorated);
        
        // Subtotal: $90 + $30 + $53 + $80 = $253
        // Duration: 12 + 3.5 + 6.5 + 7 = 29 hrs → SpecialDiscount applies (-$12)
        // MultiModule applies (-$15, four modules)
        // DevelopingCountry applies (-$10)
        // Total: $253 - $15 - $12 - $10 = $216
        cart7.generateReceipt();

        // ---- Scenario 8: Single long course with mentor support ----
        System.out.println(">>> Scenario 8: Intensive single course with mentor support");
        Cart cart8 = new Cart(true);
        
        Lesson l13 = new Lesson("Phase 1: Foundations",  8.0, 60.0);
        Lesson l14 = new Lesson("Phase 2: Intermediate", 7.0, 55.0);
        Lesson l15 = new Lesson("Phase 3: Advanced",     10.0, 80.0);
        Course intensiveCourse = new Course("Full-Stack Intensive");
        intensiveCourse.addLesson(l13);
        intensiveCourse.addLesson(l14);
        intensiveCourse.addLesson(l15);  // 25 hrs, $195
        
        Module m6 = new Module("Bootcamp Track");
        m6.addCourse(intensiveCourse);   // 25 hrs, $195
        
        ModuleComponent m6WithBoth = new LiveMentorSupport(new PracticeSet(m6));  // $195+$10+$20 = $225
        
        cart8.addItems(m6WithBoth);
        // Subtotal: $225, Duration: 25 hrs
        // SpecialDiscount applies (-$12, >= 10 hrs)
        // MultiModule does NOT apply (only 1 module)
        // DevelopingCountry applies (-$10)
        // Total: $225 - $12 - $10 = $203
        cart8.generateReceipt();

        // ---- Scenario 9: Large complex scenario with mixed everything ----
        System.out.println(">>> Scenario 9: Complex cart - 6 items, every type");
        Cart cart9 = new Cart(true);
        
        cart9.addItems(l1);       // Single lesson: 2 hrs, $15
        cart9.addItems(c1);       // Single course: 5 hrs, $35
        cart9.addItems(new PracticeSet(m1));  // Decorated module: 12 hrs, $90
        cart9.addItems(m2);       // Plain module: 3.5 hrs, $30
        
        // New module for cart9
        Lesson l16 = new Lesson("Data Science Intro",  4.0, 40.0);
        Lesson l17 = new Lesson("ML Algorithms",       5.0, 50.0);
        Course c7 = new Course("Data Science Path");
        c7.addLesson(l16);
        c7.addLesson(l17);
        Module m7 = new Module("Data Science");
        m7.addCourse(c7);         // 9 hrs, $90
        
        cart9.addItems(new LiveMentorSupport(new PracticeSet(m7)));  // $90+$10+$20 = $120
        
        // Another simple lesson
        Lesson l18 = new Lesson("Git & Version Control", 1.5, 12.0);
        cart9.addItems(l18);
        
        // Subtotal: $15 + $35 + $90 + $30 + $120 + $12 = $302
        // Duration: 2 + 5 + 12 + 3.5 + 9 + 1.5 = 33 hrs
        // SpecialDiscount applies (-$12, >= 10 hrs)
        // MultiModule applies (-$15, 3 modules: m1, m2, m7)
        // DevelopingCountry applies (-$10)
        // Total: $302 - $15 - $12 - $10 = $265
        cart9.generateReceipt();
    }
}
