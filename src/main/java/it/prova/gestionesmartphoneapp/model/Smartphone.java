package it.prova.gestionesmartphoneapp.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table ()
public class Smartphone {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modello")
    private String modello;
    @Column(name = "prezzo")
    private float prezzo;
    @Column(name = "versioneOS")
    private String versioneOS;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY )
    @JoinTable (name = "smartphone_app", joinColumns = @JoinColumn(name = "id_smartphone", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_app", referencedColumnName = "id"))
    private Set<App> apps = new HashSet<>();

    public Smartphone(String marca, String modello, float prezzo, String versioneOS) {
        this.marca = marca;
        this.modello = modello;
        this.prezzo = prezzo;
        this.versioneOS = versioneOS;
    }

    public Smartphone() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public String getVersioneOS() {
        return versioneOS;
    }

    public void setVersioneOS(String versioneOS) {
        this.versioneOS = versioneOS;
    }

    public Set<App> getApps() {
        return apps;
    }

    public void setApps(Set<App> apps) {
        this.apps = apps;
    }

    @Override
    public String toString() {
        return "Smartphone{" +
                "versioneOS='" + versioneOS + '\'' +
                ", prezzo=" + prezzo +
                ", modello='" + modello + '\'' +
                ", marca='" + marca + '\'' +
                ", id=" + id +
                '}';
    }
}
