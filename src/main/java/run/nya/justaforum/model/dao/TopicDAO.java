package run.nya.justaforum.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import run.nya.justaforum.model.bean.Topic;

import java.util.List;

@Mapper
public interface TopicDAO {

    public List<Topic> getAllTopic();

    public Integer addTopic(@Param("tname") String tname, @Param("tcont") String tcont,
                            @Param("tdate") String tdate, @Param("uid") Integer uid,
                            @Param("nid") Integer nid, @Param("gid") Integer gid);

    public Integer delTopic(@Param("tid") Integer tid);

}
