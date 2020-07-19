# LoginPageUsingHtmlAndSpringBoot
This project uses html as frontend, spring boot at backend and mysql as the database.
This project helps a new user to register by hitting the register page.

This project has been created using Spring Boot as backend( used to create api's) and html pages used as frontend.

MySql has been used as the backend Database (for user's details- username, password, current login time, last login time). 

Redis has been used to store the session details of the user.

This project stores the session details using redis at backend.  When the user logs in, check if the username and password in DB. 
If the username and password matches,it shows a welcome page with the current login time and previously when they have logged in.

Thus, to see the details as to the time and date when the user last logged in, he has to simply login and they can fetch all the details present with us in 
this project.

