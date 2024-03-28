::@echo off
javac Game.java View.java Controller.java Wall.java Model.java Json.java Pacman.java
if %errorlevel% neq 0 (
	echo There was an error; exiting now.	
) else (
	echo Compiled correctly!  Running Game...
	java Game	
)

