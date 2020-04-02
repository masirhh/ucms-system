package com.masirhh.ucmssystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("us_file")
public class Ufile {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String name;
    private String path;
    private String fileOwner;
    private String createTime;
}
