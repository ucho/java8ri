#!/usr/bin/jjs

var loggingArrayListFactory = function() {
    var arr = new (Java.extend(java.util.ArrayList)) {
        add: function(x) {
            print('Adding ' + x);
            return Java.super(arr).add(x);
        }
    };
    return arr;
};

var test = function() {
    var arr01 = loggingArrayListFactory();
    arr01.add('1');
    arr01.add('2');
    var arr02 = loggingArrayListFactory();
    arr02.add('3');

    if (arr01.size() !== 2) {
        exit(1);
    }
    if (arr02.size() !== 1) {
        exit(1);
    }
};

test();
