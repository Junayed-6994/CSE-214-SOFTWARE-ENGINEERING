# Course Platform — Design Documentation

---

## Class Structure

```
CourseComponent (interface)
├── Lesson
├── Course
├── Module
│     └── ModuleDecorator        (abstract, extends Module)
│           └── AddOns           (abstract, extends ModuleDecorator)
│                 ├── PracticeSet          +$10
│                 └── LiveMentorSupport    +$20
│
├── CartSnapshot
│
└── Discount                     (abstract)
      ├── MultiModuleDiscount    -$15
      ├── SpecialDiscount        -$12
      └── DevelopingCountryDiscount -$10

Checkout
```

---

## Design Patterns Used

### 1. Composite Pattern
`Lesson`, `Course`, and `Module` all implement the same `CourseComponent` interface. This means checkout logic calls `calculatePrice()` on anything without caring what it actually is. Price and duration bubble up automatically through the hierarchy.

### 2. Decorator Pattern — Add-ons
Add-ons wrap a `Module` and stack extra charges on top of its price. They extend `Module` directly (not just the interface) so that passing a `Course` or `Lesson` is a compile-time error — the business rule is enforced by the type system, not by runtime checks. Multiple add-ons can be stacked on the same module freely.

### 3. Decorator Pattern — Discounts
Discounts wrap a `CourseComponent` and self-evaluate whether they should apply through an `evaluateEligibility()` method. Each discount holds a reference to the `Checkout` so it can inspect the cart — module count, total duration, student country — on its own. No external if-else logic is needed anywhere.

---

## The Discount Chain

All three discounts are always chained together in `generateReceipt()`. Each one checks its own eligibility and either subtracts its amount or passes the price through unchanged. They cascade inward, so each discount applies on top of the previous one's result.

`CartSnapshot` sits at the bottom of the chain. Since the cart is a list of items, the discounts need a single object to wrap. `CartSnapshot` represents the entire cart as one unit — its price is the cart subtotal, its duration is the cart total duration.

---

## Discount Eligibility Rules

| Discount | Applies When |
|---|---|
| Multi-Module | 2 or more modules in the cart |
| Special | Total cart duration is 5 hours or more |
| Developing Country | Student is flagged as from a developing country |

---

## Extensibility

To add a new discount, only two things are needed — a new class with its eligibility rule, and one extra line in the chain. No existing class changes. The same applies for add-ons — just a new class extending `AddOns`.

---

## Key Design Decisions

| Decision | Reason |
|---|---|
| `ModuleDecorator extends Module` | Compile-time guarantee that add-ons only wrap modules |
| `Discount` holds a `Checkout` reference | Each discount evaluates itself — no logic leaks into `Checkout` |
| `evaluateEligibility()` is abstract | Every discount owns its own rule, nothing is hardcoded outside |
| `CartSnapshot` as the chain base | Lets the discount chain treat the whole cart as a single unit |
