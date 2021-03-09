package com.example.geststock.Controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.geststock.Model.CategoryModel;
import com.example.geststock.Model.FournisseurModel;
import com.example.geststock.Repository.FournisseurRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/fournisseur")
public class FournisseurController {
	@Autowired
	private FournisseurRepository fournisseurRepository;
	@GetMapping("/")
	private List<FournisseurModel>getAll(){
		return fournisseurRepository.findAll();
	}
	@PostMapping("/add")
    public void add(@RequestBody FournisseurModel fournisseurModel)
    {
        this.fournisseurRepository.save(fournisseurModel);
    }
	@GetMapping("/delete/{id}")
	private void delete(@PathVariable int id){
		 fournisseurRepository.deleteById(id);
	}
	@GetMapping("/getById/{id}")
	private FournisseurModel getById(@PathVariable int id) {
		return fournisseurRepository.findById(id).orElse(null);
	}
	@PostMapping("/edit/{id}")
    public void edit(@PathVariable int id,@RequestBody FournisseurModel fournisseurModel)
    {
		fournisseurModel.setId(id);
        this.fournisseurRepository.save(fournisseurModel);
    }
}
