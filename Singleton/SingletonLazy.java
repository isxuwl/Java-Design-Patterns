package Singleton;

public class SingletonLazy {
    // 类加载过程不创建实例，实例设置为null
    private static SingletonLazy instance = null;

    private SingletonLazy() {

    }

    public static SingletonLazy getInstance() {
        // 第一次调用该类才创建实例
        if (instance == null) {
            return instance = new SingletonLazy();
        }
        // 其后每次调用实例都是用上面创建的实例
        return instance;
    }
}
