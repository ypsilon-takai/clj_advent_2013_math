# clojure.core.memoize

clojure�ɂ͂��Ƃ��ƃ������̊֐�memoize�������Ă��܂����A��荂���@�\��񋟂��邽�߂̃��C�u�����ł��B

## �T�v
�������ɂ��ĂȂɁH�Ƃ������́A[wikipedia�̐���](http://ja.wikipedia.org/wiki/%E3%83%A1%E3%83%A2%E5%8C%96)
��ǂ�ł���������Ƃ킩��Ǝv���܂��B�悤����ɁA�v���O�����̍������̃e�N�j�b�N�̈�ŁA��x�v�Z�������ʂ��ǂ����ɕۑ����Ă����āA���ɓ��������ŌĂ΂ꂽ�Ƃ��ɁA�Čv�Z�����ɂ��̌��ʂ�Ԃ��悤�ɂ��邱�Ƃł��B
�܂��Aclojure�͊֐����ꋉ�s���ł��邱�Ƃ𗘗p���āA���łɒ�`����Ă���֐������Ƃ��烁�������邱�Ƃ��ł��܂��Bwikipedia�ł́A�u�����������v�Ƃ��ďЉ��Ă��܂��B

�������́A�v�Z�ɃR�X�g(=����)��������ꍇ�ɗL���ł����A���̑㏞�Ƃ��ĂɋL���e�ʂ�]�v�Ɏg���܂��B���̂��Ƃ́A�����ԉ^�p�����V�X�e���ł͖��ɂȂ�ꍇ������A���܂�Q�Ƃ���Ȃ��悤�Ȍ��ʂ����܂ł��ۑ����Ă������Ƃ�
�������̖��ʂł���A��������ƃ������̈�݂̂Ń�������H���ׂ��Ă��܂��悤�Ȃ��ƂɂȂ��Ă��܂��܂��B

����memoize���C�u�����ł́A�������̈�T�C�Y��臒l��ݒ肷��@�\��񋟂��Ă��܂��B臒l���z�����Ƃ��ɂǂ̃f�[�^���̂Ă邩�Ƃ����헪�̈Ⴄ����ނ��̊֐���񋟂��Ă��܂��B

�܂��A�������̈�ɂ́A[core.cash���C�u����](http://github.com/clojure/core.cache)���g���Ă��āAmemoize�֐������̈�̊Ǘ��ɗD��Ă��܂��B

### ����
�������̎d�g�݂���킩��Ǝv���܂����A����p�̂���֐������������邱�ƂɈӖ��͂���܂���B ���������ꂽ�֐��́A���������ł�2��ڈȍ~�̌Ăяo���ł͊֐����Ă΂�܂���̂ŁA���҂������ʂ������Ȃ��ł��傤�B

### �܂܂�Ă���֐�
�ȉ��̊֐����܂܂�Ă��܂��B

#### ��v�֐�
���������ꂽ�֐������֐��ł��B�����ɂ��Ă͎g�������̂Ƃ���Ő������܂��B

* fifo (First-In-First-Out)
  �������̈�ɃL���[���g���܂��B�����e�ʂ��z�����ꍇ�ɁA�n�߂ɓ����ꂽ�f�[�^���̂Ă��邱�ƂɂȂ�܂��B

* lru  (Least-recently-used)
  �����e�ʂ��z�����ꍇ�̓�������Ă��錋�ʂ̂����A�A�N�Z�X����Ă���ł����Ԃ̌o���Ă�����̂��̂Ă��܂��B

* lu   (Least-used)
  �����e�ʂ��z�����ꍇ�̓�������Ă��錋�ʂ̂����A�ł��g��ꂽ�񐔂����Ȃ����̂��̂Ă��܂��B
  
* ttl  (Time-to-live)
  �������Ɏ��Ԑ�����t�������̂ł��B�ݒ肵�����Ԃ��߂������ʂ͎̂Ă��܂��B

* memo
  ��L��4�Ƃ͈قȂ�A�̈搧���̂Ȃ����������ꂽ�֐������܂��B
  ���Ƃ��炠��memoize�Ɠ��l�̋@�\�ł����A�������̈�����_��Ɏg�p���Ă��܂��B
 

#### �Ǘ��p
�������̈�̊Ǘ��̂��߂̊֐��ł��B

* snapshot [f]
  ���������ꂽ�֐�f�̃������̈�̓��e��Ԃ��܂��B

* memoized? [f]
  �֐�f���A���̃��C�u�����Ń���������Ă��邩�ǂ�����Ԃ��܂��B

* memo-clear! [f & args]
  �֐�f�̃������̈�̑S�Ă������́A�w�肳�ꂽ�����̃����̓��e���������܂��B

* memo-swap! [f base]
  �֐�f�̃������̈�̓��e���Abase�Ŏw�肷����e�ɒu�������܂��B

* memo-unwrap [f]
  ����������Ă���֐�f�̃���������Ă��Ȃ����̂�Ԃ��܂��B

* build-memoizer [cache-factory f & args]
  ���������ꂽ�֐�����邽�߂́A�჌�x���ȃC���^�[�t�F�[�X��񋟂��܂��B


### �v���W�F�N�g���
�v���W�F�N�g�́AGitHub�ŊǗ�����Ă��āA�ȉ���URL����A�N�Z�X�ł��܂��B

[https://github.com/clojure/core.memoize](https://github.com/clojure/core.memoize)

## �g������

### �C���X�g�[��
Leiningen���g�����Ƃ�O��ɐ������܂��B

�v���W�F�N�g��`project.clj`��`:dependencies`�Ɉȉ��̃G���g����ǉ����܂��B������`project.clj`���Q�l�ɂ��Ă���
�����B

```
[org.clojure/core.memoize "0.5.6"]
```

`0.5.6`�́A2013/12/3���_�̍ŐV�łł��B�ŐV�ł̏��́A�v���W�F�N�g�T�C�g���Q�Ƃ��Ă��������B

���C�u�������C���X�g�[�����邽�߂ɁA`deps`�R�}���h�����s���܂��B

```
lein deps
```

����ŁA���C�u��������A���[�J���̃��|�W�g��(���Ԃ� `$HOME/.m2`�z��)�Ƀ��C�u�������]������A�g����悤�ɂȂ�܂��B

�C���X�g�[���͈ȏ�ł��B


## �e�֐��̎g����
���ꂼ��g���Ȃ���A�@�\��������܂��B

### ��
���C�u������ǂݍ��ނƂ��ɂ́Ause��require���g���܂��B�ŋ߂̃g�����h��require�̂悤�ł��B
�����āA�ǂ̃��C�u�����̋@�\���g���Ă���̂��킩��悤�ɂ��邽�߂ɁA��
�{�I�ɂ�require �� :as ��t���Ďg���Ă��܂��B

ns�ɓ����Ƃ��͂����ł��B

```clojure
(ns sample
  (require [clojure.core.memoize :as memo]))
```

repl���ƁA�����ł��ˁB

```clojure
(require '[clojure.math.combinatorics :as combo])
```


### �܂�����
����������֐��̊ȒP�ȗ�Ƃ��Ĉȉ��̂悤�Ȋ֐����g���܂��B�P��2�{��������Ԃ����̂ł����A���Ԃ������邱�Ƃ��V�~�����[�g���邽�߂ɁA10msec��ɕԂ��܂��B

```clojure
(defn twice [x]
  (Thread/sleep 10)
  (* x 2))

(time (twice 1))
;-> "Elapsed time: 10.857736 msecs"
;-> 2
```

### ��{�I�Ȏg������
��v�֐��ɕ��ނ����֐��́Amemo�֐��ȊO�͊�{�I�ɓ����C���^�[�t�F�[�X�������Ă��āA�����悤�Ɏg�����Ƃ��ł��܂��B

�����͈ȉ���3�ł��B
```
  [f base key threshold]
    f         : �����������֐�
    base      : �������̈�̏����l�Bf�̈����̃x�N�^���L�[�Ƃ��āA���̎��̕Ԃ�l��l
                �Ƃ���map���w�肵�܂��B�s�v�ȂƂ��͋�({})���w�肵�܂��B
    key       : threshold���w�肷��ꍇ�Ɂu:fifo/threshold�v���w�肵�܂��B
    threshold : �L���[�̃T�C�Y�B �ۑ��\�Ȓl�̌��Ŏw�肵�܂��B
```

�܂��A�A���`�B�[�Ⴂ����������`����Ă��܂��B

```
�`��A�F  [f]                ->  (fifo f {} :fifo/threshold 32))
�`��B�F  [f base]           ->  (fifo f base :fifo/threshold 32))
�`��C�F  [f tkey threshold] ->  (fifo f {} tkey threshold))
```

base��threshold�̃f�t�H���g�l�͂��ꂼ��A{} �� 32 �ł��邱�Ƃ��킩���
���B


fifo���Ɋ�{�I�Ȏg������������܂��傤�B
�`��A���g���ƁAbase��threshold�̃f�t�H���g�̒l�Ń������������̂��ł�������܂��B

```clojure
(memo/fifo twice)
;-> #< clojure.lang.AFunction$1@ed4680a>
```

�ł����A�����͊֐��^�ł�����A���Ƃ�twice�����������ꂽ���̂ɕύX�����킯�ł͂Ȃ��A���������ꂽ�֐����Ԃ��Ă��܂��B���p���邽�߂ɂ́Adef�Ń��[�g����������A���[�J���ɑ���������A���K�֐��ɓn�����肵�܂��B

```clojure
(def fifo-twice (memo/fifo twice))
;-> #'user/fifo-twice
```

fifo-twice�Ƃ������O��t�����̂ŁA������g���Ă݂܂��B

```clojure
(time (fifo-twice 1))
;-> "Elapsed time: 9.78648 msecs"
;-> 2

(time (fifo-twice 1))
;-> "Elapsed time: 0.226019 msecs"
;-> 2
```

���̂悤�ɁA1��ڂ͖�10msec�������Ă��܂����A2��ڂ�1msec�ȉ��ŕԂ��Ă��܂��B
���̂悤�ɁA2��ڈȍ~��fifo-twice�̖{�̂��Ă΂ꂸ�Ƀ������ꂽ�A�v�Z�ς݂̌��ʂ��g���܂킷���Ƃ��������̖ړI�ł��B


�`��C���g���ƁA�������̈�̃T�C�Y���w��ł��܂��B
������2�Ԗڂ̓L�[�ŁA `:<�^�C�v>/threshold` �Ƃ����`�ŁA�^�C�v�̂Ƃ���ɁAfifo,lru�Ȃǂ̎�ʂ����܂��B

```clojure
(def fifo3-twice (memo/fifo twice :fifo/threshold 3))
;-> #'user/fifo3-twice

(def lru3-twice (memo/lru twice :lru/threshold 3))
:-> #'user/lru3-twice
```

���āA���̗�ł̓������̈��3�ł��B �m�F���Ă݂܂��傤�B

```
user> (time (fifo3-twice 3))
"Elapsed time: 9.573864 msecs"    �� 1��ڂ� 10msec
6
user> (time (fifo3-twice 3))
"Elapsed time: 0.120436 msecs"    �� 2��ڂ� ����
6
user> (time (fifo3-twice 4))
"Elapsed time: 9.844984 msecs"
8
user> (time (fifo3-twice 5))
"Elapsed time: 10.347077 msecs"    �� �����3�g����
10
user> (time (fifo3-twice 6))
"Elapsed time: 10.121053 msecs"    �� �����4��
12
user> (time (fifo3-twice 3))
"Elapsed time: 9.487524 msecs"     �� 3���x���Ȃ��Ă��܂����B
6
```

�̈���g�������Ă��܂��ƒl���̂Ă��Ă����āA���x�����ɖ߂�̂��m�F�ł��܂��B
�ǂ̃f�[�^���̂Ă���̂��͎�ʂɂ���ĈقȂ�Afifo�̏ꍇ�A���̎��_�̃����̒��ōŏ��ɓ��ꂽ���̂��̂Ă��܂��B

�`��B�ł̓����̏����l��ݒ�ł���̂ł����A�����_�ł��܂���������Ă��Ȃ��悤�Ɏv���̂ŁA�Ō�ɏ����܂��B���̂������������̂ł͂Ȃ����Ǝv���܂��B

### ���������Ă͂����Ȃ�����
����p�̂���֐������������Ă͂����܂���B��Ɏg���Ă���twice�ł́A10msec�̑҂�����������Ɏ��s����Ȃ��悤�ɁA�Q�Ɠ��߂łȂ��֐��̌��ʂ͗\���������̂ƈقȂ��Ă��܂��܂��B

�ʂ̗�Ƃ��ĐώZ�֐�������Ă݂܂��B

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

make-acc�́A�����ɉ��Z��������Ă��āA�����ɗ^����ꂽ�������݂̉��Z��̒l�ɉ��������̂�Ԃ��܂��B�Ȃ̂ŁA���������ŌĂ�ł��ꍇ�ɂ���ĕԂ�l���Ⴂ�܂��B
��������������܂��B

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

�\�����Ȃ����ʂɂȂ��Ă��܂��܂����B


### ��v�֐��̈Ⴂ
���ꂼ��̎�v�֐��́A�ݒ肵��臒l���z�����Ƃ��ɂǂ̃f�[�^���̂Ă��邩�ɈႢ������܂��B

#### fifo
_�Â����̂���_�폜����܂��B����̗�̒ʂ�ł��B

#### lru
_�Ō�Ɏg���Ă���ł����Ԃ̌o����_���̂���폜����܂��B

```
user> (def lru3-twice (memo/lru twice :lru/threshold 3))     �� �̈�3�ō쐬
'user/lru3-twice

user> (time (lru3-twice 1))
"Elapsed time: 39.583961 msecs"
2
user> (time (lru3-twice 2))
"Elapsed time: 10.205193 msecs"
4
user> (time (lru3-twice 3))
"Elapsed time: 10.278885 msecs"    �� 1�`3��3����܂���
6
user> (time (lru3-twice 1))
"Elapsed time: 0.586234 msecs"    �� 1 ���g����
2
user> (time (lru3-twice 2))
"Elapsed time: 0.360209 msecs"    �� 2���g���܂����B ����Ԏg���Ă��Ȃ��̂�3�ł��B
4
user> (time (lru3-twice 4))
"Elapsed time: 10.387772 msecs"    �� 4�߂������...
8
user> (time (lru3-twice 3))
"Elapsed time: 9.848833 msecs"    �� 3 �������Ă��܂��B
6
```

#### lu
_�g����p�x�̍ł����Ȃ�����_����̂Ă��܂��B

```
user> (def lu3-twice (memo/lu twice :lu/threshold 3)) �� �̈�3�ō쐬
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
"Elapsed time: 0.514742 msecs"    �� 1��3��
2
user> (time (lu3-twice 2))
"Elapsed time: 10.144699 msecs"
4
user> (time (lu3-twice 2))
"Elapsed time: 0.421252 msecs"
4
user> (time (lu3-twice 2))
"Elapsed time: 0.44105 msecs"    �� 2��2�� 
4
user> (time (lu3-twice 3))
"Elapsed time: 9.973119 msecs"
6
user> (time (lu3-twice 3))
"Elapsed time: 0.235373 msecs"    �� 3��1��g���܂����B �p�x��3���Œ�
6
user> (time (lu3-twice 4))
"Elapsed time: 9.912076 msecs"
8
user> (time (lu3-twice 3))
"Elapsed time: 10.04516 msecs"    �� 4����ꂽ��A3�������܂����B
6
```

#### ttl
����3�ƈ���āA_���Ԃ�臒l_�Ƃ��܂��B ���̎��Ԃ��o�߂���Ə�����܂��B
���̎��Ԃ́A���̃f�[�^����������Ă���̎��ԂȂ̂ŁA�r���ŗ��p����Ă����Z�b�g����܂���B

```clojure
(def ttl10-twice (memo/ttl twice :ttl/threshold 10000))
```

ttl��threshold�̓~���b�P�ʂȂ̂ŁA�����ł�10�b���w�肵���̂ŁA10�b��ɏ����܂��B
1�b���ƂɌĂяo���ėl�q�����Ă݂܂��傤�B

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
"Elapsed time: 10.269865 msecs" �� 10�b�������̂ŏ����܂����B
nil
```




### �Ǘ��p�֐��̎g������
���̃��C�u�����ɂ́A�����������֐��������e�i���X���邽�߂̊֐����p�ӂ���Ă��܂��B

#### snapshot [f]
snapshot�́A���������ꂽ�֐�f�̃����̓��e��Ԃ��܂��B���e�́A������������̃x�N�^���L�[�Ƃ��āA�Ԃ�l��l�Ƃ���map�ł��B

```
user> (memo/snapshot accum)
{[10] 23, [3] 13}
```

�������A�Ԃ��Ă������̂̓R�s�[�ł��̂ŁA�����ύX���Ă������N��܂���B

#### memoized? [f]
f�����̃��C�u�����Ń��������ꂽ�֐��ł��邩�ǂ������肵�܂��B

```
user> (memo/memoized? fifo-twice)
true
user> (memo/memoized? twice)
false
```

#### memo-clear! [f & args]
f�̃������̈���N���A���܂��B�폜����Ώۂ̈������w�肷�邱�Ƃ��ł��܂��B

```
user> (memo/snapshot accum)
{[2] 26, [1] 24, [10] 23, [3] 13}
user> (memo/memo-clear! accum [10])   �� 10���������܂��B�����̓x�N�^�ɂ��܂��B
#<PluggableMemoization ...��
user> (memo/snapshot accum)
{[2] 26, [1] 24, [3] 13}
user> 
user> (memo/memo-clear! accum )      �� �S�������܂��B
#<PluggableMemoization ...��
user> (memo/snapshot accum)
{}
```

#### memo-swap! [f base]
f�̃������̈�̓��e��base�Ŏw�肷��map�ɏ��������܂��B
���̂Ƃ��A�S���u�������Ă��܂��̂ŁA�ݒ肳��Ă�����̂̈ꕔ����������������΁Asnapshot�Ŏ��o���ĉ��H���ď���������K�v������܂��B

```
user> (memo/snapshot accum)
{[3] 32, [2] 29, [1] 27}
user> (memo/memo-swap! accum {[3] "fizz"}) 
#<PluggableMemoization ...��
user> (memo/snapshot accum)
{[3] "fizz"}
user> (accum 1)
33
user> (accum 3)
"fizz"
```

#### memo-unwrap [f]
���������ꂽ�֐�f�̌��ɂȂ����֐���Ԃ��܂��Bf���̂��͕̂ύX����܂���B

```
user> (def org-accum (memo/memo-unwrap accum))
#'user/org-accum
user> (org-accum 3)
36
user> (accum 3)
"fizz"
```

#### build-memoizer [cache-factory f & args]
�������֐�����邽�߂́A�჌�x���C���^�[�t�F�[�X�ł��B
cache-factory��core.cache�ɏ����������̂�^����ƁA��{�֐��ɖ����헪�̂��̂���邱�Ƃ��ł��܂��B
�����ƒ��ׂĂ��Ȃ��̂ŁA�ڂ������Ƃ͏����܂���B ���߂�Ȃ����B


### �g�p��
�������@�\�́A�R�X�g��������A�p�ɂɍČv�Z������K�v�����鏈���ɓK�p����ƌ��ʂ�����܂��B�܂��A���I�v��@�̃x�[�X�ɂ�����A��`���ċA�I�ɓ�������̌v�Z�ɂ��g�����Ƃ��ł��܂��B

�P��̃t�B�{�i�b�`����Ŏg���Ă݂܂��B��`�ʂ�̓�d�ċA�Ŏ������Ă��āAclojure�͍œK�����Ă���Ȃ��̂ŁA������x�傫�Ȑ��̂��̂��v�Z���悤�Ƃ���ƁA�X�^�b�N�����ӂ�Ă��܂��܂��B
�R���A���������āA������������v�Z���Ă������ƂŁA������ł��傫�Ȃ��̂��v�Z�ł���悤�ɂȂ�܂��B

```clojure
(defn fibo [n]
  (if (< n 2)
    1
    (+ (fibo (- n 2))
       (fibo (- n 1)))))

(def fibo (memo/fifo fibo))
```

�����Œ��ӂ���̂́A����܂ł̗�̗l�Ƀ��������ꂽ���̂�ʖ��ɂ��Ă͂����Ȃ��Ƃ������Ƃł��B�ċA�֐��Ȃ̂ŁA�����ŌĂ΂��֐��Ɠ������O�ɂ��Ă����Ȃ��ƁA�ċA�����ł̃����̎Q�Ƃ��ł��Ȃ��Ȃ�܂��B
�܂��A���̂悤�ɓ����ōĒ�`�����Ƃ��̓���́Aclojure�{�̂�memoize�֐��Ƃ͈���Ă��āA������̕��������I�Ȏd�l�ɂȂ��Ă��܂��B

�ł́A�g���Ă݂܂��傤�B 

```
user> (fibo 2)
2
user> (fibo 3)
3
user> (memo/snapshot fibo)
{[3] 3, [2] 2}
```

���̂悤�ɁA�����ƃ�������Ă���̂��킩��܂��B
10���o���Ă݂�ƁA

```
user> (fibo 10)
89
user> (memo/snapshot fibo)
{[1] 1, [2] 2, [3] 3, [4] 5, [5] 8, [6] 13, [7] 21, [8] 34, [9] 55, [10] 89, [0] 1}

```

������l���Ă����ׂ��Ȃ̂́A�������̈�̃T�C�Y�ł��B�ړI�ɂ����܂����A�������̈�̃T�C�Y�͏����������D�܂����ł��傤�B����̗�ł́A�K�v�Ȃ̂͒��O��1�O�̂��̂����Ȃ̂�fifo�ŁA���́A���݌v�Z�����܂߂�3����Ώ[���ł��B

����ł́A������g���đ傫�ȃt�B�{�i�b�`�������߂Ă݂܂��傤�B

```clojure
(def fibo (memo/fifo fibo :fifo/threshold 3))

(->> (map fibo (iterate inc 0N))  �� �����������珇�ɋ��߂܂��B
     (drop 10000)
     (first))                     �� 10001�Ԗڂ����܂��B

"Elapsed time: 129.03515 msecs"
```


### �����l�̐ݒ�base�ɂ���
�Ō�ɂȂ�܂����B��{�̂Ƃ���Ō�܂킵�ɂɂĂ���base�����ɂ��Ăł��B
���̃��C�u�����̊�{�֐��̂̃C���^�[�t�F�[�X��base�Ƃ����������g���Ə����l��ݒ�ł���Ƃ����悤�Ȃ��Ƃ������Ă���܂��B
�Ƃ��낪�A����炵���l�����Ă����܂������܂���B

```
user> (def fifo-twice (memo/fifo twice {[3] "fiz"}))
#'user/fifo-twice
user> (fifo-twice 3)
ClassCastException java.lang.String cannot be cast to java.util.concurrent.Future  clojure.core/deref-future (core.clj:2108)
```

�\�[�X��ǂ�ł݂�ƁAbase�ɗ^�����}�b�v�͂��̂܂܃������̈�ɕۑ������̂ɑ΂��āA�������̓��쎞�ɂ̓������̈�̒l��future���ۑ�����Ă��Ď��o���Ƃ���deref�����悤�ɂȂ��Ă��܂��B����ł́A���o���Ƃ��ɃG���[�ɂȂ��ē��R�ł��B
�T���v���Ȃǂ�ǂ�ł݂Ă��A�\�[�X�����낢�댩�Ă݂Ă��g�������Ȃ��͓̂����Ă��܂���B�����r���Ȃ̂ł��傤���B

memo-swap!�̃\�[�X����A�ނ���ݒ肷����@������Ă݂܂����B

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

����ł������������܂��B


## �܂Ƃ�
�����֐��Ƀ������@�\��ǉ��ł��Ă��܂��Ƃ����̂͊֐��^�炵���@�\�ł��B����̂��̂����łȂ��A�V�X�e�����p�ӂ������̂��������\�Ȃ̂ł��B���̈�ɂ���Ă͂ƂĂ��֗��Ɏg�����Ƃ��ł��܂��B

�܂��A���̃��C�u�����́A���Ƃ���clojure�ɂ���memoize�֐��ƈقȂ�A�������̈�𐧌�ł���悤�ɂ��Ă��邽�߁A�������̊Ǘ����������悤�Ȏ��p�̃v���O�����Ń������@�\���g���₷���Ȃ��Ă��܂��B


�l�I�ɂ́AProject Euler�������Ă����Ƃ��Ƀ���������������g�����̂ł����Aclojure�{�̂�memoize�֐��́A�ċA���ɓ��삵�Ȃ��Ƃ��A�������̈�̊Ǘ����ł��Ȃ��ȂǂŎg���ɂ����A���̂���͂܂����̃��C�u�����͂���܂���ł����̂ŁA����̃������}�N��������Ďg���Ă��܂����B
���̃��C�u�����͂���ȕs������������Ă���΂���łȂ��A��荂�@�\�ł��B�g���������낢��l���Ă݂Ă͂������ł��傤���B










