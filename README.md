Build Steps
===========
The current iteration of the backend requires you to have Vagrant. The build
process will create the vm, setup the vm, and build the required jars to run
the webserver. The steps are as follows:

    1. Clone the repo
    2. Create the VM (using the command "vagrant up")
    3. SSH into the VM ("vagrant ssh")
    4. Go to the shared directory ("cd /vagrant")
    5. Run the build script "init.sh" ("./init.sh")
        - This will take a while
        - You will be prompted to accept an OpenJDK agreement, you must
            accept this for the installation to be successful
    6. Run the server using the command specified at the end of the prompt
        ("java -jar /vagrant/target/ra3-1.0-SNAPSHOT-jar-with-dependencies.jar")
    7. You can now access the website on your host machine @ localhost:8080


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
**Main endpoint for displaying the page:** "/"

**RA-query endpoint:** "/query/*"
- * is a multi-query string in URL encoding
- Multiple queries are separated by ";" characters
- Newline characters are allowed
- JSON format: JSONArray consisting of results for each query
```
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
                    ...
                },
                ...
            ]
    },
    ...
]
```
**Schema-request endpoint:** "/schema/*"
- Syntax:
    - Request for a list of relations: "\d;"
    - Request for table details: "\d [tablename];"
- * is a multi-query string in URL encoding
- Multiple queries are separated by ";" characters
- Newline characters are allowed
- Leading/trailing/multiple whitespaces are disregarded
- JSON format: JSONArray consisting of results for each request.
```
[
    {
        isError: boolean
        query: String of original query
        error:
            {
                message: String error message
            }
        title: String title of table
        columnNames: [col1, col2, ...]
        data: list of maps for each tuple
            [
                {
                    col1: data1
                    col2: data2
                    ...
                },
                ...
            ]
    },
    ...
]
```
**Lookahead strings endpoint:** "/lookahead/"
- Returns a list of strings containing table names and columns
- List is returned in sorted order with duplicates removed
- JSON format: JSONArray containing lookahead strings
```
[str1, str2, ...]
```
