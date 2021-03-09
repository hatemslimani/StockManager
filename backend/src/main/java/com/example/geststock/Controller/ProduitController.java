package com.example.geststock.Controller;
import com.example.geststock.Model.DetailCommandeAchatModel;
import com.example.geststock.Model.ProduitModel;
import com.example.geststock.Repository.DetailCommandeAchatRepository;
import com.example.geststock.Service.CategoryService;
import com.example.geststock.Service.ProduitService;
import com.example.geststock.entity.ProduitEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/produit")
public class ProduitController {
    @Autowired
    private ProduitService produitService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    ServletContext context;
    @Autowired
    private DetailCommandeAchatRepository detailCommande;
    @GetMapping("/")
    public List<ProduitModel> getAll() throws IOException  {
    	List<ProduitModel> produitModels=produitService.getAll();
    	for (int i = 0; i < produitModels.size(); i++) {
    		String extension = FilenameUtils.getExtension(produitModels.get(i).getImage());
            File file = new File(context.getRealPath("/Images/")+produitModels.get(i).getImage());
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStream.read(bytes);
            String encodeBase64 = Base64.getEncoder().encodeToString(bytes);
            fileInputStream.close();
			produitModels.get(i).setImage("data:image/"+extension+";base64,"+encodeBase64);
		}
        return produitModels;
    }

    @PostMapping("/add")
    public void add(@RequestParam("img") MultipartFile file,@RequestParam("produit") String p) throws IOException 
    {
    	boolean isExit = new File(context.getRealPath("/Images/")).exists();
        if (!isExit) {
            new File(context.getRealPath("/Images/")).mkdir();
        }
        String filename = file.getOriginalFilename();
        long millis = System.currentTimeMillis();
        String datetime = new Date().toGMTString();
        datetime = datetime.replace(" ", "");
        datetime = datetime.replace(":", "");
        Random random = new Random();

        String rndchars = random.ints(97, 123).limit(16).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        	      .toString();
        String newFileName = "img_" + rndchars + "_" + datetime + "_" + millis + "." + FilenameUtils.getExtension(filename);
        File serverFile = new File(context.getRealPath("/Images/" + File.separator + newFileName));
        try {
            org.apache.commons.io.FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    	ProduitEntity produitEntity=new ObjectMapper().readValue(p, ProduitEntity.class);
        ProduitModel produitModel=new ProduitModel();
        produitModel.setNom(produitEntity.getNom());
        produitModel.setStatut(produitEntity.getStatut());
        produitModel.setOrigine(produitEntity.getOrigine());
        produitModel.setUnite_mesure(produitEntity.getUnite_mesure());
        produitModel.setPrix_achat(produitEntity.getPrix_achat());
        produitModel.setPrix_unitaire(produitEntity.getPrix_unitaire());
        produitModel.setCode_barres(produitEntity.getCode_barres());
        produitModel.setCode(produitEntity.getCode());
        produitModel.setMarque(produitEntity.getMarque());
        produitModel.setDimensions(produitEntity.getDimensions());
        produitModel.setDate_expiration(produitEntity.getDate_expiration());
        produitModel.setCategoryModel(categoryService.getById(produitEntity.getId_categorie()));
        produitModel.setDescription(produitEntity.getDescription());
        produitModel.setImage(newFileName);
        produitModel.setPrix_achat(produitEntity.getPrix_achat());
        produitService.add(produitModel);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable int id)
    {
        ProduitModel produitModel=this.produitService.getById(id);
        if(produitModel==null){
            System.out.printf("aucun produit");
        }
        this.produitService.delete(id);
    }
}
