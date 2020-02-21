package run.nya.justaforum.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import run.nya.justaforum.model.bean.User;

import java.util.List;

@Mapper
public interface UserDAO {

    public User userFind(@Param("uname") String uname, @Param("upass") String upass);

    public User adminFind(@Param("uname") String uname, @Param("upass") String upass);

    public User getUser(@Param("uname") String uname);

    public User getUserById(@Param("uid") Integer uid);

    public List<User> getAllUser();

    public Integer delUser(@Param("uname") String uname);

    public Integer addUser(@Param("uname") String uname, @Param("upass") String upass,
                           @Param("umail") String umail);

    public Integer modUserById(@Param("uid") Integer uid, @Param("uname") String uname,
                               @Param("umail") String umail, @Param("uacce") Integer uacce,
                               @Param("ustat") Integer ustat);

    public Integer modUserPass(@Param("uname") String uname, @Param("oldpass") String oldpass,
                               @Param("newpass") String newpass);

}
