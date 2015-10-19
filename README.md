TODO
====
- Finish visitor/generation of SQL statements
- Create REST API
- Set up PostgreSQL database, maybe need to make a build script to set it up?
    - Current plan: run PostgreSQL on Vagrant and connect to it from local

Build Steps
===========
Using IntelliJ:
    - Create a JAR artifact
    - Make sure the output directory is resources/META-INF
    - Build -> Generate Artifacts
    - This creates a jar file with all dependenceis in ra3/out/..../ra3_jar
    - java -jar [ JAR name ]


PostgreSQL
CREATE DATABASE
CREATE USER
GRANT PRIVILEGES
Connect to connection string in Java

Heroku PostgreSQL DB Setup
==========================

First, add the PostgreSQL addon to create the database for the web app;

"heroku addons:create heroku-postgresql:hobby-dev"

Then, add rows to database:

"cat setup-queries.sql | heroku pg:psql --app ra-beers-example"

Then you should be done! You can access the database using "heroku pg:psql" and
the URL will be an environment variable "DATABASE_URL".
