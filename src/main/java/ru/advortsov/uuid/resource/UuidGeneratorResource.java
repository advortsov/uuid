package ru.advortsov.uuid.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.advortsov.uuid.resource.dto.UuidResponseDto;

/**
 * UuidGeneratorResource.
 *
 * @author Aleksandr_Dvortsov
 */
@RequestMapping("/uuid")
public interface UuidGeneratorResource {

    @GetMapping
    UuidResponseDto getUuid();
}
