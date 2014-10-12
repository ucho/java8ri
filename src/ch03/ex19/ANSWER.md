BiFunctionへの最初の型引数でUを? super Uと宣言すべきか？
------
すべきではない。

その理由は何か？
------

* accumulator自身には問題がないかもしれない。例えば第一引数にEmployeeを受け取っても、Person型の値を返せばよく、EmployeeはPersonとして使えるため。
* しかしそのようにすると、identityやcombinerと整合させるために、identityの型やcombinerの引数の型も? super Uに揃えなければならない。そしてそれはできないため、? super Uと宣言すべきではない。
  * identityの型は戻り値の型にもなるため、共変であるべきなので、? extends Uにしたい
  * combinerでは引数かつ戻り値の型になるため、共変と反変が打ち消し合うので、Uにしたい