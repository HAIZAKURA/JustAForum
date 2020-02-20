package run.nya.justaforum.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import run.nya.justaforum.model.bean.User;

@Mapper
public interface UserDAO {

    public User userFind(@Param("uname")String uname, @Param("upass")String upass);

    public User adminFind(@Param("uname")String uname, @Param("upass")String upass);

}
