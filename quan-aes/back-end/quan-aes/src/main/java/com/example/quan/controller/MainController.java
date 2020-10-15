package com.example.quan.controller;

import com.example.quan.MaHoaService;
import com.example.quan.models.GiaiMa;
import com.example.quan.models.MaHoa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MainController {



    @Autowired
    private MaHoaService maHoaService;

    @GetMapping("/")
    public ResponseEntity<?> getting() {

        return new ResponseEntity<>("CaoKD", HttpStatus.OK);
    }

    @PostMapping("/ma-hoa")
    public ResponseEntity<?> maHoa(@RequestBody MaHoa maHoa) {
        try {
            String ketQuaMaHoa = maHoaService.encrypt(maHoa.getChuoiMaHoa(), maHoa.getMatKhauMaHoa());

            maHoa.setKetQuaMaHoa(ketQuaMaHoa);

            return new ResponseEntity<>(maHoa, HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/giai-ma")
    public ResponseEntity<?> giaiMa(@RequestBody GiaiMa giaiMa) {
        try {
            String ketQuaGiaiMa = maHoaService.decrypt(giaiMa.getChuoiCanGiaiMa(), giaiMa.getMatKhauGiaiMa());

            giaiMa.setKetQuaGiaiMa(ketQuaGiaiMa);

            return new ResponseEntity<>(giaiMa, HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
