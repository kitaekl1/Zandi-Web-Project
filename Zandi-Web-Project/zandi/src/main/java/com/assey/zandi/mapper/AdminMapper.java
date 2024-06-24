package com.assey.zandi.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.assey.zandi.project.CateVO;
import com.assey.zandi.project.ProjectVO;

@Mapper
public interface AdminMapper {
    void projRegi(ProjectVO proj);
    List<CateVO> cateList();
}