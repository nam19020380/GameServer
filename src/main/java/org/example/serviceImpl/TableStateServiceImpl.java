package org.example.serviceImpl;

import org.example.entity.TableState;
import org.example.repository.TableStateRepository;
import org.example.service.TableStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TableStateServiceImpl implements TableStateService {
    @Autowired
    TableStateRepository tableStateRepository;
    @Override
    public ResponseEntity<?> createTable() {
        try{
            TableState tableState = new TableState();
            TableState tableState1 = tableStateRepository.save(tableState);
            return new ResponseEntity<>(tableState1, HttpStatus.CREATED);
        } catch(Exception e){
            return ResponseEntity.internalServerError().body("Sever Error");
        }
    }

    @Override
    public ResponseEntity<?> getTableState(Integer id) {
        try{
            TableState tableState = tableStateRepository.findById(String.valueOf(id)).get();
            return new ResponseEntity<>(tableState, HttpStatus.OK);
        } catch(Exception e){
            return ResponseEntity.internalServerError().body("Sever Error");
        }
    }

    @Override
    public ResponseEntity<?> getTableStateByType(String type) {
        try{
            List<TableState> tableStateList = tableStateRepository.findByType(type);
            return new ResponseEntity<>(tableStateList, HttpStatus.OK);
        } catch(Exception e){
            return ResponseEntity.internalServerError().body("Sever Error");
        }
    }

    @Override
    public ResponseEntity<?> getTableStateByUserId(Integer id) {
        try{
            TableState tableStateList = tableStateRepository.findByPlayer1IdOrPlayer2Id(id, id);
            return new ResponseEntity<>(tableStateList, HttpStatus.OK);
        } catch(Exception e){
            return ResponseEntity.internalServerError().body("Sever Error");
        }
    }

    @Override
    public ResponseEntity<?> updateTable(TableState tableState) {
        try{
            TableState tableState1 = tableStateRepository.save(tableState);
            return new ResponseEntity<>(tableState1, HttpStatus.OK);
        } catch(Exception e){
            return ResponseEntity.internalServerError().body("Sever Error");
        }
    }

    @Override
    public ResponseEntity<?> deleteTable(Integer id) {
        try{
            tableStateRepository.deleteById(String.valueOf(id));
            return new ResponseEntity<>("Xoa thanh cong", HttpStatus.OK);
        } catch(Exception e){
            return ResponseEntity.internalServerError().body("Sever Error");
        }
    }
}
