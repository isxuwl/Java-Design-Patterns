package Singleton;

public class SingletonDouble {
    // 会产生线程安全问题，使用双重校验锁来保证安全
    // 使用volatile禁止指令重排序
    private static volatile SingletonDouble instance = null;

    private SingletonDouble() {

    }

    public static SingletonDouble getInstance() {
        if (instance == null) {
            // 先判断是否存在，不存在再加锁
            synchronized (SingletonDouble.class) {
                if (instance == null) {
                    // 多线程情况下，只能有一个锁进入
                    // 避免创建多个对象
                    return instance = new SingletonDouble();
                }
            }

        }
        return instance;
    }
}
