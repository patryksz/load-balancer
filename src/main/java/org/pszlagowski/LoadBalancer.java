package org.pszlagowski;

import java.net.URL;
import java.util.*;

public class LoadBalancer {

    private RandomProvider randomProvider;

    private volatile List<URLRecord> urlRecords = new ArrayList<>();

    public LoadBalancer() {
        this.randomProvider = (bound) -> new Random().nextInt(bound);
    }

    public LoadBalancer(RandomProvider randomProvider) {
        this.randomProvider = randomProvider;
    }

    public synchronized void addAddress(URL address) throws UniqueAddressException, AddressNumberExceededException {
        var record = new URLRecord(address);
        if (urlRecords.contains(record)) {
            throw new UniqueAddressException("address should be unique");
        }
        if (urlRecords.size() >= 10) {
            throw new AddressNumberExceededException("maximum addresses count is 10");
        }

        urlRecords.add(record);
    }

    public URLRecord get() throws NoAddressesDefinedException {
        if (urlRecords.isEmpty()) {
            throw new NoAddressesDefinedException("no addresses defined");
        }
        return urlRecords.get(randomProvider.get(urlRecords.size()));
    }

    public URLRecord get(int index) throws NoAddressesDefinedException {
        if (urlRecords.isEmpty()) {
            throw new NoAddressesDefinedException("no addresses defined");
        }

        return urlRecords.get(index);
    }
}


