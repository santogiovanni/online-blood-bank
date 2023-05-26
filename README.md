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
* Clone github repository by running in terminal
* ```git clone https://github.com/santogiovanni/online-blood-bank.git```

* For each microservice folder, run
* ```cd donation_center```
* ```./gradlew bootRun```

* ```cd blood_bank```
* ```./gradlew bootRun```

* ```cd hospital```
* ```./gradlew bootRun```

* Once donation_center microservice is up and running, run
* ```http://localhost:8080/blood-appts``` (GET call in Postman)
* in either localhost browser or on Postman 

![Screen Shot 2023-05-25 at 9 57 12 PM](https://github.com/santogiovanni/online-blood-bank/assets/106194360/6a57068c-13e7-4927-b2e9-83de9ea0cfa5)

* To get a specific blood schedule via id, run with id as any integer (GET call in Postman)
* ```http://localhost:8080/blood-appts/{id}```

* To get all the blood schedules sorted by blood type, run (GET call in Postman)
* ```http://localhost:8080/blood-schedules/{bloodtype}```

* To remove a specific appointment via id, run (DELETE call in Postman)
* ```http://localhost:8080/remove-appt/{id}```

* To create a new donation appointment, run (POST call in Postman)
* ```http://localhost:8080/create-appt```
* Follow example below to manually insert raw JSON data

![Screen Shot 2023-05-25 at 10 41 52 PM](https://github.com/santogiovanni/online-blood-bank/assets/106194360/bb941542-690a-497c-ba43-9299265325d5)


* To simulatenously create an appointment and push the blood unit, run (POST call in Postman)

* ```http://localhost:8080/create-appt-and-push-blood```

* Follow example below to manually insert raw JSON data

![Screen Shot 2023-05-25 at 10 02 09 PM](https://github.com/santogiovanni/online-blood-bank/assets/106194360/fbf69914-d5be-4e68-9df4-5a466393c0c8)

* To update an existing specific appointment via id, run (PUT call in Postman)

* ```http://localhost:8080/update-appt/{id}```

* Follow example below to manually insert raw JSON data

![Screen Shot 2023-05-25 at 10 04 37 PM](https://github.com/santogiovanni/online-blood-bank/assets/106194360/a4212d01-30fa-48d8-8b37-d08ea9b2b1af)

* To push a unit of blood via blood type, run (POST call in Postman)

* ```http://localhost:8080/pushBlood```

* Follow example below and enter any blood type that was previously randomly generated in donation center schedules

![Screen Shot 2023-05-25 at 10 05 45 PM](https://github.com/santogiovanni/online-blood-bank/assets/106194360/009e1744-995e-4074-92e8-7295909cfbbf)

* To see the inventory of blood units in the blood bank, run (GET call in Postman)
* ```http://localhost:8081/bank```

* To clear the inventory of blood units in the blood bank, run (DELETE call in Postman)
* ```http://localhost:8081/clearbank```

* To pull all blood units to the hospital via blood types available in the blood bank, run (DELETE call in Postman)
* ```http://localhost:8082/pullBlood```
* Follow example below and enter any blood type in which that blood unit(s) was previously pushed to blood bank

![Screen Shot 2023-05-25 at 10 13 22 PM](https://github.com/santogiovanni/online-blood-bank/assets/106194360/0b6a2cd0-c883-48f4-9cb5-784503445b4c)

* To see the inventory of blood units in the hospital, run (GET call in Postman)

* ```http://localhost:8082/hospital```

### How to Run Application on Kubernetes:
* Clone github repository by running in terminal
* ```git clone https://github.com/santogiovanni/online-blood-bank.git```

* ```cd donation_center```
* ```./gradlew build```
* ```./gradlew bootRun```
* ```docker build -t {DockerDesktopUsername}/donation_center:v1 .```
* ```docker push {DockerDesktopUsername}/donation_center:v1```

* ```cd blood_bank```
* ```./gradlew build```
* ```./gradlew bootRun```
* ```docker build -t {DockerDesktopUsername}/blood_bank:v1 .```
* ```docker push {DockerDesktopUsername}/blood_bank:v1```

* ```cd hospital```
* ```./gradlew build```
* ```./gradlew bootRun```
* ```docker build -t {DockerDesktopUsername}/hospital:v1 .```
* ```docker push {DockerDesktopUsername}/hospital:v1```

* ```cd k8s```
* ```kubectl apply -f configmap.yaml```
* ```kubectl apply -f donation_center.yaml```
* ```kubectl apply -f blood_bank.yaml```
* ```kubectl apply -f hospital.yaml```

* To check status of pods, run
* ```kubectl get pods```

* To get info on services, run
* ```kubectl get services --all-namespaces -o wide```

![Screen Shot 2023-05-25 at 10 24 40 PM](https://github.com/santogiovanni/online-blood-bank/assets/106194360/c86f408c-28fa-44a0-9b6b-ccd7392a7475)

* To test all REST calls, implement the same instructions as above for localhost on Postman

* However, replace port 8080 with 31000 (follow example below)

![Screen Shot 2023-05-25 at 10 25 58 PM](https://github.com/santogiovanni/online-blood-bank/assets/106194360/d35020f0-c7e8-4742-95c0-05b422ce509c)

* Replace port 8081 with 31001 (follow example below)

![Screen Shot 2023-05-25 at 10 26 21 PM](https://github.com/santogiovanni/online-blood-bank/assets/106194360/181f7c98-7578-4c74-8251-998cbdc55d28)

* Replace port 8082 with 31002

![Screen Shot 2023-05-25 at 10 26 53 PM](https://github.com/santogiovanni/online-blood-bank/assets/106194360/f8868f84-be4f-4266-80dd-d491b4419e81) 
