# SintelTestAutomation

###### How to Run the Test script?
		##### Mac ###
		Navigate to
		# src/test/java/testRunner/Runner.java
		# Run as JUnit

		##### Linux #########
		Change Directory to : Current Project location in terminal
		Then Rum : mvn test

### How to Run the mobile Test cases:
        Prerequisites
           ## Appium Server should be started
                == > Open the terminal and type "appium"

           ## Android Simulator should be started
           ==> How to run the emulator?
                # open the terminal > /Library/Android/sdk/emulator
                # Run command Emulator -avd <Device Name>
                # <Device Name> Nexus_5X_API_27

      # Then run the "androidWeb" as Java main class

### Configuration, Permission Issues
**Configuration for Issue 1 : **

If get any message : "Warning message : “chromedriver” cannot be opened because the developer cannot be verified. Unable to launch the chrome browser"

		Solution :
		# Navigate to Driver folder.
		# Run the command "xattr -d com.apple.quarantine chromedriver"

**Configuration for Issue 2 : **
Getting Error Message "Driver is not executable.

Solution :
Navigate to Driver Folder then open in terminal
chmod 750 chromedriver

##### Outputs
		Generating Report
		# /target/cucumber-html-reports.html

		Log file
		log/testlog.log
