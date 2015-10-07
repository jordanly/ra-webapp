grammar RAGrammar ;

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

// PARSER

exp0        : exp STATEMENT_TERMINATOR EOF ;

exp_unit    : TABLE_NAME
            | LEFT_PAREN exp RIGHT_PAREN
            ;

exp_unary   : exp_unit
            | SELECT OPERATOR_OPTION exp_unary
            | PROJECT OPERATOR_OPTION exp_unary
            | RENAME OPERATOR_OPTION exp_unary
            ;

exp         : exp_unary
            | exp_unary JOIN OPERATOR_OPTION exp_unary
            | exp_unary CROSS exp_unary
            | exp_unary UNION exp_unary
            | exp_unary DIFF exp_unary
            | exp_unary INTERSECT exp_unary
            ;