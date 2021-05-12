package com.revature.p0.util.datastructs.queue;

import com.revature.p0.util.collection.Collection;

public interface Queue<T> extends Collection<T> {
    T poll();
}
