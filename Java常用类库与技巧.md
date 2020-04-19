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


