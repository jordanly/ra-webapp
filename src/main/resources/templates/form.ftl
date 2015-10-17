<!DOCTYPE html>
<html>
<head>
    <title>RA Queries From Beers</title>
    <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css'>
</head>
<body>
    <h1>Type in your RA Query and press Go!</h1>
    <form action='/'>
        <div class="form-group">
            <label for="ra_query">Your RA Query</label>
            <input id="ra_query" name="ra_query" class="form-control" type="text">
        </div>
        <button type="submit" class="btn">Go!</button>
    </form>
    <h1>Your Query Output</h1>
    <p>${ra_output}</p>
</body>
</html>