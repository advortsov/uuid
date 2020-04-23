package ru.advortsov.dsg.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.advortsov.dsg.resource.dto.IdResponseDto;
import ru.advortsov.dsg.service.SequenceGeneratorService;

/**
 * DsgResourceImpl.
 *
 * @author Aleksandr_Dvortsov
 */
@RestController
@RequiredArgsConstructor
public class DsgResourceImpl implements DsgResource {

    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public IdResponseDto getNextId() {
        return new IdResponseDto(sequenceGeneratorService.nextId());
    }
}
