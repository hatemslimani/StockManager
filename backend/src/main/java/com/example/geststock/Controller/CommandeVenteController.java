package com.example.geststock.Controller;

import com.example.geststock.Model.CommandeVenteModel;
import com.example.geststock.Model.LigneCommandeVenteModel;
import com.example.geststock.Repository.LigneCommandeVenteRepository;
import com.example.geststock.Service.CommandeVenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ventes")
public class CommandeVenteController {
    @Autowired
    private CommandeVenteService commandeVenteService;
    @Autowired
    LigneCommandeVenteRepository ligneCommandeVenteRepository;

    @GetMapping("/")
    public ResponseEntity<List<CommandeVenteModel>> getAll() {
        return ResponseEntity.ok(commandeVenteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeVenteModel> getById(@PathVariable int id) {
        return ResponseEntity.ok(commandeVenteService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<CommandeVenteModel> add(@RequestBody CommandeVenteModel commande) {
        return ResponseEntity.ok(commandeVenteService.add(commande));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CommandeVenteModel> update(@PathVariable int id, @RequestBody CommandeVenteModel commande) {
        return ResponseEntity.ok(commandeVenteService.update(id, commande));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        commandeVenteService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/valider")
    public ResponseEntity<Void> validerCommande(@PathVariable int id) {
        commandeVenteService.validerCommande(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/livrer")
    public ResponseEntity<Void> livrerCommande(@PathVariable int id) {
        commandeVenteService.livrerCommande(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/annuler")
    public ResponseEntity<Void> annulerCommande(@PathVariable int id) {
        commandeVenteService.annulerCommande(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CommandeVenteModel>> findByClient(@PathVariable int clientId) {
        return ResponseEntity.ok(commandeVenteService.findByClient(clientId));
    }

    @GetMapping("/{commandeId}/lignes")
    public ResponseEntity<List<LigneCommandeVenteModel>> getLigneCommandeByIdCommande(@PathVariable int commandeId) {

        return ResponseEntity.ok(ligneCommandeVenteRepository.findByCommandeVente_Id(commandeId));
    }

} 