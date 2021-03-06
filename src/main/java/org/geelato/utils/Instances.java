package org.geelato.utils;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author geemeta
 *
 */
public class Instances {

    private static Lock lock = new ReentrantLock();
    private static HashMap instances = new HashMap();

    public static <T> T single(Class<T> clazz) {
        lock.lock();
        if(!instances.containsKey(clazz.getName())){
            try {
                instances.put(clazz.getName(),clazz.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        lock.unlock();
        return (T)instances.get(clazz.getName());
    }
}
