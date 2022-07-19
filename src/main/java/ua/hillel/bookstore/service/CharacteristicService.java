package ua.hillel.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hillel.bookstore.persistence.dto.CoverDTO;
import ua.hillel.bookstore.persistence.dto.LanguageDTO;
import ua.hillel.bookstore.persistence.mapper.CoverMapper;
import ua.hillel.bookstore.persistence.mapper.LanguageMapper;
import ua.hillel.bookstore.persistence.repository.CoverRepository;
import ua.hillel.bookstore.persistence.repository.LanguageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacteristicService {

    private final CoverMapper coverMapper;
    private final LanguageMapper languageMapper;
    private final CoverRepository coverRepository;
    private final LanguageRepository languageRepository;

    public List<LanguageDTO> getAllLanguages(){
        return languageRepository.findAll().stream().map(languageMapper::toDTO).collect(Collectors.toList());
    }

    public List<CoverDTO> getAllCovers(){
        return coverRepository.findAll().stream().map(coverMapper::toDTO).collect(Collectors.toList());
    }

    public CoverDTO getCoverById(Integer id) {
        return coverMapper.toDTO(coverRepository.getReferenceById(id));
    }

    public LanguageDTO getLanguageById(Integer id) {
        return languageMapper.toDTO(languageRepository.getReferenceById(id));
    }
}
