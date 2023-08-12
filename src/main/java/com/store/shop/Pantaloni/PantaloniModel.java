package com.store.shop.Pantaloni;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "pantaloni")
public class PantaloniModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String titolo;

    @NotEmpty
    private String descrizione;

    @NotNull
    @Min(5)
    private Integer prezzo;

    // GET E SET

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Integer getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Integer prezzo) {
        this.prezzo = prezzo;
    }
}
