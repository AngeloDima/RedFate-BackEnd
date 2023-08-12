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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.store.shop.Felpe.FelpeModel;

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
    public ResponseEntity<PantaloniModel> getPantaloniById(@PathVariable("id") Integer id) {
        Optional<PantaloniModel> optionalPantaloni = pantaloniRepository.findById(id);

        if (optionalPantaloni.isPresent()) {
            PantaloniModel Pantaloni = optionalPantaloni.get();
            return ResponseEntity.ok(Pantaloni);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public PantaloniModel createPantaloni(@RequestBody PantaloniModel Pantaloni) {
        return pantaloniRepository.save(Pantaloni);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PantaloniModel> updatePantaloni(@PathVariable("id") Integer id,
            @RequestBody PantaloniModel updatePantaloni) {
        Optional<PantaloniModel> optionalPantaloni = pantaloniRepository.findById(id);

        if (optionalPantaloni.isPresent()) {
            PantaloniModel existingPantaloni = optionalPantaloni.get();
            existingPantaloni.setTitolo(updatePantaloni.getTitolo());
            existingPantaloni.setDescrizione(updatePantaloni.getDescrizione());
            existingPantaloni.setPrezzo(updatePantaloni.getPrezzo());

            PantaloniModel updated = pantaloniRepository.save(existingPantaloni);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePantaloni(@PathVariable("id") Integer id) {
        Optional<PantaloniModel> optionalPantaloni = pantaloniRepository.findById(id);

        if (optionalPantaloni.isPresent()) {
            pantaloniRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
