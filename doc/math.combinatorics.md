# math.combinatorics

## 概要
math.combinatricsは、順列/組み合わせに関連する関数を提供します。

生成されるデータは、遅延シーケンスになっているので扱いやすく、また、優
れたアルゴリズムを使って実装されています。

### 含まれている関数
以下のような関数が含まれています。

基本的に、結果はすべての場合の遅延シーケンスで返ります。

```
  (combinations items n)     - itemsからn個取った組み合わせ
  (subsets items)            - itemsの部分
  (cartesian-product & seqs) - seqsの直積
  (selections items n)       - itemsの要素から作られた、n個のシーケンス
  (permutations items)       - itemsの順列
  (partitions items)         - itemsの分割
```

### プロジェクト情報
プロジェクトは、GitHubで管理されていて、以下のURLからアクセスできます。

[https://github.com/clojure/math.combinatorics](https://github.com/clojure/math.combinatorics)


## 使いかた


### インストール
Leiningenを使うことを前提に説明します。

プロジェクトの`project.clj`の`:dependencies`に
以下のエントリを追加します。このサイトの`project.clj`も参考にしてくだ
さい。

```
[org.clojure/math.combinatorics "0.0.7"]
```

`0.0.7`は、2013/11/27時点の最新版です。最新版の情報は、プロジェクトサ
イトを参照してください。

ライブラリをインストールするために、`deps`コマンドを実行します。

```
lein deps
```

これで、ライブラリから、ローカルのリポジトリ(たぶん `$HOME/.m2`配下)に
ライブラリが転送され、使えるようになります。

インストールは以上です。

## 各関数について
ソースコードにある解説の訳をベースに説明します。

* (combinations items n)
  itemsから、n個の違う要素をとったもの。
  itemsからn個取ったときの、組み合わせです。

* (subsets items)
  itemsから作ることのできる、全ての部分集合。ただし、本来の集合では無
  いので要素の重複も許される。

* (cartesian-product & seqs)
  複数のシーケンス(seqs)のそれぞれから1つずつ取ってできるすべてのシー
  ケンス。 デカルト積と呼ばれるものです。
  forを使って作るよりも高速です。

  (cartesian-product seq1 seq2 seq3 ...) はforを使って
  (for [i1 seq1 i2 seq2 i3 seq3 ...] (list i1 i2 i3 ...))
  のように書くことができますが、これより高速です。
  
* (selections items n)
  itemsの要素から作られる、全てのn要素のシーケンスを返します。このとき、
  itemsの要素は複数回使われることもあります。

* (permutations items)
  itemsのすべての順列を返します。
  _注意_ 1.2以前は、lex-permutationsという、ソートされているシーケンス
  だと高速に動作するバージョンの関数もありましたが、これは、1.3以降で
  はpermutationsに統合されています。
  
* (partitions items)
  itemsを分割したものを返します。



## 使用例
実際に使って計算してみます。

### 環境
ライブラリを読み込むときには、useかrequireを使います。もともと個人的に
useの使用は控えていましたが、最近のトレンドもrequireのようです。
そして、どのライブラリの機能を使っているのかわかるようにするために、基
本的にはrequire に :as を付けて使っています。

nsに入れるときはこうです。

```clojure
(ns sample
  (require [clojure.math.combinatorics :as combo]))
```

replだと、こうですね。

```clojure
(require '[clojure.math.combinatorics :as combo])
```

nsの中で使うときと、書式が違うところはいつもひっかかります。


### combinations
全ての組み合わせを作ります。

```clojuer
user> (combo/combinations [1 2 3] 2)
((1 2) (1 3) (2 3))

user> (class *1)
clojure.lang.LazySeq
```

返ってくるのは遅延シーケンス(LazySeq)です。が、

```clojure
user> (take 2 (combo/combinations (iterate #(* 2 %) 1) 2))
OutOfMemoryError Java heap space  clojure.core/iterate (core.clj:2651)
```
itemsとして無限シーケンスを渡すと、全部展開しようとしてしまって、
OutOfMemoryになってしまいます。

### permutations
全ての順列を作ります。

```clojure
user> (combo/permutations [1 2 3])
([1 2 3] [1 3 2] [2 1 3] [2 3 1] [3 1 2] [3 2 1])

user> (class *1)
clojure.lang.LazySeq
```

これも遅延シーケンスが返ります。
また、当然のことながら、無限シーケンスの順列は作れません。


同様の関数として、lex-permutations というものも提供されていて、1.3(た
ぶん、clojureの)より前は、ソートされているシーケンスを渡すときには、こ
ちらの方が高速であるというようなことだったようです。
しかし、1.3以降は統合されて、permutationsをソートされているシーケンス
で呼ぶと、内部でlex-permutationsが呼ばれるようになっています。


### subsets
全ての部分集合を作ります。

```clojure
user> (combo/subsets [1 2 3])
(() (1) (2) (3) (1 2) (1 3) (2 3) (1 2 3))

user> (class *1)
clojure.lang.LazySeq
```

このように、空集合と自身を含んだリストが返ります。
また、部分集合という用語を使いましたが、厳密には集合ではないので、要素
が重複していても動きます。


```clojure
user> (combo/subsets (repeat 3 7))
(() (7) (7) (7) (7 7) (7 7) (7 7) (7 7 7))
```

### selections
シーケンスの要素を使ってできる、n個の要素のシーケンスのリストを返しま
す。

下の例であれば、 1と2を使ってつくることができる、3つの数字のペアを計算
できます。

```clojure
user> (combo/selections [1 2] 3)
((1 1 1) (1 1 2) (1 2 1) (1 2 2) (2 1 1) (2 1 2) (2 2 1) (2 2 2))
```

日本の硬貨4枚で作れる金額をすべて求めてみましょう。
まず、4枚の硬貨の並べ方は何通りあるかというと、

```clojure
(->> (combo/selections [1 5 10 50 100 500] 4)
     (count ,,))
1296
```

1296通りもあります。
ですが、この中には、(1 1 1 5) (1 1 5 1) など、同じものが沢山入っている
ので排除しないといけませんが、とりえず、足して金額にしてしまいます。
重複している金額を排除するために、一度setに入れて、sortします。

```clojure
(->> (combo/selections [1 5 10 50 100 500] 4)
     (map #(reduce + %) ,,)
     (into #{} ,,)
     (sort ,,))

(4 8 12 13 16 17 20 21 22 25 26 30 31 35 40 53 57 61 62 65
 66 70 71 75 80 102 103 106 107 110 111 112 115 116 120 121
 125 130 151 152 155 156 160 161 165 170 200 201 202 205 206
 210 211 215 220 250 251 255 260 300 301 305 310 350 400 503
 507 511 512 515 516 520 521 525 530 552 556 560 561 565 570
 601 602 605 606 610 611 615 620 650 651 655 660 700 701 705
 710 750 800 1002 1006 1010 1011 1015 1020 1051 1055 1060 1100
 1101 1105 1110 1150 1200 1501 1505 1510 1550 1600 2000)
```

120通りありました。


### cartesian-product
与えられた全てのシーケンスから1つずつ取ってできるシーケンスのリストを
返します。

```clojure
user> (combo/cartesian-product [1 2] [3 4])
((1 3) (1 4) (2 3) (2 4))
```

要素数は異なっていてもOKです。

```clojure
user> (combo/cartesian-product [1 2] [3 4 5])
((1 3) (1 4) (1 5) (2 3) (2 4) (2 5))
```

これを使うと、トランプのカード一覧とか作れますね。

```clojure
user> (combo/cartesian-product [:s :d :h :c] (concat [:A] (range 2 11) [:J :Q :K]))
((:s :A) (:s 2) (:s 3) (:s 4) (:s 5) (:s 6) (:s 7) (:s 8) (:s 9) (:s 10) (:s :J) (:s :Q) (:s :K)
 (:d :A) (:d 2) (:d 3) (:d 4) (:d 5) (:d 6) (:d 7) (:d 8) (:d 9) (:d 10) (:d :J) (:d :Q) (:d :K)
 (:h :A) (:h 2) (:h 3) (:h 4) (:h 5) (:h 6) (:h 7) (:h 8) (:h 9) (:h 10) (:h :J) (:h :Q) (:h :K)
 (:c :A) (:c 2) (:c 3) (:c 4) (:c 5) (:c 6) (:c 7) (:c 8) (:c 9) (:c 10) (:c :J) (:c :Q) (:c :K))
```

これと同じようなことは、`for`を使っても実現できるわけですが、より高速
だそうです。試してみましょう。


```clojure
user> (time (count (for [a (range 100) b (range 100) c (range 100) d (range 100)] (list a b c))))
"Elapsed time: 28645.263827 msecs"
100000000
user> (time (count (combo/cartesian-product (range 100) (range 100) (range 100) (range 100) )))
"Elapsed time: 50881.359754 msecs"
100000000


user> (time (count (for [a (range 100) b (range 100) c (range 100)] (list a b c))))
"Elapsed time: 381.198072 msecs"
1000000
user> (time (count (combo/cartesian-product (range 100) (range 100) (range 100) )))
"Elapsed time: 533.59404 msecs"
1000000
```

なんか、全然速くないですね。
forの速度が向上しているのではないかと思います。

### partitions
シーケンスの中身を任意の個数に分割したものを返します。
いくつかに分けるとしたらどのような分けかたがあるかということです。

```clojure
user> (combo/partitions [1 2 3])
(([1 2 3]) ([1 2] [3]) ([1 3] [2]) ([1] [2 3]) ([1] [2] [3]))
```

:maxと:minで分割数の上限と下限を指定することができます。

```clojure
user> (combo/partitions [1 2 3 4] :max 2)
(([1 2 3 4]) ([1 2 3] [4]) ([1 2 4] [3]) ([1 2] [3 4]) ([1 3 4] [2]) ([1 3] [2 4]) ([1 4] [2 3]) ([1] [2 3 4]))

user> (combo/partitions [1 2 3 4] :min 3)
(([1 2] [3] [4]) ([1 3] [2] [4]) ([1] [2 3] [4]) ([1 4] [2] [3]) ([1] [2 4] [3]) ([1] [2] [3 4]) ([1] [2] [3] [4]))
```

両方指定すれば、ちょうどn個に分割することもできます。

```clojure
user> (combo/partitions [1 2 3 4] :min 3 :max 3)
(([1 2] [3] [4]) ([1 3] [2] [4]) ([1] [2 3] [4]) ([1 4] [2] [3]) ([1] [2 4] [3]) ([1] [2] [3 4]))
```

要素は重複していても問題無く扱えるので、たとえば、数字を1の並びとして
表わすことにすれば、
[整数分割](http://ja.wikipedia.org/wiki/整数分割)の問題を解くのに使え
たりします。

4の整数分割であれば、4は[1 1 1 1]になるので、

```clojure
(map (fn [x] (map #(count %) x))
     (combo/partitions [1 1 1 1]))

((4)
 (3 1)
 (2 2)
 (2 1 1)
 (1 1 1 1))
```

