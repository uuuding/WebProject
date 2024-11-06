package csu.web.mypetstore.persistence;

import csu.web.mypetstore.domain.Log;

import java.util.List;

public interface LogDao {
    List<Log> getLogListByUserName(String userName);
}
