package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialsMapper) {
        this.credentialMapper = credentialsMapper;
    }

    public List<Credential> getCredentials(Integer userId) {
        return credentialMapper.getCredentials(userId);
    }

    public Integer saveCredential(Credential credential) {
        Integer credentialId = credential.getCredentialid();
        if(credentialId == null){
            credentialId = credentialMapper.insert(credential);
        }else{
            credentialMapper.update(credential);
        }
        return credentialId;
    }

    public void deleteCredential(Integer credentialId) {
        credentialMapper.delete(credentialId);
    }
}
