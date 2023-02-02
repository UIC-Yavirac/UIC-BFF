package uic.bffweb.career;

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
@RequestMapping("api/career")
@CrossOrigin({"*"})
public class CareerController {

    @Autowired Career career;

    @GetMapping("/")
    public List<CareerDTO> findAll() {
        return career.findAll();
    }

    @GetMapping("/{id}/")
    public CareerDTO findById(@PathVariable Long id){
        return career.findById(id);
    }

    @PostMapping("/")
    public CareerDTO save(@RequestBody CareerDTO entity){
        return career.save(entity);
    }

    @DeleteMapping("/{id}/")
    public void deleteById(@PathVariable Long id){
        career.deleteById(id);
    }

    @PutMapping("/{id}/")
    public CareerDTO update(@PathVariable Long id, @RequestBody CareerDTO entity){
        return career.update(id, entity);
    }
    @PatchMapping("/{id}/")
    public CareerDTO partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fields){

        CareerDTO CareerDTO = career.findById(id);

        // itera sobre los campos que se desean actualizar
        for (Map.Entry<String, Object> field : fields.entrySet()) {
            String fieldName = field.getKey();
            Object fieldValue = field.getValue();
            
            // utiliza reflection para establecer el valor del campo en la entidad
            try {
                Field campoEntidad = CareerDTO.class.getDeclaredField(fieldName);
                campoEntidad.setAccessible(true);
                campoEntidad.set(CareerDTO, fieldValue);
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                // maneja la excepción si ocurre algún error al acceder al campo
            }
        }
        return career.update(id, CareerDTO);
    }
}
