# Fitness Diet Tracker

Fitness Diet Tracker is a JavaFX-based application designed to help users effectively manage their fitness and dietary goals. The program allows users to create profiles, set fitness goals, and track their daily activities and meal history. It also provides insights into calorie intake, recommended workout durations, and sleep recommendations based on user profiles.

## Features

### 1. Create Profiles
- Users can input personal details including:
  - Username
  - Age
  - Height
  - Weight
  - Gender

### 2. View Profile
- Displays previously entered information.
- Automatically calculates and displays the user's Basal Metabolic Rate (BMR).

### 3. Fitness Goal Setting
- Users answer a series of guided questions to set personalized fitness goals.
- Goals are saved for future reference.

### 4. Log Daily Meals
- Users can input meals consumed on a specific date.
- All entries are stored for tracking and analysis.

### 5. View Diet History
- Retrieves and displays historical meal data.
- Calculates and shows the average daily calorie intake.

### 6. View Fitness Goals
- Provides recommendations based on user profiles, including:
  - Suggested sleep duration.
  - Recommended workout duration per day.

## Installation

To set up the Fitness Diet Tracker on your local machine, follow these steps:

### Step 1: Clone the Repository
Clone the repository using the following link:
```
git clone https://github.com/NabeelFK/FitnessDietTrackerApp.git
```

### Step 2: Install Dependencies
Ensure the following technologies are installed to run the program:
- JavaFX
- JUnit

### Step 3: Run the Program
You can run the application using:
- `FitnessAppRun` class, or
- `RunApp.jar` file.

## File Requirements
For the program to function correctly, ensure the following files are present in the specified paths:
- `profiles.csv` - `src/main/resources/com/example/fitnesstrackergp_gui/profiles.csv`
- `goals.csv` - `src/main/resources/com/example/fitnesstrackergp_gui/goals.csv`
- `meals.csv` - `src/main/resources/com/example/fitnesstrackergp_gui/meals.csv`

> **Note:** If the program is launched with fewer than three parameters, it will not display the welcome page and will instead output an error message to the console.

## Technologies Used
- **JavaFX**: For creating the graphical user interface.
- **JUnit**: For unit testing.
- **Git**: For version control.
- **IntelliJ IDEA**: For development.

## Usage
Fitness Diet Tracker is a tool designed to help users gain a better understanding of their fitness goals and the necessary steps to achieve them. It provides insights into workout durations, calorie intake, and more, all tailored to the user's profile.

## Contributors
This project was developed by:
- Steven Barclay
- Kendra Cowan
- Shiva Das
- Pavneet Dhanoa
- Nabeel Furqan
- Myah Rustad
