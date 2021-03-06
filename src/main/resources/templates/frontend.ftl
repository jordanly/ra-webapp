<!DOCTYPE html>
<html>
<head>
    <meta http-equiv='Content-type' content='text/html; charset=utf-8'>
    <title>RA 3.0</title>
    <link rel="stylesheet" href="shared/thirdparty/bootstrap.min.css" />
    <link rel="stylesheet" href="shared/css/base.css" />
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
</head>

<body>
<div id="leftpane">
    <p>Try running <pre> python -m SimpleHTTPServer</pre> and going to <a href="http://localhost:8000/">http://localhost:8000/</a>.</p>
</div>
<div id="rightpane"></div>
<script src="shared/js/react.min.js"></script>
<script src="shared/js/JSXTransformer.js"></script>
<script src="shared/js/d3.min.js"></script>
<script src="shared/thirdparty/file-saver/FileSaver.min.js"></script>
<script type="text/jsx" src="left.js"></script>
<script type="text/jsx" src="right.js"></script>
<script type="text/jsx" src="main.js"></script>
</body>
</html>
