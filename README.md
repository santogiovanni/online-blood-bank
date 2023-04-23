# online-blood-bank

### Author: Stephen Antogiovanni

### Project Name: Online Blood Bank System Hosted on Kubernetes

### Project Description:
This cloud-based application will serve as a repository for different patient blood types and will include information about donors (name and specific blood type information). The purpose of this application will be to maximize efficiency to accessing blood during a potential emergency. The cloud will host blood type information, storage data about such blood, the availability of each blood type (either A+, B+, AB+, or O-). The purpose of this web-based application will be so that each patient can have insight into their potential donor's blood donation history and the results produced by the donor's blood.

### 3 Estimated Modules (each serves as its own microservice):

* Donation Center Microservice --> donor comes to donation center and fulfills blood transaction (1 unit = 1 donation). Handles CRUD operations for creating, reading, updating, and deleting blood donation appointments. (pushes blood units to blood bank)

* Blood Bank Microservice --> responsible for holding inventory of blood (retrieves from donation center). Handles CRUD operations for blood inventory (e.g., adding, deleting, accessing, or updating blood units).

* Hospital Microservice --> module to pull blood from blood bank based on demand.

### Estimated Languages and Frameworks:

* Backend language --> Java to build the application server

* Database Module --> H2 Database

* Containerization Module --> Use Docker to build and package container images

* Kubernetes Module --> Use kubectl to deploy and manage Kubernetes-based application

### General description of the UI with the primary actions:
The user interface will allow users to search for their ideal blood donor, filter their searches based on blood type and location availability, and schedule appointments.
