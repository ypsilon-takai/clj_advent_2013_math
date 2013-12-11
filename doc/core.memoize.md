# clojure.core.memoize

clojureにはもともとメモ化の関数memoizeが入っていますが、より高い機能を提供するためのライブラリです。

## 概要
メモ化にってなに？という方は、[wikipediaの説明](http://ja.wikipedia.org/wiki/%E3%83%A1%E3%83%A2%E5%8C%96)
を読んでいただけるとわかると思います。ようするに、プログラムの高速化のテクニックの一つで、一度計算した結果をどこかに保存しておいて、次に同じ引数で呼ばれたときに、再計算せずにその結果を返すようにすることです。
また、clojureは関数が一級市民であることを利用して、すでに定義されている関数をあとからメモ化することができます。wikipediaでは、「自動メモ化」として紹介されています。

メモ化は、計算にコスト(=時間)がかかる場合に有効ですが、その代償としてに記憶容量を余計に使います。このことは、長時間運用されるシステムでは問題になる場合があり、あまり参照されないような結果をいつまでも保存しておくことは
メモリの無駄であり、悪くするとメモ化領域のみでメモリを食い潰してしまうようなことになってしまいます。

このmemoizeライブラリでは、メモ化領域サイズに閾値を設定する機能を提供しています。閾値を越えたときにどのデータを捨てるかという戦略の違う何種類かの関数を提供しています。

また、メモ化領域には、[core.cashライブラリ](http://github.com/clojure/core.cache)を使っていて、memoize関数よりも領域の管理に優れています。

### 注意
メモ化の仕組みからわかると思いますが、副作用のある関数をメモ化することに意味はありません。 メモ化された関数は、同じ引数での2回目以降の呼び出しでは関数が呼ばれませんので、期待した結果が得られないでしょう。

### 含まれている関数
以下の関数が含まれています。

#### 主要関数
メモ化された関数を作る関数です。引数については使いかたのところで説明します。

* fifo (First-In-First-Out)
  メモ化領域にキューを使います。制限容量を越えた場合に、始めに入れられたデータが捨てられることになります。

* lru  (Least-recently-used)
  制限容量を越えた場合はメモされている結果のうち、アクセスされてから最も時間の経っているものが捨てられます。

* lu   (Least-used)
  制限容量を越えた場合はメモされている結果のうち、最も使われた回数が少ないものが捨てられます。
  
* ttl  (Time-to-live)
  メモ化に時間制限を付けたものです。設定した時間を過ぎた結果は捨てられます。

* memo
  上記の4つとは異なり、領域制限のないメモ化された関数を作ります。
  もとからあるmemoizeと同様の機能ですが、メモ化領域をより柔軟に使用しています。
 

#### 管理用
メモ化領域の管理のための関数です。

* snapshot [f]
  メモ化された関数fのメモ化領域の内容を返します。

* memoized? [f]
  関数fが、このライブラリでメモ化されているかどうかを返します。

* memo-clear! [f & args]
  関数fのメモ化領域の全てもしくは、指定された引数のメモの内容を消去します。

* memo-swap! [f base]
  関数fのメモ化領域の内容を、baseで指定する内容に置き換えます。

* memo-unwrap [f]
  メモ化されている関数fのメモ化されていないものを返します。

* build-memoizer [cache-factory f & args]
  メモ化された関数を作るための、低レベルなインターフェースを提供します。


### プロジェクト情報
プロジェクトは、GitHubで管理されていて、以下のURLからアクセスできます。

[https://github.com/clojure/core.memoize](https://github.com/clojure/core.memoize)

## 使いかた

### インストール
Leiningenを使うことを前提に説明します。

プロジェクトの`project.clj`の`:dependencies`に以下のエントリを追加します。ここの`project.clj`も参考にしてくだ
さい。

```
[org.clojure/core.memoize "0.5.6"]
```

`0.5.6`は、2013/12/3時点の最新版です。最新版の情報は、プロジェクトサイトを参照してください。

ライブラリをインストールするために、`deps`コマンドを実行します。

```
lein deps
```

これで、ライブラリから、ローカルのリポジトリ(たぶん `$HOME/.m2`配下)にライブラリが転送され、使えるようになります。

インストールは以上です。


## 各関数の使い方
それぞれ使いながら、機能を説明します。

### 環境

### まえおき
メモ化する関数の簡単な例として以下のような関数を使います。単に2倍した数を返すものですが、時間がかかることをシミュレートするために、10msec後に返します。

```clojure
(defn twice [x]
  (Thread/sleep 10)
  (* x 2))

(time (twice 1))
;-> "Elapsed time: 10.857736 msecs"
;-> 2
```

### 基本的な使いかた
主要関数に分類した関数は、memo以外は基本的に同じインターフェースを持っていて、同じように使うことができます。

引数は以下の3つです。
```
  [f base key threshold]
    f         : メモ化される関数
    base      : メモ化領域の初期値。fの引数のベクタをキーとして、その時の返り値を値
                とするmapを指定します。不要なときは空({})を指定します。
    key       : thresholdを指定する場合に「:fifo/threshold」を指定します。
    threshold : キューのサイズ。 保存可能な値の個数で指定します。
```

また、アリチィー違いがいくつか定義されています。

```
形式A：  [f]                ->  (fifo f {} :fifo/threshold 32))
形式B：  [f base]           ->  (fifo f base :fifo/threshold 32))
形式C：  [f tkey threshold] ->  (fifo f {} tkey threshold))
```

baseとthresholdのデフォルト値はそれぞれ、{} と 32 であることがわかりま
す。

fifoを例に基本的な使い方を説明します。
形式Aを使うと、デフォルトの値でメモ化したものができあがります。

```clojure
(memo/fifo twice)
;-> #< clojure.lang.AFunction$1@ed4680a>
```

ですが、そこは関数型ですから、もとのtwiceがメモ化されたものに入れ替わるわけではなく、メモ化された関数が返ってきます。なので、defなどで受けてあげます。

```clojure
(def fifo-twice (memo/fifo twice))
;-> #'user/fifo-twice
```

名前は元の関数と同じにすることもできますが、ここでは別名のfifo-twiceを付けました。

早速fifo-twiceを使ってみます。

```clojure
(time (fifo-twice 1))
;-> "Elapsed time: 9.78648 msecs"
;-> 2

(time (fifo-twice 1))
;-> "Elapsed time: 0.226019 msecs"
;-> 2
```

このように、1回目は10msecほどかかっていますが、2回目は1msec以下で返ってきます。
これは、twiceの本体が呼ばれずにメモされた内容が返ってくるからで、このように、以前計算した結果を使いまわすことがメモ化の目的です。

形式Cを使うと、メモ化領域のサイズを指定できます。
引数の2番目はキーで、 `:<タイプ>/threshold` という形式になっていて、タイプのところには、fifo,lruなどの種別が入ります。

```clojure
(def fifo3-twice (memo/fifo twice :fifo/threshold 3))
;-> #'user/fifo3-twice

(def lru3-twice (memo/lru twice :lru/threshold 3))
:-> #'user/lru3-twice
```

この例ではメモ化領域は3つです。 確認してみましょう。

```
user> (time (fifo3-twice 3))
"Elapsed time: 9.573864 msecs"    ★ 1回目は 10msec
6
user> (time (fifo3-twice 3))
"Elapsed time: 0.120436 msecs"    ★ 2回目は 速い
6
user> (time (fifo3-twice 4))
"Elapsed time: 9.844984 msecs"
8
user> (time (fifo3-twice 5))
"Elapsed time: 10.347077 msecs"    ★ これで3つ使った
10
user> (time (fifo3-twice 6))
"Elapsed time: 10.121053 msecs"    ★ これは4つめ
12
user> (time (fifo3-twice 3))
"Elapsed time: 9.487524 msecs"     ★ 3が遅くなってしまった。
6
```

このように、領域を使いきってしまうと値が捨てられてしまうため、速度が元に戻ってしまいます。
どのデータが捨てられるのかは種別によって異なり、fifoの場合、最初に入ったものから捨てられます。

形式Bの初期値を設定する使い方については、うまく実装されていないように思うので、最後に書きます。

### メモ化戦略による違い
設定した閾値を越えたときにどのデータが捨てられるかがそれぞれの関数の違いです。

#### fifo
_古いものから_削除されます。先程の例の通りです。

#### lru
_最後に使われてから最も時間の経った_ものから削除されます。

```
user> (def lru3-twice (memo/lru twice :lru/threshold 3))     ★ 領域3つで作成
#'user/lru3-twice

user> (time (lru3-twice 1))
"Elapsed time: 39.583961 msecs"
2
user> (time (lru3-twice 2))
"Elapsed time: 10.205193 msecs"
4
user> (time (lru3-twice 3))
"Elapsed time: 10.278885 msecs"    ★ 1～3の3つ入れました
6
user> (time (lru3-twice 1))
"Elapsed time: 0.586234 msecs"    ★ 1 を使って
2
user> (time (lru3-twice 2))
"Elapsed time: 0.360209 msecs"    ★ 2も使いました。 今一番使われていないのは3です。
4
user> (time (lru3-twice 4))
"Elapsed time: 10.387772 msecs"    ★ 4つめを入れると...
8
user> (time (lru3-twice 3))
"Elapsed time: 9.848833 msecs"    ★ 3 が消えています。
6
```

####lu
_最も使われる頻度の少ないもの_から捨てられます。

```
user> (def lu3-twice (memo/lu twice :lu/threshold 3)) ★ 領域3つで作成
#'user/lu3-twice
user> (time (lu3-twice 1))
"Elapsed time: 10.05341 msecs"
2
user> (time (lu3-twice 1))
"Elapsed time: 1.157619 msecs"
2
user> (time (lu3-twice 1))
"Elapsed time: 0.516942 msecs"
2
user> (time (lu3-twice 1))
"Elapsed time: 0.514742 msecs"    ★ 1を3回
2
user> (time (lu3-twice 2))
"Elapsed time: 10.144699 msecs"
4
user> (time (lu3-twice 2))
"Elapsed time: 0.421252 msecs"
4
user> (time (lu3-twice 2))
"Elapsed time: 0.44105 msecs"    ★ 2を2回 
4
user> (time (lu3-twice 3))
"Elapsed time: 9.973119 msecs"
6
user> (time (lu3-twice 3))
"Elapsed time: 0.235373 msecs"    ★ 3を1回使いました。 頻度は3が最低
6
user> (time (lu3-twice 4))
"Elapsed time: 9.912076 msecs"
8
user> (time (lu3-twice 3))
"Elapsed time: 10.04516 msecs"    ★ 4を入れたら、3が消えました。
6
```

####ttl
他の3つと違って、_時間を閾値_
