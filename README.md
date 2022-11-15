# WebP_EMSE_Backend
For the cloud


This project is the backend of a web project for managing a building system including its rooms, windows and heaters. This uses the Spring framework, Spring security and gradle. The main entities are Building, Room, Window, and Heater.

## API endpoints

#### api/windows -> All things to do with windows

Here you can create, update and delete a window. You can also see all the windows or just a specific one. You can also switch the status of the window from OPEN to CLOSED or viceversa.
* GET
  * / -> Will show a list of all windows.
  * /{id} -> Will show one specific window with that id.
* POST
  * / -> Will create or update a window. In both cases you need to include the name, status and room id. You must include the window id if you want to update.
* PUT
  * /{id}/switch -> Will switch the status of the window with that id from OPEN to CLOSED or viceversa. Returns the updated window. Please use a valid id.
* DELETE
  * /{id} -> Deletes the window with that id and only returns status code.

#### api/heaters -> All things to do with heaters
Here you can create, update and delete a heater. You can also see all the heaters or just a specific one. You can also switch the status of the heater from ON to OFF or viceversa.

* GET
  * / -> Will show a list of all heaters.
  * /{id} -> Will show one specific heater with that id.
* POST
  * / -> Will create or update a heater. In both cases you need to include the name, status and room id. You must include the heater id if you want to update. Optionally you can include the power.
* PUT
  * /{id}/switch -> Will switch the status of the heater with that id from OFF to ON or viceversa. Returns the updated heater. Please use a valid id.
* DELETE
  * /{id} -> Deletes the heater with that id and only returns status code.

#### api/rooms -> All things to do with rooms
Here you can create, update and delete a room. You can also see all the rooms or just a specific one.
You can also close all the windows in the room and turn off all the heaters, simulating what you would need to do at the end of the day. You can also switch the status or all the windows or all the heaters in the room. 

* GET
  * / -> Will show a list of all rooms.
  * /{id} -> Will show one specific room with that id.
* POST
  * / -> Will create or update a room. When creating you must include the name, the floor and the id of the building the room will go in. To update you must include the room id if you want to update and the only thing you can change is the target temperature. You can also include target temperature when creating but it is not necessary.
* PUT
  * /{id}/switchWindows -> Will switch the status of the all the windows in the room with that id from OPEN to CLOSED or viceversa. Returns the list of updated windows. Please use a valid id.
  * /{id}/switchHeaters -> Will switch the status of the all the heaters in the room with that id from OFF to ON or viceversa. Returns the list of updated heaters. Please use a valid id.
  * /{id}/closeWindows -> Will close all the windows in the room with that id. Returns the list of updated windows. Please use a valid id.
  * /{id}/offHeaters -> Will turn off all the heaters in the room with that id. Returns the list of updated heaters. Please use a valid id.
* DELETE
  * /{id} -> Deletes the room with that id and only returns status code.


#### api/buildings -> All things to do with buildings
Here you can create, update and delete a building. You can also see all the buildings or just a specific one.
You can also close all the windows in the building and turn off all the heaters, simulating what you would need to do at the end of the day.

* GET
  * / -> Will show a list of all buildings.
  * /{id} -> Will show one specific building with that id.
* POST
  * / -> Will create or update a building. When creating you must include the name. To update you must include the building id if you want to update and the only thing you can change is the outside temperature. You can also include outside temperature when creating but it is not necessary.
* PUT
  * /{id}/closeWindows -> Will close all the windows in the building with that id. Returns the list of updated windows. Please use a valid id.
  * /{id}/offHeaters -> Will turn off the all the heaters in the building with that id. Returns the list of updated heaters. Please use a valid id.
* DELETE
  * /{id} -> Deletes the building with that id and only returns status code.

#### /user -> Shows the username if you are an admin. Only works if security is on, see note below.

## Running with spring security
To be able to use the frontend we had to turn off spring security on the cloud, to run a local version with spring security please do the following changes.
* Comment line 11 in src/main/java/com/emse/spring/faircorp/Application.java and uncomment line 10.
* Uncomment line 16 in src/main/java/com/emse/spring/faircorp/SpringSecurityConfig.java.
By default the server runs on localhost:8080.
