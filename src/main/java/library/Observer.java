package library;

public interface Observer<E extends Event> {

    void onEvent(E event);
}