package StreamApiTest;

import java.util.stream.Stream;

/**
 * Monad 案例
 *
 * @author zhaoqw
 * @date 2023/01/05
 */
public class Event<T> {
    T data;
    public Event(T data) {
        this.data =  data;
    }

    static class EventData {
        Integer id;
        String msg;

        public EventData(Integer id, String msg) {
            this.id = id;
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "EventData{" +
                    "id=" + id +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }

    static class Transforms {
        static EventData transform(Integer id) {
            switch (id) {
                case 0:
                    return new EventData(id, "Start");
                case 1:
                    return new EventData(id, "Running");
                case 2:
                    return new EventData(id, "Done");
                case 3:
                    return new EventData(id, "Fail");
                default:
                    return new EventData(id, "Default");
            }
        }
    }

    @FunctionalInterface
    interface FN<A, B> {
        B apply(A a);
    }

    <B> Event<?> map(FN<T, B> f) {
        return new Event<>(f.apply(this.data));
    }

    public static void main(String[] args) {
        Stream<Event<Integer>> s = Stream.of(
                new Event(1),
                new Event(2),
                new Event(3),
                new Event(17));
        s.map(event -> event.map(Transforms::transform))
                .forEach(e -> {
                    System.out.println(e.data);
                });
    }
}
