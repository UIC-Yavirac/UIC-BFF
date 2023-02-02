package uic.bffweb.inscription;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "bff.inscription", url = "http://localhost:9010/api/inscription")
public interface Inscription {

    @GetMapping("/{id}/")
    InscriptionDTO findById(@PathVariable("id") Long id);

    @GetMapping("/")
    List<InscriptionDTO> findAll();

    @PostMapping("/") 
    InscriptionDTO save(InscriptionDTO entity);

    @DeleteMapping("/{id}/")
    void deleteById(@PathVariable("id")  Long id);

    @PutMapping("/{id}/")
    InscriptionDTO update(@PathVariable("id")  Long id, InscriptionDTO entity);

    @PutMapping("/{id}/completardatos/")
    InscriptionDTO completardatos(@PathVariable("id")  Long id);

}