# math.numeric-tower

## 概要
### について
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




