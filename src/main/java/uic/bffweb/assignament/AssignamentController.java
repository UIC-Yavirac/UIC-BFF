package uic.bffweb.assignament;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/assignament")
@CrossOrigin({"*"})
public class AssignamentController {

    @Autowired Assignament assignament;

    @GetMapping("/")
    public List<AssignamentDTO> findAll() {
        return assignament.findAll();
    }

    @GetMapping("/{id}/")
    public AssignamentDTO findById(@PathVariable Long id){
        return assignament.findById(id);
    }

    @PostMapping("/")
    public AssignamentDTO save(@RequestBody AssignamentDTO entity){
        return assignament.save(entity);
    }

    @DeleteMapping("/{id}/")
    public void deleteById(@PathVariable Long id){
        assignament.deleteById(id);
    }

    @PutMapping("/{id}/")
    public AssignamentDTO update(@PathVariable Long id, @RequestBody AssignamentDTO entity){
        return assignament.update(id, entity);
    }
    @PatchMapping("/{id}/")
    public AssignamentDTO partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fields){

        AssignamentDTO AssignamentDTO = assignament.findById(id);

        // itera sobre los campos que se desean actualizar
        for (Map.Entry<String, Object> field : fields.entrySet()) {
            String fieldName = field.getKey();
            Object fieldValue = field.getValue();
            
            // utiliza reflection para establecer el valor del campo en la entidad
            try {
                Field campoEntidad = AssignamentDTO.class.getDeclaredField(fieldName);
                campoEntidad.setAccessible(true);
                campoEntidad.set(AssignamentDTO, fieldValue);
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                // maneja la excepción si ocurre algún error al acceder al campo
            }
        }
        return assignament.update(id, AssignamentDTO);
    }
}
