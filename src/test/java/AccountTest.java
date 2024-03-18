import org.example.account.Account;
import org.example.account.Currency;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {

    Account a = new Account("Name");
    // Тесты для валюты
    @Test
    void TestNullCurrency(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            a.setCurrencySum(null, -1);
        }, "Валюта не может быть пустой!");
    }
    @Test
    void TestNegativeAmount(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            a.setCurrencySum(Currency.USD, -100);
            }, "Сумма не может быть < 0!");
    }
    @Test
    void TestPositiveAmount(){
            a.setCurrencySum(Currency.RUB, 100);
            assertEquals(a.getCurMoney(Currency.RUB),100);
    }
    @Test
    void TestZeroAmount(){
        a.setCurrencySum(Currency.RUB, 0);
        assertEquals(a.getCurMoney(Currency.RUB),0);
    }

    // Тесты для наименования
    @Test
    void TestEmptyName(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Account a = new Account("");
        },"Имя не может быть пустым!");
    }
    @Test
    void TestNullName(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Account a = new Account(null);
        },"Имя не может быть пустым!");
    }
    @Test
    void TestNotNullName(){
        Account a = new Account("myName");
        assertEquals(a.getName(),"myName");
    }
    @Test
    void TestSetNullName(){
        Account a = new Account("myName");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            a.setName("");
        }, "Имя не может быть пустым!");
    }
    @Test
    void TestSetNotNullName(){
        a.setName("NewName");
        assertEquals(a.getName(),"NewName");
    }

    // Тесты для отмены
    @Test
    void TestUndoName(){
        assertEquals(a.setName("NewName").undo().getName(),"Name");
    }
    @Test
    void TestCanUndoName(){
        assertEquals(a.setName("NewName").canUndo(),true);
    }
    @Test
    void TestCanNotUndoName(){
        assertEquals(a.canUndo(),false);
    }
    @Test
    void TestDontUndoName(){
        UnsupportedOperationException thrown = assertThrows(UnsupportedOperationException.class, () -> a.undo());
    }
    @Test
    void TestUndoBalance(){
        assertEquals(a.setCurrencySum(Currency.USD,100)
                .setCurrencySum(Currency.USD,200)
                .undo()
                .getCurMoney(Currency.USD),
                100);
    }

    @Test
    void TestUndo2Balance(){
        var balanceTest = new HashMap<Currency,Integer>();
        balanceTest.put(Currency.USD,100);
        balanceTest.put(Currency.CNY,400);
        assertEquals(a.setCurrencySum(Currency.USD,100)
                .setCurrencySum(Currency.CNY,400)
                .setCurrencySum(Currency.USD,200)
                .undo().getBalance(),
                balanceTest);
    }

    @Test
    void TestRestoreAccount(){
        a.setCurrencySum(Currency.RUB,100);
        var accSave1 = a.save();
        a.setName("NewName").setCurrencySum(Currency.RUB,1000);
        assertEquals(a.restoreAccount(accSave1),new Account("Name").setCurrencySum(Currency.RUB,100));
    }
    @Test
    void TestSaveImmutable(){
        a.setCurrencySum(Currency.RUB,100);
        var accSave1 = a.save();
        a.setName("NewName");
        assertEquals(a.restoreAccount(accSave1).getName(),"Name");
    }
}
