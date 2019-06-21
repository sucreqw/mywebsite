package com.sucre.cool.mywebsite.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sucre.cool.mywebsite.dto.GenerationsDTO;
import com.sucre.cool.mywebsite.entity.GenerationsDO;
import com.sucre.cool.mywebsite.info.GenerationsInfo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-06-14
 */
public interface IGenerationsService extends IService<GenerationsDO> {
    Integer createGenerations(GenerationsDTO generationsDTO);

    void deleteGenerations(Integer id);

    void updateGenerations(Integer id, GenerationsDTO generationsDTO);

    Page<GenerationsInfo> listPage(Integer page, Integer pageSize, String query,String father);

    GenerationsInfo getGenerations(Integer id);

    List<String> allFather(Integer id);
}
