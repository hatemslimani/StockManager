package com.example.geststock.Controller;

import com.example.geststock.Model.CommandeAchatModel;
import com.example.geststock.Model.DetailCommandeAchatModel;
import com.example.geststock.Model.FournisseurModel;
import com.example.geststock.Model.ProduitModel;
import com.example.geststock.Service.CommandeAchatService;
import com.example.geststock.Service.FournisseurService;
import com.example.geststock.entity.CommandeAchatEntity;
import com.example.geststock.dto.LigneCommandeAchatDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/commandes-achat")
public class CommandeAchatController {
	@Autowired
	private CommandeAchatService commandeAchatService;

	@Autowired
	private FournisseurService fournisseurService;

	@GetMapping("/")
	public List<CommandeAchatEntity> getAll() {
		return commandeAchatService.getAll().stream()
				.map(this::convertToEntity)
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CommandeAchatEntity> getById(@PathVariable int id) {
		CommandeAchatModel commande = commandeAchatService.getById(id);
		return ResponseEntity.ok(convertToEntity(commande));
	}

	@PostMapping("/add")
	public ResponseEntity<CommandeAchatEntity> add(@RequestBody CommandeAchatEntity commandeEntity) {
		CommandeAchatModel commande = convertToModel(commandeEntity);
		CommandeAchatModel savedCommande = commandeAchatService.add(commande);
		return ResponseEntity.ok(convertToEntity(savedCommande));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CommandeAchatEntity> update(@PathVariable int id, @RequestBody CommandeAchatEntity commandeEntity) {
		CommandeAchatModel commande = convertToModel(commandeEntity);
		CommandeAchatModel updatedCommande = commandeAchatService.update(id, commande);
		return ResponseEntity.ok(convertToEntity(updatedCommande));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		commandeAchatService.delete(id);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/valider/{id}")
	public ResponseEntity<Void> validerCommande(@PathVariable int id) {
		commandeAchatService.validerCommande(id);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/refuser/{id}")
	public ResponseEntity<Void> refuserCommande(@PathVariable int id) {
		commandeAchatService.refuserCommande(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> downloadCommandeAchat(@PathVariable int id) throws FileNotFoundException, JRException {
		CommandeAchatModel commande = commandeAchatService.getById(id);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("code", commande.getCode());
		parameters.put("date_ajout", commande.getDate_ajout());
		parameters.put("fournisseur", commande.getFournisseurModel().getNom());
		parameters.put("total", commande.getTotal_commande());

		List<LigneCommandeAchatDTO> lignesDTO = commande.getDetailList().stream()
			.map(detail -> new LigneCommandeAchatDTO(
				detail.getProduitModel().getNom(),
				detail.getQuantite_achete(),
				detail.getPrix_achat(),
				detail.getPrix_achat() * detail.getQuantite_achete()
			))
			.collect(Collectors.toList());

		String path = "src/main/resources/Blank_A5.jrxml";
		JasperReport jasperReport = JasperCompileManager.compileReport(path);

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lignesDTO);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("filename", "commande_" + commande.getCode() + ".pdf");
		
		return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	}

	private CommandeAchatEntity convertToEntity(CommandeAchatModel model) {
		CommandeAchatEntity entity = new CommandeAchatEntity();
		entity.setId(model.getId());
		entity.setCode(model.getCode());
		entity.setTotal_commande(model.getTotal_commande());
		entity.setStatut(model.getStatut());
		entity.setId_fournisseur(model.getFournisseurModel().getId());
		entity.setNomFournisseur(model.getFournisseurModel().getNom());
		entity.setDate_ajout(model.getDate_ajout());
		entity.setDate_modification(model.getDate_modification());
		
		List<ProduitModel> produits = model.getDetailList().stream()
				.map(DetailCommandeAchatModel::getProduitModel)
				.collect(Collectors.toList());
		entity.setProduits(produits);
		
		return entity;
	}

	private CommandeAchatModel convertToModel(CommandeAchatEntity entity) {
		CommandeAchatModel model = new CommandeAchatModel();
		model.setId(entity.getId());
		model.setCode(entity.getCode());
		model.setTotal_commande(entity.getTotal_commande());
		model.setStatut(entity.getStatut());
		
		FournisseurModel fournisseur = fournisseurService.getById(entity.getId_fournisseur());
		model.setFournisseurModel(fournisseur);
		
		List<DetailCommandeAchatModel> details = new ArrayList<>();
		if (entity.getProduits() != null) {
			for (ProduitModel produit : entity.getProduits()) {
				DetailCommandeAchatModel detail = new DetailCommandeAchatModel();
				detail.setProduitModel(produit);
				detail.setCommandeAchat(model);
				detail.setPrix_achat(produit.getPrix_achat());
				detail.setQuantite_achete(produit.getQuantite_stock());
				details.add(detail);
			}
		}
		model.setDetailList(details);
		
		return model;
	}
}
