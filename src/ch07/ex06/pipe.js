#!/usr/bin/jjs

var pipe = function() {
    var out;
    for (var i in arguments) {
        $EXEC(arguments[i], out);
        out = $OUT;
        if ($ERR) {
            print($ERR);
            exit(1);
        }
    }
    return out;
};

var test = function() {
    var result = pipe('cat pipe.js', 'tail -n1');
    if (result !== 'test();\n') {
        print('unexpected result: ' + result);
        exit(1);
    }
};

test();
