package com.stackroute.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse
{
    @Id
    private String uuid;
    private String name;
    private String  image_url;
    private String owner_mailid;
    private String time_stamp;
    private List<Partition> partitions;
    private String location;
    private String address;
    private String occupied_partitions;
    private String total_sqft;
}
