# math.numeric-tower

## �T�v
math.numeric-tower�́A�������̐��w�I�֐��̋@�\��񋟂��܂��B

clojure1.2�ȑO�́Aclojure.contrib.math�Ƃ������O�ł����B

### �܂܂�Ă���֐�
�ȉ��̂悤�Ȋ֐����܂܂�Ă��܂��B
```
  (expt x y)  - x��y��
  (abs n)     - n�̐�Βl
  (gcd m n)   - m��n�̍ő����
  (lcm m n)   - m��n�̍ŏ����{��
  (floor x)   - �����ւ̊ۂ� (�؂�̂�)
  (ceil x)    - �����ւ̊ۂ� (�؂�グ)
  (round x)   - �����ւ̊ۂ� (�l�̌ܓ�)
  (sqrt x)    - ������
  (exact-integer-sqrt k)
            -�����ł̕�������"�]��"
```

### �v���W�F�N�g���
�v���W�F�N�g�́AGitHu�ŊǗ�����Ă��āA�ȉ���URL����A�N�Z�X�ł��܂��B

[https://github.com/clojure/math.numeric-tower](https://github.com/clojure/math.numeric-tower)


## �g������
Leiningen���g�����Ƃ�O��ɐ������܂��B

### �C���X�g�[��
�v���W�F�N�g��`project.clj`��`:dependencies`��
�ȉ��̃G���g����ǉ����܂��B���̃T�C�g��`project.clj`���Q�l�ɂ��Ă���
�����B

```
[org.clojure/math.numeric-tower "0.0.2"]
```

`0.0.2`�́A2013/11/14���_�̍ŐV�łł��B�ŐV�ł̏��́A�v���W�F�N�g��
�Q�Ƃ��Ă��������B

���C�u�������C���X�g�[�����邽�߂ɁA`deps`�R�}���h�����s���܂��B

```
lein deps
```

����ŁA���C�u��������A���[�J���̃��|�W�g��(���Ԃ� `$HOME/.m2`�z��)��
���C�u�������]������A�g����悤�ɂȂ�܂��B

�C���X�g�[���͈ȏ�ł��B

## �e�֐��ɂ���
�e�֐���doc string��󂵂Ă����܂��B

_����_
�Ԃ�l�̌^�ɂ��ẮA�L�ڂƈ�������ʂ��ł܂����B���̂�����͎g�p���
�Ƃ���ɋL�ڂ��܂��B


###  (expt base pow)  - base��pow��
base��pow���Ԃ��܂��B
base������(exact number)�ŁApow��integer�̂Ƃ��A������Ԃ��܂��B
����ȊO�̏ꍇ�́Adouble��Ԃ��܂��B

###  (abs n)     - n�̐�Βl
n�̐�Βl��Ԃ��܂��B

###  (gcd m n)   - m��n�̍ő����
m��n�̍ő���񐔂�Ԃ��܂��B

###  (lcm m n)   - m��n�̍ŏ����{��
m��n�̍ŏ����{����Ԃ��܂��B

###  (floor x)   - �����ւ̊ۂ� (�؂�̂�)
x���z���Ȃ����������ő�̐�����Ԃ��܂��B
x�������̏ꍇ��integer��Ԃ��܂��B�����łȂ����double��Ԃ��܂��B

###  (ceil x)    - �����ւ̊ۂ� (�؂�グ)
x���ȏ�̍ő�̐�����Ԃ��܂��B
x�������̏ꍇ��integer��Ԃ��܂��B�����łȂ����double��Ԃ��܂��B

###  (round x)   - �����ւ̊ۂ� (�l�̌ܓ�)
x���������ʂŎl�̌ܓ�����������Ԃ��܂��B
��ɐ�����Ԃ��܂��B

###  (sqrt x)    - ������
x�̕�������Ԃ��܂��B
�\�ł���΁A������Ԃ��܂��B

###  (exact-integer-sqrt k) -�����ł̕�������"�]��"
���łȂ�k�ɂ��āA`k=s^2+r`����`k<(s+1)^2`�ł���悤��s��r�̃x�N�^
`[s r]`��Ԃ��܂��B����������ƕ�������`floor`�Ɨ]���Ԃ��܂��B

���Ƃ��΁A(exact-integer-sqrt 15) �� �A`15 = 3^2+6`�Ȃ̂ŁA[3 6] ���
���܂��B


## �g�p��
���ۂɎg���Čv�Z���Ă݂܂��B

### ��
���C�u������ǂݍ��ނƂ��ɂ́Ause��require���g���܂��B���Ƃ��ƌl�I��
use�̎g�p�͍T���Ă��܂������A�ŋ߂̃g�����h��require�̂悤�ł��B
�����āA�ǂ̃��C�u�����̋@�\���g���Ă���̂��킩��悤�ɂ��邽�߂ɁA��
�{�I�ɂ�require �� :as ��t���Ďg���Ă��܂��B

ns�ɓ����Ƃ��͂����ł��B

```clojure
(ns sample
  (require [clojure.math.numeric-tower :as nt]))
```

repl���ƁA�����ł��ˁB

```clojure
(require '[clojure.math.numeric-tower :as nt])
```

ns�̒��Ŏg���Ƃ��ƁA�������Ⴄ�Ƃ���͂����Ђ�������܂��B


### expt
10��3����v�Z���Ă݂܂��B

```clojuer
sample> (nt/expt 10 3)
1000
sample> (class *1)
java.lang.Long
```

Long�ŕԂ��Ă��܂��B�傫�Ȓl���Ƃǂ��ł��傤�B

```clojuer
sample> (nt/expt 10 100000)
1000000000000000000000000000000000... (0��100000��)
sample> (class *1)
clojure.lang.BigInt
```

�����I��BigInt�ɂȂ�܂��B
0��͂ǂ��ł��傤�B

```clojure
sample> (nt/expt 5 0)
1
```

������1�ɂȂ�܂��B


�����ȊO�ł�Double�ɂȂ�Ƃ���܂��B�����A

```clojure
sample> (nt/expt 1.2 34.5)
539.2038551823487
sample> (class *1)
java.lang.Double
```

Double�ŕԂ��Ă��܂��B
�傫�����Ă����ƁA���܂��B

```clojure
sample> (nt/expt 1.2 3456.7)
5.079409335487194E273
sample> (nt/expt 1.2 34567.8)
Infinity
```

�������ƁA

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

�����ł��ˁB�Ō�̗�́A�����̐����悾���Ԃ�l�������ɂȂ��Ă܂��B�ł��邾���A���̌^��
�ۑ������悤�ɂȂ��Ă�݂����ł��ˁB



### abs
����͓��ɏ������Ƃ���܂���B

```clojure
sample> (nt/abs -100)
100
sample> (nt/abs -3.5)
3.5
sample> (nt/abs -4/5)
4/5
```

### gcd �� lcm
�ő���񐔂ƍŏ����{���ł��B

```clojure
sample> (nt/gcd 12 16)
4
sample> (nt/lcm 12 16)
48
```

�����╪��������Ɨ�O���������܂��B

```clojure
sample> (nt/lcm 3.8 4.2)
IllegalArgumentException lcm requires two integers  clojure.math.numeric-tower/lcm (numeric_tower.clj:188)
sample> (nt/gcd 3/2 7/9)
IllegalArgumentException gcd requires two integers  clojure.math.numeric-tower/gcd (numeric_tower.clj:179)
```

### �ۂߊ֐� (floor, ceil, round)
�܂Ƃ߂Čv�Z���Ă݂܂��B

```clojure
sample> (map (juxt nt/floor nt/ceil nt/round) [3 3.2 3.5 3.7])
([3 3 3] [3.0 4.0 3] [3.0 4.0 4] [3.0 4.0 4])
```

�ǂ������������Ƃ��̂܂ܕԂ�܂��B

`[3 3 3]`

3.2������Ƃ����Ȃ�܂��B

`[3.0 4.0 3]`

floor��ceil��Double�ŕԂ�܂����Around�͐����ɂȂ��Ă܂��B

3.5������Ƃ����Ȃ�܂��B

`[3.0 4.0 4]`

round�͎l�̌ܓ��Ȃ̂ŁA4�ɂȂ��Ă܂��ˁB


### ������ (sqrt, exact-integer-sqrt)
docstring�ɂ͏����Ă���܂��񂪁Asqrt�͕������̐��̕���Ԃ��܂��B

```clojure
sample> (nt/sqrt 10)
3.1622776601683795
sample> (class *1)
java.lang.Double
```

��������^����ƁA���ʂ�Long�ŕԂ�܂��B

```clojure
sample> (nt/sqrt 169)
13
sample> (class *1)
java.lang.Long
```

`exact-integer-sqrt`�͕������̐����łł��B

```clojure
sample> (nt/exact-integer-sqrt 10)
[3 1]
```

���̐���^����ƁA��O�ł͂Ȃ��ADouble/NaN���Ԃ�܂��B

```clojure
clj-advent-2013-math.sample> (nt/sqrt -1)
NaN
```

���̂悤�ɁA��������floor�Ƃ��̐���2�悵�Č��̐�����������]�肪������
�x�N�^���Ԃ�܂��B


�f������Ɏg���܂��ˁB  
���鐔���f���ł��邩�ǂ����́A���̐��̕������ȉ��̐����Ŋ��肫��邩��
�����𒲂ׂ�΂����̂ŁA���̊֐��g���܂��B

�x�^�Ȏ����ł����A����Ȋ����ł��B

```clojure
(defn prime? [n]
  (let [[int-sqrt _] (nt/exact-integer-sqrt n)]
    (loop [test-nums (range 2 (inc int-sqrt))]
      (cond (empty? test-nums) true
            (zero? (rem n (first test-nums))) false
            :else (recur (next test-nums))))))

(prime? 1018291)
�G-> true
```


