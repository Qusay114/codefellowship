# codefellowship

## Description :
application simply allows to each user to sign up and has his own profile , besides the ability to create posts , and see other people posts :

### home Route : a route that view the home page of codefellows website
Example:

                /

### sign up Route : a route that enable a user to sign up in the website 

Example:

                /signup

### log in Route : a route enable the user to sign in the website

Example:

                /login


### profile route : a route that shows the information of the user , and shows his posts , besides form post to add a new post 

Example:

                /profile


### users Route : a route only available to the admin that shows for specific user , his information and posts , besides the ability to delete that user 

Example:

                /users/1



## Dependencies :

        dependencies {
        implementation 'org.springframework.boot:spring-boot-starter-data-jdbc'
        implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
        implementation 'org.springframework.boot:spring-boot-starter-security'
        implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
        implementation 'org.springframework.boot:spring-boot-starter-web'
        implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity5'
        implementation group: 'org.webjars', name: 'bootstrap', version: '4.6.0'
        developmentOnly 'org.springframework.boot:spring-boot-devtools'
        runtimeOnly 'org.postgresql:postgresql'
        testImplementation 'org.springframework.boot:spring-boot-starter-test'
        testImplementation 'org.springframework.security:spring-security-test'
        }


## Instructions :
* to run the application you need first to have postgres and open a connection to a specific database then add the name of the database , the username and the password in the application.properies file then use this commend to run it:

                gradle run

* run tests :

                ./gradlew test






