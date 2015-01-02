#!/usr/bin/jjs

var org = 'hoge';
print('js string\'s class: ' + org.getClass());
var sub = org.slice(1);
print('substring\'s class: ' + sub.getClass());
print('cast sub to String result: ' + java.lang.String.class.cast(sub));
// -> 'oge'
// java.lang.Stringをjava.lang.Stringにキャストしただけ。
// 特筆すべきことは発生していない。
