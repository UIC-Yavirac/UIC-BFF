package uic.bffweb.modality;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "bff.modality", url = "http://localhost:8085/api/modalities")
public interface Modality {

    @GetMapping("/{id}/")
    ModalityDTO findById(@PathVariable("id") Long id);

    @GetMapping("/")
    List<ModalityDTO> findAll();

    @PostMapping("/") 
    ModalityDTO save(ModalityDTO entity);

    @DeleteMapping("/{id}/")
    void deleteById(@PathVariable("id")  Long id);

    @PutMapping("/{id}/")
    ModalityDTO update(@PathVariable("id")  Long id, ModalityDTO entity);

    @PutMapping("/{id}/completardatos/")
    ModalityDTO completardatos(@PathVariable("id")  Long id);

}
