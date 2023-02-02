package uic.bffweb.modality;

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
@RequestMapping("api/modalities")
@CrossOrigin({"*"})
public class ModalityController {

    @Autowired Modality modality;

    @GetMapping("/")
    public List<ModalityDTO> findAll() {
        return modality.findAll();
    }

    @GetMapping("/{id}/")
    public ModalityDTO findById(@PathVariable Long id){
        return modality.findById(id);
    }

    @PostMapping("/")
    public ModalityDTO save(@RequestBody ModalityDTO entity){
        return modality.save(entity);
    }

    @DeleteMapping("/{id}/")
    public void deleteById(@PathVariable Long id){
        modality.deleteById(id);
    }

    @PutMapping("/{id}/")
    public ModalityDTO update(@PathVariable Long id, @RequestBody ModalityDTO entity){
        return modality.update(id, entity);
    }
    @PatchMapping("/{id}/")
    public ModalityDTO partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fields){

        ModalityDTO ModalityDTO = modality.findById(id);

        // itera sobre los campos que se desean actualizar
        for (Map.Entry<String, Object> field : fields.entrySet()) {
            String fieldName = field.getKey();
            Object fieldValue = field.getValue();
            
            // utiliza reflection para establecer el valor del campo en la entidad
            try {
                Field campoEntidad = ModalityDTO.class.getDeclaredField(fieldName);
                campoEntidad.setAccessible(true);
                campoEntidad.set(ModalityDTO, fieldValue);
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                // maneja la excepción si ocurre algún error al acceder al campo
            }
        }
        return modality.update(id, ModalityDTO);
    }
}
