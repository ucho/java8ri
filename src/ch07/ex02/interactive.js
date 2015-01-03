#!/usr/bin/jjs

// インタラクティブに処理を記述することそのもの以外につまづいた。
//  * jjsとJavaとの違いにはまる
//  * jjsがREPLとしてのデキがイマイチで時間がかかる
// インタラクティブに書くことそのものは、Rubyで書くときによく行っており、
// 例えばメソッドの振る舞いを簡単に確認するときや、使い捨ての処理を書くときなどに便利であることを実感している。

var bytes = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get('alice.txt'));
var content = new java.lang.String(bytes);
var words = java.util.Arrays.asList(content.split(/\W/));
var uniq_words = new java.util.HashSet(words);
var long_words = uniq_words.stream().filter(function(w) { return w.length() > 12; }).collect(java.util.stream.Collectors.toList());
java.util.Collections.sort(long_words);
print(long_words);
