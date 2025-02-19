# Java-Design-Patterns

## 单例模式
Java中单例(Singleton)模式是一种广泛使用的设计模式。单例模式的主要作用是保证在Java程序中，某个类只有一个实例存在。

1. 它能够避免实例对象的重复创建，不仅可以减少每次创建对象的时间开销，还可以节约内存空间；

2. 能够避免由于操作多个实例导致的逻辑错误。

3. 如果一个对象有可能贯穿整个应用程序，而且起到了全局统一管理控制的作用，那么单例模式也许是一个值得考虑的选择。


### 饿汉
饿汉模式在类加载的时候就对实例进行创建，实例在整个程序周期都存在。


它的好处是只在类加载的时候创建一次实例，不会存在多个线程创建多个实例的情况，避免了多线程同步的问题。


它的缺点也很明显，即使这个单例没有用到也会被创建，而且在类加载之后就被创建，内存就被浪费了。

 这种实现方式适合单例占用内存比较小，在初始化时就会被用到的情况。但是，如果单例占用的内存比较大，或单例只是在某个特定场景下才会用到，使用饿汉模式就不合适了，这时候就需要用到懒汉模式进行延迟加载。



### 懒汉
好处：懒汉模式中单例是在需要的时候才去创建的，如果单例已经创建，再次调用获取接口将不会重新创建新的对象，而是直接返回之前创建的对象。


适用于：如果某个单例使用的次数少，并且创建单例消耗的资源较多，那么就需要实现单例的按需创建，这个时候使用懒汉模式就是一个不错的选择。


缺点：但是这里的懒汉模式并没有考虑线程安全问题，在多个线程可能会并发调用它的getInstance()方法，导致创建多个实例，因此需要加锁解决线程同步问题。


### 双重校验锁
加锁的懒汉模式看起来即解决了线程并发问题，又实现了延迟加载，然而它存在着性能问题，依然不够完美。


synchronized修饰的同步方法比一般方法要慢很多，如果多次调用getInstance()，累积的性能损耗就比较大了。


因此就有了双重校验锁，在同步代码块内多了一层instance为空的判断。由于单例对象只需要创建一次，如果后面再次调用getInstance()只需要直接返回单例对象。


JVM中并没有规定编译器优化相关的内容，也就是说JVM可以自由的进行指令重排序的优化。
这个问题的关键就在于由于指令重排优化的存在，导致初始化Singleton和将对象地址赋给instance字段的顺序是不确定的。


在某个线程创建单例对象时，在构造方法被调用之前，就为该对象分配了内存空间并将对象的字段设置为默认值。


此时就可以将分配的内存地址赋值给instance字段了，然而该对象可能还没有初始化。若紧接着另外一个线程来调用getInstance，取到的就是状态不正确的对象，程序就会出错。

volatile是一个特殊的修饰符，只有成员变量才能使用它。


在Java并发程序缺少同步类的情况下，多线程对成员变量的操作对其它线程是透明的。


volatile变量可以保证下一个读取操作会在前一个写操作之后发生。 