import org.example.cache.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CachingTest {

    public class SomeOperation implements org.example.cache.ReturningNumber {
        private int a, b;
        private int cnt = 0;
        public int getCnt() {
            return cnt;
        }
        public SomeOperation(int a, int b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public void modify(int a, int b) {
            this.a = a;
            this.b = b;
            this.cnt = 0;
        }

        @Override
        public int getResult() {
            cnt++;
            return a+b;
        }

        @Override
        public int getAnotherResult() {
            return 0;
        }
    }

    @Test
    void TestEqualResult(){
        var oper = new SomeOperation(1,2);
        org.example.cache.ReturningNumber s = CachingHandler.CacheFactory.makeCachable(oper);
        assertEquals(s.getResult(),oper.getResult());
    }
    @Test
    void TestGetFromCache(){
        var oper = new SomeOperation(1,2);
        org.example.cache.ReturningNumber s = CachingHandler.CacheFactory.makeCachable(oper);
        s.getResult();
        s.getResult();
        assertEquals(oper.getCnt(),1);
    }
    @Test
    void TestClearCache(){
        var oper = new SomeOperation(1,2);
        org.example.cache.ReturningNumber s = CachingHandler.CacheFactory.makeCachable(oper);
        s.getResult();
        s.modify(3,3);
        assertEquals(oper.getCnt(),0);
    }
    @Test
    void TestRecalcResultAfterModify(){
        var oper = new SomeOperation(1,2);
        org.example.cache.ReturningNumber s = CachingHandler.CacheFactory.makeCachable(oper);
        s.getResult();
        s.modify(3,3);
        oper.getResult();
        assertEquals(oper.getCnt(),1);
    }
    @Test
    void TestGetNoCache(){
        var oper = new SomeOperation(1,2);
        org.example.cache.ReturningNumber s = CachingHandler.CacheFactory.makeCachable(oper);
        int a = s.getAnotherResult();
        assertEquals(a,oper.getAnotherResult());
    }
}
