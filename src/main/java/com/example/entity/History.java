package com.example.entity;

import com.example.dto.HistoryId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "history")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class History {
    @EmbeddedId
    private HistoryId historyId;
}
