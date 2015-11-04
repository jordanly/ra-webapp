TODO
====
- Finish visitor/generation of SQL statements
- Create REST API
- Set up PostgreSQL database, maybe need to make a build script to set it up?
    - Current plan: run PostgreSQL on Vagrant and connect to it from local

Build Steps
===========
Clone repo
mvn compile
mvn package
run generated jar in target folder


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

Front-Back API Details
=========
JSON Format: JSONArray consisting of results for each query.

    [
        {
            isError: boolean
            query: String of original query
            error:
                {
                    start: line:column
                    end: line:column
                    message: String error message
                }
            columnNames: [col1, col2, ...]
            data: list of maps for each tuple
                [
                    {
                        col1: data1
                        col2: data2
                        ....
                    },
                    ....
                ]
        },
        ...
    ]

