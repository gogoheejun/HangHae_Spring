package com.sparta.blogwithcomments.service;

import com.sparta.blogwithcomments.model.Content;
import com.sparta.blogwithcomments.dto.ContentRequestDto;
import com.sparta.blogwithcomments.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ContentService {

    private final ContentRepository contentRepository;

    @Transactional
    public Long update(Long id, ContentRequestDto requestDto){
        Content content = contentRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
        content.update(requestDto);
        return content.getId();
    }
}
