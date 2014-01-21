grammar Json;

options 
{
    output=AST;
}

jsonText
        : (jsonObject
        | jsonArray)  EOF!
;

jsonValue
        : FALSE
        | TRUE
        | NULL
        | jsonObject
        | jsonArray
        | jsonNumber
        | jsonString
;

jsonNumber
        : NUMBER
;

jsonString
        : STRING
;

member
        : STRING^ ':'! jsonValue
;

jsonObject
        : OBJECT^ (member (','! member)*)? '}'
;


jsonArray
        : ARRAY^ (jsonValue (','! jsonValue)*)? ']'
;

ARRAY
	:	'[';

OBJECT
	:	'{';

fragment
INT
        : '0'..'9'+
;

NULL
	: 'null';
	
TRUE 
	: 'true';
	
FALSE
	:	'false';

NUMBER
        : '-'? ('0' | ( '1'..'9' INT* )) ('.' INT+)? EXPONENT?
;

WS
        : ( ' '
        | '\t'
        | '\n'
        | '\r'
        ) {$channel=HIDDEN;}
;

STRING
        : '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
;

fragment
EXPONENT
        : ('e'|'E') ('+'|'-')? ('0'..'9')+
;

fragment
HEX_DIGIT
        : ('0'..'9'|'a'..'f'|'A'..'F')
;

fragment
ESC_SEQ
        : '\\' ('\"'|'\\'|'/'|'b'|'f'|'n'|'r'|'t')
        | UNICODE_ESC
;

fragment
UNICODE_ESC
        : '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
;