package uic.bffweb.inscription;

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
@RequestMapping("api/inscription")
@CrossOrigin({"*"})
public class InscriptionController {

    @Autowired Inscription inscription;

    @GetMapping("/")
    public List<InscriptionDTO> findAll() {
        return inscription.findAll();
    }

    @GetMapping("/{id}/")
    public InscriptionDTO findById(@PathVariable Long id){
        return inscription.findById(id);
    }

    @PostMapping("/")
    public InscriptionDTO save(@RequestBody InscriptionDTO entity){
        return inscription.save(entity);
    }

    @DeleteMapping("/{id}/")
    public void deleteById(@PathVariable Long id){
        inscription.deleteById(id);
    }

    @PutMapping("/{id}/")
    public InscriptionDTO update(@PathVariable Long id, @RequestBody InscriptionDTO entity){
        return inscription.update(id, entity);
    }
    @PatchMapping("/{id}/")
    public InscriptionDTO partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fields){

        InscriptionDTO InscriptionDTO = inscription.findById(id);

        // itera sobre los campos que se desean actualizar
        for (Map.Entry<String, Object> field : fields.entrySet()) {
            String fieldName = field.getKey();
            Object fieldValue = field.getValue();
            
            // utiliza reflection para establecer el valor del campo en la entidad
            try {
                Field campoEntidad = InscriptionDTO.class.getDeclaredField(fieldName);
                campoEntidad.setAccessible(true);
                campoEntidad.set(InscriptionDTO, fieldValue);
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                // maneja la excepción si ocurre algún error al acceder al campo
            }
        }
        return inscription.update(id, InscriptionDTO);
    }
}
