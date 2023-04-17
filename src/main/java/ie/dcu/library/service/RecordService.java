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

    public Boolean getBookAvailability(int bookid) {
    	Iterable<LibraryRecord> records = recordsRepository.findByBookid(bookid);
    	
    	for(LibraryRecord rec : records) {
    	 if(rec.getIsReturned()==false) {
    		return false;
    	 }
    	}    		    
    	return true;
      }

    public Iterable<LibraryRecord> getRecordsbyUser(int memberid){
        return recordsRepository.findByMemberid(memberid);
    }

    //delete record using id
    public void deleterecord(int id)
    {
    	var record = recordsRepository.findById(id);
    	recordsRepository.deleteById(record.getId());
    }
	
}
