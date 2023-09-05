# Hyperskill project - Car Sharing service

## Summary of the project

Brief description: Car-sharing is becoming a more and more popular green alternative to owning a car. 
Here the goal is to create a program that manages a car-sharing service allowing companies to rent out their cars to customers.
Project is simple, so we'll not care about price. 
But at the same time we'll deal with car availability, setting up companies and new customers and also adding cars to companies fleet.

This is console based with multilevel menu run from Main. Data storage via H2 RDB. 

MVC approach controller -> service -> persistence.

## Elements

### Data objects
* Customer
* Car
* Company

All above have corresponding DAO classes that deals with DB interactions

```mermaid
---
title: DB entities with relations
---
erDiagram
  CUSTOMER ||--o| CAR : rents
  COMPANY ||--o{ CAR : owns
  CUSTOMER {
      int pk_id
      string name
      int fk_rented_car_id
  }
  COMPANY {
      int pk_id
      string name
  }
  CAR {
      int pk_id
      string name
      int fk_company_id
  }
```
### 

### Multilevel menu flowchart
* All levels have option to go back by single step (not marked on graph for clarity)
  * Exceptions that jump back by more than one step are marked with back arrow
* Method prefixes: 
  * cus -> customer
  * mgr -> manager
  * all -> everyone

![multilevelMenuFlowchart.png](carsharing/multilevelMenuFlowchart.png) 