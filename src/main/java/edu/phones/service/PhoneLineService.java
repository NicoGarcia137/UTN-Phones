package edu.phones.service;

import edu.phones.dao.PhoneLineDao;
import edu.phones.domain.PhoneLine;
import edu.phones.domain.User;
import edu.phones.dto.LineRequestDto;
import edu.phones.exceptions.alreadyExist.PhoneLineAlreadyExistsException;
import edu.phones.exceptions.notExist.PhoneLineNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneLineService {

    PhoneLineDao lineDao;

    @Autowired
    public PhoneLineService(@Qualifier("phoneLineMySQLDao") PhoneLineDao lineDao) {
        this.lineDao = lineDao;
    }

    /** CRUD **/
    public PhoneLine createLine(PhoneLine line) throws PhoneLineAlreadyExistsException {
        return lineDao.add(line);
    }

    public void removeLine(PhoneLine line) throws PhoneLineNotExistException {
        if(lineDao.remove(line) == 0){
            throw new PhoneLineNotExistException();
        }
    }

    public PhoneLine updateLine(PhoneLine line) throws PhoneLineNotExistException {
        if(lineDao.update(line) > 0){
            return line;
        }else {
            throw new PhoneLineNotExistException();
        }
    }

    public PhoneLine getLine(Integer id) {
        return lineDao.getById(id);
    }

    public List<PhoneLine> getAll() {
        return lineDao.getAll();
    }

    public List<LineRequestDto> getTopTen(User currentUser) {
        return lineDao.getTopTen(currentUser);
    }
}
