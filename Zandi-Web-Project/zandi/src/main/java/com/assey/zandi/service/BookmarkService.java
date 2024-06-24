package com.assey.zandi.service;

import com.assey.zandi.project.ProjectVO;

import java.util.List;

public interface BookmarkService {
    List<ProjectVO> getBookmarkedProjects(String loginID, int startRow, int pageSize);
    int getBookmarkedProjectCount(String loginID);
    void deleteBookmark(int prCode, String loginID);
}