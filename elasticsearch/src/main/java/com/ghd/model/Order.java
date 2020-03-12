package com.ghd.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Document(indexName = "order",type = "order")
public class Order {

    @Id
    private String id;
    @Field(type = FieldType.Keyword)
    private String username;
    @Field(type = FieldType.Double)
    private BigDecimal price;
    @Field(type = FieldType.Keyword)
    private String orderNum;
    @Field(type = FieldType.Long)
    private Long payTime;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String productName;

}
