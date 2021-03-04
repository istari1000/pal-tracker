package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TimeEntryController
{
    TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository)
    {
        this.timeEntryRepository = timeEntryRepository;
    }
    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry)
    {
        TimeEntry returnTimeEntry = timeEntryRepository.create(timeEntry);
        return ResponseEntity.created(null).body(returnTimeEntry);
    }
    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id)
    {
        return Optional.ofNullable(timeEntryRepository.find(id))
                .map(timeEntry -> ResponseEntity.ok().body(timeEntry))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }
    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list()
    {
        List<TimeEntry> list = timeEntryRepository.list();
        return ResponseEntity.ok(list);
    }
    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeEntry)
    {
        return Optional.ofNullable(timeEntryRepository.update(id, timeEntry))
                .map((returnTimeEntry -> ResponseEntity.ok().body(returnTimeEntry)))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id)
    {
        timeEntryRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
