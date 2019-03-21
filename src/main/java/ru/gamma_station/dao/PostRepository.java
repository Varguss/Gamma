package ru.gamma_station.dao;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.gamma_station.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @NotNull
    @Override
    Page<Post> findAll(@NotNull Pageable pageable);

    @NotNull
    @Override
    List<Post> findAll();

    @NotNull
    @Override
    Optional<Post> findById(@NotNull Long id);

    @NotNull
    @Override
    <S extends Post> S save(@NotNull S post);

    @Override
    void delete(@NotNull Post post);
}
