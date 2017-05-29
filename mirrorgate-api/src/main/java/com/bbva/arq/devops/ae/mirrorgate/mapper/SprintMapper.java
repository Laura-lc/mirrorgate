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

package com.bbva.arq.devops.ae.mirrorgate.mapper;

import com.bbva.arq.devops.ae.mirrorgate.core.utils.SprintStatus;
import com.bbva.arq.devops.ae.mirrorgate.core.dto.SprintDTO;

import java.util.stream.Collectors;

/**
 * Created by alfonso on 28/05/17.
 */
public class SprintMapper {

    private SprintMapper(){}

    public static SprintDTO map(com.bbva.arq.devops.ae.mirrorgate.model.Sprint source) {
        return source == null ? null : map(source, new SprintDTO());
    }

    public static SprintDTO map(com.bbva.arq.devops.ae.mirrorgate.model.Sprint source, SprintDTO target) {
        return target
                .setId(source.getId())
                .setName(source.getName())
                .setStartDate(source.getStartDate())
                .setEndDate(source.getEndDate())
                .setStatus(source.getStatus() == null ? null : SprintStatus.valueOf(source.getStatus()))
                .setIssues(source.getFeatures() == null ? null : source.getFeatures().stream()
                        .map(IssueMapper::map)
                        .collect(Collectors.toList())
                );
    }


}
