parser grammar RAParser ;

exp0        : exp STATEMENT_TERMINATOR
            | EOF
            ;

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