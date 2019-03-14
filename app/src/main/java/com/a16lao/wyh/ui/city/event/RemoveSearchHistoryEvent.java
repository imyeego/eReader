package com.a16lao.wyh.ui.city.event;

public class RemoveSearchHistoryEvent {
    private String historyItem;

    public RemoveSearchHistoryEvent(String historyItem) {
        this.historyItem = historyItem;
    }

    public String getHistoryItem() {
        return historyItem;
    }
}
