package com.example.geststock.Service;

import com.example.geststock.Model.ClientModel;
import com.example.geststock.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<ClientModel> getAll() {
        return clientRepository.findAll();
    }

    public ClientModel getById(int id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
    }

    public ClientModel add(ClientModel client) {
        if (clientRepository.existsByEmail(client.getEmail())) {
            throw new RuntimeException("Un client avec cet email existe déjà");
        }
        return clientRepository.save(client);
    }

    public ClientModel update(int id, ClientModel client) {
        ClientModel existingClient = getById(id);
        existingClient.setNom(client.getNom());
        existingClient.setPrenom(client.getPrenom());
        existingClient.setEmail(client.getEmail());
        existingClient.setTelephone(client.getTelephone());
        existingClient.setAdresse(client.getAdresse());
        existingClient.setVille(client.getVille());
        existingClient.setCode_postal(client.getCode_postal());
        existingClient.setPays(client.getPays());
        return clientRepository.save(existingClient);
    }

    public void delete(int id) {
        clientRepository.deleteById(id);
    }

    public List<ClientModel> findByNomContaining(String nom) {
        return clientRepository.findByNomContaining(nom);
    }

    public List<ClientModel> findByVille(String ville) {
        return clientRepository.findByVille(ville);
    }

    public ClientModel findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public List<ClientModel> findByPays(String pays) {
        return clientRepository.findByPays(pays);
    }
} 