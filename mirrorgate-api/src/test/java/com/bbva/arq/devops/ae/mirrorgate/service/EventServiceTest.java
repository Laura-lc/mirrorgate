package com.bbva.arq.devops.ae.mirrorgate.service;

import com.bbva.arq.devops.ae.mirrorgate.model.Build;
import com.bbva.arq.devops.ae.mirrorgate.model.Event;
import com.bbva.arq.devops.ae.mirrorgate.model.EventType;
import com.bbva.arq.devops.ae.mirrorgate.repository.EventRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    private EventServiceImpl eventService;

    @Before
    public void init(){

        eventService = new EventServiceImpl(eventRepository);
    }


    @Captor ArgumentCaptor<Event> captor;
    @Test
    public void test() {

        Build build = createBuild();

        eventService.saveEvent(build, EventType.BUILD);

        verify(eventRepository).save(captor.capture());

        assertEquals(EventType.BUILD, captor.getValue().getEventType());
        assertEquals(build.getId(), captor.getValue().getCollectionId());
        assertNotNull(captor.getValue().getTimestamp());
    }

    private Build createBuild(){
        return new Build();
    }

}
