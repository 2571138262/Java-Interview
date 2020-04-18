# Java知识考点
## 一、谈谈你对Java的理解
* 平台无关性     --      一次编译到处运行
* GC             --      垃圾回收机制
* 语言特性       --      包括泛型、反射、Lambda表达式
* 面向对象       --      封装、继承、多态
* 类库           --      集合、并发库、网络库、IO和NIO之类的
* 异常处理       --      

## 二、Compile Once，Run Anywhere 如何实现 （平台无关性如何实现）
* 编译时
###### 编译时会用到 javac 指令，javac的编译编译的是Java的源码，即将源码编译生成字节码，并存入到对应的 .class 文件中，
###### class文件保存的就是我们java文件翻译成的二级制字节码，也就是Java类文件中的属性方法以及类中的常量信息都会被分别存储在 .class 文件中，
###### 当然还会添加一个共有的静态常量属性 .class，这个属性记录了类的相关信息，即类型信息，是Class的一个实例  
* 运行时
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/javapingtaiwuguanxing.jpg)
#### Java源码首先被编译成字节码，再由不同的JVM进行解析，Java语言在不同平台的JVM进行解析，Java语言在不同的平台上运行时不需要进行重新编译，Java虚拟机在执行字节码的时候，把字节码转换成具体凭条和是哪个的机器指令

### 为什么JVM不直接将源码解析成机器码去执行？
* 准备工作 ：每次执行都需要各种检查 （语法，语义检查）
* 兼容性 ：也可以将别的语言解析成字节码


## 三、JVM如何加载.class文件
#### JVM主要有 Class Loader、Runtime Data Area、Execution Engine、Native Interface这四个部分组成，它主要通过ClassLoader将符合格式要求的Class文件加载到内存里，并通过Execution Engine去解析Class文件里的字节码并提交给操作系统去执行。
### 1、Java虚拟机 
###### 虚拟机是一种抽象化的计算机，通过在实际的计算机上，仿真模拟各种计算机功能来实现的，JVM有自己完善的硬件架构（如：处理器，堆栈，寄存器等，还具有相应的指令系统），JVM屏蔽了与具体操作系统平台相关的信息，使得Java程序只需生成在Java虚拟机上运行的目标代码（字节码），就可以在多个平台上不加修改的运行
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/javapingtaiwuguanxing.jpg)
* Class Loader : 依据特定格式，加载class文件到内存
* Runtime Data Area : JVM内存空间结构模型
* Execution Engine : 对命令进行解析
* Native Interface : 融合不同开发语言的原生库为Java所用 


## 四、谈谈反射
#### Java反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用他的任意方法和属性；这种动态获取信息以及动态调用对象方法的功能成为Java语言的反射机制。

### 1、写一个反射的例子


## 五、谈谈ClassLoader

### 1、类从编译到执行的过程
* 编译器将Robot.java源文件编译为Robot.class字节码文件
* ClassLoader将字节码转换为JVM中的Class<Robot>对象
* JVM利用Class<Robot>对象实例化为Robot对象

### 2、谈谈ClassLoader
###### ClassLoader在Java中有着非常重要的作用，它主要工作在Class装载的加载阶段，其主要作用是从系统外部获得Class二进制数据流，它是Java的核心组件，所有的Class都是由ClassLoader进行加载的，ClassLoader负责通过将Class文件里的二进制数据流装载进系统，然后交给Java虚拟机进行连接，初始化等操作

### 3、ClassLoader的种类
* BootStrapClassLoader : C++编写，加载核心库 java.*
* ExtClassLoader : Java编写，加载扩展库 javax.*， 是用来加载位于 jre\lib\ext 目录下的这些jar包，用户可以将自定义的jar包放在这些目录下，通过这个ExtClassLoader去加载
######  System.getProperty("java.ext.dirs")
######  xxx\jdk1.8.0_91\jre\lib\ext;C:\Windows\Sun\Java\lib\ext
* AppClassLoader : Java编写，加载程序所在目录， 是用来加载 Classpath（类路径） 下的内容的
###### System.getProperty("java.class.path");  // 查看当前类路径
* 自定义ClassLoader : Java编写，定制化加载


### 4、自定义ClassLoader的实现
#### 关键函数

    函数1：这个函数是去寻找Class文件的，包括怎么去读进二进制流，包括怎么去处理，进而给它返回一个Class对象
    protected Class<?> findClass(String name) throws ClassNotFoundException {
            throw new ClassNotFoundException(name);
    }
    
    函数2：通过接受的字节流去定义一个类
    protected final Class<?> defineClass(String name, byte[] b, int off, int len)
            throws ClassFormatError
    {
            return defineClass(name, b, off, len, null);
    }

###### findClass根据名称或者位置去加载 .class 字节码， 然后它会调用 defineClass 去解析定义 .class 字节流， 返回Class对象

## 六、ClassLoader的双亲委派机制
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/ClassLoaderdeshuangqinweipaijizhi.jpg)
### 为什么要使用双亲委派机制去加载类
* 避免多份同样字节码的加载


## 七、类的加载方式
* 隐式加载 ：new
* 显示加载 ：loadClass， forName等
###### 对于显示加载来讲，但我们获取到类的Class对象之后，需要调用Class对象的 newInstance() 方法来生成对象实例； 
###### 而通过new来隐式加载则无需调用对象的 newInstance() 方法即可获取对象实例，并且new支持调用带参数的构造器生成对象实例
###### 而 Class 的 newInstance 不支持参数参数，需要通过反射，构造器对象的 newInstance() 方法才能支持参数

### 1、loadClass 和 forName 的区别？
###### 首先他们都能在运行时，对任意一个类，都能知道该类的所有属性和方法，对于任意一个对象，都能够调用他的任意方法和属性；
###### 通过下边的类的加载过程和 loadClass 与 forName源码 可以看出
* Class.forName 得到的class是已经初始化完成的，也就是说已经完成了类装载过程的第三步了，因为它在调用forName0()方法中的initialize参数传的是true
* Class.loadClass得到的class是还没有链接的，也就说它只完成了类的装载过程的第一步，第二步和第三步还没有做，因为它在调用 loadClass() 方法中的 resolve 传的是 false

    
    // 获取ClassLoader
    ClassLoader cl = Robot.class.getClassLoader();
    cl.loadClass("com.baixiaowen.javainterview.reflect.Robot");
    // 通过forName来加载Robot
    Class c = Class.forName("com.baixiaowen.javainterview.reflect.Robot");
    
    Class.forName("com.mysql.jdbc.Driver");
###### 在加载数据库驱动的时候，需要用forName来装载数据库驱动类，为的是调用Driver类中的静态代码块，来生成Driver对象
##### 既然 forName 可以完全初始化类，那为什么还要有 loadClass 呢？
###### 在SpringIOC中， 在资源加载器要读入资源的时候，即读取一些Bean的配置的时候，如果是以 classpath 的方方式来加载，就需要使用ClassLoader.loadClass() 来加载，之所以这样做，是和SpringIOC的lazyLoading（延迟加载），SpringIOC为了加载初始化速度，大量使用了延迟加载技术，而使用ClassLoader不需要执行类中的链接和初始化的加载步骤，这样做可以加快加载速度，把类的初始化工作留到实际使用到这个类的时候才去做


### 2、类的装载过程
#### （1）、加载 ：
* 通过ClassLoader加载class文件字节码，生成Class对象
###### 通过ClassLoader的 loadClass() 方法把Class文件字节码加载到内存中，并将这些静态数据转换成运行时数据区（Runtime Data Area）方法区的类型数据，在运行时，数据区堆中生成一个代表这个类的java.lang.Class 对象，作为方法区类的访问入口

#### （2）、连接：
* 校验 ：检查加载的class的正确性和安全性
###### 比如说检查Class文件的格式是否正确
* 准备 ：为变量分配存储空间并设置类变量初始化值，类变量随类型信息存放在方法区中，生命周期很长，使用不当很容易造成内存泄露
###### 这里说的类变量就是 static 的变量， 初始值类变量类型的默认值，而不是实际要赋的值
* 解析 ：JVM将常量池内的序号引用转换为直接引用 

#### （3）、初始化：
* 执行类变量赋值和静态代码块


## 八、你了解Java的内存模型么？
### 1、内存简介
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/neicunjianjie.jpg)

### 2、地址空间的划分
* 内核空间 ：主要的操作系统程序，和C运行时的空间，包括计算机硬件、调度程序、提供联网和虚拟内存等服务的逻辑和基于C的进程
* 用户空间 ：Java进程实际运行时使用的空间

### 3、JVM内存模型 - JDK8
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/JVMneicunjiegou.jpg)
#### （1）、程序技术器（Program Counter Register）
####### 程序技术器是逻辑计数器，非物理计数器，为了线程切换后都能恢复正确的执行位置，每个线程都有一个独立的线程计数器，只为Java方法计数
* 当前线程所执行的字节码行号指示器（逻辑）
* 改变计数器的值来选取下一条西药执行的字节码指令
* 和线程是一对一的关系，即"线程私有"
* 对Java方法技术，如果是Native方法则计数器值为Undifined
* 不会发生内存泄露


#### （2）、Java虚拟机栈（Stack）
* Java方法执行的内存模型，是线程私有的
* 包含多个栈帧
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/zhanzhen.jpg)
###### 每个方法被执行时够会创建一个栈帧，即方法运行期间的基础数据结构，栈帧用于存储局部变量表、操作数栈，动态链接、方法出口等，每个方法执行中对应虚拟机栈帧从入栈到出栈的过程，Java虚拟机栈用来存储栈帧，而栈帧持有局部变量的部分结果以及参与方法的调用与返回，当方法调用结束时，帧才会被销毁
    局部变量表和操作数栈
    1、局部变量表：包含方法执行过程中的所有变量
    2、操作数栈 ：入栈、出栈、赋值、交换、产生消费变量
    
##### 栈帧的执行过程 
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/zhixingadd.jpg)
###### 通过这张图的分析，局部变量列表主要是为操作数栈提供必要的数据支撑

##### 递归为什么会引发java.lang.StackOverflowError异常？
######  递归层数过多，导致栈的深度增加，每次递归都会往栈里压一个栈帧，如果超过了栈的最大深度，所以抛出了异常
##### 虚拟机栈过多会引发java.lang.OutOfMemoryError异常


#### （3）、本地方法栈
* 与虚拟机栈相似，主要作用于标注了native的方法



#### （4）、元空间（MetaSpace）与永久代（PermGen）的区别
###### 在JDK8以后开始把类的元数据放在本地堆内存中这一块区域，即元空间，该区域在JDK7及以前是属于永久代的，元空间和永久代都是用来存储Class的相关信息的，包括Class的method和Field等，
###### 元空间和永久代都是方法区的实现，就是实现有所不同
* 元空间使用本地内存，而永久代使用的是jvm的内存

##### MetaSpace想比PermGen的优势
* 字符串常量池存在永久代中，容易出现性能问题和内存溢出，MetaSpace没有了常量池，常量池在JDK7的时候移动到了堆中
* 类和方法的信息大小难易确定，给永久代的大小指定带来困难
* 永久代会为GC带来不必要的复杂性，回收效率偏低
* 方便HotSpot与其他JVM如Jrockit的集成


#### （5）、Java堆（Heap）
* 对象实例的分配区域
* GC管理的主要区域
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/Heap.jpg)



## 八、JVM的内存结构常考题解析
### 1、JVM三大性能调优参数 -Xms -Xmx -Xss的含义
###### java -Xms128m -Xmx128m -Xss256k -jar xxxx.jar 
* -Xss ：规定了每个线程虚拟机栈（堆栈）的大小，这个配置此进程中并发线程数的大小
* -Xms ：堆的初始值，可以进行扩容，扩容最大至 -Xmx
* -Xmx ：堆能达到的最大值

### 2、Java内存模型中堆和栈的区别 --- 内存分配策略
* 静态存储 ：编译时确定每个数据目标在运行时的存储空间需求
* 栈式存储 ：数据区需求在编译时未知，运行时模块入口前确定
* 堆式存储 ：编译时或运行时模块入口都无法确定，动态分配
* 联系 ：引用对象、数组时，栈里定义变量保存堆中的首地址。栈中的变量就成了数组或者对象的引用变量，因此我们就可以在程序中使用栈中的引用变量来访问堆中的数组或者对象，引用变量就相当于为数组或者对象起的一个名称，
引用变量是普通的变量，定义时在栈中分配，引用变量在程序运行到其作用域之外就会被释放掉了，而数组和对象本身在堆中分配，即使程序运行到使用new产生数组或者对象之外的代码块，数组和对象本身占据的内存不会释放，
在没有引用变量指向的时候，才会变成垃圾，等待垃圾回收器来释放掉
* 管理方式 ：栈自动释放，堆需要GC
* 空间大小 ：栈比堆小
* 碎片相关 ：栈产生的碎片远小于堆
* 分配方式 ：栈支持静态和动态分配，而堆仅支持动态分配
* 效率 ：栈的效率比堆高


### 3、元空间、堆、线程独占部分间的联系 --- 内存角度
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/shilidaima.jpg)
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/neicunjiaodu.jpg)


### 4、不同JDK版本之间的intern()方法的区别 --- JDK6 VS JDK6+
    String s = new String("a");
    s.intern();
* JDK6：当调用 intern 方法时，如果字符串常量池先前已创建出该字符串对象，则返回池中的该字符串的引用。否则，将此字符串对象添加到字符串常量池中，并且返回该字符串对象的引用。
* JDK6+：当调用 intern 方法时，如果字符串常量池先前已创建出该字符串对象，则返回池中的该字符串的引用；否则，如果该字符串对象已经存在于Java堆中，则将堆中对此对象的引用添加到字符串常量池中，并且返回该引用；如果堆中不存在，则在池中创建该字段并且返回其引用



## 九、Java垃圾回收机制
### 1、对象被判定为垃圾的标准
*  没有别其他对象引用

### 2、判定对象是否为垃圾的算法
* 引用计数算法
* 可达性分析算法

#### （1）、判断对象的引用数量
* 通过判断对象的引用数量来决定对象是否可以被回收
* 每个对象实例都有一个引用计数器，被引用则+1，完成引用则-1
* 任何引用计数为0的对象实例可以被当作垃圾收集

#### （2）、引用计数算法
* 优点 ：执行效率高，程序执行受影响较小
* 缺点 ：无法检测出循环引用的情况，导致内存泄露

#### （3）。可达性分析算法
##### 通过判断对象的引用链是否可达来决定对象是否可以被回收？
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/kexingxingfenxisuanfa.png)
##### 可以作为GC Root的对象
* 虚拟机栈中引用的对象（栈帧中的本地变量表）
###### 比如在Java方法里new 了一个Object对象，并赋值给了一个局部变量，那么在该局部变量没有销毁之前，new 出的Object就会成为GC Root
* 方法区中的常量引用的对象
###### 比如在类中定义了一个常量，该常量保存的是某个对象的地址，那么被保存的对象也就会成为GC Root
* 方法区中的类静态属性引用的对象
* 本地方法栈中JNI（Native方法）的引用对象
* 活跃线程的引用对象


### 3、谈谈你了解的垃圾回收算法
####（1）、标记-清除算法（Mark and Sweep）
* 标记 ：从根集合进行扫描，对存活的对象进行标记
* 清除 ：对堆内存从头到尾进行线性遍历，回收不可达对象内存
##### 缺点  --  碎片化
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/biaojiqingchusuanfa.jpg)

#### （2）、复制算法（Copying）
* 分为对象面和空闲面
* 对象在对象面上创建
* 存活的对象从对象面复制到空间面
* 将对象面所有对象内存清除
##### 优点 
* 解决碎片化问题
* 顺序分配内存，简单高效
* 适用于对象存活率低的场景 

![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/fuzhisuanfa.jpg)
##### 缺点
###### 复制收集算法在应对对象存活率较高的时候，要进行较多的赋值操作，效率就会变低，更关键的是如果不想浪费50%的空间，就需要有额外的空间进行分配担保，以应对使用的内存中所有对象都100%存活的极端情况，比如老年代

#### （3）、标记-整理算法（Compacting）
* 标记 ：从根集合进行扫描，对存活的对象进行标记
* 清除 ：移动所有存活的对象，且按照内存地址次序一次排列，然后将末端内存地址以后全部回收

##### 好处
* 避免内存的不连续性
* 不需要设置俩块内存互换
* 适用于存活率高的场景
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/biaojizhenglisuanfa.jpg)

#### （4）、分代收集算法（Generational Collector）   -----   主流的垃圾回收算法
* 垃圾回收算法的组合拳
* 按照对象生命周期的不同划分区域以采用不同的垃圾回收算法
* 目的：提高JVM的回收效率

##### JDK6、JDK7
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/GCJDK67.jpg)
##### JDK8+
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/GCJDK8.jpg)

##### 分代收集算法的GC分类
* Minor GC 年轻代的GC 采用复制算法
* Full GC

##### 年轻代 ：尽可能快速地收集掉那些生命周期短的对象
* Eden 区
8 两个Survivor区
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/nianqingdai.jpg)


##### 对象如何晋升到老年代
* 经历一定Minor次数依然存活的对象，默认15岁
* Survivor区中存放不下的对象
* 新生成的大对象（-XX:+PretenuerSizeThreshold）


##### 常用的调优参数
* -XX:SurvivorRatio : Eden和Survivor的比值，默认是 8 : 1
* -XX:NewRatio : 老年代和年轻代内存大小的比例，比如 = 2， 那就说明老年代占的内存是年轻代的 2 倍， 新生代占内存的三分之一，新生代和老年代的总内存是怎么决定的呢？通过 -Xms 和 -Xmx来决定的
* -XX:MaxTenuringThreshold : 对象从年轻代晋升到老年代经历过GC次数的最大阈值 可以设置为超过15岁就成为老年代

##### 老年代 ：存放生命周期较长的对象
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/laoniandai.jpg)

##### 老年代
* Full GC 和 Major GC
* Full GC 比 Minor GC 慢，但执行频率低

##### 触发Full GC的条件
* 老年代空间不足， 如果创建一个大对象，Eden中放不下这个对象，就会直接保存在老年代中，如果老年代空间也不足，就会触发 Full GC
* 永久代空间不足， （这仅仅针对JDK7之前的版本），当系统需要加载的类调用的方法很多，同时永久代中没有足够的空间去存放类的信息还有方法的信息，就会触发出一次 Full GC
* CMS GC 时出现promotion failed，concurrent mode failure
* Minor GC 晋升到老年代的平均大小大于老年代的剩余空间
* System.gc()
* 使用RMI来进行RPC或者管理的JDK应用，每小时执行1次Full GC


### 4、java垃圾回收机制之新生代垃圾收集器
#### （1）、Stop-the-World
* JVM由于要执行GC而停止了应用程序的执行
* 任何一种GC算法中都会发生
* 多数GC优化通过减少 Stop-the-World 发生的时间来提高程序性能

#### （2）、Safepoint
* 分析过程中对象引用关系不会发生变化的点
* 产生Safepoint的地方 : 方法调用；循环跳转；异常跳转等
* 安全点数量得适中

## 十、常见的垃圾收集器
### 1、JVM的运行模式
* Server
* Client
### 2、垃圾收集器之间的联系
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/lajishoujiqizhijiandelianxi.jpg)
### 3、年轻代常见的垃圾收集器
#### Serial收集器（-XX:+UserSerialGC,复制算法）
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/Serialshoujiqi.jpg)
#### ParNew收集器（-XX:+UserParNewGC,复制算法）
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/ParNewshoujiqi.jpg)
#### Parallel Scavenge 收集器（-XX:+UseParallelGC,复制算法）
* 吞吐量 = 运行用户代码时间/(运行用户代码时间+垃圾收集时间)
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/ParallelScavenger.jpg)

### 4、老年代常见的垃圾收集器
#### Serial Old 收集器（-XX:+UseSerialOldGC，标记-整理算法）
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/SerialOld.jpg)
#### Parallel Old 收集器（-XX:+UseParallelOldGC,标记-整理算法）
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/ParallelOld.jpg)
#### CMS收集器（-XX:UseConcMarkSweepGC,标记-清除算法）
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/CMS.jpg)
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/CMSshoujiqitushi.jpg)
#### Garbage First收集器的特点
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/Garbage.jpg)
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/Garbage2.jpg) 


### 5、GC相关的面试题
#### （1）、Object的finalize()方法的作用是否与C++的析构函数相同？
* 与C++的析构函数不同，析构函数调用确定，而它的是不确定的
* 将未被引用的对象放置于F-Queue队列
* 方法执行随时可能会被终止
* 给予对象最后一次重生的机会

#### （2）、Java中的强引用，软引用，若引用，虚引用有什么用
##### 强引用（Strong Reference）
* 最普遍的引用 ：Object obj = new Object();
* 如果一个对象具有强引用，当内存空间不足的时候，Java虚拟机宁可抛出OutOfMemoryError终止程序也不会回收具有强引用的对象
* 通过将对象设置为null来弱化引用，使其被回收
##### 软引用（Soft Reference）
* 对象处在有用但非必须的状态
* 只有当内存空间不足时，GC会回收该引用的对象的内存
* 可以用来实现内存敏感的高速缓存
* 用法

    
    String str = new String("abc"); //强引用
    SoftReference<String> softRef = new SoftReference<String>(str); // 软引用  可以配合引用队列使用
    
##### 若引用（Weak Reference）
* 非必须的对象，比软引用更若一些
* GC时会被回收
* 被回收的概率也不大，因为GC线程优先级比较低
* 使用于引用偶尔被使用且不影响垃圾收集的对象
* 用法
    
    
    String str = new String("abc");// 强引用
    WeakReference<String> abcWeakRef = new WeakReference<String>(str); // 若引用  可以配合引用队列使用
    
##### 虚引用（PhantomReference）
* 不会决定对象的声明周期
* 任何时候都可能被垃圾收集器回收
* 跟踪对象被垃圾收集器回收的活动，其哨兵作用
* 必须和引用队列ReferenceQueue联合使用
* 用法


    String str = new String("abc"); // 强引用
    ReferenceQueue queue = new ReferenceQueue(); // 引用队列
    PhantomReference ref = new PhantomReference(str, queue); // 虚引用
    
    
##### 总结：强引用 > 软引用 >  若引用 > 虚引用 
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/qiangruanruoxuyinyong.jpg)

##### 类层次结构
![Image](https://github.com/2571138262/Java-Interview/tree/master/images-folder/leicengcijiegou.jpg)

##### 引用队列（ReferenceQueue）
* 无实际存储结构，存储逻辑依赖于内部节点之间的关系来表达
* 存储关联的且被GC的软引用，弱引用以及虚引用