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
��v�֐��ɕ��ނ����֐��́Amemo�ȊO�͊�{�I�ɓ����C���^�[�t�F�[�X�������Ă��āA�����悤�Ɏg�����Ƃ��ł��܂��B

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

fifo���Ɋ�{�I�Ȏg������������܂��B
�`��A���g���ƁA�f�t�H���g�̒l�Ń������������̂��ł�������܂��B

```clojure
(memo/fifo twice)
;-> #< clojure.lang.AFunction$1@ed4680a>
```

�ł����A�����͊֐��^�ł�����A���Ƃ�twice�����������ꂽ���̂ɓ���ւ��킯�ł͂Ȃ��A���������ꂽ�֐����Ԃ��Ă��܂��B�Ȃ̂ŁAdef�ȂǂŎ󂯂Ă����܂��B

```clojure
(def fifo-twice (memo/fifo twice))
;-> #'user/fifo-twice
```

���O�͌��̊֐��Ɠ����ɂ��邱�Ƃ��ł��܂����A�����ł͕ʖ���fifo-twice��t���܂����B

����fifo-twice���g���Ă݂܂��B

```clojure
(time (fifo-twice 1))
;-> "Elapsed time: 9.78648 msecs"
;-> 2

(time (fifo-twice 1))
;-> "Elapsed time: 0.226019 msecs"
;-> 2
```

���̂悤�ɁA1��ڂ�10msec�قǂ������Ă��܂����A2��ڂ�1msec�ȉ��ŕԂ��Ă��܂��B
����́Atwice�̖{�̂��Ă΂ꂸ�Ƀ������ꂽ���e���Ԃ��Ă��邩��ŁA���̂悤�ɁA�ȑO�v�Z�������ʂ��g���܂킷���Ƃ��������̖ړI�ł��B

�`��C���g���ƁA�������̈�̃T�C�Y���w��ł��܂��B
������2�Ԗڂ̓L�[�ŁA `:<�^�C�v>/threshold` �Ƃ����`���ɂȂ��Ă��āA�^�C�v�̂Ƃ���ɂ́Afifo,lru�Ȃǂ̎�ʂ�����܂��B

```clojure
(def fifo3-twice (memo/fifo twice :fifo/threshold 3))
;-> #'user/fifo3-twice

(def lru3-twice (memo/lru twice :lru/threshold 3))
:-> #'user/lru3-twice
```

���̗�ł̓������̈��3�ł��B �m�F���Ă݂܂��傤�B

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

���̂悤�ɁA�̈���g�������Ă��܂��ƒl���̂Ă��Ă��܂����߁A���x�����ɖ߂��Ă��܂��܂��B
�ǂ̃f�[�^���̂Ă���̂��͎�ʂɂ���ĈقȂ�Afifo�̏ꍇ�A�ŏ��ɓ��������̂���̂Ă��܂��B

�`��B�̏����l��ݒ肷��g�����ɂ��ẮA���܂���������Ă��Ȃ��悤�Ɏv���̂ŁA�Ō�ɏ����܂��B

### �������헪�ɂ��Ⴂ
�ݒ肵��臒l���z�����Ƃ��ɂǂ̃f�[�^���̂Ă��邩�����ꂼ��̊֐��̈Ⴂ�ł��B

#### fifo
_�Â����̂���_�폜����܂��B����̗�̒ʂ�ł��B

#### lru
_�Ō�Ɏg���Ă���ł����Ԃ̌o����_���̂���폜����܂��B

```
user> (def lru3-twice (memo/lru twice :lru/threshold 3))     �� �̈�3�ō쐬
#'user/lru3-twice

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

####lu
_�ł��g����p�x�̏��Ȃ�����_����̂Ă��܂��B

```
user> (def lu3-twice (memo/lu twice :lu/threshold 3)) �� �̈�3�ō쐬
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

####ttl
����3�ƈ���āA_���Ԃ�臒l_
