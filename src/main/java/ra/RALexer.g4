lexer grammar RALexer ;

fragment DIGIT : [0-9] ;
fragment ALPHA : [a-zA-Z]+ ;
WS : [ \t\r\n]+ -> skip ;

LEFT_PAREN : '(' ;
RIGHT_PAREN : ')' ;
STATEMENT_TERMINATOR : ';' ;
TABLE_NAME : ALPHA (ALPHA|DIGIT|'_')* ;

// Base RA commands
SELECT : '\\select' ;
PROJECT : '\\project' ;
JOIN : '\\join' ;
CROSS : '\\cross' ;
UNION : '\\union' ;
DIFF : '\\diff' ;
INTERSECT: '\\intersect' ;
RENAME : '\\rename' ;
SQLEXEC : '\\sqlexec' ; // Do we need?

OPERATOR_OPTION : '_{' (INSIDE_OPERATOR_OPTION)* '}' ;
INSIDE_OPERATOR_OPTION : ~('}'|'\n'|'\r') ;

// C style comments
COMMENT : '/*' .*? '*/' -> channel(HIDDEN) ;