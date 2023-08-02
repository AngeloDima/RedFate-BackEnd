package com.store.shop.Pantaloni;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin
@RequestMapping("/pantaloni")
public class PantaloniController {

    @Autowired
    private PantaloniRepository pantaloniRepository;

    @GetMapping
    public List<PantaloniModel> index() {
        return pantaloniRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PantaloniModel> getFelpeById(@PathVariable("id") Integer id) {
        Optional<PantaloniModel> optionalFelpe = pantaloniRepository.findById(id);

        if (optionalFelpe.isPresent()) {
            PantaloniModel felpe = optionalFelpe.get();
            return ResponseEntity.ok(felpe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public PantaloniModel createFelpe(@RequestBody PantaloniModel felpe) {
        return pantaloniRepository.save(felpe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PantaloniModel> updateFelpe(@PathVariable("id") Integer id,
            @RequestBody PantaloniModel updatedFelpe) {
        Optional<PantaloniModel> optionalFelpe = pantaloniRepository.findById(id);

        if (optionalFelpe.isPresent()) {
            PantaloniModel existingFelpe = optionalFelpe.get();
            existingFelpe.setTitolo(updatedFelpe.getTitolo());
            existingFelpe.setDescrizione(updatedFelpe.getDescrizione());
            existingFelpe.setPrezzo(updatedFelpe.getPrezzo());

            PantaloniModel updated = pantaloniRepository.save(existingFelpe);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFelpe(@PathVariable("id") Integer id) {
        Optional<PantaloniModel> optionalFelpe = pantaloniRepository.findById(id);

        if (optionalFelpe.isPresent()) {
            pantaloniRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
