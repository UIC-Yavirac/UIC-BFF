package uic.bffweb.event;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import uic.bffweb.event.EventDTO;

@FeignClient(name = "bff.event", url = "http://localhost:8000/api/event")
public interface Event {

    @GetMapping("/{id}/")
    EventDTO findById(@PathVariable("id") Long id);

    @GetMapping("/")
    List<EventDTO> findAll();

    @PostMapping("/") 
    EventDTO save(EventDTO entity);

    @DeleteMapping("/{id}/")
    void deleteById(@PathVariable("id")  Long id);

    @PutMapping("/{id}/")
    EventDTO update(@PathVariable("id")  Long id, EventDTO entity);

    @PutMapping("/{id}/completardatos/")
    EventDTO completardatos(@PathVariable("id")  Long id);

}
