#!/usr/bin/jjs

// import
var ZonedDateTime = Java.type('java.time.ZonedDateTime');

// test function
var test = function() {
    var now = ZonedDateTime.now();
    var set_year = now.withYear(2020).getYear();
    if (set_year !== 2020) {
        print("unexpected year: " + set_year);
        exit(1);
    }
};

// do test
test();

// テスト用のfunctionを作成してユニットテストのようなことは可能。
// 書くコードとしてはJavaと大差ないが、ツールやライブラリはJavaの方が充実しているため、
// Javaの方がラクにテストが書けるのではないかと思う。
// NashornもJS用の各種ツールが使えれば、もっとラクになるのかもしれない。
