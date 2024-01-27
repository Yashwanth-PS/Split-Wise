**Design Splitwise:**
- Requirement Gathering
- Class Diagram
- Schema Design

**Overview:**

1) Align with the Interviewer
  - Splitwise is an Expense Tracker
  - User logs expense with other user
  - Helps with settlements

2) What kind of app?
  - How to test my design - API
  - Is persistence required?

**Requirement Gathering:**

1) Sketch
2) User Journey

**Clarify Requirements:**
1) Is expenses paid by a single user as multiple users can pay
    A B C D --> (10000)
    Expense - Travel Gift | 26/12/2021
    Amount = 10K
    Paid By = A(5K) + B(5K)                    
    Shared By = A(1K) B(1K) C(4K) D(4K)          

2) Is the  expense  shared  equally? not necessary!!!

3) Should an expense in group always involve all member? - No 
    roommates 4 members(A B C D)    
    A B C went dinner yesterday 
    why to add D

4) Can an expense be added that doesn't belong to a group? - No

5) What is settling the Group?
   RH -> A B C D
   Office -> B C D

A List of transaction if followed would make everyones share in the group expense to 0

**Models:**
- Schema Design
- Data
- Algorithm

**Class Diagram:**
- Users can register and update their profiles
  1) User
  2) Group

Activity Selection -> Round Robin and Greedy Algorithm

Command Design Pattern
API Annotation
One data flow end to end

**5 steps to enhance the Spring Boot application:**
- Include the Spring Boot Starter Web dependency from the Maven Repository.
- Update the project's POM file and refresh Maven to download the necessary dependencies.
- Modify the controller to a RestController, enabling it to handle HTTP POST and GET requests.
- Update methods with @GetMapping or @PostMapping annotations. For example, use @PostMapping
  for registering a user and @GetMapping for retrieving user data.
- Understand how to retrieve data. For HTTP requests, you can utilize Request DTOs
  (Data Transfer Objects). In HTTP, data can be sent either in the message body or as headers.
  Use the @RequestBody annotation to handle data sent in the message body. */