package com.sucre.cool.mywebsite.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class MybatisObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime date = LocalDateTime.now();
        /*//新增时填充的字段
        metaObject.setValue("createUser",  UserUtil.getUserId());
        metaObject.setValue("createDate",  date);

        metaObject.setValue("updateUser",  UserUtil.getUserId());
        metaObject.setValue("updateDate",  date);*/
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新时 需要填充字段
      /*  metaObject.setValue("updateUser",  UserUtil.getUserId());
        metaObject.setValue("updateDate",  LocalDateTime.now());*/
    }
}
