package Singleton;

public class SingletonEager {
    private static SingletonEager instance = new SingletonEager();

    private SingletonEager() {

    }

    // 调用方法获取实例，只有读，所以没有线程安全问题
    public static SingletonEager getInstance() {
        return instance;
    }
}
