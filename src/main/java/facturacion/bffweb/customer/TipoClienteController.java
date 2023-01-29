package facturacion.bffweb.customer;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/tipocliente")
@CrossOrigin({"*"})
@Tag(name = "Controlador del producto")


public class TipoClienteController {

    @Autowired TipoClienteClient client;
    @Operation(summary = "Obtiene todos los productos registrados en este microservicio")


    @GetMapping("/")
    public List<TipoClienteDTO> findAll() {
        return client.findAll();
    }

    @GetMapping("/{id}/")
    public TipoClienteDTO findById(@PathVariable Long id){
        return client.findById(id);
    }

    @PostMapping("/")
    public TipoClienteDTO save(@RequestBody TipoClienteDTO entity){
        return client.save(entity);
    }

    @DeleteMapping("/{id}/")
    public void deleteById(@PathVariable Long id){
        client.deleteById(id);
    }

    @PutMapping("/{id}/")
    public TipoClienteDTO update(@PathVariable Long id, @RequestBody TipoClienteDTO entity){
        return client.update(id, entity);
    }
    @PatchMapping("/{id}/")
    public TipoClienteDTO partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fields){

        TipoClienteDTO TipoClienteDTO = client.findById(id);

        // itera sobre los campos que se desean actualizar
        for (Map.Entry<String, Object> field : fields.entrySet()) {
            String fieldName = field.getKey();
            Object fieldValue = field.getValue();
            
            // utiliza reflection para establecer el valor del campo en la entidad
            try {
                Field campoEntidad = TipoClienteDTO.class.getDeclaredField(fieldName);
                campoEntidad.setAccessible(true);
                campoEntidad.set(TipoClienteDTO, fieldValue);
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                // maneja la excepción si ocurre algún error al acceder al campo
            }
        }
        return client.update(id, TipoClienteDTO);
    }
}