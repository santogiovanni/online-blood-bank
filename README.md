# online-blood-bank

### Author: Stephen Antogiovanni

### Project Name: Online Blood Bank System Hosted on Kubernetes

### Project Description:
This cloud-based application will serve as a repository for different patient blood types and will include information about donors (name and specific blood type information). The purpose of this application will be to maximize efficiency to accessing blood during a potential emergency. The cloud will host blood type information, storage data about such blood, the availability of each blood type (either A+, B+, AB+, or O-) in a specific geographical area. The purpose of this web-based application will be so that each patient can have insight into their potential donor's blood donation history and the results produced by the donor's blood.

### Estimated Modules (each can serve as its own microservice):

* Frontend module --> UI of online bank system; users can search donor types, customize their profile, and schedule donation appointments.

* Backend API module --> handles user frontend requests; acts as REST API for users to do such functionality mentioned in the frontend module.

* User Management module --> responsible for user authenication and authorization (e.g., user registration, login and password info, account management).

* Donor Management module --> responsible for managing the donors' database (e.g., CRUD operations such as reading, inserting, deleting, and updating donor profiles) as well as searching for donor availability, their blood type and location, and filtering such results.

* Appointment module --> handles appointment booking process (e.g., scheduling and canceling donor appointments).

* Blood Inventory module --> handles CRUD operations for blood bank's inventory (e.g., adding, deleting, accessing, or updating blood units) and tracking the expiration dates for the blood units.

* Reporting module --> responsible for the analytics of the blood inventory (e.g., generates donor, appointment, and inventory reports).

### Estimated Languages and Frameworks:
* Frontend language --> HTML, CSS, JavaScript to build UI. Not sure whether to incorporate a frontend framework such as React for a more interactive UI.

* Backend language --> Java to build the application server

* Database Module --> H2 Database

* Containerization Module --> Use Docker to build and package container images

* Kubernetes Module --> Use kubectl to deploy and manage Kubernetes-based application

### General description of the UI with the primary actions:
The user interface will allow users to search for their ideal blood donor, filter their searches based on blood type and location availability, and schedule appointments.
