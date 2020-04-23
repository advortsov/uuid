package ru.advortsov.uuid.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.advortsov.uuid.resource.dto.UuidResponseDto;
import ru.advortsov.uuid.service.SequenceGeneratorService;

/**
 * UuidGeneratorResourceImpl.
 *
 * @author Aleksandr_Dvortsov
 */
@RestController
@RequiredArgsConstructor
public class UuidGeneratorResourceImpl implements UuidGeneratorResource {

    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public UuidResponseDto getUuid() {
        return new UuidResponseDto(sequenceGeneratorService.nextId());
    }
}
