package ru.advortsov.dsg.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.advortsov.dsg.resource.dto.IdResponseDto;

/**
 * DsgResource.
 *
 * @author Aleksandr_Dvortsov
 */
@RequestMapping("/dsg")
public interface DsgResource {

    @GetMapping("/next-id")
    IdResponseDto getNextId();

}
