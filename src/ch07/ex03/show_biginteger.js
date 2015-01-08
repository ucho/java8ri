#!/usr/bin/jjs

var b = new java.math.BigInteger('1234567890987654321');

print(b);  // 1234567890987654400
// bは表示される時点でJavaScriptのNumber型として解釈されるため、
// Numberで扱える範囲までしか正しく表示できない。

print(b.mod(java.math.BigInteger.TEN));  // 1
// modメソッド内部では正しく計算が行われ、結果はJavaScriptのNumberの範囲内なので、
// 結果は正しく表示される。

print(java.lang.String.format('%d', b));  // 1234567890987654321
// formatを使えば正しく表示できる。
