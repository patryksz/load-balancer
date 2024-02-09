import org.junit.jupiter.api.Test;
import org.pszlagowski.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoadBalancerTest {

    @Test
    void testAddingNewAddresses() throws UniqueAddressException, MalformedURLException, AddressNumberExceededException, NoAddressesDefinedException {
        var lb = new LoadBalancer();
        var address = new URL("http://foo.bar");

        lb.addAddress(address);
        assertEquals(new URLRecord(address), lb.get(0));
    }

    @Test
    void testAddingNewAddressesThatCauseUniqueException() throws UniqueAddressException, AddressNumberExceededException, MalformedURLException {
        var lb = new LoadBalancer();
        var address = new URL("http://foo.bar");

        lb.addAddress(address);
        assertThrows(UniqueAddressException.class, () -> {
            lb.addAddress(address);
        });
    }

    @Test
    void testAddingNewAddressesThatCauseNumberExceededException() {
        var lb = new LoadBalancer();

        IntStream.range(0, 10).forEach(i -> {
            try {
                lb.addAddress(new URL("http://foo" + i + ".bar"));
            } catch (UniqueAddressException | MalformedURLException | AddressNumberExceededException e) {
                throw new RuntimeException(e);
            }
        });

        assertThrows(AddressNumberExceededException.class, () -> {
            lb.addAddress(new URL("http://foo.bar"));
        });
    }

    @Test
    void testGettingAddressesRandomly() throws MalformedURLException, UniqueAddressException, AddressNumberExceededException, NoAddressesDefinedException {
        var lb = new LoadBalancer((bound) -> 0);
        var address = new URL("http://foo.bar");
        var record = new URLRecord(address);

        lb.addAddress(address);
        IntStream.range(0, 9).forEach(i -> {
            try {
                lb.addAddress(new URL("http://foo" + i + ".bar"));
            } catch (UniqueAddressException | MalformedURLException | AddressNumberExceededException e) {
                throw new RuntimeException(e);
            }
        });

        assertEquals(record, lb.get());
        assertEquals(record, lb.get());
        assertEquals(record, lb.get());
    }

    @Test
    void testNoAddressesDefined() {
        var lb = new LoadBalancer();
        assertThrows(NoAddressesDefinedException.class, () -> {
            lb.get();
        });
    }
}
