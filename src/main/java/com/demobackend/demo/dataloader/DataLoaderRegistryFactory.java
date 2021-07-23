package com.demobackend.demo.dataloader;

import com.demobackend.demo.models.User;
import com.demobackend.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class DataLoaderRegistryFactory {

    private final UserRepository userRepository;

    public static final String USER_DATA_LOADER = "USER_DATA_LOADER";
    public static final Executor userThreadPool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    public DataLoaderRegistry create() {
        // We could use the same registry for batching several unrelated queries,
        // but due to security we create a registry per batch
        var registry = new DataLoaderRegistry();

        registry.register(USER_DATA_LOADER, createCreatorDataLoader());

        return registry;
    }

    private DataLoader<String, User> createCreatorDataLoader() {
        return DataLoader.newMappedDataLoader((Set<String> userIds) -> CompletableFuture
                .supplyAsync(() -> userRepository.getCreatorFor(userIds), userThreadPool));
    }
}
