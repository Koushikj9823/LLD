package com.koushik.codeathon.service;

import com.koushik.codeathon.constants.ResponseMessage;
import com.koushik.codeathon.entity.Coder;
import com.koushik.codeathon.repository.CoderRepository;
import com.koushik.codeathon.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoderService {

    @Autowired
    CoderRepository coderRepository;

    @Autowired
    ContestRepository contestRepository;

    public ResponseMessage createCoder(Coder coder){
        if(coderRepository.findByUsername(coder.getUsername()).isPresent())
            return ResponseMessage.ALREADY_EXISTS;

        coderRepository.save(coder);
        return ResponseMessage.SUCCESS;
    }

    public ResponseMessage joinContest(){
        return ResponseMessage.SUCCESS;
    }
    public ResponseMessage withdrawContest(){
        return ResponseMessage.SUCCESS;
    }
}
