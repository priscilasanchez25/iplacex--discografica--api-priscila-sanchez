package org.iplacex.proyectos.discografia.discos;

import java.util.List;
import java.util.Optional;

 import org.iplacex.proyectos.discografia.artistas.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
 import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

public class DiscoController {

    @Autowired
    private IDiscoRepository discorepo;
    private IArtistaRepository artistarepo;

    @PostMapping(value = "/disco", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Disco> HandlePostDiscoRequest(@RequestBody Disco disco) {
        if (!artistarepo.existsById(disco.idArtista))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Disco temp = discorepo.insert(disco);
        return ResponseEntity.status(HttpStatus.CREATED).body(temp);
    }

    @GetMapping(value = "/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> HandleGetDiscosRequest() {
        List<Disco> lista = discorepo.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping(value = "/disco/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Disco> HandleGetDiscoRequest(@PathVariable("id") String id) {
        Optional<Disco> disco = discorepo.findById(id);
        if (!disco.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return ResponseEntity.ok(disco.get());
    }

    @PutMapping(value = "/artista/{id}/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> HandleGetDiscosByArtistaRequest(@PathVariable("id") String id) {

        if (!artistarepo.existsById(id))
            return ResponseEntity.notFound().build();

        List<Disco> discos = discorepo.findDiscosByIdArtista(id);
        return ResponseEntity.ok(discos);
    }

}
