Filterという名前を持つ関数型インタフェース
----------------------------------

4つ

* java.nio.file.DirectoryStream.Filter<T>
* java.io.file.FileFilter
* java.io.file.FilenameFilter
* java.util.logging.Filter

Predicate<T>より付加価値があるもの
-------------------------------

### 付加価値？

* P62 3.3節 「それを使用しない唯一の状況は、FileFilterインスタンスを生成する多くの便利なメソッドをすでに持っている場合です」
* -> そのインタフェースを生成するメソッドを持っているかどうかで判定

### 付加価値があるもの

なし