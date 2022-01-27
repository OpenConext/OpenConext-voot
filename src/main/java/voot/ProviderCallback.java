package voot;

import voot.provider.Provider;

public interface ProviderCallback<T> {

    T execute(Provider provider);

}
