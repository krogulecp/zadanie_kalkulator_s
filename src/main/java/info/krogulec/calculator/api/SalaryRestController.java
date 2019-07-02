package info.krogulec.calculator.api;

import info.krogulec.calculator.model.SalaryPln;
import info.krogulec.calculator.service.SalaryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author krogulecp
 */
@RestController
public class SalaryRestController {

    private final SalaryService salaryService;

    public SalaryRestController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping(path = "/api/salary", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SalaryPln> calculateSalary(SalaryRequest salaryRequest){
        return ResponseEntity
                .ok(salaryService.calculateSalary(salaryRequest.getDailyRate(), salaryRequest.getCountry()));
    }

}
