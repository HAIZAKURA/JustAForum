package run.nya.justaforum.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import run.nya.justaforum.model.bean.Tag;

import java.util.List;

@Mapper
public interface TagDAO {

    public List<Tag> getAllTag();

    public Tag getTag(@Param("gid") Integer gid);

    public Integer addTag(@Param("gname") String gname, @Param("nid") Integer nid);

    public Integer delTag(@Param("gid") Integer gid);

    public Integer modTag(@Param("gid") Integer gid, @Param("gname") String gname,
                          @Param("nid") Integer nid);

}
