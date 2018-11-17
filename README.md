# Minimo1

Hecho por Víctor Piñón

The EETAC wants to offer a new service to campus to allow their members to share electrical
bikes.
For this reason, it requests that DSA students build a new service with the following features:
- Add users to the system
- Get information about users
- Show the available bikes

PART I: 6 points
  1. Implementation of the Façade (MyBike.java): MyBikeImpl.java
    1.1. The data structures that should be used will be the following:
      ● Array to store the Stations (Object[] )
      ● LinkedList to store the bikes of a Station
      ● HashMap to store the Users
      ● LinkedList to store the bikes of a User
    1.2. The Façade will be implemented as a Singleton pattern
    1.3. All the operations will have debug logs (LOG4J) at the beginning and end of each
    operation and error logs when the exceptions are thrown
    1.4. Integration on a JUNIT test provided
    
PART II: 4 points
  1. Implement a REST Service (using the Jersey libraries) that allows showing the
  operations of the façade: addUser, addStation, addBike, bikesByStationOrderByKms,
  getBike, bikesByUser. Define the routes and HTP methods for each operation
  2. Add the swagger annotations on the REST service and generate the documentation
  
NOTE:
- It is not allowed the use of “System.out.println”
- You should use the Maven tool to manage the libraries
- The delivery should be made on a Github repository and upload a text file on Atenea task with the URL of the repo
