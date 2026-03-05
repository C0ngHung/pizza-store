# 🍕 OOP & SOLID Practice -- Pizza Ordering System

## 1. Overview

This project is a **training exercise for Object-Oriented Programming
(OOP) and SOLID principles** using a simple **Pizza Ordering System**.

The goal is **not only to build a working program**, but to **practice
the thinking process of a professional software engineer**:

Problem → Analysis → Design → Architecture → Implementation

Instead of coding immediately, we focus on:

-   Understanding the problem
-   Modeling the domain
-   Applying SOLID principles
-   Designing extensible architecture

------------------------------------------------------------------------

# 2. Learning Objectives

This project helps developers practice:

## OOP Concepts

-   Encapsulation
-   Abstraction
-   Inheritance
-   Polymorphism

## SOLID Principles

  Principle   Description
  ----------- ---------------------------------
  S           Single Responsibility Principle
  O           Open Closed Principle
  L           Liskov Substitution Principle
  I           Interface Segregation Principle
  D           Dependency Inversion Principle

------------------------------------------------------------------------

# 3. Problem Statement

We want to build a **Pizza Ordering System** with the following
requirements.

## Pizza Properties

A pizza contains:

-   Name
-   Dough
-   Sauce
-   Toppings

Example toppings:

-   Mushrooms
-   Onions
-   Bacon

------------------------------------------------------------------------

## Pizza Types

The system should support different types of pizzas:

-   Cheese Pizza
-   Greek Pizza
-   Pepperoni Pizza

Each pizza may have **different toppings and ingredients**.

------------------------------------------------------------------------

## Pizza Preparation Process

Every pizza goes through the following steps:

1.  Select pizza type
2.  Prepare ingredients
3.  Bake pizza
4.  Box pizza

------------------------------------------------------------------------

# 4. Domain Modeling

## Core Entity

Pizza

Attributes:

-   name
-   dough
-   sauce
-   toppings

Methods:

-   prepare()
-   bake()
-   box()

------------------------------------------------------------------------

## Pizza Variants

Pizza ├── CheesePizza ├── GreekPizza └── PepperoniPizza

------------------------------------------------------------------------

# 5. Design Approach

We follow a **step-by-step engineering workflow**.

## Step 1 --- Problem Analysis

Identify:

Entities

-   Pizza
-   Dough
-   Sauce
-   Topping

Processes

-   prepare
-   bake
-   box

Variations

-   Pizza types

------------------------------------------------------------------------

## Step 2 --- Identify Change Points

What may change in the future?

-   New pizza types
-   Different toppings
-   Different preparation styles

These change points guide our architecture.

------------------------------------------------------------------------

## Step 3 --- Apply SOLID

### SRP -- Single Responsibility

Each class should have **one responsibility only**.

Example:

Pizza → pizza behavior\
PizzaFactory → pizza creation\
OrderService → order flow

------------------------------------------------------------------------

### OCP -- Open Closed Principle

We should be able to **add new pizza types without modifying existing
code**.

Example:

Add class: VeggiePizza

WITHOUT modifying existing pizza classes

------------------------------------------------------------------------

### DIP -- Dependency Inversion

High level modules should not depend on low level modules.

Instead depend on abstractions.

Example:

OrderService\
↓\
PizzaFactory (interface)

------------------------------------------------------------------------

# 6. Project Architecture

Example architecture:

pizza-oop-solid │ ├── README.md │ ├── model │ ├── Pizza.java │ ├──
CheesePizza.java │ ├── GreekPizza.java │ └── PepperoniPizza.java │ ├──
factory │ └── PizzaFactory.java │ ├── service │ └──
PizzaOrderService.java │ └── Main.java

------------------------------------------------------------------------

# 7. Implementation Roadmap

We implement the system in **multiple levels**.

## Level 1 --- Basic Pizza Model

Create:

-   Pizza
-   CheesePizza

Run preparation process.

------------------------------------------------------------------------

## Level 2 --- Add More Pizza Types

Add:

-   GreekPizza
-   PepperoniPizza

------------------------------------------------------------------------

## Level 3 --- Introduce Factory Pattern

Avoid using:

if(type == CHEESE)\
if(type == GREEK)\
if(type == PEPPERONI)

Instead implement:

PizzaFactory

------------------------------------------------------------------------

## Level 4 --- Improve Extensibility

Support:

-   More toppings
-   New pizza types
-   New preparation styles

------------------------------------------------------------------------

## Level 5 --- Add Tests

Test cases:

-   createCheesePizza()
-   createGreekPizza()
-   createPepperoniPizza()

------------------------------------------------------------------------

# 8. Example Workflow

Customer selects pizza type

→ PizzaFactory creates pizza

→ prepare()

→ bake()

→ box()

→ return pizza

------------------------------------------------------------------------

# 9. Future Improvements

Possible extensions:

-   Pizza size (Small / Medium / Large)
-   Pricing system
-   Online ordering
-   Multiple pizza stores
-   Abstract Factory Pattern

------------------------------------------------------------------------

# 10. Technologies

Example stack:

-   Java 21+
-   JUnit 5
-   Maven / Gradle

------------------------------------------------------------------------

# 11. Author

This project is created for **learning OOP and SOLID design
principles**.

It is intended for:

-   Junior Developers
-   Backend Trainees
-   Students learning Software Architecture
