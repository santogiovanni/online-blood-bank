# online-blood-bank

### Author: Stephen Antogiovanni

### Project Name: Online Blood Bank System Hosted on Kubernetes

![IMG_3766](https://github.com/santogiovanni/online-blood-bank/assets/106194360/32a7214e-15b8-4174-b78c-6a9169f567ad)

### Project Description:
This cloud-based application will serve as a repository for different patient blood types and will include information about donors (name and specific blood type information). The purpose of this application will be to maximize efficiency to accessing blood during a potential emergency. The cloud will host blood type information, storage data about such blood, the availability of each blood type (either A+/A-, B+/B-, AB+/AB-, or O+/O-). The purpose of this web-based application will be so that each patient can have insight into their potential donor's blood donation history and the results produced by the donor's blood.

### 3 Estimated Modules (each serves as its own microservice):

* Donation Center Microservice --> donor comes to donation center and fulfills blood transaction (1 unit = 1 donation). Handles CRUD operations for creating, reading, updating, and deleting blood donation appointments. (pushes blood units to blood bank)

* Blood Bank Microservice --> responsible for holding inventory of blood (retrieves from donation center). Handles CRUD operations for blood inventory (e.g., adding, deleting, accessing, or updating blood units).

* Hospital Microservice --> module to pull blood from blood bank based on demand.

### Estimated Languages and Frameworks:

* Backend language --> Java to build the application server

* Containerization Module --> Use Docker to build and package container images

* Kubernetes Module --> Use kubectl to deploy and manage Kubernetes-based application

### General description of this proof of concept:
Postman will be used to show intra microservice communication (blood pushes and pulls) and will demonstrate use of approved and rejected transactions between the donation center and blood bank (blood push), as well as between the hospital and blood bank (blood pull).

### How to Run Application on Localhost:
* Clone github repository

* ```cd donation_center```
* ```cd blood_bank```
* ```cd hospital```

* For each microservice, run
* ```./gradlew bootRun```

* Once donation_center microservice is up and running, run
* ```http://localhost:8080/blood-appts```
* in either localhost browser or on Postman 

![Screen Shot 2023-05-25 at 9 57 12 PM](https://github.com/santogiovanni/online-blood-bank/assets/106194360/6a57068c-13e7-4927-b2e9-83de9ea0cfa5)

* To get a specific blood schedule via id, run with id as any integer
* ```http://localhost:8080/blood-appts{id}```

* To get all the blood schedules sorted by bloodtype, run
* ```http://localhost:8080/blood-schedules/{bloodtype}```

* To remove a specific appointment via id, run
* ```http://localhost:8080/remove-appt/{id}```

* To simulatenously create an appointment and push the blood unit, run
* ```http://localhost:8080/create-appt-and-push-blood```
* Follow example below to manually insert raw JSON data
* ![Screen Shot 2023-05-25 at 10 02 09 PM](https://github.com/santogiovanni/online-blood-bank/assets/106194360/fbf69914-d5be-4e68-9df4-5a466393c0c8)

* To update an existing specific appointment via id, run
* ```http://localhost:8080/update-appt/{id}```
* Follow example below to manually insert raw JSON data
* ![Screen Shot 2023-05-25 at 10 04 37 PM](https://github.com/santogiovanni/online-blood-bank/assets/106194360/a4212d01-30fa-48d8-8b37-d08ea9b2b1af)

* To push a unit of blood via bloodtype, run
* ```http://localhost:8080/pushBlood```
* Follow example below and enter any bloodtype that was previously randomly generated in donation center schedules
* ![Screen Shot 2023-05-25 at 10 05 45 PM](https://github.com/santogiovanni/online-blood-bank/assets/106194360/009e1744-995e-4074-92e8-7295909cfbbf)


