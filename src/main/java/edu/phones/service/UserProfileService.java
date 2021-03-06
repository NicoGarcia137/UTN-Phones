package edu.phones.service;

import edu.phones.dao.UserProfileDao;
import edu.phones.domain.UserProfile;
import edu.phones.exceptions.alreadyExist.ProfileAlreadyExistException;
import edu.phones.exceptions.notExist.ProfileNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    UserProfileDao profileDao;

    @Autowired
    public UserProfileService(@Qualifier("profileMySQLDao") UserProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public UserProfile createProfile(UserProfile newProfile) throws ProfileAlreadyExistException {
        return profileDao.add(newProfile);
    }

    public UserProfile updateProfile(UserProfile toModify) throws ProfileNotExistException {
        if(profileDao.update(toModify) > 0){
            return toModify;
        }else {
            throw new ProfileNotExistException();
        }
    }

    public UserProfile getProfile(Integer id) {
        return profileDao.getById(id);
    }
}
