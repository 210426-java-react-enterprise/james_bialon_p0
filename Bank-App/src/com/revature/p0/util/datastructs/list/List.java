package com.revature.p0.util.datastructs.list;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 4/30/2021
 * Time: 4:05 PM
 * Description: {Insert Description}
 */
public interface List<T> {

    void add(T data);
    T pop();
    boolean contains(T data);
    int size();

}
