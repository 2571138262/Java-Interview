# Java线程知识考点
## 一、进程和线程的区别
### 1、进程和线程的由来
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/jinchenghexianchengdeyoulai.jpg)
### 2、进程和线程的区别
#### 进程是资源分配的最小单位，线程是CPU调度的最小单位
* 所有与进行相关的资源，都被记录在PCD中
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/pcb.jpg)
* 进程是抢占处理机的调度单位；线程属于某个进程，共享其资源
* 线程只有堆栈寄存器、程序计数器和TCB(线程控制表)组成
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/xianchengzicheng.jpg)


### 3、总结
#### 进程是资源分配的最小单位，线程是CPU调度的最小单位
* 线程不能看做独立应用，而进程可看做独立应用（操作系统）
* 进程有独立的地址空间，相互不影响，线程只是进程的不同执行路径，某个线程挂了，它所在的进程也就挂了
* 线程没有独立的地址空间，多进程的程序比多线程线程健壮
* 进程的切换比线程的切换开销大

### 4、Java进程和线程的关系
* Java对操作系统提供的功能进行封装，包括进程和线程
* 运行一个程序会产生一个进程，进程包含至少一个线程
* 每个进程对应一个JVM实例，多个线程共享JVM里的堆
* Java采用单线程编程模型，程序会自动创建主线程
* 主线程可以创建子线程，原则上要后于子线程完成执行



## 二、Thread中的start和run方法的区别？
* 调用start()方法会创建一个新的子线程并启动
* run()方法只是Thread的一个普通方法的调用


## 三、Thread和Runnable是什么关系？
* Thread 是实现了Runnable接口的类，使得run支持多线程
* 因类的单一继承原则，推荐多使用Runnable接口


## 四、如何实现处理线程的返回值
### 1、如何给run方法传参
#### 实现的方式主要有三种
* 构造函数传参
* 成员变量传参 
* 回调函数传参
### 2、如何实现处理线程的返回值
#### 实现的方式主要有三种
* 主线程等待法
* 使用Thread类的join()阻塞当前线程以等待子线程处理完毕
* 通过Callable接口实现 : 通过FutureTask Or 线程池获取


## 五、线程的状态
### 1、六个状态
* 新建（New）：创建后尚未启动的线程的状态，即新建的Thread没有调用start()方法
* 运行（Runnable）：包含Running和Ready
* 无限期等待（Waiting）：不会被分配CPU执行时间，需要显示被唤醒
###### 没有设置 Timeout 参数的 Object.wait()方法
###### 没有设置 Timeout 参数的 Thread.join()方法
###### LockSupport.park() 方法
* 限期等待（Timed Waiting）：在一定时间后会由系统自动唤醒
###### Thread.sleep() 方法
###### 设置了 Timeout 参数的 Object.wait() 方法
###### 设置了 Timeout 参数的 Thread.join() 方法
###### LockSupport.parkNanos() 方法
###### LockSupport.parkUntil() 方法
* 阻塞（Blocked）：等待获取排他锁
* 结束（Terminated）：已终止线程的状态，线程已经结束执行


## 六、sleep和wait的区别
### 1、基本的差别
* sleep是Thread类的方法，wait是Object类中定义的方法
* sleep()方法可以在任何地方使用
* wait()方法只能在synchronized方法或synchronized块中使用

### 2、最主要的本质区别
* Thread.sleep只会让出CPU，不会导致锁行为的改变
* Object.wait不仅让出CPU，还会释放已经占有的同步资源锁

## 七、notify 和 notifyAll 的区别
### 1、锁池EntryList
#### 锁池：假设线程A已经拥有了某个对象（不是类）的锁，而其他线程B，C想要调用这个对象的某个 synchronized 方法（或者块），由于B、C线程在进入对象的 synchronized 方法（或者块）之前必须先获得该对象锁的拥有权，而恰巧该对象的锁目前正被线程A所占用，此时B、C线程就会被阻塞，进入一个地方去等待锁的释放，这个地方便是该对象的锁池

### 2、等待池WaitSet
#### 等待池：假设线程A调用了某个对象的wait()方法，线程A就会释放该对象的锁，同时线程A就进入到了该对象的等待池中，进入到等待池中的线程不会去竞争该对象的锁

### notify和notifyAll的区别?
* notifyAll 会让所有处于等待池的线程全部进入锁池去竞争获取锁的机会
* notify 只会随机选取一个处于等待池中的线程进入锁池去竞争获取锁的机会

## 八、yield方法
#### 概念: 当调用Thread.yield()函数时，会给线程调度器一个当前线程愿意让出CPU使用的暗示，但是线程调度器可能会忽略这个暗示

## 九、如何中断线程
#### 已经被抛弃的方法
* 通过调用stop()方法停止线程
* 通过调用suspend() 和 resume() 方法

### 目前使用的方法
#### 1、调用interrupt(), 通知线程应该中断了
* 如果线程处于被阻塞状态，那么线程将立即退出被阻塞状态（例如 sleep，wait，join等方法），并抛出一个InterruptedException异常
* 如果线程处于正常活动状态，那么会将该线程的撞断标志设置为true，被设置中断标志的线程将继续正常运行，不受影响。
#### 2、需要被调用的线程配合中断
* 在正常运行任务时，经常检查本线程的中断标志位，如果被设置了中断标志就自行停止线程
* 如果线程处于正常活动状态，那么会将该线程的中断标志设置为true，被设置中断标志的线程将继续执行，不受影响


## 十、线程状态以及状态之间的转换