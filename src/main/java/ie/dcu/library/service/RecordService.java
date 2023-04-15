package ie.dcu.library.service;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.dcu.library.model.LibraryRecord;
import ie.dcu.library.repository.RecordsRepository;

@Service
public class RecordService {
	private final RecordsRepository recordsRepository;
	
	@Autowired
	public RecordService(RecordsRepository recordsRepository) {
		this.recordsRepository = recordsRepository;
	}
	
    public void add(LibraryRecord record) {
    	recordsRepository.save(record);
    }
 
    public LibraryRecord getRecordById(int id)
    {
      return recordsRepository.findById(id);
    }

    public Iterable<LibraryRecord> getAllRecords() {
      return recordsRepository.findAll();
    }
 
    //delete record using isbn
    public void deleterecord(int isbn)
    {
    	var record = recordsRepository.findByIsbn(isbn);
    	recordsRepository.deleteById(record.getRecordid());
    }
	
}
