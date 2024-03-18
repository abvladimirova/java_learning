import org.example.multithread.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestClock implements Clock {
    long time;
    @Override
    public long currentMillis() {
        return time;
    }
    public TestClock() {
        this.time= System.currentTimeMillis();
    }
    public void sleep(long waitTime) {
        time += waitTime;
    }
}

public class MultithreadTest {


    public static class TestClass implements GetAnswer {
        private int cnt = 0;

        public int getCnt() {
            return cnt;
        }

        @Override
        public double getCourse(String currency) {
            double result;
            switch (currency) {
                case ("RUB"):
                    result = 1;
                    break;
                case "USD":
                    result = 90;
                    break;
                case "CNY":
                    result = 5;
                    break;
                default:
                    result = 0;
                    break;
            }
            cnt++;
            return result;
        }

        @Test
        void TestEqualResult() {
            var oper = new TestClass();
            GetAnswer cacheOper = new CacheFactory().makeCachable(oper);
            assertEquals(oper.getCourse("RUB"), cacheOper.getCourse("RUB"));
        }

        @Test
        void TestGetFromCache() {
            var oper            = new TestClass();
            var clock           = new TestClock();
            var cacheFactory    = new CacheFactory(clock);
            GetAnswer cacheOper = cacheFactory.makeCachable(oper);

            cacheOper.getCourse("USD");
            clock.sleep(1000);
            cacheOper.getCourse("USD");

            assertEquals(1, oper.getCnt());
        }

        @Test
        void TestRewriteCache() throws InterruptedException {
            var oper    = new TestClass();
            var clock   = new TestClock();
            GetAnswer cacheOper = new CacheFactory(clock).makeCachable(oper);

            cacheOper.getCourse("RUB");
            clock.sleep(1500);
            cacheOper.getCourse("RUB");
            assertEquals(2, oper.getCnt());
        }

        @Test
        void TestUpdateTimeCache() {
            var oper = new TestClass();
            var clock = new TestClock();
            var cacheFactory = new CacheFactory(clock);
            GetAnswer cacheOper = cacheFactory.makeCachable(oper);

            cacheOper.getCourse("USD");
            clock.sleep(1000);
            cacheOper.getCourse("USD");
            clock.sleep(1000);
            cacheOper.getCourse("USD");

            assertEquals(1, oper.getCnt());
        }
    }
}
