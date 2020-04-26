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



## 六、J.U.C知识点梳理
### 1、java.util.concurrent:提供了并发编程的解决方案
* CAS是java.util.concurrent.atomic包的基础
* AQS是java.util.concurrent.locks包以及一些常用类比如Semaphore、ReentrantLock等类的基础

### 2、J.U.C包的分类
* 线程执行器executor
* 锁locks
* 原子变量类atomic
* 并发工具类tools
* 并发集合collections 

### 3、J.U.C包介绍
![Image](https://github.com/2571138262/Java-Interview/tree/master/J.U.Czhishishuli.jpg)
###### J.U.C 中的 Lock 方便对线程间的共享资源做更细粒度的锁控制
###### Condition 是由 Lock 对象创建的，一个Lock对象可以创建多个Condition，主要用于将线程的等待和唤醒(即 wait()、notify()、notifyAll()) 进行对象化
###### 不管是Lock 还是 Condition 都是基于AQS来实现的，而 AQS 的底层是通过 LockSupport.unPark() 和 LockSupport.park() 方法去实现线程阻塞和唤醒的， 而 LockSupport 方法的底层也是调用了 UNSAFE 本地方法来实现的
###### ReentrantReadWriteLock 可重入读写锁，指的是没有线程进行写操作的时，多个线程可同时进行读操作，当有线程进行写操作时，其他读写操作只能等待，即读读能共存，读写和写写都不能共存，这和Mysql中读锁和写锁的机制类似，在读多余写的时候，可重入读写锁能够提供比排他锁更好的并发性和吞吐量
###### Atomic是指一个操作是不可中断的，所谓原子类指的就是具有原子特征的操作类

### 4、并发工具类
* 闭锁 CountDownLatch
* 栅栏 CyclicBarrier
* 信号量 Semaphore
* 交换器 Exchanger

#### (1)、CountDownLatch : 让主线程等待一组事件发生后继续执行
* 事件指的是CountDownLatch里的countDown()方法
###### 需要注意的是其他线程调用完 countDown() 方法之后还是会继续执行的，也就是说 countDown() 方法调用完成之后，并不代表该子线程已经执行完毕，而是告诉主线程你可以继续执行，至少我(当前子线程)这边不会拖你后腿了，具体还需要看其他线程给不给力了
![Image](https://github.com/2571138262/Java-Interview/tree/master/countDownLatch.jpg)
#### (2)、CyclicBarrier : 阻塞当前线程，等待其他线程
* 等待其他线程，且会阻塞自己当前线程，所有线程必须同时到达栅栏位置后，才能继续执行；
* 所有线程到达栅栏处，可以出发执行另一个预先设置的线程
###### 和CountDownLatch一样，CyclicBarrier内部也是包含了一个计数器，当每个线程调用一次await()，那么它的计数器就会减一，且在他们调用await()方法的时候，如果计数器不为0，这些线程也会被阻塞，另外，当前线程会在所有线程到达栅栏的时候，即计数器为0的时候，才会跟着其他子线程一起去执行
###### 同样都是阻塞当前线程以等待其他线程，CountDownLatch的其他子线程是可以继续执行的，而CyclicBarrier的所有子线程会被阻塞，直到计数器变成0，这是俩者作用上的区别
#### (3)、Semaphore : 控制某个资源可被同时访问的线程个数
![Image](https://github.com/2571138262/Java-Interview/tree/master/Semaphore.jpg)

#### (4)、Exchanger : 俩个线程到达同步点后，相互交换数据 
![Image](https://github.com/2571138262/Java-Interview/tree/master/exchanger.jpg)
###### 交换器主要用来线程之间进行数据交换，它提供一个同步点，在这个同步点，俩个线程可以交换彼此的数据，Exchanger会产生一个同步点，一个线程先执行到达同步点，就会被阻塞，直到另外一个线程也进入到同步点为止，当来个都到达同步点之后就开始交换数据，
###### 线程方法中调用Exchanger.exchange()的地方，就是这个同步点，Exchanger只能用在俩个线程互相交换数据


### 5、BlockingQueue : 提供可阻塞的入队和出队操作 
![Image](https://github.com/2571138262/Java-Interview/tree/master/BlockingQueue.jpg)

##### 主要用于生产者-消费者模式，在多线程场景时生产者线程在队列尾部添加元素，而消费者线程则在队列头部消费元素，通过这种方式能够达到将任务的生产者和消费者进行隔离的目的

### 6、BlockingQueue 的主要实现类
#### (1)、ArrayBlockingQueue : 一个由数组结构组成的有界阻塞队列；
#### (2)、LinkedBlockingQueue : 一个由链表结构组成的有界/无界阻塞队列
#### (3)、PriorityBlockingQueue : 一个支持优先级排序的无界阻塞队列；
#### (4)、DealyQueue : 一个使用优先级队列实现的无界阻塞队列；支持延时获取，可以指定多久才能获取当前元素
#### (5)、SynchronousQueue : 一个不存储元素的阻塞队列；仅允许容纳一个元素
#### (6)、LinkedTransferQueue : 一个由链表结构组成的无界阻塞队列；是LinkedBlockingQueue 和Synchronous的合体 ，比Linked性能更高， 比Sync存储的元素更多
#### (7)、LinkedBlockingDeque : 一个由链表结构组成的双向阻塞队列；与窃取工作(即 Work-Stealing算法：某个线程从其他队列里窃取任务来执行)相关联


## 七、Java的IO机制

### 1、BIO、NIO、AIO
#### (1)、Block-IO : InputStream和OutputStream、Reader和Writer、Socket、ServerSocket等
![Image](https://github.com/2571138262/Java-Interview/tree/master/BlockingQueue.jpg)
###### BIO是基于流模型来实现的，意味着其交互方式是同步阻塞的方式，在读输入流或者写入输出流的时候在读写操作完成之前，线程会一直阻塞在那里，
###### 他们之间的调用是可靠的线性调用顺序：程序发送请求给内核，由内核去进行通信，在内核准备好数据之前，这个线程是被挂起的，
###### 类比成Client-Server则其实现模式为一个连接一个线程，即客户端有连接请求时，服务端需要启动一个线程来进行处理，代操作系统返回结果。如果这个连接不做任何事情，会造成不必要的线程开销(当然可以通过线程池的机制来改善)
###### BIO的好处：代码比较直观，简单。缺点：IO效率和扩展性存在瓶颈

#### (2)、NonBlock-IO : 构建多路复用的、同步非阻塞的IO操作
![Image](https://github.com/2571138262/Java-Interview/tree/master/NonBlock-IO.jpg)
###### NIO和BIO的明显区别是，在发起第一次请求之后线程并没有被阻塞，而是反复去检查数据是否被准备好，
###### 类比成Client-Server模式，则其实现的模式为一个请求一个线程，即客户端发送的连接的线程都会注册到多路复用器上，多路复用器轮训到连接有IO请求时，才启动一个线程进行处理
###### NIO的特点是程序需要不断去询问内核是否已经准备好，第一个阶段是非阻塞的，第二个阶段是阻塞的

#### (3)、NIO的核心
* Channels
* Buffers
* Selectors

#### (4)、NIO-Channels
* FileChannel
###### transferTo : 把FileChannel中的数据拷贝到另一个Channel 
###### transferFrom : 把另外一个Channel中的数据拷贝到FileChannel 
###### 避免了俩次用户到到内核态的上下文切换，即 "零拷贝"， 效率较高
* DatagramChannel
* SocketChannel
* ServerSocketChannel

#### (5)、NIO-Buffers
* ByteBuffer
* CharBuffer
* DoubleBuffer
* FloatBuffer
* IntBuffer
* LongBuffer
* ShortBuffer
* MappedByteBuffer

#### (6)、NIO-Selector
![Image](https://github.com/2571138262/Java-Interview/tree/master/NIO-Selector.jpg)

###### NIO的底层使用了操作系统的多路复用

#### (7)、IO多路复用 : 调用系统级别的select\poll\epoll
###### 优点 : 单线程可以同时处理多个网络IO
###### IO多路复用调用系统级别的 select\poll\epoll ，由系统进行监控IO状态，select 轮循可以监控许多的IO请求，当有一个Socket的数据被准备好的时候，就可以返回了
![Image](https://github.com/2571138262/Java-Interview/tree/master/select-poll-epoll.jpg)
![Image](https://github.com/2571138262/Java-Interview/tree/master/FD剧增后带来的IO效率问题.jpg)
![Image](https://github.com/2571138262/Java-Interview/tree/master/消息传递方式.jpg)


#### (8)、Asynchronous IO : 基于事件和回调机制
![Image](https://github.com/2571138262/Java-Interview/tree/master/AIO.jpg)

##### AIO 如何进一步加工处理结果
* 基于回调 : 实现CompletionHandler接口，调用时触发回调函数
* 返回Future : 通过isDone() 查看是否准备好，通过get()等待返回结果数据













