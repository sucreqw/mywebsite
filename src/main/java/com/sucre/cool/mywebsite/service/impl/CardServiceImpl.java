package com.sucre.cool.mywebsite.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sucre.cool.mywebsite.common.PageUtil;
import com.sucre.cool.mywebsite.dao.CardMapper;
import com.sucre.cool.mywebsite.dto.CardDTO;
import com.sucre.cool.mywebsite.entity.CardDO;
import com.sucre.cool.mywebsite.info.CardInfo;
import com.sucre.cool.mywebsite.service.ICardService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, CardDO> implements ICardService {
    @Override
    public Integer createCard(CardDTO cardDTO) {
        CardDO cardDO = new CardDO();
        BeanUtils.copyProperties(cardDTO, cardDO);
        baseMapper.insert(cardDO);
        return cardDO.getId();
    }

    @Override
    public void deleteCard(Integer id) {
        baseMapper.deleteById(id);
    }

    @Override
    public void updateCard(Integer id, CardDTO cardDTO) {
        CardDO cardDO = baseMapper.selectById(id);
        if (cardDO == null) {
            return;
        }
        BeanUtils.copyProperties(cardDTO, cardDO);
        baseMapper.updateById(cardDO);
    }

    @Override
    public Page<CardInfo> listPage(Integer page, Integer pageSize, String query) {
        QueryWrapper<CardDO> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(query)) {
            wrapper.like("card_name", query);
        }
        wrapper.orderByDesc("id");
        Page<CardDO> commentsDOPage = new Page<>(page, pageSize);
        return PageUtil.buildPage(baseMapper.selectPage(commentsDOPage, wrapper), CardInfo.class);
    }

    @Override
    public CardInfo getCard(Integer id) {
        CardDO cardDO = baseMapper.selectById(id);
        if (cardDO == null) {
            return null;
        }
        CardInfo cardInfo = new CardInfo();
        BeanUtils.copyProperties(cardDO, cardInfo);
        return cardInfo;
    }

    @Override
    public List<CardDO> allCard() {
        QueryWrapper<CardDO> wrapper = new QueryWrapper<>();
        List<CardDO> list=baseMapper.selectList(wrapper);
        return list;
    }
}
