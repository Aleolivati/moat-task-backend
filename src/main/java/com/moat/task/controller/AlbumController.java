package com.moat.task.controller;

import com.moat.task.model.Album;
import com.moat.task.service.AlbumService;
import com.moat.task.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(
    value = AlbumController.PATH,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class AlbumController {
    private final Logger logger = LoggerFactory.getLogger(AlbumController.class);

    public static final String PATH = "/api/v1/albums";
    private final AlbumService albumService;
    private final SessionService sessionService;

    public AlbumController(AlbumService albumService, SessionService sessionService) {
        this.albumService = albumService;
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<Album> create(@RequestBody Album request, @RequestHeader(name = "Authorization") String token) {
        sessionService.tokenValidation(token);

        Album album = albumService.create(request);
        logger.info("Album created with success");
        return ResponseEntity.created(URI.create(PATH + "/" + album.getId())).body(album);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Album>> findAll(@RequestHeader(name = "Authorization") String token) {
        sessionService.tokenValidation(token);

        List<Album> albums = albumService.findAll();
        logger.info("Find ALL albums with success");
        return ResponseEntity.ok(albums);
    }

    @GetMapping
    public ResponseEntity<List<Album>> findByArtist(@RequestParam(name = "artist") String artist, @RequestHeader(name = "Authorization") String token) {
        sessionService.tokenValidation(token);

        List<Album> albumsByArtist = albumService.findByArtist(artist);
        logger.info("Find all albums by selected artist with success");
        return ResponseEntity.ok(albumsByArtist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id, @RequestHeader(name = "Authorization") String token) {
        sessionService.tokenValidation(token);

        albumService.delete(id);
        logger.info("Album deleted with success");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> update(@RequestBody Album update, @PathVariable(name = "id") Integer id, @RequestHeader(name = "Authorization") String token) {
        sessionService.tokenValidation(token);

        Album album = albumService.update(update, id);
        logger.info("Album updated with success");
        return ResponseEntity.ok(album);
    }
}
