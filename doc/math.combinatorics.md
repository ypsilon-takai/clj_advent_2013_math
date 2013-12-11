# math.combinatorics

## �T�v
math.combinatrics�́A����/�g�ݍ��킹�Ɋ֘A����֐���񋟂��܂��B

���������f�[�^�́A�x���V�[�P���X�ɂȂ��Ă���̂ň����₷���A�܂��A�D
�ꂽ�A���S���Y�����g���Ď�������Ă��܂��B

### �܂܂�Ă���֐�
�ȉ��̂悤�Ȋ֐����܂܂�Ă��܂��B

��{�I�ɁA���ʂ͂��ׂĂ̏ꍇ�̒x���V�[�P���X�ŕԂ�܂��B

```
  (combinations items n)     - items����n������g�ݍ��킹
  (subsets items)            - items�̕���
  (cartesian-product & seqs) - seqs�̒���
  (selections items n)       - items�̗v�f������ꂽ�An�̃V�[�P���X
  (permutations items)       - items�̏���
  (partitions items)         - items�̕���
```

### �v���W�F�N�g���
�v���W�F�N�g�́AGitHub�ŊǗ�����Ă��āA�ȉ���URL����A�N�Z�X�ł��܂��B

[https://github.com/clojure/math.combinatorics](https://github.com/clojure/math.combinatorics)


## �g������


### �C���X�g�[��
Leiningen���g�����Ƃ�O��ɐ������܂��B

�v���W�F�N�g��`project.clj`��`:dependencies`��
�ȉ��̃G���g����ǉ����܂��B���̃T�C�g��`project.clj`���Q�l�ɂ��Ă���
�����B

```
[org.clojure/math.combinatorics "0.0.7"]
```

`0.0.7`�́A2013/11/27���_�̍ŐV�łł��B�ŐV�ł̏��́A�v���W�F�N�g�T
�C�g���Q�Ƃ��Ă��������B

���C�u�������C���X�g�[�����邽�߂ɁA`deps`�R�}���h�����s���܂��B

```
lein deps
```

����ŁA���C�u��������A���[�J���̃��|�W�g��(���Ԃ� `$HOME/.m2`�z��)��
���C�u�������]������A�g����悤�ɂȂ�܂��B

�C���X�g�[���͈ȏ�ł��B

## �e�֐��ɂ���
�\�[�X�R�[�h�ɂ������̖���x�[�X�ɐ������܂��B

* (combinations items n)
  items����An�̈Ⴄ�v�f���Ƃ������́B
  items����n������Ƃ��́A�g�ݍ��킹�ł��B

* (subsets items)
  items�����邱�Ƃ̂ł���A�S�Ă̕����W���B�������A�{���̏W���ł͖�
  ���̂ŗv�f�̏d�����������B

* (cartesian-product & seqs)
  �����̃V�[�P���X(seqs)�̂��ꂼ�ꂩ��1������Ăł��邷�ׂẴV�[
  �P���X�B �f�J���g�ςƌĂ΂����̂ł��B
  for���g���č����������ł��B

  (cartesian-product seq1 seq2 seq3 ...) ��for���g����
  (for [i1 seq1 i2 seq2 i3 seq3 ...] (list i1 i2 i3 ...))
  �̂悤�ɏ������Ƃ��ł��܂����A�����荂���ł��B
  
* (selections items n)
  items�̗v�f��������A�S�Ă�n�v�f�̃V�[�P���X��Ԃ��܂��B���̂Ƃ��A
  items�̗v�f�͕�����g���邱�Ƃ�����܂��B

* (permutations items)
  items�̂��ׂĂ̏����Ԃ��܂��B
  _����_ 1.2�ȑO�́Alex-permutations�Ƃ����A�\�[�g����Ă���V�[�P���X
  ���ƍ����ɓ��삷��o�[�W�����̊֐�������܂������A����́A1.3�ȍ~��
  ��permutations�ɓ�������Ă��܂��B
  
* (partitions items)
  items�𕪊��������̂�Ԃ��܂��B



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
  (require [clojure.math.combinatorics :as combo]))
```

repl���ƁA�����ł��ˁB

```clojure
(require '[clojure.math.combinatorics :as combo])
```

ns�̒��Ŏg���Ƃ��ƁA�������Ⴄ�Ƃ���͂����Ђ�������܂��B


### combinations
�S�Ă̑g�ݍ��킹�����܂��B

```clojuer
user> (combo/combinations [1 2 3] 2)
((1 2) (1 3) (2 3))

user> (class *1)
clojure.lang.LazySeq
```

�Ԃ��Ă���̂͒x���V�[�P���X(LazySeq)�ł��B���A

```clojure
user> (take 2 (combo/combinations (iterate #(* 2 %) 1) 2))
OutOfMemoryError Java heap space  clojure.core/iterate (core.clj:2651)
```
items�Ƃ��Ė����V�[�P���X��n���ƁA�S���W�J���悤�Ƃ��Ă��܂��āA
OutOfMemory�ɂȂ��Ă��܂��܂��B

### permutations
�S�Ă̏�������܂��B

```clojure
user> (combo/permutations [1 2 3])
([1 2 3] [1 3 2] [2 1 3] [2 3 1] [3 1 2] [3 2 1])

user> (class *1)
clojure.lang.LazySeq
```

������x���V�[�P���X���Ԃ�܂��B
�܂��A���R�̂��ƂȂ���A�����V�[�P���X�̏���͍��܂���B


���l�̊֐��Ƃ��āAlex-permutations �Ƃ������̂��񋟂���Ă��āA1.3(��
�Ԃ�Aclojure��)���O�́A�\�[�g����Ă���V�[�P���X��n���Ƃ��ɂ́A��
����̕��������ł���Ƃ����悤�Ȃ��Ƃ������悤�ł��B
�������A1.3�ȍ~�͓�������āApermutations���\�[�g����Ă���V�[�P���X
�ŌĂԂƁA������lex-permutations���Ă΂��悤�ɂȂ��Ă��܂��B


### subsets
�S�Ă̕����W�������܂��B

```clojure
user> (combo/subsets [1 2 3])
(() (1) (2) (3) (1 2) (1 3) (2 3) (1 2 3))

user> (class *1)
clojure.lang.LazySeq
```

���̂悤�ɁA��W���Ǝ��g���܂񂾃��X�g���Ԃ�܂��B
�܂��A�����W���Ƃ����p����g���܂������A�����ɂ͏W���ł͂Ȃ��̂ŁA�v�f
���d�����Ă��Ă������܂��B


```clojure
user> (combo/subsets (repeat 3 7))
(() (7) (7) (7) (7 7) (7 7) (7 7) (7 7 7))
```

### selections
�V�[�P���X�̗v�f���g���Ăł���An�̗v�f�̃V�[�P���X�̃��X�g��Ԃ���
���B

���̗�ł���΁A 1��2���g���Ă��邱�Ƃ��ł���A3�̐����̃y�A���v�Z
�ł��܂��B

```clojure
user> (combo/selections [1 2] 3)
((1 1 1) (1 1 2) (1 2 1) (1 2 2) (2 1 1) (2 1 2) (2 2 1) (2 2 2))
```

���{�̍d��4���ō�����z�����ׂċ��߂Ă݂܂��傤�B
�܂��A4���̍d�݂̕��ו��͉��ʂ肠�邩�Ƃ����ƁA

```clojure
(->> (combo/selections [1 5 10 50 100 500] 4)
     (count ,,))
1296
```

1296�ʂ������܂��B
�ł����A���̒��ɂ́A(1 1 1 5) (1 1 5 1) �ȂǁA�������̂���R�����Ă���
�̂Ŕr�����Ȃ��Ƃ����܂��񂪁A�Ƃ肦���A�����ċ��z�ɂ��Ă��܂��܂��B
�d�����Ă�����z��r�����邽�߂ɁA��xset�ɓ���āAsort���܂��B

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

120�ʂ肠��܂����B


### cartesian-product
�^����ꂽ�S�ẴV�[�P���X����1������Ăł���V�[�P���X�̃��X�g��
�Ԃ��܂��B

```clojure
user> (combo/cartesian-product [1 2] [3 4])
((1 3) (1 4) (2 3) (2 4))
```

�v�f���͈قȂ��Ă��Ă�OK�ł��B

```clojure
user> (combo/cartesian-product [1 2] [3 4 5])
((1 3) (1 4) (1 5) (2 3) (2 4) (2 5))
```

������g���ƁA�g�����v�̃J�[�h�ꗗ�Ƃ����܂��ˁB

```clojure
user> (combo/cartesian-product [:s :d :h :c] (concat [:A] (range 2 11) [:J :Q :K]))
((:s :A) (:s 2) (:s 3) (:s 4) (:s 5) (:s 6) (:s 7) (:s 8) (:s 9) (:s 10) (:s :J) (:s :Q) (:s :K)
 (:d :A) (:d 2) (:d 3) (:d 4) (:d 5) (:d 6) (:d 7) (:d 8) (:d 9) (:d 10) (:d :J) (:d :Q) (:d :K)
 (:h :A) (:h 2) (:h 3) (:h 4) (:h 5) (:h 6) (:h 7) (:h 8) (:h 9) (:h 10) (:h :J) (:h :Q) (:h :K)
 (:c :A) (:c 2) (:c 3) (:c 4) (:c 5) (:c 6) (:c 7) (:c 8) (:c 9) (:c 10) (:c :J) (:c :Q) (:c :K))
```

����Ɠ����悤�Ȃ��Ƃ́A`for`���g���Ă������ł���킯�ł����A��荂��
�������ł��B�����Ă݂܂��傤�B


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

�Ȃ񂩁A�S�R�����Ȃ��ł��ˁB
for�̑��x�����サ�Ă���̂ł͂Ȃ����Ǝv���܂��B

### partitions
�V�[�P���X�̒��g��C�ӂ̌��ɕ����������̂�Ԃ��܂��B
�������ɕ�����Ƃ�����ǂ̂悤�ȕ������������邩�Ƃ������Ƃł��B

```clojure
user> (combo/partitions [1 2 3])
(([1 2 3]) ([1 2] [3]) ([1 3] [2]) ([1] [2 3]) ([1] [2] [3]))
```

:max��:min�ŕ������̏���Ɖ������w�肷�邱�Ƃ��ł��܂��B

```clojure
user> (combo/partitions [1 2 3 4] :max 2)
(([1 2 3 4]) ([1 2 3] [4]) ([1 2 4] [3]) ([1 2] [3 4]) ([1 3 4] [2]) ([1 3] [2 4]) ([1 4] [2 3]) ([1] [2 3 4]))

user> (combo/partitions [1 2 3 4] :min 3)
(([1 2] [3] [4]) ([1 3] [2] [4]) ([1] [2 3] [4]) ([1 4] [2] [3]) ([1] [2 4] [3]) ([1] [2] [3 4]) ([1] [2] [3] [4]))
```

�����w�肷��΁A���傤��n�ɕ������邱�Ƃ��ł��܂��B

```clojure
user> (combo/partitions [1 2 3 4] :min 3 :max 3)
(([1 2] [3] [4]) ([1 3] [2] [4]) ([1] [2 3] [4]) ([1 4] [2] [3]) ([1] [2 4] [3]) ([1] [2] [3 4]))
```

�v�f�͏d�����Ă��Ă���薳��������̂ŁA���Ƃ��΁A������1�̕��тƂ���
�\�킷���Ƃɂ���΁A
[��������](http://ja.wikipedia.org/wiki/��������)�̖��������̂Ɏg��
���肵�܂��B

4�̐��������ł���΁A4��[1 1 1 1]�ɂȂ�̂ŁA

```clojure
(map (fn [x] (map #(count %) x))
     (combo/partitions [1 1 1 1]))

((4)
 (3 1)
 (2 2)
 (2 1 1)
 (1 1 1 1))
```

