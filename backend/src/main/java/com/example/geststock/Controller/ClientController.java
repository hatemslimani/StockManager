package com.example.geststock.Controller;

import com.example.geststock.Model.ClientModel;
import com.example.geststock.Service.ClientService;
import com.example.geststock.entity.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/customer")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/")
    public List<ClientEntity> getAll() {
        return clientService.getAll().stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getById(@PathVariable int id) {
        ClientModel client = clientService.getById(id);
        return ResponseEntity.ok(convertToEntity(client));
    }

    @PostMapping("/add")
    public ResponseEntity<ClientEntity> add(@RequestBody ClientEntity clientEntity) {
        ClientModel client = convertToModel(clientEntity);
        ClientModel savedClient = clientService.add(client);
        return ResponseEntity.ok(convertToEntity(savedClient));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClientEntity> update(@PathVariable int id, @RequestBody ClientEntity clientEntity) {
        ClientModel client = convertToModel(clientEntity);
        ClientModel updatedClient = clientService.update(id, client);
        return ResponseEntity.ok(convertToEntity(updatedClient));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }

    private ClientEntity convertToEntity(ClientModel model) {
        ClientEntity entity = new ClientEntity();
        entity.setId(model.getId());
        entity.setNom(model.getNom());
        entity.setPrenom(model.getPrenom());
        entity.setEmail(model.getEmail());
        entity.setTelephone(model.getTelephone());
        entity.setAdresse(model.getAdresse());
        entity.setVille(model.getVille());
        entity.setCode_postal(model.getCode_postal());
        entity.setPays(model.getPays());
        entity.setDate_ajout(model.getDate_ajout());
        entity.setDate_modification(model.getDate_modification());
        return entity;
    }

    private ClientModel convertToModel(ClientEntity entity) {
        ClientModel model = new ClientModel();
        model.setId(entity.getId());
        model.setNom(entity.getNom());
        model.setPrenom(entity.getPrenom());
        model.setEmail(entity.getEmail());
        model.setTelephone(entity.getTelephone());
        model.setAdresse(entity.getAdresse());
        model.setVille(entity.getVille());
        model.setCode_postal(entity.getCode_postal());
        model.setPays(entity.getPays());
        return model;
    }
} 