package org.iplacex.proyectos.discografia.artistas;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
 import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
@RequestMapping("/api")
@CrossOrigin

public class ArtistaController {

    @Autowired
    private IArtistaRepository artistarepo;

    @PostMapping(value = "/artista", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artista> HandleInsertArtistaRequest(@RequestBody Artista artista) {
        Artista temp = artistarepo.insert(artista);
        return ResponseEntity.status(HttpStatus.CREATED).body(temp);
    }

    @GetMapping(value = "/artistas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Artista>> HandleGetAristasRequest() {
        List<Artista> lista = artistarepo.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping(value = "/artista/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artista> HandleGetArtistaRequest(@PathVariable("id") String id) {
        Optional<Artista> artista = artistarepo.findById(id);
        if (!artista.isPresent())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(artista.get());
    }

    @PutMapping(value = "/artista/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artista> HandleUpdateArtistaRequest(@PathVariable("id") String id,
            @RequestBody Artista artista) {

        if (!artistarepo.existsById(id))
            return ResponseEntity.notFound().build();

        artista._id = id;
        Artista temp = artistarepo.save(artista);
        return ResponseEntity.ok(temp);
    }

    @DeleteMapping(value = "/artista/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artista> HandleDeleteArtistaRequest(@PathVariable("id") String id) {

        if (!artistarepo.existsById(id))
            return ResponseEntity.notFound().build();

        Artista temp = artistarepo.findById(id).get();
        artistarepo.deleteById(id);
        return ResponseEntity.ok(temp);
    }

}
