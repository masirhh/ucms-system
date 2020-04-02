package com.masirhh.ucmssystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.masirhh.ucmssystem.domain.Ufile;
import com.masirhh.ucmssystem.mapper.UfileMapper;
import com.masirhh.ucmssystem.service.UfileService;
import org.springframework.stereotype.Service;

@Service
public class UfileServiceImpl extends ServiceImpl<UfileMapper, Ufile> implements UfileService {
}
