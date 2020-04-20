# Java常用类库与技巧

## 一、Java 异常体系
### 1、异常处理机制主要回答了三个问题
* What : 异常类型回答了什么被抛出
* Where : 异常堆栈跟踪回答了在哪抛出
* Why : 异常信息回答了为什么被抛出

### 2、Error和Exception的区别
#### (1)、Java的异常体系
![Image](https://github.com/2571138262/Java-Interview/tree/master/javayichangtixi.jpg)
* RuntimeException : 不可预知的，程序应当自行避免
* 非RuntimeException : 可预知的，从编译器检验的异常

##### 从责任角度看
###### 1、Error属于JVM需要负担的责任
###### 2、RuntimeException是程序应该负担的责任
###### 3、Checked Exception可检查异常是Java编译器应该负担的责任


#### (2)、从概念角度解析Java的异常处理机制
* Error : 程序无法处理的系统错误，编译器不做检查
* Exception : 程序可以处理的异常，捕获后可能恢复
* 总结 : 前者是程序无法处理的错误，后者是可以处理的异常

### 3、常见Error以及Exception
#### (1)、RuntimeException
* NullPointedException - 控制住引用异常
* ClassCastException - 类型强制转换异常
* IllegalArgumentException - 传递非法参数异常
* IndexOutOfBoundsException - 下标越界异常
* NumberFormatException - 数据格式异常

#### (2)、非RuntimeException
* ClassNotFoundException - 找不到指定class的异常
* IOException - IO操作异常

#### (3)、Error
* NoClassDefFoundError - 找不到class定义的异常
* StackOverflowError - 深递归导致站被耗尽而抛出的异常
* OutOfMemoryError - 内存溢出异常


## 二、Java异常要点分析

### 1、Java的异常处理机制
* 抛出异常 : 创建异常对象，交由运行时系统处理
* 捕获异常 : 寻找合适的异常处理器处理异常，否则终止运行

### 2、Java异常的处理原则 
* 具体明确 : 抛出的异常应能通过异常类名和message准确说明异常的类型和产生异常的原因
* 提早抛出 : 应尽可能早的发现并抛出异常，便于精确定位问题。
* 延迟捕获 : 异常的捕获和处理应尽可能延迟，让掌握更多信息的作用域来处理异常

### 3、高效主流的异常处理框架
#### 在用户看来，应用系统发生的所有异常都是应用系统内部的异常
* 设计一个通用的继承自RuntimeException的异常来统一处理
* 其余异常都统一转译为上述异常AppException
* 在catch之后，抛出上述异常的子类，并提供足以定位的信息
* 由前端接收AppException做统一处理

![Image](https://github.com/2571138262/Java-Interview/tree/master/yichangchulikuangjiatixi.jpg)

### 4、try-catch的性能
#### Java异常处理消耗性能的地方
* try-catch块影响JVM的优化
* 异常对象实例需要保存栈快照等信息，开销较大


## 三、Java集合框架
#### 工作中小时而面试却长存的算法与数据结构
* 优秀的算法和数据结构被封装到了Java的集合框架之中

### 1、数据机构考点
#### 数组和链表的区别
#### 链表的操作，如反转，链表环路检测，双向链表，循环链表相关操作;
#### 队列，栈的应用；
#### 二叉树的遍历方式以及其递归和非递归的实现
#### 红黑树的旋转

### 2、算法考点
#### 内部排序 : 如递归排序、交叉排序(冒泡、快排)、选择排序、插入排序;
#### 外部排序 : 应掌握如何利用有限的内存配合海量的外部存储来处理超大的数据集，写不出来也要有相关的思路

### 3、考点扩展
#### 那些排序是不稳定的，稳定以为着什么
    快排、堆排序
#### 不同数据集，各种排序最好或最差的情况
#### 如何优化算法
    以空间换时间，给出一些思路
    
### 4、集合之List和Set
![Image](https://github.com/2571138262/Java-Interview/tree/master/ListAndSet.jpg)


## 四、HashMap
### 1、集合之Map
![Image](https://github.com/2571138262/Java-Interview/tree/master/Map.jpg)

### 2、HashMap、HashTable、ConcurrentHashMap
#### (1)、HashMap（Java8 以前）: 数组 + 链表




