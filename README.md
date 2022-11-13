# WebP_EMSE_Backend
For the cloud


This project is the backend of a web project for managing a building system including its rooms, windows and heaters. This uses the Spring framework, Spring security and gradle. The main entities are Building, Room, Window, and Heater.

## API endpoints

#### api/windows -> All things to do with windows

Here you can create, update and delete a window. You can also see all the windows or just a specific one. You can also switch the status of the window from OPEN to CLOSED or viceversa.

#### api/rooms -> All things to do with rooms
Here you can create, update and delete a window. You can also see all the rooms or just a specific one.
You can also close all the windows in the room and turn off all the heaters, simulating what you would need to do at the end of the day. You can also switch the status or all the windows or all the heaters in the room. 

#### api/heaters -> All things to do with heaters
Here you can create, update and delete a window. You can also see all the heaters or just a specific one. You can also switch the status of the heater from ON to OFF or viceversa.

#### api/buildings -> All things to do with buildings
Here you can create, update and delete a building. You can also see all the buildings or just a specific one.
You can also close all the windows in the building and turn off all the heaters, simulating what you would need to do at the end of the day.
#### api/user -> Shows the username if you are an admin. 

## Running with spring security
To be able to use the frontend we had to turn off spring security on the cloud, to run a local version with spring security please do the following changes.
Comment line 11 in src/main/java/com/emse/spring/faircorp/Application.java and uncomment line 10.
Uncomment line 16 in src/main/java/com/emse/spring/faircorp/SpringSecurityConfig.java.
