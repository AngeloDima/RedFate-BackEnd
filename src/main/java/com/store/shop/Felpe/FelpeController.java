package com.store.shop.Felpe;

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
@RequestMapping("/felpe")
public class FelpeController {

    @Autowired
    private FelpeRepository felpeRepository;

    @GetMapping
    public List<FelpeModel> index() {
        return felpeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FelpeModel> getFelpeById(@PathVariable("id") Integer id) {
        Optional<FelpeModel> optionalFelpe = felpeRepository.findById(id);

        if (optionalFelpe.isPresent()) {
            FelpeModel felpe = optionalFelpe.get();
            return ResponseEntity.ok(felpe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public FelpeModel createFelpe(@RequestBody FelpeModel felpe) {
        return felpeRepository.save(felpe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FelpeModel> updateFelpe(@PathVariable("id") Integer id,
            @RequestBody FelpeModel updatedFelpe) {
        Optional<FelpeModel> optionalFelpe = felpeRepository.findById(id);

        if (optionalFelpe.isPresent()) {
            FelpeModel existingFelpe = optionalFelpe.get();
            existingFelpe.setTitolo(updatedFelpe.getTitolo());
            existingFelpe.setDescrizione(updatedFelpe.getDescrizione());
            existingFelpe.setPrezzo(updatedFelpe.getPrezzo());

            FelpeModel updated = felpeRepository.save(existingFelpe);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFelpe(@PathVariable("id") Integer id) {
        Optional<FelpeModel> optionalFelpe = felpeRepository.findById(id);

        if (optionalFelpe.isPresent()) {
            felpeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
