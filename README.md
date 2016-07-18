Build Steps
===========
The current build will only run on Ubuntu-based systems. The steps are as follows:

    1. Clone the repo
    2. Run the build script "init.sh" ("./init.sh")
        - This will take a while
        - You will be prompted to accept an OpenJDK agreement, you must
            accept this for the installation to be successful
    3. Run the server using the command specified at the end of the prompt
        ("java -jar /vagrant/target/ra3-1.0-SNAPSHOT-jar-with-dependencies.jar")
    4. You can now access the website on port 8000.

Hosting on Amazon AWS
=====================
In order to host and access the application on an Amazon EC2 instance, make sure
you edit the security group to allow TCP traffic on port 8000.

Front-Back API Details
=========
**Main endpoint for displaying the page:** "/"

**RA-query endpoint:** "/query/*"
- * is a multi-query string in URL encoding
- Multiple queries are separated by ";" characters
- Newline characters are allowed
- JSON format: JSONArray consisting of JSONObjects for each query
```
[
    {
        isError: boolean
        query: string of original query
        error:
            {
                start: line:column
                end: line:column
                message: string error message
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
- JSON format: JSONArray consisting of JSONObjects for each request
```
[
    {
        isError: boolean
        query: string of original query
        error:
            {
                message: string error message
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

**Abstract syntax tree (AST) query endpoint:** "/ast/*"
- * is a multi-query string in URL encoding
- Multiple queries are separated by ";" characters
- Newline characters are allowed
- JSON format: JSONArray consisting of JSONObjects for each query
    - NOTE: Tree is returned as the root node JSONObject with children contained within
```
[
    {
        isError: boolean
        query: string of original query
        error:
            {
                start: line:column
                end: line:column
                message: string error message
            }
        tree: root node object 
            {
                name: string name of operation or table name (i.e. "\\project" or "serves")
                subscript: string indicating operation input if any (i.e. "beer" in \project_{beer})
                    - empty string if no subscript
                children: JSONArray of node JSONObjects in {name, subscript, children} format
                    - empty JSONArray if leaf node
            }
    },
    ...
]
```
