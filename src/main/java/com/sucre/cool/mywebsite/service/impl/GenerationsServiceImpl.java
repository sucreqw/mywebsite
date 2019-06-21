package com.sucre.cool.mywebsite.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sucre.cool.mywebsite.common.PageUtil;
import com.sucre.cool.mywebsite.dao.GenerationsMapper;
import com.sucre.cool.mywebsite.dto.GenerationsDTO;
import com.sucre.cool.mywebsite.entity.GenerationsDO;
import com.sucre.cool.mywebsite.enums.ResultCodeEnum;
import com.sucre.cool.mywebsite.exception.BizException;
import com.sucre.cool.mywebsite.info.GenerationsInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sucre.cool.mywebsite.service.IGenerationsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-06-14
 */
@Service
public class GenerationsServiceImpl extends ServiceImpl<GenerationsMapper, GenerationsDO> implements IGenerationsService {


    @Override
    public Integer createGenerations(GenerationsDTO generationsDTO) {
        GenerationsDO generationsDO = new GenerationsDO();
        BeanUtils.copyProperties(generationsDTO, generationsDO);
        baseMapper.insert(generationsDO);
        return generationsDO.getId();

    }

    @Override
    public void deleteGenerations(Integer id) {
        baseMapper.deleteById(id);

    }

    @Override
    public void updateGenerations(Integer id, GenerationsDTO generationsDTO) {
        GenerationsDO generationsDO = baseMapper.selectById(id);
        if (generationsDO == null) {
            return;
        }
        BeanUtils.copyProperties(generationsDTO, generationsDO);
        baseMapper.updateById(generationsDO);

    }

    @Override
    public Page<GenerationsInfo> listPage(Integer page, Integer pageSize, String query, String father) {
        QueryWrapper<GenerationsDO> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(query)) {
            wrapper.like("nickname", query);
        }
        if (StringUtils.isNotBlank(father)) {
            wrapper.eq("father", father);
        }
        Page<GenerationsDO> generationsDOPage = new Page<>(page, pageSize);
        return PageUtil.buildPage(baseMapper.selectPage(generationsDOPage, wrapper), GenerationsInfo.class);

    }

    @Override
    public GenerationsInfo getGenerations(Integer id) {
        GenerationsDO generationsDO = baseMapper.selectById(id);
        if (generationsDO == null) {
            return null;
        }
        GenerationsInfo generationsInfo = new GenerationsInfo();
        BeanUtils.copyProperties(generationsDO, generationsInfo);
        return generationsInfo;

    }

    @Override
    public List<String> allFather(Integer id) {
        List<String> result = new ArrayList<>();
        String fatherId = null;
        while (true) {
            GenerationsDO generationsDO = new GenerationsDO();
            generationsDO = baseMapper.selectById(id);
            if (generationsDO != null) {
                fatherId = generationsDO.getFather();
                result.add(fatherId);
                id = Integer.parseInt(fatherId);
            } else {
                break;
            }

        }
        return result;
    }
}
