Version 2: Removed Board from class diagram

# Design Document

**Author**: Team 45

## 1 Design Considerations

### 1.1 Assumptions

- The application is assumed to run on Android platform.
- The application will run in English.
- The application is intended to be used on mobile phones.
- The application should not require network access to run.
- The application does not require account name and password registration.

### 1.2 Constraints

- The application is a single-player game.
- The application stores the played game statistics but not the player names.
- The stored game statistics cannot be shared in cloud or on other mobile devices.

### 1.3 System Environment

- The Android system version should be  at least API level 23: Android 6.0 (Marshmallow).
- The mobile devices should have large (at least 5 inch) touch screens.
- The resolution of the mobile phones running the application should be no lower than 120 dpi.
- The mobile devices should have at least 100mb available space.

## 2 Architectural Design

### 2.1 Component Diagram

![ComponentDiagram](https://github.gatech.edu/gt-omscs-se-2020spring/6300Spring20Team45/blob/master/GroupProject/Docs/Diagrams/ComponentDiagram.png)

### 2.2 Deployment Diagram

The deployment diagram is unnecessary here, since all components will be deployed within the same hardware device.

## 3 Low-Level Design

### 3.1 Class Diagram

![ClassDiagram](https://github.gatech.edu/gt-omscs-se-2020spring/6300Spring20Team45/blob/master/GroupProject/Design-Team/uml.png)

### 3.2 Other Diagrams

## 4 User Interface Design
![UImockups](https://github.gatech.edu/gt-omscs-se-2020spring/6300Spring20Team45/blob/master/GroupProject/Docs/Diagrams/UImocks.png)
