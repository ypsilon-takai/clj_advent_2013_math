# math.numeric-tower

## 概要
math.numeric-towerは、いくつかの数学的関数の機能を提供します。

clojure1.2以前は、clojure.contrib.mathという名前でした。

### 含まれている関数
以下のような関数が含まれています。
```
  (expt x y)  - xのy乗
  (abs n)     - nの絶対値
  (gcd m n)   - mとnの最大公約数
  (lcm m n)   - mとnの最小公倍数
  (floor x)   - 整数への丸め (切り捨て)
  (ceil x)    - 整数への丸め (切り上げ)
  (round x)   - 整数への丸め (四捨五入)
  (sqrt x)    - 平方根
  (exact-integer-sqrt k)
            -整数での平方根と"余り"
```

### プロジェクト情報
プロジェクトは、GitHuで管理されていて、以下のURLからアクセスできます。

[https://github.com/clojure/math.numeric-tower](https://github.com/clojure/math.numeric-tower)


## 使いかた
Leiningenを使うことを前提に説明します。

### インストール
プロジェクトの`project.clj`の`:dependencies`に
以下のエントリを追加します。このサイトの`project.clj`も参考にしてくだ
さい。

```
[org.clojure/math.numeric-tower "0.0.2"]
```

`0.0.2`は、2013/11/14時点の最新版です。最新版の情報は、プロジェクトを
参照してください。

ライブラリをインストールするために、`deps`コマンドを実行します。

```
lein deps
```

これで、ライブラリから、ローカルのリポジトリ(たぶん `$HOME/.m2`配下)に
ライブラリが転送され、使えるようになります。

インストールは以上です。

## 各関数について
各関数のdoc stringを訳しておきます。

_注意_
返り値の型については、記載と違った結果がでました。そのあたりは使用例の
ところに記載します。


###  (expt base pow)  - baseのpow乗
baseのpow乗を返します。
baseが整数(exact number)で、powがintegerのとき、整数を返します。
それ以外の場合は、doubleを返します。

###  (abs n)     - nの絶対値
nの絶対値を返します。

###  (gcd m n)   - mとnの最大公約数
mとnの最大公約数を返します。

###  (lcm m n)   - mとnの最小公倍数
mとnの最小公倍数を返します。

###  (floor x)   - 整数への丸め (切り捨て)
xを越えないか等しい最大の整数を返します。
xが整数の場合はintegerを返します。そうでなければdoubleを返します。

###  (ceil x)    - 整数への丸め (切り上げ)
xを以上の最大の整数を返します。
xが整数の場合はintegerを返します。そうでなければdoubleを返します。

###  (round x)   - 整数への丸め (四捨五入)
xを小数第一位で四捨五入した整数を返します。
常に整数を返します。

###  (sqrt x)    - 平方根
xの平方根を返します。
可能であれば、整数を返します。

###  (exact-integer-sqrt k) -整数での平方根と"余り"
負でないkについて、`k=s^2+r`かつ`k<(s+1)^2`であるようなsとrのベクタ
`[s r]`を返します。言い換えると平方根の`floor`と余りを返します。

たとえば、(exact-integer-sqrt 15) は 、`15 = 3^2+6`なので、[3 6] を返
します。


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
  (require [clojure.math.numeric-tower :as nt]))
```

replだと、こうですね。

```clojure
(require '[clojure.math.numeric-tower :as nt])
```

nsの中で使うときと、書式が違うところはいつもひっかかります。


### expt
10の3乗を計算してみます。

```clojuer
sample> (nt/expt 10 3)
1000
sample> (class *1)
java.lang.Long
```

Longで返ってきます。大きな値だとどうでしょう。

```clojuer
sample> (nt/expt 10 100000)
1000000000000000000000000000000000... (0が100000個)
sample> (class *1)
clojure.lang.BigInt
```

自動的にBigIntになります。
0乗はどうでしょう。

```clojure
sample> (nt/expt 5 0)
1
```

ちゃんと1になります。


整数以外ではDoubleになるとあります。小数、

```clojure
sample> (nt/expt 1.2 34.5)
539.2038551823487
sample> (class *1)
java.lang.Double
```

Doubleで返ってきます。
大きくしていくと、溢れます。

```clojure
sample> (nt/expt 1.2 3456.7)
5.079409335487194E273
sample> (nt/expt 1.2 34567.8)
Infinity
```

分数だと、

```clojure
sample> (nt/expt 3/2 7/5)
1.7641185337870102
sample> (nt/expt 7 7/5)
15.245344971379456
sample> (nt/expt 3/2 8)
6561/256
sample> (class *1)
clojure.lang.Ratio
```

こうですね。最後の例の、分数の整数乗だけ返り値も分数になってます。できるだけ、元の型が
保存されるようになってるみたいですね。



### abs
これは特に書くことありません。

```clojure
sample> (nt/abs -100)
100
sample> (nt/abs -3.5)
3.5
sample> (nt/abs -4/5)
4/5
```

### gcd と lcm
最大公約数と最小公倍数です。

```clojure
sample> (nt/gcd 12 16)
4
sample> (nt/lcm 12 16)
48
```

小数や分数を入れると例外が発生します。

```clojure
sample> (nt/lcm 3.8 4.2)
IllegalArgumentException lcm requires two integers  clojure.math.numeric-tower/lcm (numeric_tower.clj:188)
sample> (nt/gcd 3/2 7/9)
IllegalArgumentException gcd requires two integers  clojure.math.numeric-tower/gcd (numeric_tower.clj:179)
```

### 丸め関数 (floor, ceil, round)
まとめて計算してみます。

```clojure
sample> (map (juxt nt/floor nt/ceil nt/round) [3 3.2 3.5 3.7])
([3 3 3] [3.0 4.0 3] [3.0 4.0 4] [3.0 4.0 4])
```

どれも整数を入れるとそのまま返ります。

`[3 3 3]`

3.2を入れるとこうなります。

`[3.0 4.0 3]`

floorとceilはDoubleで返りますが、roundは整数になってます。

3.5を入れるとこうなります。

`[3.0 4.0 4]`

roundは四捨五入なので、4になってますね。


### 平方根 (sqrt, exact-integer-sqrt)
docstringには書いてありませんが、sqrtは平方根の正の方を返します。

```clojure
sample> (nt/sqrt 10)
3.1622776601683795
sample> (class *1)
java.lang.Double
```

平方数を与えると、結果はLongで返ります。

```clojure
sample> (nt/sqrt 169)
13
sample> (class *1)
java.lang.Long
```

`exact-integer-sqrt`は平方根の整数版です。

```clojure
sample> (nt/exact-integer-sqrt 10)
[3 1]
```

負の数を与えると、例外ではなく、Double/NaNが返ります。

```clojure
clj-advent-2013-math.sample> (nt/sqrt -1)
NaN
```

このように、平方根のfloorとその数を2乗して元の数から引いた余りが入った
ベクタが返ります。


素数判定に使えますね。  
ある数が素数であるかどうかは、その数の平方根以下の整数で割りきれるかど
うかを調べればいいので、この関数使えます。

ベタな実装ですが、こんな感じです。

```clojure
(defn prime? [n]
  (let [[int-sqrt _] (nt/exact-integer-sqrt n)]
    (loop [test-nums (range 2 (inc int-sqrt))]
      (cond (empty? test-nums) true
            (zero? (rem n (first test-nums))) false
            :else (recur (next test-nums))))))

(prime? 1018291)
；-> true
```


