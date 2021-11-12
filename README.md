# User Management

It is a user management web service which exposes a set of APIs to do CRUD operations on users.

It uses the following dependencies: 
1. spring web to expose a set of rest APIs
2. spring data to use JPA repositories to make connection with database which is h2 in our case.
3. h2 to have in-memory database to save users information in users table
4. lombok to have getters/setters and logger for pojo/services
5. testng to write unit test cases

It has the following APIs:
1. getUsers which fetches users from the database for the given userRequest. it returns users in the paginated format.
2. getUserById, this API is to fetch full details for the given userId.
3. createUser, this API is to create a new user in the database.
4. updateUser, this API is to update an existing user in the database.
5. deleteUser, this API is to delete an existing user from the database.

It has docker. Run the following commands to build docker image and run it in local.

Build docker Image
    docker build -t="user-management" .

Run docker image
    docker run -p 8080:8080 -it --rm user-management

How to check the application is up and running?
    curl localhost:8080/api/heartbeat
    
    it should return Service is up and running!!
