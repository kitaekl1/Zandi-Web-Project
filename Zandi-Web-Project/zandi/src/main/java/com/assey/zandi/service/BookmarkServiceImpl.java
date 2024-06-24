package com.assey.zandi.service;

import com.assey.zandi.project.ProjectVO;
import com.assey.zandi.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Override
    public List<ProjectVO> getBookmarkedProjects(String loginID, int startRow, int pageSize) {
        Pageable pageable = PageRequest.of(startRow / pageSize, pageSize);
        return bookmarkRepository.findBookmarkedProjectsByUserId(loginID, pageable);
    }

    @Override
    public int getBookmarkedProjectCount(String loginID) {
        return bookmarkRepository.countBookmarkedProjectsByUserId(loginID);
    }

    @Override
    @Transactional
    public void deleteBookmark(int prCode, String loginID) {
        bookmarkRepository.deleteByPrCodeAndMId(prCode, loginID);


        ProjectVO project = bookmarkRepository.findProjectByPrCode(prCode);
        if (project != null && project.getPrLikecount() > 0) {
            project.decrementLikecount();
           
        }
    }
}