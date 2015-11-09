grammar RAGrammar ;

// Lexer rules

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
SINGLELINE_COMMENT: '//' ~('\r'|'\n')*  -> channel(HIDDEN) ;

// ==========================================

// Paddrser Rules

exp0        : exp STATEMENT_TERMINATOR EOF ;

exp_unit    : TABLE_NAME                                #tableExp
            | LEFT_PAREN exp1 RIGHT_PAREN               #parenExp
            ;

exp_unary   : exp_unit                                  #unitExp
            | SELECT OPERATOR_OPTION exp_unary          #unaryExp
            | PROJECT OPERATOR_OPTION exp_unary         #unaryExp
            | RENAME OPERATOR_OPTION exp_unary          #unaryExp
            ;

exp         : exp_unary                                 #singleUnaryExp
            | exp_unary JOIN OPERATOR_OPTION exp_unary  #joinExp
            | exp_unary JOIN exp_unary                  #binaryExp
            | exp_unary CROSS exp_unary                 #binaryExp
            | exp_unary UNION exp_unary                 #binaryExp
            | exp_unary DIFF exp_unary                  #binaryExp
            | exp_unary INTERSECT exp_unary             #binaryExp
            ;

exp1        : exp                                       #singleTermExp
            | exp JOIN OPERATOR_OPTION exp_unary        #joinTermExp
            | exp JOIN exp_unary                        #binaryTermExp
            | exp CROSS exp_unary                       #binaryTermExp
            | exp UNION exp_unary                       #binaryTermExp
            | exp DIFF exp_unary                        #binaryTermExp
            | exp INTERSECT exp_unary                   #binaryTermExp
            ;

// ==========================================
