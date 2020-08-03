package service.event.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.event.models.EventInfo;
import service.event.providers.EventStateProvider;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private EventStateProvider eventStateProvider;

    @Test
    void addTest() {
        this.eventService.add(mock(EventInfo.class));
        verify(eventStateProvider, times(1)).add(any(EventInfo.class));
    }

    @Test
    void getAllTest() {
        Collection<EventInfo<?>> events = Arrays.asList(mock(EventInfo.class), mock(EventInfo.class));
        when(this.eventStateProvider.getAll()).thenReturn(events);
        final int eventSize = events.size();

        Collection<EventInfo<?>> all = this.eventService.getAll();

        verify(this.eventStateProvider, times(1)).getAll();
        assertThat(all).isNotNull();
        assertThat(all.size()).isEqualTo(eventSize);
    }

    @Test
    void deleteAllTest() {
        this.eventService.deleteAll();
        verify(eventStateProvider, times(1)).deleteAll();
    }

}
