# Course Platform — Design Documentation

---

## Class Structure

```
CourseComponent (interface)
├── Lesson                       (leaf)
├── Course                       (composite, contains Lessons)
├── Module                       (composite, contains Courses)(implements ModuleComponent)
│     |
ModuleComponent (interface, extends CourseComponent)
|
|└── ModuleDecorator              (abstract, implements ModuleComponent)
|      └──---| 
|           ├── PracticeSet      (+$10)
|           └── LiveMentorSupport (+$20)
|
|
├── CartDiscount                (wraps cart calculations)
│
└── Discount                    (abstract, decorator pattern)
      ├── MultiModuleDiscount   (-$15)
      ├── SpecialDiscount       (-$12)
      └── DevelopingCountryDiscount (-$10)

Cart(User)

Main
```

---

## Design Patterns Used

### 1. Composite Pattern
`Lesson`, `Course`, and `Module` all implement the same `CourseComponent` interface. This means checkout logic calls `calculatePrice()` on anything without caring what it actually is. Price and duration bubble up automatically through the hierarchy.

### 2. Decorator Pattern — Add-ons
Add-ons wrap a `ModuleComponent` (which includes `Module` or other decorators) and stack extra charges on top of its price. They extend `ModuleDecorator` which implements `ModuleComponent`, ensuring type safety at compile time. Multiple add-ons can be stacked on the same module freely—for example, `new LiveMentorSupport(new PracticeSet(m1))` creates a double-decorated module.

### 3. Decorator Pattern — Discounts
Discounts wrap a `Discount` instance (or `CartDiscount` as the base) and self-evaluate whether they should apply through an `evaluateEligibility()` method. Each discount holds a reference to the `Cart` so it can inspect cart properties—module count, total duration, student country—on its own. No external if-else logic is needed anywhere.

---

## The Discount Chain

All three discounts are always chained together in `generateReceipt()`. Each one checks its own eligibility and either subtracts its amount or passes the price through unchanged. They cascade inward, so each discount applies on top of the previous one's result.

`CartDiscount` sits at the bottom of the chain. Since the cart is a list of items, the discounts need a single object to wrap. `CartDiscount` represents the entire cart as one unit—its price is the cart subtotal, its duration is the cart total duration.

---

## Discount Eligibility Rules

| Discount | Applies When | Discount Amount |
|---|---|---|
| Multi-Module | 2 or more modules in the cart | -$15 |
| Special | Total cart duration is 10 hours or more | -$12 |
| Developing Country | Student is flagged as from a developing country | -$10 |

---

## Extensibility

To add a new discount, only two things are needed—a new class extending `Discount` with its eligibility rule, and one extra line in the `generateReceipt()` chain. No existing class changes. The same applies for add-ons—just a new class extending `AddOns` with the new price amount. The composite hierarchy (Lesson, Course, Module) also supports adding new node types without modifying existing code.

---

## Key Design Decisions

| Decision | Reason |
|---|---|
| `ModuleDecorator implements ModuleComponent` | Compile-time guarantee that add-ons only wrap modules and other module components |
| `ModuleComponent extends CourseComponent` | Allows decorated modules to be used anywhere CourseComponent is expected, while maintaining type safety |
| `Discount` holds a `Cart` reference | Each discount evaluates itself independently — no logic leaks into `Cart` |
| `evaluateEligibility()` is abstract | Every discount owns its own rule, nothing is hardcoded outside |
| `CartDiscount` as the chain base | Lets the discount chain treat the whole cart as a single unit |
| Fixed discount amounts | Simplicity and consistency: $15 (multi-module), $12 (special), $10 (developing country)
