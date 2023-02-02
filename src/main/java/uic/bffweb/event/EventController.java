package uic.bffweb.event;

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
@RequestMapping("api/event")
@CrossOrigin({"*"})
public class EventController {

    @Autowired Event event;

    @GetMapping("/")
    public List<EventDTO> findAll() {
        return event.findAll();
    }

    @GetMapping("/{id}/")
    public EventDTO findById(@PathVariable Long id){
        return event.findById(id);
    }

    @PostMapping("/")
    public EventDTO save(@RequestBody EventDTO entity){
        return event.save(entity);
    }

    @DeleteMapping("/{id}/")
    public void deleteById(@PathVariable Long id){
        event.deleteById(id);
    }

    @PutMapping("/{id}/")
    public EventDTO update(@PathVariable Long id, @RequestBody EventDTO entity){
        return event.update(id, entity);
    }
    @PatchMapping("/{id}/")
    public EventDTO partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fields){

        EventDTO EventDTO = event.findById(id);

        // itera sobre los campos que se desean actualizar
        for (Map.Entry<String, Object> field : fields.entrySet()) {
            String fieldName = field.getKey();
            Object fieldValue = field.getValue();
            
            // utiliza reflection para establecer el valor del campo en la entidad
            try {
                Field campoEntidad = EventDTO.class.getDeclaredField(fieldName);
                campoEntidad.setAccessible(true);
                campoEntidad.set(EventDTO, fieldValue);
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                // maneja la excepción si ocurre algún error al acceder al campo
            }
        }
        return event.update(id, EventDTO);
    }
}
