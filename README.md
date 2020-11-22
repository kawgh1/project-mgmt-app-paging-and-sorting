#### Spring Project Management App

#### Things I added:
- the Manager entity class and implementation to be more realistic to
project management organizations.
- Additional Project and Employee attributes/relationships to better
model real PM organizations.
- Pagination and Sorting of the table records using JpaRepository, 
Hibernate and Thymeleaf.
- Extra real-time graphs using graph.js and back end SQL queries to give 
the Dashboard more oomph.
- Active Navbar Links using Thymeleaf to show user clearly what page they're on
- Separated the Add Entity and Update Entity implementations with separate
routes, methods and pages
- Added custom CSS for mobile and tablet views
- Customized error page content, sticky footer
- Live Heroku deployment 

#### Things I learned about:
- AWS deployment and RDS
- Docker Container of a Spring Boot Web App
- Google Charts
- Building real time graphs from backend SQL queries using graph.js
- Pagination and Sorting using JPA Repository
- Data Transfer Objects (DTO)
- deeper knowledge of Thymeleaf templating

Pagination and Sorting from Java Guides: https://www.youtube.com/watch?v=Aie8n12EFQc

Initially, I included a fourth Entity for Location, but the database relationships
quickly got muddled - once I realized Location and Project shared a One-to-One
relationship I refactored and just made String location a property of Project class.