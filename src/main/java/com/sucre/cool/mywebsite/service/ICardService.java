package com.sucre.cool.mywebsite.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sucre.cool.mywebsite.dto.CardDTO;
import com.sucre.cool.mywebsite.entity.CardDO;
import com.sucre.cool.mywebsite.info.CardGatherInfo;
import com.sucre.cool.mywebsite.info.CardInfo;

import java.util.List;

public interface ICardService extends IService<CardDO> {
    Integer createCard(CardDTO cardDTO);

    void deleteCard(Integer id);

    void updateCard(Integer id, CardDTO cardDTO);

    Page<CardInfo> listPage(Integer page, Integer pageSize, String query);

    CardInfo getCard(Integer id);

    List<CardDO> allCard();
}
