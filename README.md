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

NOTES
=====
We need to generate aliases when using FROM in some cases. Will probably just
generate aliases everywhere we use FROM