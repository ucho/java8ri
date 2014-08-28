IとJを実装した場合のすべての組み合わせ
===============================

I.f()が | J.f()が | 何が起きる
-------+---------+-----------
abstract|abstract|I.f() or J.f()の実装が強制される
abstract|default|デフォルトメソッドのコンフリクトが発生する。実装すれば解消できる。
abstract|static|I.f()の実装が強制される
default|abstract|デフォルトメソッドのコンフリクトが発生する。実装すれば解消できる。
default|default|デフォルトメソッドのコンフリクトが発生する。実装すれば解消できる。
default|static|I.f()が継承される
static|abstract|J.f()の実装が強制される
static|default|J.f()が継承される
static|static|何も継承されない。また、何も実装する必要はない。

Sを拡張し、Iを実装した場合
=====================

I.f()が | 何が起きる
--------+----------
abstract|S.f()が継承される
default|S.f()が継承される
static|S.f()が継承される