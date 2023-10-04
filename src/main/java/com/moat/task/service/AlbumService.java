package com.moat.task.service;

import com.moat.task.model.Album;

import java.util.List;

public interface AlbumService {
    Album create(Album album);
    List<Album> findAll();
    List<Album> findByArtist(String artist);
    void delete(Integer id);
    Album update(Album album, Integer id);
}
