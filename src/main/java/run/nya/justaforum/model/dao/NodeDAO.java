package run.nya.justaforum.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import run.nya.justaforum.model.bean.Node;

import java.util.List;

@Mapper
public interface NodeDAO {

    public List<Node> getAllNode();

    public Integer addNode(@Param("nname") String name, @Param("ncont") String ncont);

    public Integer delNode(@Param("nid") Integer nid);

    public Integer modNode(@Param("nid") Integer nid, @Param("nname") String nname,
                           @Param("ncont") String ncont);

}
