package org.ds.model.stat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс, представляющий статистику. Это то, что будет получать клиент. Содержит список {@link Stat} определённого
 * периода.
 */
public class Statistics {
    private final LocalDateTime fromDateTime;
    private final LocalDateTime toDateTime;
    private final List<? extends Stat> stats;

    public Statistics(LocalDateTime fromDateTime, LocalDateTime toDateTime, List<? extends Stat> stats) {
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
        this.stats = stats;
    }

    public LocalDateTime getFromDateTime() {
        return fromDateTime;
    }

    public LocalDateTime getToDateTime() {
        return toDateTime;
    }

    public List<? extends Stat> getStats() {
        return stats;
    }
}
