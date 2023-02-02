package uic.bffweb.assignament;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "bff.assignament", url = "http://localhost:8000/api/assignament")
public interface Assignament {

    @GetMapping("/{id}/")
    AssignamentDTO findById(@PathVariable("id") Long id);

    @GetMapping("/")
    List<AssignamentDTO> findAll();

    @PostMapping("/") 
    AssignamentDTO save(AssignamentDTO entity);

    @DeleteMapping("/{id}/")
    void deleteById(@PathVariable("id")  Long id);

    @PutMapping("/{id}/")
    AssignamentDTO update(@PathVariable("id")  Long id, AssignamentDTO entity);

    @PutMapping("/{id}/completardatos/")
    AssignamentDTO completardatos(@PathVariable("id")  Long id);

}
