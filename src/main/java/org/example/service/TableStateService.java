package org.example.service;

import org.example.entity.TableState;
import org.springframework.http.ResponseEntity;

public interface TableStateService {
    public ResponseEntity<?> createTable();
    public ResponseEntity<?> getTableState(Integer id);
    public ResponseEntity<?> getTableStateByType(String type);
    public ResponseEntity<?> getTableStateByUserId(Integer id);
    public TableState getTableStateById(Integer id);
    public ResponseEntity<?> updateTable(TableState tableState);
    public ResponseEntity<?> deleteTable(Integer id);
}
