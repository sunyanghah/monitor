package com.ido85.monitor.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ido85.monitor.auth.entity.SysOauthClientDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by dell on 2018/11/12.
 * @author dell
 */
@Mapper
public interface ClientMapper extends BaseMapper<SysOauthClientDetails> {
}
