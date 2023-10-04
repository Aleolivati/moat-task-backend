package com.moat.task.repository;

import com.moat.task.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    List<Album> findAlbumByArtistOrderByReleasedYear(String artist);
}
