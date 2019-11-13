package com.bbva.arq.devops.ae.mirrorgate.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.bbva.arq.devops.ae.mirrorgate.dto.ProgramIncrementDTO;
import com.bbva.arq.devops.ae.mirrorgate.service.ProgramIncrementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProgramIncrementController {

    private final ProgramIncrementService programIncrementService;

    @Autowired
    public ProgramIncrementController(final ProgramIncrementService programIncrementService){
        this.programIncrementService = programIncrementService;
    }

    @RequestMapping(value = "/dashboards/{name}/program-increment", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProgramIncrementDTO getProgramIncrement(final @PathVariable("name") String name) {
        return programIncrementService.getProgramIncrement(name);
    }

}
