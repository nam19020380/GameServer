package org.example.controller;

import org.example.entity.TableState;
import org.example.entity.UserInfo;
import org.example.service.TableStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/tableState")
public class TableStateController {
    @Autowired
    TableStateService tableStateService;
    @GetMapping
    public ResponseEntity<?> getTableState(@RequestParam Integer id){
        return tableStateService.getTableState(id);
    }
    @GetMapping(value = "/type")
    public ResponseEntity<?> getTableStateByType(@RequestParam String type){
        return tableStateService.getTableStateByType(type);
    }
    @GetMapping(value = "/userId")
    public ResponseEntity<?> getTableStateByUserId(@RequestParam Integer id){
        return tableStateService.getTableStateByUserId(id);
    }
    @PostMapping
    public ResponseEntity<?> createTableState(){
        return tableStateService.createTable();
    }

    @PutMapping
    public ResponseEntity<?> updateTableState(@RequestBody TableState tableState){
        return tableStateService.updateTable(tableState);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTableState(@RequestParam Integer id){
        return tableStateService.deleteTable(id);
    }
}
