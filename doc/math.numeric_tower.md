# math.numeric-tower

## �T�v
### �ɂ���
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




