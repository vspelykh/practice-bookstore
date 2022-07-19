package ua.hillel.bookstore.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ua.hillel.bookstore.persistence.dto.CoverDTO;
import ua.hillel.bookstore.persistence.dto.LanguageDTO;
import ua.hillel.bookstore.service.CharacteristicService;

import java.util.List;

@RestController("/characteristic")
@RequiredArgsConstructor
public class CharacteristicController {

    private final CharacteristicService service;

    @GetMapping("/languages")
    public ResponseEntity<List<LanguageDTO>> getAllLanguages(){
        return new ResponseEntity<>(service.getAllLanguages(), HttpStatus.OK);
    }

    @GetMapping("/covers")
    public ResponseEntity<List<CoverDTO>> getAllCovers(){
        return new ResponseEntity<>(service.getAllCovers(), HttpStatus.OK);
    }

    @GetMapping("/cover/{id}")
    public CoverDTO getCoverById(@PathVariable Integer id) {
        return service.getCoverById(id);
    }

    @GetMapping("/language/{id}")
    public LanguageDTO getLanguageById(@PathVariable Integer id){
        return service.getLanguageById(id);
    }
}
