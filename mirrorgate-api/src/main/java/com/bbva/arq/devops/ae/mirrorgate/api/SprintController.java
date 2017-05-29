/*
 * Copyright 2017 Banco Bilbao Vizcaya Argentaria, S.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bbva.arq.devops.ae.mirrorgate.api;

import com.bbva.arq.devops.ae.mirrorgate.core.dto.SprintDTO;
import com.bbva.arq.devops.ae.mirrorgate.core.utils.SprintStatus;
import com.bbva.arq.devops.ae.mirrorgate.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


/**
 * Defines feature rest methods
 */
@RestController
public class SprintController {

    private final SprintService sprintService;

    @Autowired
    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @RequestMapping(value = "/api/sprints/changing-sample", method = GET, produces = APPLICATION_JSON_VALUE)
    public List<SprintDTO> getChangingSprintsSample() {

         return sprintService.getSampleForStatus(new SprintStatus[]{SprintStatus.ACTIVE, SprintStatus.FUTURE});

    }

    @RequestMapping(value = "/api/sprints/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<SprintDTO> getChangingSprint(@PathVariable("id") Long id) {

        SprintDTO sprint = sprintService.getSprint(id);

        if(sprint == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(sprint);
        }

    }

}
