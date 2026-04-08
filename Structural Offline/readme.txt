================================================================================
                  COURSE PLATFORM - DESIGN PATTERNS PROJECT
================================================================================

PROJECT OVERVIEW
================================================================================
This project implements a hierarchical course management system for an online
learning platform. It demonstrates practical use of Composite and Decorator
design patterns to manage courses, modules, lessons, and add-ons flexibly while
maintaining clean code and extensibility.

The system supports:
- Building course hierarchies (Lessons → Courses → Modules)
- Adding optional features via decorators (Practice Sets, Mentor Support)
- Calculating total price and duration with discounts based on eligibility
- Shopping cart functionality with multi-tier pricing discounts


PROJECT STRUCTURE
================================================================================

CORE HIERARCHY:
  Lesson (Leaf)
    └─ implements CourseComponent
       
  Course (Composite)
    └─ contains: List of Lessons
    └─ implements: CourseComponent
       
  Module (Composite)
    └─ contains: List of Courses
    └─ implements: CourseComponent, ModuleComponent
       
BASE CLASSES:
  AbstractComposite
    └─ Base class for Course and Module
    └─ Implements aggregation logic for price/duration calculation
       
  CourseComponent (Interface)
    └─ defineContractfor all course elements
    └─ Methods: calculatePrice(), calculateDuration(), getName(), displayDetails()
       
  ModuleComponent (Interface)
    └─ Extends CourseComponent
    └─ Marks decoratable components


ADD-ONS (DECORATORS):
  ModuleDecorator (Abstract)
    └─ Base class for all module decorators
    └─ implements ModuleComponent
    
  PracticeSet extends ModuleDecorator
    └─ Adds +$10 to wrapped module price
    
  LiveMentorSupport extends ModuleDecorator
    └─ Adds +$20 to wrapped module price


DISCOUNTS:
  Discount (Abstract)
    └─ Base class for all discount decorators
    
  MultiModuleDiscount
    └─ Applies -$15 if cart contains 2+ modules
    
  SpecialDiscount
    └─ Applies -$12 if total duration ≥ 5 hours
    
  DevelopingCountryDiscount
    └─ Applies -$10 if student is from developing country


UTILITIES:
  Cart
    └─ Holds course components for shopping
    └─ isFromDevelopingCountry flag
    └─ Calculates total price with discount chain
    
  Main
    └─ Entry point with example scenarios


================================================================================
DESIGN PATTERNS EXPLAINED
================================================================================

1. COMPOSITE PATTERN
────────────────────────────────────────────────────────────────────────────
   Purpose: Treat individual objects and compositions uniformly
   
   Implementation:
   - CourseComponent interface defines contract for all elements
   - Leaf: Lesson (has no children, concrete values)
   - Composites: Course, Module (contain child components)
   - AbstractComposite: Base class with aggregation logic
   
   How it works:
   - A Module contains Courses
   - A Course contains Lessons
   - When you call module.calculatePrice():
       → Sums all course prices
       → Which sum all lesson prices
       → Automatically handles the hierarchy
   
   Benefit: Cart code treats Lesson, Course, and Module the same way.
   No special cases needed. New hierarchy levels can be added easily.


2. DECORATOR PATTERN - ADD-ONS
────────────────────────────────────────────────────────────────────────────
   Purpose: Add behavior to objects dynamically without subclassing
   
   Implementation:
   - ModuleDecorator (abstract): wraps a ModuleComponent
   - PracticeSet, LiveMentorSupport: concrete decorators
   - Each decorator adds a fixed price on top of the wrapped component
   
   How it works:
   - PracticeSet wraps a Module
   - When you call calculatePrice() on PracticeSet:
       → It calls wrappedModule.calculatePrice()
       → Adds its own price (+$10)
       → Returns the combined result
   
   Stacking:
   - Decorators can be nested:
       new LiveMentorSupport(new PracticeSet(m1))
   - Each layer adds its price independently
   - No modification to original Module class needed
   
   Benefit: Extensible without cluttering core classes. Easy to add new
   add-ons (e.g., Exam Prep, Certificate Track) by creating new decorators.


3. DECORATOR PATTERN - DISCOUNTS
────────────────────────────────────────────────────────────────────────────
   Purpose: Apply business rule logic without hardcoding conditionals
   
   Implementation:
   - Discount (abstract): wraps a CourseComponent and a Cart reference
   - Each discount subclass implements evaluateEligibility()
   - Discounts are chained together in generateReceipt()
   
   How it works:
   - SpecialDiscount checks: Is total duration ≥ 5 hours?
   - DevelopingCountryDiscount checks: isFromDevelopingCountry flag?
   - MultiModuleDiscount checks: Are there 2+ modules in cart?
   - Each discount independently decides if it applies
   - Prices cascade through the chain with each discount applied in order
   
   Benefit: Clear separation of concerns. Adding a new discount rule
   only requires a new discount subclass. No modification to Cart logic.


================================================================================
CLASS DESCRIPTIONS
================================================================================

LESSONS (Leaf Node)
──────────────────
  Lesson
    - name: String (e.g., "Intro to Java")
    - duration: double (hours)
    - price: double (dollars)
    - calculatePrice(): returns price directly
    - calculateDuration(): returns duration directly
    - No children; this is a leaf in the hierarchy


COURSES (Composite)
──────────────────
  Course extends AbstractComposite
    - name: String (e.g., "Java Basics")
    - components: List<Lesson>
    
    Methods:
    - addLesson(lesson): Adds a lesson to the course
    - removeLesson(lesson): Removes a lesson
    - calculatePrice(): Sums all lesson prices
    - calculateDuration(): Sums all lesson durations
    
    Example:
      Course c1 = new Course("Java Basics");
      c1.addLesson(lesson1);  // 2 hrs, $15
      c1.addLesson(lesson2);  // 3 hrs, $20
      c1.calculatePrice() → $35
      c1.calculateDuration() → 5 hours


MODULES (Composite)
──────────────────
  Module extends AbstractComposite implements ModuleComponent
    - name: String (e.g., "Java Programming")
    - components: List<Course>
    
    Methods:
    - addCourse(course): Adds a course to the module
    - removeCourse(course): Removes a course
    - calculatePrice(): Sums all course prices
    - calculateDuration(): Sums all course durations
    
    Example:
      Module m1 = new Module("Java Programming");
      m1.addCourse(course1);  // $35
      m1.addCourse(course2);  // $45
      m1.calculatePrice() → $80
      m1.calculateDuration() → 12 hours


DECORATORS - ADD-ONS
───────────────────
  ModuleDecorator (Abstract Base)
    - wrappedModule: ModuleComponent
    - Constructor takes a ModuleComponent to wrap
    - Delegates: calculatePrice(), calculateDuration(), getName(), displayDetails()
    
  PracticeSet extends ModuleDecorator
    - price: double = 10.0 (fixed)
    - calculatePrice(): returns wrapped.calculatePrice() + 10
    - displayDetails(): shows "Added Practice Set: $10"
    
    Example:
      ModuleComponent decorated = new PracticeSet(m1);
      decorated.calculatePrice()
      → m1.calculatePrice() + 10
      → $80 + $10 = $90
    
  LiveMentorSupport extends ModuleDecorator
    - price: double = 20.0 (fixed)
    - calculatePrice(): returns wrapped.calculatePrice() + 20
    - displayDetails(): shows "Added Live Mentor Support: $20"
    
    Stacking Example:
      ModuleComponent addon = new LiveMentorSupport(new PracticeSet(m1));
      addon.calculatePrice()
      → $80 (m1) + $10 (practice) + $20 (mentor) = $110


CART
────
  Cart
    - contents: List<CourseComponent>
    - isFromDevelopingCountry: boolean
    
    Methods:
    - addItems(item): Adds lesson/course/module to cart
    - removeItems(item): Removes item from cart
    - totalPrice(): Sums all item prices
    - totalDuration(): Sums all item durations
    - countModules(): Returns number of Module objects in cart
    - generateReceipt(): Displays items and applies discount chain


DISCOUNTS
─────────
  Discount (Abstract Base)
    - cart: Cart reference (for eligibility checks)
    - wrapped: CourseComponent (what the discount applies to)
    - discountAmount: double (fixed reduction if eligible)
    
    Methods:
    - evaluateEligibility(): abstract, overridden by subclasses
    - effectiveDiscount(): returns discountAmount if eligible, else 0
    - calculatePrice(): returns wrapped price minus discount
    
  SpecialDiscount
    - Checks: totalDuration() ≥ 5 hours?
    - discountAmount: $12
    
  DevelopingCountryDiscount
    - Checks: cart.isFromDevelopingCountry == true?
    - discountAmount: $10
    
  MultiModuleDiscount
    - Checks: cart contains 2 or more Module objects?
    - discountAmount: $15


================================================================================
HOW EVERYTHING WORKS TOGETHER
================================================================================

SCENARIO: Student from developing country buys 1 module with add-ons

  1. Build items:
     Module m1 = new Module("Java Programming");
     m1.addCourse(course1);        // $35
     m1.addCourse(course2);        // $45
     Total: $80, 12 hours
  
  2. Add decorators:
     ModuleComponent withAddon = new PracticeSet(m1);
     Effective price: $80 + $10 = $90
  
  3. Create cart:
     Cart cart = new Cart(true);  // true = developing country
     cart.addItems(withAddon);
  
  4. Generate receipt (discounts applied in order):
     SpecialDiscount:
       → Check: 12 hours ≥ 5? YES
       → Apply -$12 → $90 - $12 = $78
     
     DevelopingCountryDiscount:
       → Check: isFromDevelopingCountry? YES
       → Apply -$10 → $78 - $10 = $68
     
     MultiModuleDiscount:
       → Check: 2+ modules in cart? NO (only 1 module)
       → Apply $0 → $68 (no change)
     
     FINAL PRICE: $68


================================================================================
EXTENDING THE SYSTEM
================================================================================

To add a new add-on:
  1. Create class that extends ModuleDecorator
  2. Implement calculatePrice() to add your fee
  3. Override displayDetails() for user information
  4. Use: new YourAddon(module)

To add a new discount:
  1. Create class that extends Discount
  2. Implement evaluateEligibility() with your business logic
  3. Call super constructor with discount amount
  4. Add to discount chain in Cart.generateReceipt()

To add a new course level (e.g., SpecializedPath containing Modules):
  1. Create class extending AbstractComposite implementing ModuleComponent
  2. Add methods like addModule(module)
  3. Inherits all price/duration logic automatically


================================================================================
RUNNING THE PROJECT
================================================================================

Compile: javac *.java
Run:     java Main

Output shows multiple scenarios:
- Single lesson with no discounts
- Modules with add-ons and discount combinations
- Various discount eligibility cases


================================================================================
KEY TAKEAWAYS
================================================================================

✓ Composite Pattern: Hierarchical structures with uniform interface
✓ Decorator Pattern: Add responsibilities to objects dynamically
✓ Separation of Concerns: Business logic isolated in separate classes
✓ Extensibility: New add-ons and discounts without modifying core
✓ Flexibility: Items can be combined and composed in many ways
✓ Maintainability: Clear class responsibilities and relationships

================================================================================
