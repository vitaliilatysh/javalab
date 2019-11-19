package com.epam.cdp.hw1.repositories.impl;

import com.epam.cdp.hw1.model.User;
import com.epam.cdp.hw1.repositories.UserRepository;
import com.epam.cdp.hw1.storage.Storage;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {

    private Storage storage;

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public User save(User entity) {
        String newEntryId = String.valueOf(entity.getId());
        storage.getUserStorage().put(newEntryId, entity);
        return storage.getUserStorage().get(newEntryId);
    }

    @Override
    public User findById(Long id) {
        return storage.getUserStorage().get(Long.toString(id));
    }

    @Override
    public User update(User entity) {
        String updatedEntityId = String.valueOf(entity.getId());
        return storage.getUserStorage().put(updatedEntityId, entity);
    }

    @Override
    public boolean delete(Long id) {
        User user = storage.getUserStorage().remove(String.valueOf(id));
        return user != null;
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> user = storage.getUserStorage().values().stream()
                .filter(entry -> entry.getEmail().equalsIgnoreCase(email))
                .findAny();

        return user.orElse(null);
    }

    @Override
    public List<User> findByName(String name, int pageSize, int pageNum) {
        List<User> storageUsers = storage.getUserStorage().values().stream()
                .filter(user -> user.getName().contains(name))
                .collect(Collectors.toList());

        List<List<User>> partitions = new LinkedList<>();
        for (int i = 0; i < storageUsers.size(); i += pageSize) {
            partitions.add(storageUsers.subList(i,
                    Math.min(i + pageSize, storageUsers.size())));
        }

        if (partitions.isEmpty() || pageNum >= partitions.size()) {
            return Collections.emptyList();
        }
        return partitions.get(pageNum);
    }
}
