package ru.advortsov.dsg.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.advortsov.dsg.resource.dto.IdResponseDto;

/**
 * DsgResource.
 *
 * @author Aleksandr_Dvortsov
 */
@RequestMapping("/next-id")
public interface DsgResource {

    @GetMapping
    IdResponseDto getNextId();
}
