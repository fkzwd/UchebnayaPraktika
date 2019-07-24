package vk.com.dwzkf.logic;

public abstract class Finder<T> extends Thread {
    private OnResultListener<T> listener;

    abstract public T operation();

    public void setResultListener(OnResultListener<T> listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        T result = operation();
        if (listener!=null) {
            listener.onResult(result);
        }
    }
}
