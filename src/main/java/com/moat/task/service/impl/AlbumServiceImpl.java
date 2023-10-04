package com.moat.task.service.impl;

import com.moat.task.model.Album;
import com.moat.task.repository.AlbumRepository;
import com.moat.task.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Album create(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public List<Album> findByArtist(String artist) {
        return albumRepository.findAlbumByArtistOrderByReleasedYear(artist);
    }

    @Override
    public void delete(Integer id) {
        albumRepository.deleteById(id);
    }

    @Override
    public Album update(Album updatedAlbum, Integer id) {
        Album album = albumRepository.findById(id).get();
        album.setName(updatedAlbum.getName());
        album.setReleasedYear(updatedAlbum.getReleasedYear());
        return albumRepository.save(album);
    }
}
