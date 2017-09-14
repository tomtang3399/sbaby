package com.sbaby.common.service;

import java.util.List;
import com.sbaby.common.entity.db.relationalDB.User;

/**
 * 用户模块，同步方法接口
 * @author Administrator
 *
 */
public interface UserSyncService {

	List<User> findAllUsers();
}
