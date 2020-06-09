package service.event.providers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import service.event.models.EventInfo;

import java.time.Instant;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class EventStateProviderImplTest {

    @InjectMocks
    private EventStateProviderImpl eventStateProvider;

    @BeforeEach
    public void init() {
        this.eventStateProvider.getAll().clear();
        this.eventStateProvider.add(new EventInfo<>("Test", Instant.now()));
        this.eventStateProvider.add(new EventInfo<>("Test2", Instant.now()));
        this.eventStateProvider.add(new EventInfo<>("Test3", Instant.now()));
    }

    @Test
    void add() {
        final int beforeSize = this.eventStateProvider.getAll().size();
        this.eventStateProvider.add(new EventInfo<>("Test4", Instant.now()));
        assertThat(this.eventStateProvider.getAll().size()).isEqualTo(beforeSize + 1);
        assertThat(this.eventStateProvider.getAll().size()).isNotEqualTo(beforeSize);
    }

    @Test
    void getAll() {
        assertThat(this.eventStateProvider.getAll()).isNotNull();
    }

    @Test
    void deleteAll() {
        this.eventStateProvider.deleteAll();
        Collection<EventInfo<?>> all = this.eventStateProvider.getAll();
        assertThat(all).isNotNull();
        assertThat(all).isEmpty();
        assertThat(all.size()).isZero();
    }

    @Test
    void deleteIf() {
        Collection<EventInfo<?>> all = this.eventStateProvider.getAll();
        final int beforeSize = all.size();
        Predicate<EventInfo<?>> deletePredicate = eventInfo -> Objects.equals(eventInfo.getMessage(), "Test");
        this.eventStateProvider.deleteIf(deletePredicate);
        Collection<EventInfo<?>> afterDelete = this.eventStateProvider.getAll();
        assertThat(afterDelete.size()).isEqualTo(beforeSize - 1);
        assertThat(afterDelete).noneMatch(deletePredicate);
    }

}
