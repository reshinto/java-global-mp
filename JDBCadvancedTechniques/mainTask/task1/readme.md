# Readme

## Setup

### Setup and Run Postgresql

> docker run --name postgresql -e POSTGRES_PASSWORD=password -d -v $(pwd):/app -w='/app' -p 5432:5432 postgres

### Create Database

> psql -h localhost -U postgres -f database.sql

### Create Tables Data

> psql -h localhost -U postgres -d socialmedia -f init.sql

### Add stored functions

> psql -h localhost -U postgres -d socialmedia -f stored_proc.sql

### Run code

- If need to add seed data

  - uncomment line 18 in `src//main/java/com/jdbc/App.java`

- press the play icon button in the code editor to run app
