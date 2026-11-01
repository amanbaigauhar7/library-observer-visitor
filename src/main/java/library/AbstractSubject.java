
package library;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentMap;

public abstract class AbstractSubject<E extends Event>
        implements Subject<E> {

    private final ConcurrentMap<EventType,
            CopyOnWriteArrayList<Observer<E>>> observers =
            new ConcurrentHashMap<>();

    @Override
    public void subscribe(EventType type, Observer<E> observer) {
        if (type == null || observer == null) {
            throw new IllegalArgumentException(
                    "Type and observer cannot be null"
            );
        }

        observers.computeIfAbsent(
                type,
                key -> new CopyOnWriteArrayList<>()
        ).addIfAbsent(observer);
    }

    @Override
    public void unsubscribe(EventType type, Observer<E> observer) {
        if (type == null || observer == null) {
            throw new IllegalArgumentException(
                    "Type and observer cannot be null"
            );
        }

        CopyOnWriteArrayList<Observer<E>> list =
                observers.get(type);

        if (list != null) {
            list.remove(observer);
        }
    }

    @Override
    public void publish(E event) {
        if (event == null) {
            throw new IllegalArgumentException(
                    "Event cannot be null"
            );
        }

        CopyOnWriteArrayList<Observer<E>> list =
                observers.get(event.type());

        if (list != null) {
            for (Observer<E> observer : list) {
                observer.onEvent(event);
            }
        }
    }
}
