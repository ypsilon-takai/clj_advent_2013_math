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
ライブラリを読み込むときには、useかrequireを使います。最近のトレンドもrequireのようです。
そして、どのライブラリの機能を使っているのかわかるようにするために、基
本的にはrequire に :as を付けて使っています。

nsに入れるときはこうです。

```clojure
(ns sample
  (require [clojure.core.memoize :as memo]))
```

replだと、こうですね。

```clojure
(require '[clojure.math.combinatorics :as combo])
```


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
主要関数に分類した関数は、memo関数以外は基本的に同じインターフェースを持っていて、同じように使うことができます。

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


fifoを例に基本的な使い方を説明しましょう。
形式Aを使うと、baseとthresholdのデフォルトの値でメモ化したものができあがります。

```clojure
(memo/fifo twice)
;-> #< clojure.lang.AFunction$1@ed4680a>
```

ですが、そこは関数型ですから、もとのtwiceがメモ化されたものに変更されるわけではなく、メモ化された関数が返ってきます。利用するためには、defでルート束縛したり、ローカルに束縛したり、高階関数に渡したりします。

```clojure
(def fifo-twice (memo/fifo twice))
;-> #'user/fifo-twice
```

fifo-twiceという名前を付けたので、これを使ってみます。

```clojure
(time (fifo-twice 1))
;-> "Elapsed time: 9.78648 msecs"
;-> 2

(time (fifo-twice 1))
;-> "Elapsed time: 0.226019 msecs"
;-> 2
```

このように、1回目は約10msecかかっていますが、2回目は1msec以下で返ってきます。
このように、2回目以降はfifo-twiceの本体が呼ばれずにメモされた、計算済みの結果を使いまわすことがメモ化の目的です。


形式Cを使うと、メモ化領域のサイズを指定できます。
引数の2番目はキーで、 `:<タイプ>/threshold` という形で、タイプのところに、fifo,lruなどの種別を入れます。

```clojure
(def fifo3-twice (memo/fifo twice :fifo/threshold 3))
;-> #'user/fifo3-twice

(def lru3-twice (memo/lru twice :lru/threshold 3))
:-> #'user/lru3-twice
```

さて、この例ではメモ化領域は3つです。 確認してみましょう。

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

領域を使いきってしまうと値が捨てられてしって、速度が元に戻るのが確認できます。
どのデータが捨てられるのかは種別によって異なり、fifoの場合、その時点のメモの中で最初に入れたものが捨てられます。

形式Bではメモの初期値を設定できるのですが、現時点でうまく実装されていないように思うので、最後に書きます。そのうち実装されるのではないかと思います。

### メモ化してはいけないもの
副作用のある関数をメモ化してはいけません。例に使っているtwiceでは、10msecの待ちがメモ化後に実行されないように、参照透過でない関数の結果は予期したものと異なってしまいます。

別の例として積算関数を作ってみます。

```clojure
(defn make-acc []
  (let [acc (atom 0)]
    (fn [n]
      (swap! acc + n)
      @acc)))

(def accum (make-acc))
;-> #'user/accum
(accum 3)
;-> 3
(accum 3)
;-> 6
```

make-accは、内部に加算器を持っていて、引数に与えられた数を現在の加算器の値に加えたものを返します。なので、同じ引数で呼んでも場合によって返り値が違います。
これをメモ化します。

```
(def accum (memo/fifo accum))
;-> #'user/accum
(accum 3)
;-> 13
(accum 3)
;-> 13
(accum 3)
;-> 13
```

予期しない結果になってしまいました。


### 主要関数の違い
それぞれの主要関数は、設定した閾値を越えたときにどのデータが捨てられるかに違いがあります。

#### fifo
_古いものから_削除されます。先程の例の通りです。

#### lru
_最後に使われてから最も時間の経った_ものから削除されます。

```
user> (def lru3-twice (memo/lru twice :lru/threshold 3))     ★ 領域3つで作成
'user/lru3-twice

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

#### lu
_使われる頻度の最も少ないもの_から捨てられます。

```
user> (def lu3-twice (memo/lu twice :lu/threshold 3)) ★ 領域3つで作成
'user/lu3-twice
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

#### ttl
他の3つと違って、_時間を閾値_とします。 一定の時間が経過すると消されます。
この時間は、そのデータがメモされてからの時間なので、途中で利用されてもリセットされません。

```clojure
(def ttl10-twice (memo/ttl twice :ttl/threshold 10000))
```

ttlのthresholdはミリ秒単位なので、ここでは10秒を指定したので、10秒後に消えます。
1秒ごとに呼び出して様子を見てみましょう。

```
user> (dotimes [n 11] 
        (time (ttl10-twice 3))
        (Thread/sleep 1000))
"Elapsed time: 16.104028 msecs"
"Elapsed time: 0.15013 msecs"
"Elapsed time: 0.153979 msecs"
"Elapsed time: 0.159478 msecs"
"Elapsed time: 0.163878 msecs"
"Elapsed time: 0.162228 msecs"
"Elapsed time: 0.173776 msecs"
"Elapsed time: 0.160029 msecs"
"Elapsed time: 0.155079 msecs"
"Elapsed time: 0.100637 msecs"
"Elapsed time: 10.269865 msecs" ★ 10秒たったので消えました。
nil
```




### 管理用関数の使いかた
このライブラリには、メモ化した関数をメンテナンスするための関数も用意されています。

#### snapshot [f]
snapshotは、メモ化された関数fのメモの内容を返します。内容は、相当する引数のベクタをキーとして、返り値を値とするmapです。

```
user> (memo/snapshot accum)
{[10] 23, [3] 13}
```

ただし、返ってきたものはコピーですので、これを変更しても何も起りません。

#### memoized? [f]
fがこのライブラリでメモ化された関数であるかどうか判定します。

```
user> (memo/memoized? fifo-twice)
true
user> (memo/memoized? twice)
false
```

#### memo-clear! [f & args]
fのメモ化領域をクリアします。削除する対象の引数を指定することもできます。

```
user> (memo/snapshot accum)
{[2] 26, [1] 24, [10] 23, [3] 13}
user> (memo/memo-clear! accum [10])   ★ 10だけ消します。引数はベクタにします。
#<PluggableMemoization ...略
user> (memo/snapshot accum)
{[2] 26, [1] 24, [3] 13}
user> 
user> (memo/memo-clear! accum )      ★ 全部消します。
#<PluggableMemoization ...略
user> (memo/snapshot accum)
{}
```

#### memo-swap! [f base]
fのメモ化領域の内容をbaseで指定するmapに書き換えます。
このとき、全部置き換えてしまうので、設定されているものの一部を書き換えたければ、snapshotで取り出して加工して書き換える必要があります。

```
user> (memo/snapshot accum)
{[3] 32, [2] 29, [1] 27}
user> (memo/memo-swap! accum {[3] "fizz"}) 
#<PluggableMemoization ...略
user> (memo/snapshot accum)
{[3] "fizz"}
user> (accum 1)
33
user> (accum 3)
"fizz"
```

#### memo-unwrap [f]
メモ化された関数fの元になった関数を返します。fそのものは変更されません。

```
user> (def org-accum (memo/memo-unwrap accum))
#'user/org-accum
user> (org-accum 3)
36
user> (accum 3)
"fizz"
```

#### build-memoizer [cache-factory f & args]
メモ化関数を作るための、低レベルインターフェースです。
cache-factoryにcore.cacheに準拠したものを与えると、基本関数に無い戦略のものを作ることができます。
ちゃんと調べていないので、詳しいことは書けません。 ごめんなさい。


### 使用例
メモ化機能は、コストがかかり、頻繁に再計算をする必要がある処理に適用すると効果があります。また、動的計画法のベースにしたり、定義が再帰的に得られる問題の計算にも使うことができます。

恒例のフィボナッチ数列で使ってみます。定義通りの二重再帰で実装していて、clojureは最適化してくれないので、ある程度大きな数のものを計算しようとすると、スタックがあふれてしまいます。
然し、メモ化して、小さい方から計算していくことで、いくらでも大きなものを計算できるようになります。

```clojure
(defn fibo [n]
  (if (< n 2)
    1
    (+ (fibo (- n 2))
       (fibo (- n 1)))))

(def fibo (memo/fifo fibo))
```

ここで注意するのは、これまでの例の様にメモ化されたものを別名にしてはいけないということです。再帰関数なので、内部で呼ばれる関数と同じ名前にしておかないと、再帰部分でのメモの参照ができなくなります。
また、このように同名で再定義したときの動作は、clojure本体のmemoize関数とは違っていて、こちらの方が直感的な仕様になっています。

では、使ってみましょう。 

```
user> (fibo 2)
2
user> (fibo 3)
3
user> (memo/snapshot fibo)
{[3] 3, [2] 2}
```

このように、ちゃんとメモされているのがわかります。
10を出してみると、

```
user> (fibo 10)
89
user> (memo/snapshot fibo)
{[1] 1, [2] 2, [3] 3, [4] 5, [5] 8, [6] 13, [7] 21, [8] 34, [9] 55, [10] 89, [0] 1}

```

もう一つ考えておくべきなのは、メモ化領域のサイズです。目的にもよりますが、メモ化領域のサイズは小さい方が好ましいでしょう。今回の例では、必要なのは直前と1つ前のものだけなのでfifoで、数は、現在計算中を含めて3つあれば充分です。

それでは、これを使って大きなフィボナッチ数を求めてみましょう。

```clojure
(def fibo (memo/fifo fibo :fifo/threshold 3))

(->> (map fibo (iterate inc 0N))  ★ 小さい方から順に求めます。
     (drop 10000)
     (first))                     ★ 10001番目を取ります。

"Elapsed time: 129.03515 msecs"
```


### 初期値の設定baseについて
最後になりました。基本のところで後まわしににていたbase引数についてです。
このライブラリの基本関数ののインターフェースのbaseという引数を使うと初期値を設定できるというようなことが書いてあります。
ところが、それらしい値を入れてもうまく動きません。

```
user> (def fifo-twice (memo/fifo twice {[3] "fiz"}))
#'user/fifo-twice
user> (fifo-twice 3)
ClassCastException java.lang.String cannot be cast to java.util.concurrent.Future  clojure.core/deref-future (core.clj:2108)
```

ソースを読んでみると、baseに与えたマップはそのままメモ化領域に保存されるのに対して、メモ化の動作時にはメモ化領域の値にfutureが保存されていて取り出すときにderefされるようになっています。これでは、取り出すときにエラーになって当然です。
サンプルなどを読んでみても、ソースをいろいろ見てみても使えそうなものは入っていません。実装途中なのでしょうか。

memo-swap!のソースから、むりやり設定する方法を作ってみました。

```clojure
(defn make-base [seed]
  (into {}
        (for [[k v] seed]
          [k (reify
               clojure.lang.IDeref
               (deref [this] v))])))

(def fifo-twice
  (memo/fifo twice (make-base {[3] "fizz"})))
```

```
user> (fifo-twice 3)
"fizz"
user> (fifo-twice 2)
4
user> (memo/snapshot fifo-twice)
{[2] 4, [3] "fiz"}
```

これでいちおう動きます。


## まとめ
既存関数にメモ化機能を追加できてしまうというのは関数型らしい機能です。自作のものだけでなく、システムが用意したものもメモ化可能なのです。問題領域によってはとても便利に使うことができます。

また、このライブラリは、もとからclojureにあるmemoize関数と異なり、メモ化領域を制御できるようにしてあるため、メモリの管理がしたいような実用のプログラムでメモ化機能が使いやすくなっています。


個人的には、Project Eulerを解いていたときにメモ化をたくさん使ったのですが、clojure本体のmemoize関数は、再帰時に動作しないとか、メモ化領域の管理ができないなどで使いにくく、そのころはまだこのライブラリはありませんでしたので、自作のメモ化マクロを作って使っていました。
このライブラリはそんな不満が解消されているばかりでなく、より高機能です。使い道をいろいろ考えてみてはいかがでしょうか。










